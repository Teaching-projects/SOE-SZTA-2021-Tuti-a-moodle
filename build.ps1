$Sep = ':'
if ([System.Environment]::OSVersion.Platform -eq 'Win32NT') {
    $Sep = ';'
}

$Ver = '2.12.1'
$Deps = @('core', 'databind', 'annotations') | % {
    'dependencies/jackson-{0}-{1}.jar' -f $_, $script:Ver
}
$Deps = $Deps + ,'dependencies/junit-platform-console-standalone-1.7.1.jar'
$cp = (,'build' + $Deps) -join $Sep

javac -d build -cp $cp src/*.java
exit $LastExitCode
