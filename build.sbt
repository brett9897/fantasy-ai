ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.8.4"

val http4sVersion = "0.23.36"
val skunkVersion = "1.0.0"
val cirisVersion = "3.15.0"
val munitVersion = "2.2.0"
val scalatagsVersion = "0.13.1"
val http4sScalatagsVersion = "0.25.3"
val jsoupVersion = "1.22.2"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .settings(
    name := "FantasyAI",
    idePackagePrefix := Some("io.github.brett9897.fantasyai"),
    dockerBaseImage := "eclipse-temurin:21-jre",
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
      "org.http4s"    %% "http4s-scalatags"    % http4sScalatagsVersion
    ),

    testFrameworks += new TestFramework("munit.Framework")
  )
