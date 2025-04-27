package org.anvilpowered.mcadm.crd

import com.fasterxml.jackson.annotation.JsonProperty
import io.fabric8.crd.generator.annotation.SchemaFrom
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.{Group, ShortNames, Version}
import io.fabric8.generator.annotation.Default
import io.fabric8.kubernetes.api.model.apps.StatefulSetSpec

@Group("org.anvilpowered")
@Version("v1alpha1")
@ShortNames(Array("mcc"))
class MinecraftCluster extends CustomResource[MinecraftClusterSpec, MinecraftClusterStatus]

abstract class MinecraftClusterSpec {
  @JsonProperty("servers")
  val servers: JUList[ServerSpec]
}

abstract class ServerSpec {
  @JsonProperty("replicas")
  val replicas: ReplicaSpec

  @JsonProperty("templateName")
  val templateName: String
}

abstract class ReplicaSpec {
  @JsonProperty("fixed")
  val fixed: Int
}

abstract class MinecraftClusterStatus {
  @JsonProperty("error")
  val error: Boolean
  @JsonProperty("message")
  val message: String
}


