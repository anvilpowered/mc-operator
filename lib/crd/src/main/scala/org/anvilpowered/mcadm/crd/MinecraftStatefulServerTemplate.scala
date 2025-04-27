package org.anvilpowered.mcadm.crd

import com.fasterxml.jackson.annotation.JsonProperty
import io.fabric8.generator.annotation.Default
import io.fabric8.kubernetes.api.model.PodTemplateSpec
import io.fabric8.kubernetes.api.model.apps.StatefulSetSpec
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.{Group, ShortNames, Version}

@Group("org.anvilpowered")
@Version("v1alpha1")
@ShortNames(Array("mcsst"))
class MinecraftStatefulServerTemplate extends CustomResource[MinecraftEphemeralServerTemplateSpec, MinecraftStatefulServerTemplateStatus]

abstract class MinecraftStatefulServerTemplateSpec extends MinecraftServerTemplateSpec 

abstract class MinecraftStatefulServerTemplateStatus extends MinecraftServerTemplateStatus
