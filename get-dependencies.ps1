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

$JacksonVer = '2.12.1'
$JacksonJar = 'jackson-{{0}}-{0}.jar' -f $JacksonVer
$JacksonUrl = 'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-{{0}}/{0}/{1}' -f $JacksonVer, $JacksonJar

if (Test-Path "dependencies/$($JacksonJar -f 'core')" -PathType Leaf) {
    echo "Jackson is already downloaded, exiting..."
    exit 0
}

echo ("Downloading Jackson {0}..." -f $JacksonVer)
Get-RemoteFile ($JacksonUrl -f 'core') ($JacksonJar -f 'core')
Get-RemoteFile ($JacksonUrl -f 'databind') ($JacksonJar -f 'databind')
Get-RemoteFile ($JacksonUrl -f 'annotations') ($JacksonJar -f 'annotations')

echo ("Jackson {0} downloaded" -f $JacksonVer, $JacksonJar)
exit 0
