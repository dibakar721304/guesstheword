# guesstheword
APIs to play single player word guess game
To run the project locally:
1) In intellij terminal execute 'mvn clean install
2) start the application (run GuessTheWordApplication class)

# API to create a game where computer selects a random word:
http://localhost:8000/games/guesstheword/createNewGame

# API to play the game :
http://localhost:8000/games/guesstheword/playGame/{letter}
e.g : http://localhost:8000/games/guesstheword/playGame/a
