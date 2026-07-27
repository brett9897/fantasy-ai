package io.github.brett9897.fantasyai
package frontend.features.myteam.views

import scalatags.Text
import scalatags.Text.all.*
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
        h1(cls := "text-2xl font-bold p-4 border-b")("My Roster"),
        table(cls := "w-full text-left border-collapse")(
          thead(cls := "bg-gray-50")(
            tr(
              th(cls := "p-4 border-b font-semibold text-gray-600")("Name"),
              th(cls := "p-4 border-b font-semibold text-gray-600")("Age"),
              th(cls := "p-4 border-b font-semibold text-gray-600")("Position"),
              th(cls := "p-4 border-b font-semibold text-gray-600")("Team")
            )
          ),
          tbody(
            players.map { player =>
              tr(cls := "hover:bg-gray-50")(
                td(cls := "p-4 border-b font-medium")(player.name),
                td(cls := "p-4 border-b font-medium")(player.age),
                td(cls := "p-4 border-b font-medium")(player.position),
                td(cls := "p-4 border-b font-medium")(player.proTeam)
              )
            }
          )
        )
      )
    )
  )
      }
