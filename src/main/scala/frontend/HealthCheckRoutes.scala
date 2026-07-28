package io.github.brett9897.fantasyai
package frontend

import cats.effect.IO
import org.http4s.*
import org.http4s.dsl.io.*

class HealthCheckRoutes:
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "health" =>
      Ok("OK")
  }
