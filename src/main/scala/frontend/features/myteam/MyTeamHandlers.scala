package io.github.brett9897.fantasyai
package frontend.features.myteam

import cats.effect.IO
import org.http4s.*
import org.http4s.dsl.io.*
import org.http4s.scalatags.*
import views.*

object MyTeamHandlers {
    def handleMyTeamIndexGet(): IO[Response[IO]] =
      Ok(Index())
}
