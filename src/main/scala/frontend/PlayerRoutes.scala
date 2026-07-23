package io.github.brett9897.fantasyai
package frontend

import cats.effect.IO
import org.http4s.*
import org.http4s.dsl.io.*
import domain.*

import _root_.scalatags.Text.all.*
import org.http4s.scalatags.*

class PlayerRoutes(repo: PlayerRepository):
  val routes: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "players" / UUIDVar(playerIdVal) =>
      val playerId = PlayerId(playerIdVal)
      repo.findById(playerId).flatMap {
        case Some(player) =>
          val htmlResponse = div(cls := "p-4 border rounded shadow-sm bg-white max-w-sm",
            _root_.scalatags.Text.all.h2(cls := "text-xl font-bold text-gray-900", player.fullName),
            p(cls := "text-gray-600", s"Sport: ${player.sport} | Team: ${player.proTeam}"),

            // 3. Custom HTMX attributes use the `attr()` function
            button(
              cls := "mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600",
              attr("hx-get") := s"/api/players/${player.id.value}/stats",
              attr("hx-target") := "#stats-container",
              "Load Advanced Metrics"
            ),
            div(id := "stats-container", cls := "mt-4 text-sm text-gray-500")
          )

          Ok(htmlResponse)
        case None =>
          NotFound(
            div(cls := "p-4 bg-green-100 text-green-800 rounded border border-green-300",
              "Player not found in catalog."
            )
          )
      }
    case GET -> Root / "players" / "seed" =>
      val newId = PlayerId.generate()
      val player = Player(newId, "tank_123", "Bobby Witt Jr.", SportCode.MLB, "KC")
      val htmlResponse = div(cls := "p-4 bg-green-100 text-green-800 rounded border border-green-300",
        p(cls := "font-bold", s"Seeded player! ID: ${newId.value}")
      )

      repo.save(player) *> Created(htmlResponse)
  }