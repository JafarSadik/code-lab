enablePlugins(ScalaJSPlugin)

name := "Scala.js First Tutorial"
scalaVersion := "2.12.5" // or any other Scala version >= 2.10.2

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.6"
scalaJSUseMainModuleInitializer := true

lazy val copyJS = TaskKey[Unit]("copyJS", "Copy javascript files to targert directory")
copyJS := {
  val outDir = baseDirectory.value / "public/js"
  val inDir = baseDirectory.value / "target/scala-2.12"

  val files = Seq("scala-js-first-tutorial-opt.js") map { p => (inDir / p, outDir / p) }
  IO.copy(files, CopyOptions().withOverwrite(true))
}

