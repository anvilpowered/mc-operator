package org.anvilpowered.mcoperator

import java.nio.file.Paths
import com.coralogix.zio.k8s.client.K8sFailure
import com.coralogix.zio.k8s.client.config.httpclient.*
import com.coralogix.zio.k8s.client.model.K8sNamespace
import com.coralogix.zio.k8s.client.v1.configmaps.ConfigMaps
import com.coralogix.zio.k8s.client.v1.pods
import com.coralogix.zio.k8s.client.v1.pods.Pods
import zio.*
import zio.Console.*

import com.coralogix.zio.k8s.client.config._
import com.coralogix.zio.k8s.client.config.httpclient._
import com.coralogix.zio.k8s.client.v1.configmaps.ConfigMaps
import com.coralogix.zio.k8s.client.v1.pods.Pods

import scala.languageFeature.implicitConversions

object Main extends ZIOAppDefault {

  override def run: ZIO[Any with ZIOAppArgs & Scope, Any, Any] = ???
}
