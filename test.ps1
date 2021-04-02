$Sep = ':'
if ([System.Environment]::OSVersion.Platform -eq 'Win32NT') {
    $Sep = ';'
}

$Ver = '2.12.1'
$Deps = @('core', 'databind', 'annotations') | % {
    'dependencies/jackson-{0}-{1}.jar' -f $_, $script:Ver
}
$Junit = 'dependencies/junit-platform-console-standalone-1.7.1.jar'
$Deps = $Deps + ,$Junit
$cp = (,'build' + $Deps) -join $Sep

java -jar $Junit -cp $cp --scan-classpath
exit $LastExitCode
