docker-compose down

# build backend image
docker build -t backend-pagnet:latest ./backend

# build frontend image
docker build -t frontend-pagnet:latest ./frontend

# start environment
docker-compose up --build --force-recreate --remove-orphans


# postgresql://celsoaquino:aEgc5X3lWetD@ep-lingering-paper-48854976.us-east-2.aws.neon.tech/pagnet-db?sslmode=require
