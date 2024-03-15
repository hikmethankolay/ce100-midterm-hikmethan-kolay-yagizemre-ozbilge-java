@echo off
@setlocal enableextensions
@cd /d "%~dp0"

echo Running Application
java -jar rental-management-app/target/rental-management-app-1.0-SNAPSHOT.jar

echo Operation Completed!
pause