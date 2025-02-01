package org.anvilpowered.mcoperator

import com.coralogix.zio.k8s.client.apiextensions.v1.customresourcedefinitions.CustomResourceDefinitions
import com.coralogix.zio.k8s.client.config.*
import com.coralogix.zio.k8s.client.config.httpclient.*
import com.coralogix.zio.k8s.client.model.K8sNamespace
import com.coralogix.zio.k8s.client.org.anvilpowered.mcoperator.definitions.minecraftcluster.v1alpha1.MinecraftCluster
import com.coralogix.zio.k8s.client.org.anvilpowered.mcoperator.definitions.minecraftcluster.v1alpha1.MinecraftCluster.metadata
import com.coralogix.zio.k8s.client.org.anvilpowered.mcoperator.v1alpha1.minecraftclusters
import com.coralogix.zio.k8s.client.org.anvilpowered.mcoperator.v1alpha1.minecraftclusters.MinecraftClusters
import com.coralogix.zio.k8s.client.v1.configmaps.ConfigMaps
import com.coralogix.zio.k8s.client.v1.pods
import com.coralogix.zio.k8s.client.v1.pods.Pods
import com.coralogix.zio.k8s.client.{K8sFailure, NamespacedResource}
import com.coralogix.zio.k8s.model.pkg.apis.apiextensions.v1.CustomResourceDefinition
import com.coralogix.zio.k8s.operator.{Operator, Registration}
import zio.*
import zio.Console.*

import java.nio.file.Paths
import scala.languageFeature.implicitConversions

object Main extends ZIOAppDefault {

  override def run: ZIO[ZIOAppArgs & Scope, Any, Any] = {
    val layers = k8sDefault >>> (MinecraftClusters.live ++ CustomResourceDefinitions.live)

    val program: ZIO[Scope & NamespacedResource[MinecraftCluster] & CustomResourceDefinitions, Throwable, Unit] = for {
      _ <- printLine("Starting Minecraft Operator").orDie
      _ <- Registration.registerIfMissing(minecraftclusters.customResourceDefinition).catchAll { failure =>
        printLineError(s"CRD registration failed: $failure") *> ZIO.fail(failure)
      }
      op <- MCOperator.operator
      fiber <- op.start().fork
      _ <- ZIO.addFinalizer {
        printLine("Shutting down Minecraft Operator")
        fiber.interrupt
      }
      _ <- fiber.join
    } yield ()

    program
      .provideSomeLayer(layers)
      .exitCode
  }
}
