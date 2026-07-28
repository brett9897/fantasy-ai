package io.github.brett9897.fantasyai
package frontend.features.myteam

import cats.effect.{IO, Ref}

import java.util.UUID
import viewmodels.*

class InMemoryMyTeamQueries(state: Ref[IO, Map[UUID, MyTeamPlayerView]]) extends MyTeamQueries:
  def loadMyTeamPlayersView: IO[List[MyTeamPlayerView]] =
    state.get.map(_.values.toList)

object InMemoryMyTeamQueries:
  private val bobbyWittId = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
  private val jacksonHollidayId = UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")
  private val paulSkenesId = UUID.fromString("cccccccc-cccc-cccc-cccc-cccccccccccc")

  private val initialRoster: Map[UUID, MyTeamPlayerView] =
    Map(
      bobbyWittId -> MyTeamPlayerView(
        id = bobbyWittId,
        name = "Bobby Witt Jr.",
        position = "SS",
        proTeam = "KCR",
        age = 26
      ),
      jacksonHollidayId -> MyTeamPlayerView(
        id = jacksonHollidayId,
        name = "Jackson Holliday",
        position = "2B/SS",
        proTeam = "BAL",
        age = 22
      ),
      paulSkenesId -> MyTeamPlayerView(
        id = paulSkenesId,
        name = "Paul Skenes",
        position = "SP",
        proTeam = "PIT",
        age = 24
      )
    )

  def make: IO[MyTeamQueries] =
    Ref.of[IO, Map[UUID, MyTeamPlayerView]](initialRoster).map(state => new InMemoryMyTeamQueries(state))