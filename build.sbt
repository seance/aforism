name := "aforism"

organization := "io.github.seance"

version := "0.1.0"

description := "Algebraic Finite State Machine"

scalaVersion := "2.12.2"

val scalazVersion = "7.2.14"
val monocleVersion = "1.4.0"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "com.github.julien-truffaut" %% "monocle-core" % monocleVersion
)

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-language:higherKinds",
  "-Xfatal-warnings",
  "-Xlint",
  "-Ywarn-unused-import",
  "-Ywarn-unused"
)

licenses += ("ISC", url("http://opensource.org/licenses/ISC"))

bintrayPackageLabels := Seq(
  "adt",
  "algebraic data type",
  "fsm",
  "finite state machine"
)
