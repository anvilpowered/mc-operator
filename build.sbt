ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.3"

lazy val root = (project in file("."))
  .settings(
    name := "mc-operator",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.14",
      "com.coralogix" %% "zio-k8s-client" % "3.1.0",
    ) ++ Seq(
      "com.coralogix" %% "zio-k8s-client",
      "com.coralogix" %% "zio-k8s-operator"
    ).map(_ % "3.1.0") ++ Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % "0.14.10"),
  )

externalCustomResourceDefinitions := Seq(
  file("manifest/crd/MinecraftCluster.yaml"),
  file("manifest/crd/MinecraftServerSet.yaml"),
  file("manifest/crd/MinecraftStorage.yaml"),
)

enablePlugins(K8sCustomResourceCodegenPlugin)
