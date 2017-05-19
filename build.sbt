organization in ThisBuild := "io.github.takayuky"

lazy val commonSettings = Seq(
  scalaVersion := "2.12.2",
  crossScalaVersions := Seq("2.11.11", scalaVersion.value),
  scalacOptions ++= Seq("-deprecation", "-feature", "-language:implicitConversions")
)


/** core **/

lazy val core = project.in(file("core"))
  .settings(commonSettings)
  .settings(coreSettings)

lazy val coreSettings = coreLibraryDependencies ++ Seq(
  name := "TypesafeFluentLogger Core",
  moduleName := "typesafe-fluent-logger-core"
)

lazy val coreLibraryDependencies = Seq(
  libraryDependencies ++= Seq(
    "org.fluentd" %% "fluent-logger-scala"  % "0.7.0"
  )
)



/** refined **/

lazy val refined = project.in(file("refined"))
  .dependsOn(core % "compile->compile; test->test")
  .settings(commonSettings)
  .settings(refinedSettings)

lazy val refinedSettings = refinedLibraryDependencies ++ Seq(
  name := "TypesafeFluentLogger Refined",
  moduleName := "typesafe-fluent-logger-refined"
)

lazy val refinedLibraryDependencies = Seq(
  libraryDependencies ++= Seq(
    "eu.timepit" %% "refined" % "0.8.1"
  )
)


/** examples **/

lazy val examples = project.in(file("examples"))
  .dependsOn(core % "compile->compile; test->test")
  .dependsOn(refined % "compile->compile; test->test")
  .settings(commonSettings)
