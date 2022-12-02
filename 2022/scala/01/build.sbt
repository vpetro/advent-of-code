
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.vpetro",
      scalaVersion := Versions.scalaVersion,
      libraryDependencies ++= List(
        Dependencies.scalatest,
        "com.github.pathikrit" %% "better-files" % "3.9.1"
      )

    )),
    name := "aoc22"
  )

