package io.github.brett9897.fantasyai
package frontend.features.myteam

import cats.effect.IO
import org.http4s.HttpRoutes
import org.http4s.dsl.io.*

class MyTeamRoutes(isDev: Boolean):
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "my_team" =>
      MyTeamHandlers.handleMyTeamIndexGet(isDev)
  }
