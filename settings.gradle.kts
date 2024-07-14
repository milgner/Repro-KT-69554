rootProject.name = "Repro-KT-69554"

include("plugins")

for (dir in File("${rootDir.absolutePath}/plugins").list { dir, name ->
    File("${dir.absolutePath}/$name").isDirectory && name != "build"
}) {
    include("plugins:$dir")
}
