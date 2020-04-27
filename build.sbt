
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
      "org.scalatest" %% "scalatest" % "3.1.0",
      "org.scalatestplus" %% "scalatestplus-scalacheck" % "3.1.0.0-RC2",
      "org.mockito" % "mockito-scala_2.12" % "1.11.1",
      "org.mockito" % "mockito-scala-scalatest_2.12" % "1.11.1"
    )
  )
