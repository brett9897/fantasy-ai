addSbtPlugin("org.jetbrains.scala" % "sbt-ide-settings" % "1.1.4")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.10.0")

addSbtPlugin("com.github.sbt" % "sbt-native-packager" % "1.11.0")

// Flyway
addSbtPlugin("com.github.sbt"      % "flyway-sbt"        % "11.11.0")
libraryDependencies ++= Seq(
  "org.flywaydb"    % "flyway-database-postgresql" % "11.20.3",
  "org.postgresql" % "postgresql"                % "42.7.13"
)