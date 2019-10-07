# POC to write a Jenkins pipeline for a Java app in Groovy
## to do

* Write a unit test
* Write an integration test
* Start building pipeline in groovy

## to run the database in a docker container

We mount it to the local drive `~/learn/db/db-dev/`.
We set an environment variable for the password
We want to run the database in a non-standard ports 5433 (for dev) and 5434 (for stg).
```bash
docker pull postgres
docker run --rm --name pg-dev -e POSTGRES_PASSWORD=docker -d -p 5433:5432 -v $HOME/learn/db/dev:/var/lib/postgresql/data -e POSTGRES_PASSWORD=docker postgres
docker run --rm --name pg-stg -e POSTGRES_PASSWORD=docker -d -p 5434:5432 -v $HOME/learn/db/stg:/var/lib/postgresql/data -e POSTGRES_PASSWORD=docker postgres
# to log into the psql console
'/Applications/Postgres.app/Contents/Versions/9.3/bin'/psql -h localhost -p5433 -Upostgres
```

## script to create the database
```psql
CREATE DATABASE persons;
\c persons
CREATE TABLE Person (id serial primary key, first_name varchar(255), last_name varchar(255));
```

## create person
```bash
curl -X POST -d '{"firstName": "Allison", "lastName": "DM" }' -H "Content-type: application/json" http://localhost:8080/persons
curl http://localhost:8080/persons
```