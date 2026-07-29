package io.github.brett9897.fantasyai

import cats.effect.{IO, IOApp}
import cats.syntax.all.toSemigroupKOps
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import org.http4s.HttpRoutes
import org.http4s.server.staticcontent.resourceServiceBuilder
import com.comcast.ip4s.*
import infrastructure.{EnvVarFeatureFlags, InMemoryPlayerRepository}
import frontend.PlayerRoutes
import frontend.HealthCheckRoutes
import frontend.features.myteam.{InMemoryMyTeamQueries, MyTeamHandlers, MyTeamRoutes}
import config.AppConfig

object Main extends IOApp.Simple:

  def run: IO[Unit] =
    val assetRoutes: HttpRoutes[IO] =
      resourceServiceBuilder[IO]("/public").toRoutes

    for
      config <- AppConfig.load

      flags = new EnvVarFeatureFlags(config.enableAdvancedStats)
      repo <- InMemoryPlayerRepository.make
      myTeamQueries <- InMemoryMyTeamQueries.make
      myTeamHandlers = MyTeamHandlers(config.isDevelopment, config.assetsVersion, myTeamQueries)

      httpApp = (Router(
        "" -> PlayerRoutes(repo, flags).routes,
        "" -> MyTeamRoutes(myTeamHandlers).routes,
        "" -> HealthCheckRoutes().routes
      ) <+> assetRoutes).orNotFound

      _ <- IO.println(s"Starting Fantasy Sports AI on http://localhost:${config.port}")
      _ <- EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(config.port)
        .withHttpApp(httpApp)
        .build
        .useForever

    yield()
