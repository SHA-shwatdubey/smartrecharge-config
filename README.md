# SmartRecharge Configuration Repository

## Overview

यह repository **SmartRecharge Cloud Microservices System** के लिए centralized configuration files contain करता है।

Spring Cloud Config Server इस repository से configuration files fetch करता है और सभी microservices को distribute करता है।

---

## 📁 Configuration Files

### **application.yml**
Common configuration सभी services के लिए:
- RabbitMQ credentials
- Management endpoints
- Logging configuration

### **eureka-server.yml**
Eureka Service Discovery के लिए:
- Server port (8061)
- Self-registration settings

### **api-gateway.yml**
Spring Cloud Gateway के लिए:
- Gateway port (8062)
- Service routes
- Discovery locator settings

### **customer-service.yml**
Customer Service के लिए:
- Port (8063)
- PostgreSQL database: `customer_db`
- Credentials: postgres/root

### **recharge-service.yml**
Recharge Service के लिए:
- Port (8064)
- PostgreSQL database: `recharge_db`
- RabbitMQ exchange configuration
- Credentials: postgres/root

### **processing-service.yml**
Processing Service के लिए:
- Port (8065)
- PostgreSQL database: `processing_db`
- RabbitMQ queue configuration
- Credentials: postgres/root

---

## 🚀 How to Use

### 1. Clone this Repository

```bash
git clone https://github.com/SHA-shwatdubey/smartrecharge-config.git
cd smartrecharge-config
```

### 2. Update Configuration (if needed)

Edit any `.yml` file as per your environment requirements.

### 3. Push to GitHub

```bash
git add .
git commit -m "Update SmartRecharge configuration"
git push origin main
```

### 4. Config Server fetches these files

Config Server automatically fetches from this repository on startup:

```bash
# Fetch customer-service configuration
curl http://localhost:8060/customer-service/default

# Fetch recharge-service configuration
curl http://localhost:8060/recharge-service/default

# Fetch processing-service configuration
curl http://localhost:8060/processing-service/default
```

---

## 📊 Configuration Structure

```
smartrecharge-config/
├── application.yml              (Common config)
├── eureka-server.yml            (Service Registry)
├── api-gateway.yml              (API Gateway Routes)
├── customer-service.yml         (Customer DB)
├── recharge-service.yml         (Recharge DB + RabbitMQ)
├── processing-service.yml       (Processing DB + RabbitMQ)
└── README.md                    (This file)
```

---

## 🔄 Update Configuration Flow

```
1. Edit YAML files
   ↓
2. Commit and push to GitHub
   ↓
3. Config Server fetches updated files
   ↓
4. Services read new configuration
   ↓
5. Changes apply immediately (or after restart)
```

---

## 🛠️ Important Configurations

### Database Connections

All services use PostgreSQL:
```yaml
Host: localhost
Port: 5432
Username: postgres
Password: root
```

Databases:
- `customer_db` - Customer Service
- `recharge_db` - Recharge Service  
- `processing_db` - Processing Service

### RabbitMQ

```yaml
Host: localhost
Port: 5672 (AMQP)
Username: guest
Password: guest

Exchange: recharge.exchange
Queue: recharge.queue
Routing Key: recharge.routing.key
```

### Service Ports

- Config Server: 8060
- Eureka Server: 8061
- API Gateway: 8062
- Customer Service: 8063
- Recharge Service: 8064
- Processing Service: 8065

---

## ✏️ Making Changes

### To update a service configuration:

```bash
# 1. Clone (if not already done)
git clone https://github.com/SHA-shwatdubey/smartrecharge-config.git
cd smartrecharge-config

# 2. Edit the YAML file (e.g., customer-service.yml)
# Change database settings, ports, etc.

# 3. Commit and push
git add .
git commit -m "Update customer service configuration"
git push origin main

# 4. Restart services or refresh Config Server
curl -X POST http://localhost:8060/actuator/bus-refresh
```

---

## 🔐 Security Notes

### Production Recommendations:

1. **Change default passwords:**
   - PostgreSQL: Change `postgres/root`
   - RabbitMQ: Change `guest/guest`

2. **Use GitHub Secrets:**
   - Don't hardcode sensitive credentials
   - Use environment variables or GitHub Secrets

3. **Enable HTTPS:**
   ```yaml
   spring:
     cloud:
       config:
         server:
           git:
             skipSslValidation: false
   ```

4. **Restrict Repository Access:**
   - Make this repo private
   - Use GitHub Personal Access Token for authentication

---

## 📝 File Descriptions

### **application.yml** - Common Configuration

```yaml
spring:
  rabbitmq:
    host: localhost        # RabbitMQ host
    port: 5672             # AMQP port
    username: guest        # RabbitMQ user
    password: guest        # RabbitMQ password
```

### **customer-service.yml** - Customer Service

```yaml
server:
  port: 8063               # Service port

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/customer_db
    username: postgres
    password: root
```

### **recharge-service.yml** - Recharge Service

```yaml
server:
  port: 8064               # Service port

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/recharge_db

rabbitmq:
  exchange: recharge.exchange
  queue: recharge.queue
  routing-key: recharge.routing.key
```

---

## 🆘 Troubleshooting

### Config Server can't find this repository

**Solution:**
- Verify GitHub URL in Config Server application.yml
- Check GitHub credentials (username/token)
- Ensure repository is accessible (public or provide auth)

### Configuration not updating

**Solution:**
```bash
# Refresh Config Server
curl -X POST http://localhost:8060/actuator/bus-refresh

# Restart specific service
docker-compose restart customer-service
```

### Database connection fails

**Solution:**
- Verify database credentials in YAML files
- Ensure PostgreSQL is running
- Check database names match in configuration

---

## 📚 Related Resources

- **Main Project:** https://github.com/SHA-shwatdubey/smartrecharge-system
- **Spring Cloud Config:** https://spring.io/projects/spring-cloud-config
- **GitHub Docs:** https://docs.github.com/

---

## ✨ Summary

✅ Centralized configuration repository  
✅ All microservices configuration in one place  
✅ Easy to update and deploy  
✅ Version controlled with Git  
✅ Production-ready setup  

---

**Last Updated:** March 15, 2026  
**Repository:** https://github.com/SHA-shwatdubey/smartrecharge-config  
**Owner:** SHA-shwatdubey

