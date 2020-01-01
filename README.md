# Azul

## Dependencies
- Maven
- JUnit
- Java >= 8

## Compilation
In the directory with the `pom.xml` type
`mvn clean compile test assembly:single && cp target/azul-1.0-SNAPSHOT-jar-with-dependencies.jar ./azul.jar`


## Execution
type `java -jar azul.jar`


## Usage
This application allows to play azul up to 4 players on the same computer.
After executing the jar, you are prompted for the number of player.
You must then enter each player's name. 
At the end, you have to choose between the Console display mode and the graphic display mode.  
