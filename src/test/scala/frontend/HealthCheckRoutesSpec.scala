package io.github.brett9897.fantasyai
package frontend

import munit.CatsEffectSuite
import frontend.HealthCheckRoutes

import cats.effect.IO
import org.http4s.{Method, Request, Status}
import org.http4s.implicits.*

class HealthCheckRoutesSpec extends CatsEffectSuite:
  test("'/health' should return 200"):
    val routes = new HealthCheckRoutes().routes
    val request = Request[IO](Method.GET, uri"/health")

    routes.orNotFound.run(request).flatMap { response =>
      response.as[String].map { body =>
        assertEquals(response.status, Status.Ok)
        assertEquals(body, "OK")
      }
    }
