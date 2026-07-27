package io.github.brett9897.fantasyai
package frontend.features.myteam.views

import scalatags.Text
import scalatags.Text.all.{tr, *}
import frontend.features.myteam.viewmodels.MyTeamPlayerView

def Index(players: List[MyTeamPlayerView]): Text.TypedTag[String] =
  html(
    head(
      tag("title")("Fantasy AI | My Team"),
      link(rel := "stylesheet", href := "/assets/app.css")
    ),
    body(
      div(cls := "bg-gray-100 p-8")(
        h1("My Roster"),
        table(
          thead(
            tr(
              th("Name"),
              th("Age"),
              th("Position"),
              th("Team")
            )
          ),
          tbody(
            players.map { player =>
              tr(
                td(player.name),
                td(player.age),
                td(player.position),
                td(player.proTeam)
              )
            }
          )
        )
      )
    )
  )
