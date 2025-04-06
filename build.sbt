ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.6.4"

val zioK8sVersion = "3.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "mc-operator", // TODO: Come up with a new name?
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.17",
    ) ++ Seq(
      "com.coralogix" %% "zio-k8s-client",
    ).map(_ % zioK8sVersion) ++ Seq(
      "org.typelevel" %% "cats-core" % "2.13.0"
    ) ++ Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % "0.14.12"),
  )

lazy val operator = (project in file("operator"))
  .settings(
    name := "operator",
    libraryDependencies ++= Seq(
      "com.coralogix" %% "zio-k8s-operator"
    ).map(_ % zioK8sVersion),
  ).enablePlugins(DockerPlugin)
  .dependsOn(root)

externalCustomResourceDefinitions := Seq(
  file("manifest/crd/MinecraftCluster.yaml"),
  file("manifest/crd/MinecraftServerSet.yaml"),
  file("manifest/crd/MinecraftStorage.yaml"),
)

enablePlugins(K8sCustomResourceCodegenPlugin)
