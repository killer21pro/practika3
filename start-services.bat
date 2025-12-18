@echo off
echo Starting Student Service and Hobby Changer Service...

start "Student Service" cmd /k "gradlew bootRun"
timeout /t 3 /nobreak >nul
start "Hobby Changer Service" cmd /k "cd hobby-changer && ..\gradlew bootRun"

echo Both services are starting...
echo Student Service: http://localhost:8080
echo Hobby Changer Service: http://localhost:8081

pause


