package org.anvilpowered.mcoperator

import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder
import io.fabric8.kubernetes.client.KubernetesClient
import org.anvilpowered.mcoperator.crd.Cluster
import zio.*
import zio.Console.*

import java.nio.file.Paths
import scala.languageFeature.implicitConversions

object Main extends ZIOAppDefault {

  private def doThing(client: KubernetesClient): Unit = {
//    val spec = new DeploymentBuilder()
//      .withNewMetadata()
//      .withName("test")
  }


  override def run: ZIO[ZIOAppArgs & Scope, Any, Any] = {
    ???
    //val client = ZIOKubernetesClient.make("http://localhost.8001")


    //    val program: ZIO[Scope, Throwable, Unit] = for {
    //      _ <- printLine("Starting Minecraft Operator").orDie
    //      _ <- Registration.registerIfMissing(minecraftclusters.customResourceDefinition).catchAll { failure =>
    //        printLineError(s"CRD registration failed: $failure") *> ZIO.fail(failure)
    //      }
    //      op <- MCOperator.operator
    //      fiber <- op.start().fork
    //      _ <- ZIO.addFinalizer {
    //        printLine("Shutting down Minecraft Operator")
    //        fiber.interrupt
    //      }
    //      _ <- fiber.join
    //    } yield ()
    //
    //    program
    //      .provideSomeLayer(layers)
    //      .exitCode
  }

  //  def reconcile(event: WatchEvent[Cluster]): ZIO[Scope, Throwable, Unit] = for {
  //    _ <- printLine("Reconciling")
  //
  //
  //  } yield ()
}
