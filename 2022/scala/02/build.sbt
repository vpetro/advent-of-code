
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.vpetro",
      scalaVersion := Versions.scalaVersion,
      libraryDependencies ++= List(
        Dependencies.scalatest,
        Dependencies.betterfiles
      )
    )),
    name := "aoc22"
  )

