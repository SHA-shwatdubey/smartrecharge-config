#!/bin/bash

# SmartRecharge Microservices - Setup & Run Script
# This script automates the setup and startup of the entire system

set -e

echo "=========================================="
echo "SmartRecharge Cloud Microservices System"
echo "=========================================="
echo ""

# Check prerequisites
echo "[1/5] Checking prerequisites..."

if ! command -v docker &> /dev/null; then
    echo "❌ Docker is not installed. Please install Docker Desktop."
    exit 1
fi

if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose is not installed. Please install Docker Compose."
    exit 1
fi

if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven 3.9+."
    exit 1
fi

echo "✓ Docker is installed"
echo "✓ Docker Compose is installed"
echo "✓ Maven is installed"
echo ""

# Clean and build
echo "[2/5] Building all microservices (this may take 2-3 minutes)..."
mvn clean package -DskipTests > /dev/null 2>&1
echo "✓ All services built successfully"
echo ""

# Check if running
echo "[3/5] Stopping any existing containers..."
docker-compose down 2> /dev/null || true
echo "✓ Ready to start fresh"
echo ""

# Start services
echo "[4/5] Starting all services with Docker Compose..."
echo "       (This will take 1-2 minutes for full startup)"
echo ""
docker-compose up -d --build

echo ""
echo "[5/5] Waiting for all services to be healthy..."

# Wait for services to be healthy
max_attempts=60
attempt=0

while [ $attempt -lt $max_attempts ]; do
    if curl -s http://localhost:8061/actuator/health > /dev/null 2>&1 && \
       curl -s http://localhost:8063/actuator/health > /dev/null 2>&1 && \
       curl -s http://localhost:8064/actuator/health > /dev/null 2>&1 && \
       curl -s http://localhost:8065/actuator/health > /dev/null 2>&1; then
        break
    fi

    attempt=$((attempt + 1))
    echo "  Waiting... ($attempt/$max_attempts)"
    sleep 2
done

if [ $attempt -eq $max_attempts ]; then
    echo "⚠️  Some services may still be starting. Check logs with: docker-compose logs"
else
    echo "✓ All services are healthy!"
fi

echo ""
echo "=========================================="
echo "✓ System is Ready!"
echo "=========================================="
echo ""
echo "📊 Access Points:"
echo ""
echo "  API Gateway:        http://localhost:8062"
echo "  Eureka Dashboard:   http://localhost:8061"
echo "  RabbitMQ Console:   http://localhost:15672 (guest/guest)"
echo ""
echo "📚 Swagger Documentation:"
echo ""
echo "  Customer Service:   http://localhost:8063/swagger-ui.html"
echo "  Recharge Service:   http://localhost:8064/swagger-ui.html"
echo "  Processing Service: http://localhost:8065/swagger-ui.html"
echo ""
echo "💾 Database Credentials:"
echo ""
echo "  PostgreSQL:  postgres / root @ localhost:5432"
echo "  Databases:   customer_db, recharge_db, processing_db"
echo ""
echo "📨 Testing the System:"
echo ""
echo "  Create customer:"
echo "    curl -X POST http://localhost:8062/api/customers \\"
echo "      -H 'Content-Type: application/json' \\"
echo "      -d '{\"customerName\": \"John\", \"email\": \"john@example.com\", \"mobileNumber\": \"9876543210\", \"city\": \"NYC\"}'"
echo ""
echo "  Create recharge (replace customerId=1 if needed):"
echo "    curl -X POST http://localhost:8062/api/recharges \\"
echo "      -H 'Content-Type: application/json' \\"
echo "      -d '{\"customerId\": 1, \"mobileNumber\": \"9876543210\", \"operatorName\": \"Verizon\", \"amount\": 49.99}'"
echo ""
echo "  Get transactions:"
echo "    curl http://localhost:8062/api/transactions"
echo ""
echo "🛑 To stop all services:"
echo ""
echo "    docker-compose down"
echo ""
echo "📖 For detailed instructions, see:"
echo ""
echo "    README.md - Full documentation and API guide"
echo "    DOCKER_GUIDE.md - Docker-specific instructions"
echo ""
echo "=========================================="

