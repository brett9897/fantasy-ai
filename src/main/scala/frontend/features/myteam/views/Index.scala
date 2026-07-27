package io.github.brett9897.fantasyai
package frontend.features.myteam.views

import scalatags.Text
import scalatags.Text.all.{tr, *}
import frontend.features.myteam.viewmodels.MyTeamPlayerView

def Index(isDev: Boolean) (players: List[MyTeamPlayerView]): Text.TypedTag[String] = {
  val assetTags: Seq[Frag] =
    if isDev then
      Seq(
        script(`type` := "module", src := "http://localhost:5173/@vite/client"),
        link(rel := "stylesheet", href := "http://localhost:5173/main.css")
      )
    else
      Seq(
        link(rel := "stylesheet", href := "/assets/app.css")
      )

  html(
    head(
      tag("title")("Fantasy AI | My Team"),
      meta(charset := "UTF-8"),
      meta(name := "viewport", content := "width=device-width, initial-scale=1.0"),
      assetTags
    ),
    body(cls := "bg-gray-100 p-8")(
      div(cls := "max-w-4xl mx-auto bg-white rounded shadow")(
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
      }
