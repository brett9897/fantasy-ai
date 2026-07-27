package io.github.brett9897.fantasyai

import cats.effect.{IO, IOApp}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import com.comcast.ip4s.*
import infrastructure.{InMemoryPlayerRepository, EnvVarFeatureFlags}
import frontend.PlayerRoutes
import frontend.features.myteam.MyTeamRoutes
import config.AppConfig

object Main extends IOApp.Simple:

  def run: IO[Unit] =
    for
      config <- AppConfig.load

      flags = new EnvVarFeatureFlags(config.enableAdvancedStats)
      repo <- InMemoryPlayerRepository.make

      httpApp = Router(
        "" -> PlayerRoutes(repo, flags).routes,
        "" -> MyTeamRoutes().routes
      ).orNotFound

      _ <- IO.println(s"Starting Fantasy Sports AI on http://localhost:${config.port}")
      _ <- EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(config.port)
        .withHttpApp(httpApp)
        .build
        .useForever

    yield()
