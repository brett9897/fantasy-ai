package io.github.brett9897.fantasyai

import cats.effect.{IO, IOApp}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Router
import com.comcast.ip4s.*
import infrastructure.InMemoryPlayerRepository
import frontend.PlayerRoutes

object Main extends IOApp.Simple:

  def run: IO[Unit] =
    for
      repo <- InMemoryPlayerRepository.make

      httpApp = Router("" -> PlayerRoutes(repo).routes).orNotFound

      _ <- IO.println("Starting Fantasy Sports AI on http://localhost:8080")
      _ <- EmberServerBuilder
        .default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(httpApp)
        .build
        .useForever

    yield()
