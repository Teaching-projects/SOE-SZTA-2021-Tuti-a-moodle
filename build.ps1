$Sep = ':'
if ([System.Environment]::OSVersion.Platform -eq 'Win32NT') {
    $Sep = ';'
}

$Ver = '2.12.1'
$Deps = @('core', 'databind', 'annotations') | % { 'dependencies/jackson-{0}-{1}.jar' -f $_, $script:Ver }
$cp = (,'build' + $Deps) -join $Sep

javac -d build -cp $cp *.java
exit $LastExitCode
