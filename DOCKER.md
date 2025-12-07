# Docker Setup Guide

This guide explains how to build and run the Package Booking System using Docker.

## Prerequisites

- Docker (version 20.10 or higher)
- Docker Compose (version 2.0 or higher)

## Quick Start

### Using Docker Compose (Recommended)

1. **Build and start all services:**
   ```bash
   docker-compose up -d
   ```

2. **View logs:**
   ```bash
   docker-compose logs -f app
   ```

3. **Stop all services:**
   ```bash
   docker-compose down
   ```

4. **Stop and remove volumes:**
   ```bash
   docker-compose down -v
   ```

### Using Dockerfile Only

1. **Build the image:**
   ```bash
   docker build -t packagebooking:latest .
   ```

2. **Run the container:**
   ```bash
   docker run -d \
     --name packagebooking-app \
     -p 9001:9001 \
     -e SPRING_PROFILES_ACTIVE=prod \
     -e DB_HOST=your-mysql-host \
     -e DB_PORT=3306 \
     -e DB_NAME=package_booking_system_db \
     -e DB_USERNAME=root \
     -e DB_PASSWORD=mysql \
     packagebooking:latest
   ```

## Environment Variables

The application uses the following environment variables (with defaults):

- `SPRING_PROFILES_ACTIVE=prod` - Spring profile (automatically set to prod in Docker)
- `DB_HOST=mysql` - Database host
- `DB_PORT=3306` - Database port
- `DB_NAME=package_booking_system_db` - Database name
- `DB_USERNAME=root` - Database username
- `DB_PASSWORD=mysql` - Database password
- `SERVER_PORT=9001` - Application server port

## Production Profile

When running in Docker, the application automatically uses the `prod` profile (`application-prod.properties`), which includes:

- Database validation mode (no auto-create)
- Production logging levels
- SSL enabled for database connections
- Optimized JPA settings

## Accessing the Application

- **API Base URL:** http://localhost:9001/api
- **Swagger UI:** http://localhost:9001/swagger-ui.html
- **API Docs:** http://localhost:9001/api-docs

## Troubleshooting

### Check container status:
```bash
docker ps
```

### View application logs:
```bash
docker logs packagebooking-app
```

### Access container shell:
```bash
docker exec -it packagebooking-app sh
```

### Rebuild after code changes:
```bash
docker-compose build --no-cache app
docker-compose up -d
```

