# RAG-assignment

## APIs Description:

### Session Management:

1- Create Session.

2- Mark Session Favourite.

3- Rename Session.

4- Delete Session.

5- List Sessions.

### Message Management:

1- Send Message.

2- Retrieve Messages.

### Used Language:

JDK 17

### Framework:

spring boot 3.4.12



### This service could be simply run locally by:
1- Download docker-compose.yaml

2- Ensure docker daemon installed on your machine 

3- Then use this command `docker-compose up -d`

# Service insights:

### Swagger link:

http://localhost:8081/rag-chat/swagger-ui/index.html#/chat-session-controller

### Service Info:

http://localhost:8081/rag-chat/actuator/info

### Health Check:

http://localhost:8081/rag-chat/actuator/health


### Configuration Files:

See [`application.yml`](src/main/resources/application.yaml)

See [`.env`](src/main/resources/.env)

### Postman Collection:

See [`Postman Collection`](Acksession%20assignment.postman_collection.json)

