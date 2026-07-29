package io.github.brett9897.fantasyai
package frontend.features.myteam

import cats.effect.IO
import org.http4s.*
import org.http4s.dsl.io.*
import org.http4s.scalatags.*
import views.*

class MyTeamHandlers(isDev: Boolean, assetsVersion: String, myTeamQueries: MyTeamQueries):
    def handleMyTeamIndexGet: IO[Response[IO]] = {
      val htmlIndexView = Index(isDev, assetsVersion)
      for
        roster <- myTeamQueries.loadMyTeamPlayersView
        htmlResponse = htmlIndexView(roster) 
        response <- Ok(htmlResponse)
      yield response
    }
    
