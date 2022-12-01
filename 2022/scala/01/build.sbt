
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ai.vetted",
      scalaVersion := Versions.scalaVersion,
      libraryDependencies ++= List(
        Dependencies.scalatest,
        "com.github.pathikrit" %% "better-files" % "3.9.1"
      )

    )),
    name := "advent-of-code-2022"
  )

