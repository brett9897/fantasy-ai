package io.github.brett9897.fantasyai
package frontend.features.myteam.views

import scalatags.Text
import scalatags.Text.all.*
import frontend.features.myteam.viewmodels.MyTeamPlayerView

def Index(isDev: Boolean, assetsVersion: String) (players: List[MyTeamPlayerView]): Text.TypedTag[String] =
  val dataTestId = attr("data-testid")

  val assetTags: Seq[Frag] =
    if isDev then
      Seq(
        script(`type` := "module", src := "http://localhost:5173/@vite/client"),
        link(rel := "stylesheet", href := "http://localhost:5173/main.css")
      )
    else
      Seq(
        link(rel := "stylesheet", href := s"/assets/app.css?$assetsVersion")
      )

  html(
    head(
      tag("title")("Fantasy AI | My Team"),
      meta(charset := "UTF-8"),
      meta(name := "viewport", content := "width=device-width, initial-scale=1.0"),
      link(href := "https://fonts.googleapis.com/css2?family=Inter:wght@400;500&family=Oswald:wght@500;700&family=Teko:wght@500;700&display=swap", rel := "stylesheet"),
      assetTags
    ),
    body()(
      tag("main")(cls := "max-w-5xl mx-auto p-6")(
        div(cls := "sports-card")(
          h2(cls := "text-xl p-4 bg-white border-b text-gray-800")("My Roster"),
          table(cls := "sports-table")(
            thead(cls := "table-header")(
              tr(
                th("Name"),
                th("Age"),
                th("Position"),
                th("Team")
              )
            ),
            tbody(
              players.map { player =>
                tr(cls := "data-row", dataTestId := "player-row")(
                  td(cls := "data-cell font-medium", dataTestId := "player-name")(player.name),
                  td(cls := "data-cell", dataTestId := "player-age")(player.age),
                  td(cls := "data-cell", dataTestId := "player-position")(player.position),
                  td(cls := "data-cell text-gray-500", dataTestId := "player-pro-team")(player.proTeam)
                )
              }
            )
          )
        )
      )
    )
  )
