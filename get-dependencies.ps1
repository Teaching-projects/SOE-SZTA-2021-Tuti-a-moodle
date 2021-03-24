$OutputEncoding = [Console]::OutputEncoding = [Text.UTF8Encoding]::UTF8

function Get-Downloader {
    $Downloader = New-Object System.Net.WebClient
    $Downloader.Credentials = [System.Net.CredentialCache]::DefaultCredentials
    $Downloader.Proxy = [System.Net.GlobalProxySelection]::GetEmptyWebProxy()

    return $Downloader
}

function Get-RemoteFile([string] $Url, [string] $Filename) {
    (Get-Downloader).DownloadFile($Url, "dependencies/$Filename")
}

function Get-Jackson {
    $JacksonVer = '2.12.1'
    $JacksonJar = 'jackson-{{0}}-{0}.jar' -f $JacksonVer
    $JacksonUrl = 'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-{{0}}/{0}/{1}' -f $JacksonVer, $JacksonJar

    if (Test-Path "dependencies/$($JacksonJar -f 'core')" -PathType Leaf) {
        echo 'Jackson is already downloaded, skipping...'
        return
    }

    echo ('Downloading Jackson {0}...' -f $JacksonVer)

    Get-RemoteFile ($JacksonUrl -f 'core') ($JacksonJar -f 'core')
    Get-RemoteFile ($JacksonUrl -f 'databind') ($JacksonJar -f 'databind')
    Get-RemoteFile ($JacksonUrl -f 'annotations') ($JacksonJar -f 'annotations')

    echo ('Jackson {0} downloaded' -f $JacksonVer, $JacksonJar)
}

function Get-Junit {
    $JunitName = 'junit-platform-console-standalone'
    $JunitVer = '1.7.1'
    $JunitJar = '{0}-{1}.jar' -f $JunitName, $JunitVer
    $JunitUrl = 'https://repo1.maven.org/maven2/org/junit/platform/{0}/{1}/{2}' -f $JunitName, $JunitVer, $JunitJar

    if (Test-Path "dependencies/$JunitJar" -PathType Leaf) {
        echo 'Junit is already downloaded, skipping...'
        return
    }

    echo ('Downloading Junit {0}...' -f $JunitVer)

    Get-RemoteFile $JunitUrl $JunitJar

    echo ('Junit {0} downloaded' -f $JunitVer)
}

Get-Jackson
Get-Junit
