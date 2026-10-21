@echo off
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot"
set "PATH=%JAVA_HOME%\bin;C:\Users\hp\.m2\wrapper\dists\apache-maven-3.9.16\56ba1f9f\bin;%PATH%"
call "C:\Users\hp\.m2\wrapper\dists\apache-maven-3.9.16\56ba1f9f\bin\mvn.cmd" %*
