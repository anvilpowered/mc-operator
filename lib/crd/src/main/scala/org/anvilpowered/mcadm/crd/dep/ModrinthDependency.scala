package org.anvilpowered.mcadm.crd.dep

import com.fasterxml.jackson.annotation.JsonProperty
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.{Group, ShortNames, Version}

@Group("org.anvilpowered")
@Version("v1alpha1")
@ShortNames(Array("mrdep"))
class ModrinthDependency extends CustomResource[ModrinthDependencySpec, ModrinthDependencyStatus] 

abstract class ModrinthDependencySpec {
  
  @JsonProperty("followChannel")
  val followChannel: String
}

abstract class ModrinthDependencyStatus {
  
}