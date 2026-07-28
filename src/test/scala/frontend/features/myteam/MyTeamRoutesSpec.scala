package io.github.brett9897.fantasyai
package frontend.features.myteam

import munit.CatsEffectSuite
import frontend.features.myteam.{MyTeamRoutes, MyTeamHandlers, InMemoryMyTeamQueries}
import cats.effect.IO
import org.http4s.{Method, Request, Status}
import org.http4s.implicits.*

class MyTeamRoutesSpec extends CatsEffectSuite:
  private val inMemoryMyTeamQueries = InMemoryMyTeamQueries.make.unsafeRunSync()
  private val myTeamHandlers = new MyTeamHandlers(false, inMemoryMyTeamQueries)
  private val routes = new MyTeamRoutes(myTeamHandlers).routes

  test("GET '/my_team' should return 200"):
    val request = Request[IO](Method.GET, uri"/my_team")
    routes.orNotFound(request).flatMap { response =>
      response.as[Unit].map { _ =>
        assertEquals(response.status, Status.Ok)
      }
    }

  test("GET '/unknown' should return 404"):
    val request = Request[IO](Method.GET, uri"/unknown")
    routes.orNotFound(request).flatMap { response =>
      response.as[Unit].map { _ =>
        assertEquals(response.status, Status.NotFound)
      }
    }