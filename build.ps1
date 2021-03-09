$Sep = ':'
if ([System.Environment]::OSVersion.Platform -eq 'Win32NT') {
    $Sep = ';'
}

$Ver = '2.12.1'
$Deps = @('core', 'databind', 'annotations') | % { './jackson-{0}-{1}.jar' -f $_, $script:Ver }
$cp = (,'.' + $Deps) -join $Sep

javac -cp $cp *.java
exit $LastExitCode
