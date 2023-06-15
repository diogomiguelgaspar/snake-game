package org.academiadecodigo.nanderthals.food;

import org.academiadecodigo.nanderthals.test.Game;
import org.academiadecodigo.nanderthals.snake.Snake;
import org.academiadecodigo.nanderthals.snake.SnakeCell;

import java.util.Random;

public class Food {

    private SnakeCell position;
    private Snake snake;

    public Food(Snake snake) {
        this.snake = snake;
        position = generatePosition();
    }

    public void respawn() {
        position = generatePosition();
    }

    public SnakeCell getPosition() {
        return position;
    }

    private SnakeCell generatePosition() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(Game.WIDTH);
            y = random.nextInt(Game.HEIGHT);
        } while (snake.getBody().contains(new SnakeCell(x, y)) || isOutOfBounds(x, y));
        return new SnakeCell(x, y);
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= Game.WIDTH || y < 0 || y >= Game.HEIGHT;
    }

}
