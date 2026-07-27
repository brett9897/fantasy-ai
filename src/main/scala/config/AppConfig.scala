package io.github.brett9897.fantasyai
package config

import cats.effect.IO
import cats.syntax.all.*
import ciris.*
import com.comcast.ip4s.{Port, port}

case class AppConfig(
  isDevelopment: Boolean,
  port: Port,
  enableAdvancedStats: Boolean
)

object AppConfig:
  implicit val portDecoder: ConfigDecoder[String, Port] =
    ConfigDecoder[String, Int].mapOption("Port")(Port.fromInt)

  def load: IO[AppConfig] =
    val environmentConfig = env("ENVIRONMENT")
      .as[String]
      .default("")

    val portConfig = env("PORT")
      .as[Port]
      .default(port"8080")

    val statsToggleConfig = env("ENABLE_ADVANCED_STATS")
      .as[Boolean]
      .default(false)

    (environmentConfig, portConfig, statsToggleConfig).parMapN { (environment, port, statsToggle) =>
      val isDevelopment = environment.toUpperCase() == "DEVELOPMENT"

      AppConfig(isDevelopment, port, statsToggle)
    }.load[IO]