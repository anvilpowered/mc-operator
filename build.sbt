ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.6.4"

lazy val root = (project in file("."))
  .settings(
    name := "mc-operator",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.17",
      "io.fabric8" % "kubernetes-client" % "7.1.0",
    ),
    generateCRDs := {
      val outputDir = (Compile / resourceManaged).value
      CRDGen.generate(
        outputDir = outputDir,
        classpath = (Compile / fullClasspath).value.files,
        classesDirs = Seq((Compile / classDirectory).value)
      )
    },
    Compile / resourceGenerators += Def.task {
      (resourceManaged.value ** "*.yaml").get
    }.taskValue,
  )

lazy val operator = (project in file("operator"))
  .settings(
    name := "operator"
  ).enablePlugins(DockerPlugin)
  .dependsOn(root)

lazy val generateCRDs = taskKey[Unit]("Generates CRDs using Fabric8 classes")
