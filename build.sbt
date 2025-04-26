ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.4"

lazy val root = (project in file("."))
  .settings(
    name := "mc-operator", // TODO: Come up with a new name?
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.17",
      "io.fabric8" % "kubernetes-client" % "7.1.0",
    )
  )

lazy val operator = (project in file("operator"))
  .settings(
    name := "operator",
  ).enablePlugins(DockerPlugin)
  .dependsOn(root)
