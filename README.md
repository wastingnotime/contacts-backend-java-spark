# contacts-backend-java-spark

**contacts-backend-java-spark** is part of "contacts" project that is an initiative where we try to explore frontend and backend implementations in order to better understand it cutting-edge features. This repository presents a java rest API sample.

## stack
* openjdk 18
* [sparkjava](https://sparkjava.com/) 

## features
* simulated database (in memory collection)



## get started (linux instructions only)

### option 1 - use latest docker image from dockerhub

execute the remote docker image
```
docker run -p 8010:8010 wastingnotime/contacts-backend-java-spark
```

### option 2 - build and run a local docker image
build a local docker image
```
docker build --tag contacts-backend-java-spark .
```

execute the local docker image
```
docker run -p 8010:8010 contacts-backend-java-spark
```
### option 3 - execute from source code 

- install jdk 18 (TODO: detail)
- install maven (TODO: detail)
- go to root of solution and execute the commands below

ensure build generated files clean up
```
 mvn clean
```

build and create package
```
 mvn package
```

and then run the application
```
java -jar target/app-jar-with-dependencies.jar 
```

## testing
create a new contact
```
curl --request POST \
  --url http://localhost:8010/contacts \
  --header 'Content-Type: application/json' \
  --data '{
	"firstName": "Albert",
	"lastName": "Einstein",
	"phoneNumber": "2222-1111"
  }'
```

retrieve existing contacts
```
curl --request GET \
  --url http://localhost:8010/contacts
```
more examples and details about requests on (link) *to be defined
