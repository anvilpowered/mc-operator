import io.fabric8.crd.generator.collector.CustomResourceCollector
import io.fabric8.crdv2.generator.CRDGenerator
import sbt.*

import java.nio.file.Files
import scala.collection.JavaConverters

object CRDGen {

  implicit class Test[E](seq: Seq[E]) {
    def toCollection: java.util.Collection[E] = JavaConverters.asJavaCollection(seq)
  }

  def generate(
    outputDir: File,
    classpath: Seq[File],
    classesDirs: Seq[File]
  ): Unit = {
    Files.createDirectories(outputDir.toPath)

    val collector = new CustomResourceCollector()
      .withParentClassLoader(getClass.getClassLoader)
      .withClasspathElements(classpath.map(_.getAbsolutePath).toCollection)
      .withFilesToScan(classesDirs.toCollection)

    val crdGenerator = new CRDGenerator()
      .customResourceClasses(collector.findCustomResourceClasses())
      .inOutputDir(outputDir)

    val generationInfo = crdGenerator.detailedGenerate()

    generationInfo.getCRDDetailsPerNameAndVersion.forEach { (crdName, versions) =>
      println(s"Generated CRD $crdName:")
      versions.forEach { (version, info) =>
        println(s"  $version -> ${info.getFilePath}")
      }
    }
  }
}
