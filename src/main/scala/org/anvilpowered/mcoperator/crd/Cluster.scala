package org.anvilpowered.mcoperator.crd

import dev.hnaderi.k8s.utils.*

case class Cluster(
  
)



case class ClusterSpec(
  storageName: String,
)

object ClusterSpec {
  given decoder: Decoder[ClusterSpec] = new Decoder[ClusterSpec] {
    override def apply[T: Reader](t: T): Either[String, ClusterSpec] = for {
      obj <- ObjectReader(t)
      storageName <- obj.read[String]("storageName")
    } yield ClusterSpec(storageName)
  }
  
  given encoder: Encoder[ClusterSpec] = new Encoder[ClusterSpec] {
    override def apply[T: Builder](r: ClusterSpec): T = {
      val obj = ObjectWriter[T]()
      obj
        .write("storageName", r.storageName)
        .build
    }
  }
}
