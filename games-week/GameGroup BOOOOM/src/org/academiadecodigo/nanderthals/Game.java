package org.academiadecodigo.nanderthals;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Game implements KeyboardHandler {

    private Snake snake;
    private Food food;
    private SnakeGraphics graphics;
    private boolean gameOver;

    public static final int WIDTH = 50;
    public static final int HEIGHT = 30;
    public static final int DIMENSION = 15;

    public Game() {
        snake = new Snake(DIMENSION);
        food = new Food(snake);
        graphics = new SnakeGraphics(WIDTH, HEIGHT, DIMENSION);
        gameOver = false;

        Keyboard keyboard = new Keyboard(this);
        registerKeys(keyboard);
    }

    public void start() {
        graphics.createWindow(WIDTH * DIMENSION, HEIGHT * DIMENSION);
        graphics.show();

        while (!gameOver) {
            snake.move();

            if (snake.collidesWithBody() || snake.isOutOfBounds()) {
                gameOver = true;
                System.out.println("Game Over");
                break;
            }

            graphics.repaint(snake, food);

            if (snake.collidesWithFood(food)) {
                food.respawn();
                snake.grow();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void registerKeys(Keyboard keyboard) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_W);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);

        event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_S);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);

        event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_A);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);

        event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_D);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);


    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        int keyCode = event.getKey();

        if (keyCode == KeyboardEvent.KEY_W && snake.getDirection() != Snake.Direction.DOWN) {
            snake.setDirection(Snake.Direction.UP);
        } else if (keyCode == KeyboardEvent.KEY_S && snake.getDirection() != Snake.Direction.UP) {
            snake.setDirection(Snake.Direction.DOWN);
        } else if (keyCode == KeyboardEvent.KEY_A && snake.getDirection() != Snake.Direction.RIGHT) {
            snake.setDirection(Snake.Direction.LEFT);
        } else if (keyCode == KeyboardEvent.KEY_D && snake.getDirection() != Snake.Direction.LEFT) {
            snake.setDirection(Snake.Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        // Handle key-released events
    }
}
