package org.anvilpowered.mcoperator

import com.coralogix.zio.k8s.client.NamespacedResource
import com.coralogix.zio.k8s.client.model.{Added, Deleted, Modified, Reseted}
import com.coralogix.zio.k8s.client.org.anvilpowered.mcoperator.definitions.minecraftcluster.v1alpha1.MinecraftCluster
import com.coralogix.zio.k8s.model.core.v1.Pod
import com.coralogix.zio.k8s.operator.Operator
import com.coralogix.zio.k8s.operator.Operator.EventProcessor
import zio.ZIO


object MCOperator {
  sealed trait CustomOperatorFailures

  private val eventProcessor: EventProcessor[Any, CustomOperatorFailures, MinecraftCluster] =
    (context, event) =>
      event match
        case Reseted() => ???
        case Added(item) => ???
        case Modified(item) => ???
        case Deleted(item) => ???

  val operator: ZIO[NamespacedResource[MinecraftCluster], Nothing, Operator[Any, CustomOperatorFailures, MinecraftCluster]] =
    Operator.namespaced[Any, CustomOperatorFailures, MinecraftCluster](eventProcessor)(namespace = None, buffer = 1024)
}
