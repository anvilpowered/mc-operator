package org.anvilpowered.mcadm.crd.dep

import com.fasterxml.jackson.annotation.JsonProperty
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.{Group, ShortNames, Version}

@Group("org.anvilpowered")
@Version("v1alpha1")
@ShortNames(Array("ghdep"))
class GitHubDependency extends CustomResource[GitHubDependencySpec, GitHubDependencyStatus] {

}

abstract class GitHubDependencySpec {
  @JsonProperty("organization")
  val organization: String
  @JsonProperty("project")
  val project: String
}


abstract class GitHubDependencyStatus {

}