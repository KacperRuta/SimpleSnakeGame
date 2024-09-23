# Simple Snake Game

This is a simple implementation of the classic Snake game developed in Java 21 using the Swing framework for the graphical interface. The game is played within a 600x600 pixel window, and the objective is to control the snake, collect points, and avoid collisions with walls or the snake's body.

## Features

- **Grid-based movement**: The snake moves in a fixed direction and can be controlled using the arrow keys (or WASD keys).
- **Food points**: The snake grows when it eats the food (randomly placed red points on the screen), and the player's score increases.
- **Game Over**: The game ends if the snake collides with the walls or its own body, with the final score displayed on the screen.
- **Score system**: Keeps track of the player's score based on the number of points eaten.

## Game Controls

- **Arrow keys (or WASD keys)**: Control the snake's movement.
    - Up / W: Move up
    - Down / S: Move down
    - Left / A: Move left
    - Right / D: Move right

## How to Run

- You can download **JavaSnakeGame.jar** to get the runable version of the game by itself.
- You can also clone or download the repository containing the `SnakeGame`, `Frame`, and `Panel` classes and then compile and run the program, the main class is **SnakeGame**.

## Project Structure

- **SnakeGame.java**: The main entry point of the game. It initializes the game window by creating a **Frame**.
- **Frame.java**: Extends **JFrame** and serves as the main window for the game. It includes the **Panel** where the game logic and rendering occur.
- **Panel.java**: Handles the core gameplay logic, including movement, collision detection, point generation, and the drawing of the snake and game grid. It also manages user input and updates the game state.

## Technologies Used
- **Java 21**: The programming language used for this project.
- **Swing**: Used to create the graphical user interface and manage the game window.
- **AWT**: Used for handling events, drawing, and other lower-level graphics functionalities.
