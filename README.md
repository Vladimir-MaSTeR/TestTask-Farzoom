# TestTask Farzoom - The-image-service
A REST service based on SpringBoot 2 that performs storage functions images in albums

# Libraries
* JDK 1.8
* Maven
* Spring Boot
* H2 Data Base
* Junit

# Execute application
The JAR file is located in the root project.

RUN: java -jar The-image-service-0.0.1-SNAPSHOT.jar

# To See H2 Console in Browser:
  http://localhost:8080/h2-console
  
# API
AlbumController
* POST  /album
* POST /album/{id}
* DELETE /album/{id}
* GET  /album
* GET  /album/{id}

ImageController
* PUT /album/{id}/image
* DELETE /album/{id}/image/{imageId}
* GET /album/{id}/image/{imageId}
