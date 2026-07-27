package io.github.brett9897.fantasyai
package frontend.features.myteam

import cats.effect.IO
import org.http4s.*
import org.http4s.dsl.io.*
import org.http4s.scalatags.*
import viewmodels.*
import views.*

import java.util.UUID

object MyTeamHandlers {
    def handleMyTeamIndexGet(): IO[Response[IO]] = {
      val hardCodedRoster: List[MyTeamPlayerView] = List(
        MyTeamPlayerView(
          id = UUID.randomUUID(),
          name = "Bobby Witt Jr.",
          position = "SS",
          proTeam = "KCR",
          age = 26
        ),
        MyTeamPlayerView(
          id = UUID.randomUUID(),
          name = "Jackson Holliday",
          position = "2B/SS",
          proTeam = "BAL",
          age = 22
        ),
        MyTeamPlayerView(
          id = UUID.randomUUID(),
          name = "Paul Skenes",
          position = "SP",
          proTeam = "PIT",
          age = 24
        )
      )
      
      val htmlResponse = Index(hardCodedRoster)
      Ok(htmlResponse)
    }
}
