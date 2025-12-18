Write-Host "Starting Student Service and Hobby Changer Service..." -ForegroundColor Green

# Start Student Service
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot'; .\gradlew bootRun" -WindowStyle Normal

# Wait a bit for the first service to start
Start-Sleep -Seconds 3

# Start Hobby Changer Service
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$PSScriptRoot\hobby-changer'; ..\gradlew bootRun" -WindowStyle Normal

Write-Host "Both services are starting..." -ForegroundColor Yellow
Write-Host "Student Service: http://localhost:8080" -ForegroundColor Cyan
Write-Host "Hobby Changer Service: http://localhost:8081" -ForegroundColor Cyan


