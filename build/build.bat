echo on

set PROJET_HOME=..
set JAVA_HOME=%PROJET_HOME%/../jdk/windows
set ANT_HOME=%PROJET_HOME%/../ant

call %ANT_HOME%/bin/ant -f build.xml build_all -Dkkk=123456

pause