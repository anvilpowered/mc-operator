package org.anvilpowered.mcadm.crd

import com.fasterxml.jackson.annotation.JsonProperty
import io.fabric8.generator.annotation.Default
import io.fabric8.kubernetes.api.model.PodSpec

abstract class MinecraftServerTemplateSpec {
  @JsonProperty("image")
  @Default("itzg/minecraft-server:latest")
  val image: String
  @JsonProperty("basePodTemplateSpec")
  val basePodTemplateSpec: PodSpec
}

abstract class MinecraftServerTemplateStatus {
  @JsonProperty("error")
  val error: Boolean
  @JsonProperty("message")
  val message: String
}

