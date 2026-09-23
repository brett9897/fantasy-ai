package io.github.brett9897.fantasyai
package domain

import cats.effect.IO

trait PlayerRepository:
  def findById(id: PlayerId): IO[Option[Player]]

  def findByTeam(league: LeagueCode, proTeam: String): IO[List[Player]]

  def save(player: Player): IO[Unit]