package io.github.brett9897.fantasyai
package domain

import java.util.UUID

opaque type PlayerId = UUID

object PlayerId:
  def apply(id: UUID): PlayerId = id

  def generate(): PlayerId = UUID.randomUUID()

  extension(id: PlayerId)
    def value: UUID = id

enum LeagueCode:
  case MLB, MILB, NHL, AHL, NFL, UFL

  def sport: String = this match
    case MLB | MILB => "Baseball"
    case NHL | AHL  => "Hockey"
    case NFL | UFL  => "Football"

case class Player (
  id: PlayerId,
  externalApiId: String,
  fullName: String,
  league: LeagueCode,
  proTeam: String
)
