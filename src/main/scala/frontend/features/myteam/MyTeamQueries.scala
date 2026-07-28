package io.github.brett9897.fantasyai
package frontend.features.myteam

import cats.effect.IO
import viewmodels.*

trait MyTeamQueries {
  def loadMyTeamPlayersView: IO[List[MyTeamPlayerView]]
}
