# Joe's Bowling Game Kata

This small project is an exploration of the best way to test an application.

### Kata Stories

1. Player can enter entire score of their game at once
    * Game ends immediately and displays final score
    * Can only enter a valid score from 0 - 300
1. Player can enter score per frame up to 10 frames
    * 10 rounds, displays final score once 10 frames finished
    * Can only enter valid number of points per frame
    * Does not include strikes, spares, or bonus bowls
1. Player can enter the points per bowl (2 per frame)
    * Can only enter valid number of points per bowl, specifically second bowl
    * Does not include strikes, spares, or bonus bowls
1. Player can record strikes and spares
    * Score of current frame is deferred until bonus is calculated when scoring a strike or spare
    * Spare Bonus = number of pins knocked down in next bowl
    * Strike Bonus = number of pins knocked down in next two bowls
    * Does not include extra frames when scoring a strike or spare in 10th frame
1. Player can record strikes and spares in 10th frame
    * Strike = two extra bowls
    * Spare = one extra bowl
    * Score for this frame is total number of pins knocked down

#### Assumptions:

* Users will only enter valid integers
* Users will only play up to the end of the 10th frame

### How to run the tests

* Execute `./gradlew test` to run all tests
* Hit `Ctrl + Shift + R` on any test file in Intellij to run a specifc test

### How to run the application

1. Execute `./gradlew build`
1. Execute `java -jar build/libs/bowling-game-kata-0.0.1-SNAPSHOT.jar`
1. Execute `help` from within the shell to see available commands and descriptions
