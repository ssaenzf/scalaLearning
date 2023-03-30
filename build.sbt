ThisBuild / version := "0.4.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.6"

ThisBuild / libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % Test
ThisBuild / libraryDependencies += "junit" % "junit" % "4.12" % Test
ThisBuild / libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.0-M2"
ThisBuild / libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.6.0-M2"



lazy val root = (project in file("."))
  .settings(
    name := "scalaLearning"
  )
