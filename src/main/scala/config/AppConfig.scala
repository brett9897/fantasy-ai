package io.github.brett9897.fantasyai
package config

import cats.effect.IO
import cats.syntax.all.*
import ciris.*
import com.comcast.ip4s.{Port, port}

case class AppConfig(
  port: Port,
  enableAdvancedStats: Boolean
)

object AppConfig:
  implicit val portDecoder: ConfigDecoder[String, Port] =
    ConfigDecoder[String, Int].mapOption("Port")(Port.fromInt)

  def load: IO[AppConfig] =
    val portConfig = env("PORT")
      .as[Port]
      .default(port"8080")

    val statsToggleConfig = env("ENABLE_ADVANCED_STATS")
      .as[Boolean]
      .default(false)

    (portConfig, statsToggleConfig).parMapN { (port, statsToggle) =>
      AppConfig(port, statsToggle)
    }.load[IO]