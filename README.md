# Restaraunt Review System
Created by Ben Diliberto, Charlotte Norford and Marc Schroder.

### Docker Compose
We are using Docker for starting up and instance of CockroachDB 
locally for development purposes. To use this locally, you need to:

1. Install Docker Desktop (https://www.docker.com/products/docker-desktop)
2. Start Docket Desktop
3. Navigate to the /docker folder in the root directory of the project in terminal
4. Run the command `docker-compose up --build --remove-orphans`

This should start a single node of CockroachDB that can be used for development.

Add the jdk17, start from restaurant-review.main 
Don't put a forward slash by user - that is automatically appended