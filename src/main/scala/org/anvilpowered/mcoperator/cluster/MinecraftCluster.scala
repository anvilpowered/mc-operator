package org.anvilpowered.mcoperator.cluster

import com.coralogix.zio.k8s.client.model
import com.coralogix.zio.k8s.client.model.{K8sObject, K8sObjectStatus, K8sResourceType, ResourceMetadata}
import com.coralogix.zio.k8s.model.pkg.apis.meta.v1.ObjectMeta
import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import zio.prelude.data.Optional

case class MinecraftCluster(metadata: Option[ObjectMeta], status: Option[MinecraftClusterStatus])

object MinecraftCluster {
  given codec: Codec[MinecraftCluster] = deriveCodec

  implicit object MetaData extends ResourceMetadata[MinecraftCluster] {
    private val group = "org.anvilpowered"
    private val version = "v1alpha1"

    override def kind: String = "MinecraftCluster"

    override def apiVersion: String = s"$group/$version"

    override def resourceType: model.K8sResourceType = K8sResourceType(kind, group, version)
  }

  implicit object K8s extends K8sObject[MinecraftCluster] {
    override def metadata(obj: MinecraftCluster): Optional[ObjectMeta] =
      obj.metadata

    override def mapMetadata(f: ObjectMeta => ObjectMeta)(r: MinecraftCluster): MinecraftCluster =
      r.copy(metadata = r.metadata.map(f))
  }

  implicit object Status extends K8sObjectStatus[MinecraftCluster, MinecraftClusterStatus] {
    override def status(obj: MinecraftCluster): Optional[MinecraftClusterStatus] =
      obj.status

    override def mapStatus(f: MinecraftClusterStatus => MinecraftClusterStatus)(r: MinecraftCluster): MinecraftCluster =
      r.copy(status = r.status.map(f))
  }
}

case class MinecraftClusterStatus(replicas: Int, labelSelector: String)

object MinecraftClusterStatus {
  given codec: Codec[MinecraftClusterStatus] = deriveCodec
}
