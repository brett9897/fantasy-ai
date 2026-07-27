package io.github.brett9897.fantasyai
package frontend.features.myteam.viewmodels

import java.util.UUID

case class MyTeamPlayerView(
  id: UUID,
  name: String,
  position: String,
  proTeam: String,
  age: Int
)

