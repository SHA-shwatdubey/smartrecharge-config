@echo off
REM SmartRecharge Microservices - Setup & Run Script for Windows
REM This script automates the setup and startup of the entire system

setlocal enabledelayedexpansion

echo.
echo ==========================================
echo SmartRecharge Cloud Microservices System
echo ==========================================
echo.

REM Check prerequisites
echo [1/5] Checking prerequisites...

where docker >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Docker is not installed. Please install Docker Desktop.
    pause
    exit /b 1
)

where docker-compose >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Docker Compose is not installed. Please install Docker Compose.
    pause
    exit /b 1
)

where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Maven is not installed. Please install Maven 3.9+.
    pause
    exit /b 1
)

echo ✓ Docker is installed
echo ✓ Docker Compose is installed
echo ✓ Maven is installed
echo.

REM Clean and build
echo [2/5] Building all microservices (this may take 2-3 minutes)...
call mvn clean package -DskipTests >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Maven build failed. Check your setup.
    pause
    exit /b 1
)
echo ✓ All services built successfully
echo.

REM Check if running
echo [3/5] Stopping any existing containers...
call docker-compose down 2>nul
echo ✓ Ready to start fresh
echo.

REM Start services
echo [4/5] Starting all services with Docker Compose...
echo       (This will take 1-2 minutes for full startup)
echo.
call docker-compose up -d --build
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Docker Compose failed. Check Docker installation.
    pause
    exit /b 1
)

echo.
echo [5/5] Services are starting...
echo       Waiting 90 seconds for full startup...
echo.

timeout /t 90 /nobreak

echo.
echo ==========================================
echo ✓ System is Ready!
echo ==========================================
echo.
echo 📊 Access Points:
echo.
echo   API Gateway:        http://localhost:8062
echo   Eureka Dashboard:   http://localhost:8061
echo   RabbitMQ Console:   http://localhost:15672 (guest/guest)
echo.
echo 📚 Swagger Documentation:
echo.
echo   Customer Service:   http://localhost:8063/swagger-ui.html
echo   Recharge Service:   http://localhost:8064/swagger-ui.html
echo   Processing Service: http://localhost:8065/swagger-ui.html
echo.
echo 💾 Database Credentials:
echo.
echo   PostgreSQL:  postgres / root @ localhost:5432
echo   Databases:   customer_db, recharge_db, processing_db
echo.
echo 📨 Testing the System:
echo.
echo   Create customer:
echo   curl -X POST http://localhost:8062/api/customers ^
echo     -H "Content-Type: application/json" ^
echo     -d "{\"customerName\": \"John\", \"email\": \"john@example.com\", \"mobileNumber\": \"9876543210\", \"city\": \"NYC\"}"
echo.
echo   Create recharge (replace customerId=1 if needed):
echo   curl -X POST http://localhost:8062/api/recharges ^
echo     -H "Content-Type: application/json" ^
echo     -d "{\"customerId\": 1, \"mobileNumber\": \"9876543210\", \"operatorName\": \"Verizon\", \"amount\": 49.99}"
echo.
echo   Get transactions:
echo   curl http://localhost:8062/api/transactions
echo.
echo 🛑 To stop all services:
echo.
echo   docker-compose down
echo.
echo 📖 For detailed instructions, see:
echo.
echo   README.md - Full documentation and API guide
echo   DOCKER_GUIDE.md - Docker-specific instructions
echo.
echo ==========================================
echo.
pause

