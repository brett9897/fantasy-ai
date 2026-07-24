package io.github.brett9897.fantasyai

import cats.effect.{IO, IOApp}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import com.comcast.ip4s.*
import infrastructure.{InMemoryPlayerRepository, EnvVarFeatureFlags}
import frontend.PlayerRoutes
import config.AppConfig

object Main extends IOApp.Simple:

  def run: IO[Unit] =
    for
      config <- AppConfig.load
      
      flags = new EnvVarFeatureFlags(config.enableAdvancedStats)
      repo <- InMemoryPlayerRepository.make

      httpApp = Router("" -> PlayerRoutes(repo, flags).routes).orNotFound

      _ <- IO.println("Starting Fantasy Sports AI on http://localhost:8080")
      _ <- EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(httpApp)
        .build
        .useForever

    yield()
