
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "mangaDownloader",
    libraryDependencies ++= Seq(
      "org.jsoup" % "jsoup" % "1.9.1",
      "org.scalatest" %% "scalatest" % "3.0.5"
    )
  )
