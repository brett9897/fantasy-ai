package io.github.brett9897.fantasyai
package frontend.features.myteam

import cats.effect.IO
import org.http4s.*
import org.http4s.dsl.io.*
import org.http4s.scalatags.*
import viewmodels.*
import views.*
import java.util.UUID

class MyTeamHandlers(isDev: Boolean, myTeamQueries: MyTeamQueries):
    def handleMyTeamIndexGet: IO[Response[IO]] =
      for
        roster <- myTeamQueries.loadMyTeamPlayersView
        htmlResponse = Index(isDev)(roster) 
        response <- Ok(htmlResponse)
      yield response
    
