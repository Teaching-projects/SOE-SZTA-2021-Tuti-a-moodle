val isCiServer = System.getenv().containsKey("CI")

buildCache {
    local {
        isEnabled = !isCiServer
    }
}

rootProject.name = "tutiamoodle"
include("app")
