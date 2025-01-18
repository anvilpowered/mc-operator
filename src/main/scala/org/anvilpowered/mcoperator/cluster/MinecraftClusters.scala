package org.anvilpowered.mcoperator.cluster

import com.coralogix.zio.k8s.client.config.backend.K8sBackend
import com.coralogix.zio.k8s.client.impl.{ResourceClient, ResourceStatusClient}
import com.coralogix.zio.k8s.client.model.K8sCluster
import com.coralogix.zio.k8s.client.{NamespacedResource, NamespacedResourceStatus, Resource, ResourceStatus}
import zio.{ZIO, ZLayer}

trait MinecraftClusters
  extends NamespacedResource[MinecraftCluster]
    with NamespacedResourceStatus[MinecraftClusterStatus, MinecraftCluster]

object MinecraftClusters {

  final class Live(
    override val asGenericResource: Resource[MinecraftCluster],
    override val asGenericResourceStatus: ResourceStatus[MinecraftClusterStatus, MinecraftCluster]
  ) extends MinecraftClusters

  val live: ZLayer[K8sCluster & K8sBackend, Nothing, MinecraftClusters] =
    ZLayer {
      import MinecraftCluster._
      for {
        cluster <- ZIO.service[K8sCluster]
        backend <- ZIO.service[K8sBackend]
        client = ResourceClient[MinecraftCluster, MinecraftClusterStatus](MetaData.resourceType, cluster, backend)
        statusClient = ResourceStatusClient[MinecraftClusterStatus, MinecraftCluster](MetaData.resourceType, cluster, backend)
      } yield new Live(client, statusClient)
    }
}
