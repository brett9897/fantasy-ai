ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.8.4"

val http4sVersion = "0.23.37"
val skunkVersion = "1.0.0"
val cirisVersion = "3.15.1"
val munitVersion = "2.2.1"
val scalatagsVersion = "0.13.1"
val http4sScalatagsVersion = "0.25.3"
val jsoupVersion = "1.23.2"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin, FlywayPlugin)
  .settings(
    name := "FantasyAI",
    idePackagePrefix := Some("io.github.brett9897.fantasyai"),
    dockerBaseImage := "eclipse-temurin:21-jre",

    // Flyway migration settings
    //                                  use jdbc:postgresql://localhost:5432/fantasy_db if Docker or Podman
    flywayUrl      := sys.env.getOrElse("FLYWAY_URL", "jdbc:postgresql://fantasy-ai-db.orb.local:5432/fantasy_db"),
    flywayUser     := sys.env.getOrElse("FLYWAY_USER", "fantasy_user"),
    flywayPassword := sys.env.getOrElse("FLYWAY_PASSWORD", "fantasy_password"),
    flywayLocations := Seq(sys.env.getOrElse("FLYWAY_LOCATIONS", "filesystem:src/main/resources/db/migration")),

    libraryDependencies ++= Seq(
      // Configuration loader
      "is.cir"        %% "ciris"               % cirisVersion,

      // HTTP Server (http4s)
      "org.http4s"    %% "http4s-ember-server" % http4sVersion,
      "org.http4s"    %% "http4s-dsl"          % http4sVersion,

      // Database Access (Skunk)
      "org.tpolecat"  %% "skunk-core"          % skunkVersion,

      // Testing (MUnit)
      "org.typelevel" %% "munit-cats-effect"   % munitVersion % Test,
      "org.jsoup"      % "jsoup"               % jsoupVersion % Test,

      // HTML Templating (ScalaTags)
      "com.lihaoyi"   %% "scalatags"           % scalatagsVersion,
      "org.http4s"    %% "http4s-scalatags"    % http4sScalatagsVersion,
    ),

    testFrameworks += new TestFramework("munit.Framework")
  )
