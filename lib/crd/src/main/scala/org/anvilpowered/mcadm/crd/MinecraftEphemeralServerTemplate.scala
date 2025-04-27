package org.anvilpowered.mcadm.crd

import com.fasterxml.jackson.annotation.JsonProperty
import io.fabric8.generator.annotation.Default
import io.fabric8.kubernetes.api.model.extensions.DeploymentSpec
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.{Group, ShortNames, Version}

@Group("org.anvilpowered")
@Version("v1alpha1")
@ShortNames(Array("mcest"))
class MinecraftEphemeralServerTemplate extends CustomResource[MinecraftEphemeralServerTemplateSpec, MinecraftEphemeralServerTemplateStatus]

abstract class MinecraftEphemeralServerTemplateSpec extends MinecraftServerTemplateSpec

abstract class MinecraftEphemeralServerTemplateStatus extends MinecraftServerTemplateStatus
