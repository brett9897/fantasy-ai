package io.github.brett9897.fantasyai
package frontend.features.myteam

import munit.CatsEffectSuite
import frontend.features.myteam.{MyTeamHandlers, MyTeamQueries}
import frontend.features.myteam.viewmodels.MyTeamPlayerView
import cats.effect.IO
import java.util.UUID
import org.http4s.Status
import org.jsoup.Jsoup

object StubMyTeamQueries extends MyTeamQueries:
  def loadMyTeamPlayersView: IO[List[MyTeamPlayerView]] =
    IO.pure(List(
      MyTeamPlayerView(
        id = UUID.randomUUID(),
        name = "Test Player",
        position = "SS",
        proTeam = "TST",
        age = 25
      )
    ))

class MyTeamHandlersSpec extends CatsEffectSuite:
  private val myTeamHandlers = new MyTeamHandlers(false, StubMyTeamQueries)

  test("handleMyTeamIndexGet should return 200"):
    val request = myTeamHandlers.handleMyTeamIndexGet

    request.flatMap { response =>
      response.as[String].map { body =>
        val document = Jsoup.parse(body)
        assertEquals(response.status, Status.Ok)

        val player = document.select("[data-testid='player-row']").first()

        assertEquals(player.select("[data-testid='player-name']").text(), "Test Player")
        assertEquals(player.select("[data-testid='player-position']").text(), "SS")
        assertEquals(player.select("[data-testid='player-pro-team']").text(), "TST")
        assertEquals(player.select("[data-testid='player-age']").text(), "25")
      }
    }
