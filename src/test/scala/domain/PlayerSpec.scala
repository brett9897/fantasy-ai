package io.github.brett9897.fantasyai
package domain

import munit.FunSuite

class PlayerSpec extends FunSuite:
  test("PlayerId generation creates a valid underlying UUID") {
    val id = PlayerId.generate()

    assertEquals(id.value.version(), 4)
  }
