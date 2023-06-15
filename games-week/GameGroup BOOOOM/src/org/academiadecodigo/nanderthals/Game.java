package org.academiadecodigo.nanderthals;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Game implements KeyboardHandler {

    private Snake snake;
    private Food food;
    private SnakeGraphics graphics;
    private boolean gameOver;
    private boolean restart;

    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;
    public static final int DIMENSION = 20;

    public Game() {

        Menu menu = new Menu();
        menu.show();
        menu.waitForEnterKey();
        snake = new Snake(DIMENSION);
        food = new Food(snake);
        graphics = new SnakeGraphics(WIDTH, HEIGHT, DIMENSION);
        gameOver = false;
        restart = false;
        music();

        Keyboard keyboard = new Keyboard(this);
        registerKeys(keyboard);
    }

    public void start() {

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

        while (!restart) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Reiniciar o jogo
        restart();
    }

    private void restart() {
        /*snake = new Snake(DIMENSION);
        food = new Food(snake);
        graphics.show();
        gameOver = false;
        restart = false;
        start();*/
        graphics.clear();

        snake = new Snake(DIMENSION);
        food = new Food(snake);
        graphics = new SnakeGraphics(WIDTH, HEIGHT, DIMENSION);
        gameOver = false;
        restart = false;

        Keyboard keyboard = new Keyboard(this);
        registerKeys(keyboard);
        start();
    }

    public void music() {
        try {
            File music = new File("resources/The Snake Game (original GB music) - audio.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(music);

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
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

        event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_ENTER);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);

    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        int keyCode = event.getKey();

        if (gameOver && keyCode == KeyboardEvent.KEY_ENTER) {
            restart = true;
        } else if (keyCode == KeyboardEvent.KEY_W && snake.getDirection() != Snake.Direction.DOWN) {
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
