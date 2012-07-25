import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "play-spring-poc"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "com.opeak.poc" %
      "simple-spring-jpa" %
      "0.0.1-SNAPSHOT")

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    resolvers += (
      "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"))

}
