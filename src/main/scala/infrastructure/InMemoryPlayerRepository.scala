package io.github.brett9897.fantasyai
package infrastructure

import cats.effect.{Async, IO, Ref}
import domain.*

class InMemoryPlayerRepository(state: Ref[IO, Map[PlayerId, Player]]) extends PlayerRepository:

  override def findById(id: PlayerId): IO[Option[Player]] =
    state.get.map(map => map.get(id))

  override def findByTeam(sport: SportCode, proTeam: String): IO[List[Player]] =
    state.get.map { map =>
      map.values.filter(p => p.sport == sport && p.proTeam == proTeam).toList
    }

  override def save(player: Player): IO[Unit] =
    state.update(map => map + (player.id -> player))

object InMemoryPlayerRepository:
  def make: IO[PlayerRepository] =
    Ref.of[IO, Map[PlayerId, Player]](Map.empty).map(state => new InMemoryPlayerRepository(state))