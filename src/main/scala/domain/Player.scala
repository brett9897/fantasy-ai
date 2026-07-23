package io.github.brett9897.fantasyai
package domain

import java.util.UUID

opaque type PlayerId = UUID

object PlayerId:
  def apply(id: UUID): PlayerId = id

  def generate(): PlayerId = UUID.randomUUID()

  extension(id: PlayerId)
    def value: UUID = id

enum SportCode:
  case NFL, MLB

case class Player (
  id: PlayerId,
  externalApiId: String,
  fullName: String,
  sport: SportCode,
  proTeam: String
)
