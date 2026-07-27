package io.github.brett9897.fantasyai
package frontend.features.myteam.views

import scalatags.Text
import scalatags.Text.all.{tr, *}

def Index(): Text.TypedTag[String] =
  html(
    head(
      tag("title")("Fantasy AI | My Team")
    ),
    body(
      div(
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
            tr(
              td("Bobby Witt Jr."),
              td(26),
              td("SS"),
              td("KCR")
            ),
            tr(
              td("Jackson Holliday"),
              td(22),
              td("2B/SS"),
              td("BAL")
            ),
            tr(
              td("Paul Skenes"),
              td(24),
              td("SP"),
              td("PIT")
            )
          )
        )
      )
    )
  )
