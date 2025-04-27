package org.anvilpowered.mcoperator.crd

import io.fabric8.kubernetes.api.model.Namespaced
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.{Group, ShortNames, Version}

@Group("org.anvilpowered")
@Version("v1alpha1")
@ShortNames(Array("clu"))
class Cluster extends CustomResource[ClusterSpec, ClusterStatus], Namespaced

class ClusterSpec {
  val someValue = 0
}

class ClusterStatus {
  val error = false
  val message: String = null
}
