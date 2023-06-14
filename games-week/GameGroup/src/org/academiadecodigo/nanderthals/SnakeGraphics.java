package org.academiadecodigo.nanderthals;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class SnakeGraphics {

    private Rectangle[][] grid;

    public SnakeGraphics(int width, int height, int dimension) {
        grid = new Rectangle[width][height];
        createGrid(dimension);
    }

    public void createWindow(int windowWidth, int windowHeight) {
        Rectangle window = new Rectangle(0, 0, windowWidth, windowHeight);
        window.setColor(Color.BLACK);
        window.fill();
    }

    public void show() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].draw();
            }
        }
    }

    public void repaint(Snake snake, Food food) {
        clear();

        // Draw snake body
        for (SnakeCell cell : snake.getBody()) {
            int x = cell.getX();
            int y = cell.getY();
            grid[x][y].setColor(Color.GREEN);
            grid[x][y].fill();
        }

        // Draw food
        SnakeCell foodPosition = food.getPosition();
        int x = foodPosition.getX();
        int y = foodPosition.getY();
        grid[x][y].setColor(Color.RED);
        grid[x][y].fill();
    }

    private void clear() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setColor(Color.BLACK);
                grid[i][j].fill();
            }
        }
    }

    private void createGrid(int dimension) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int x = i * dimension;
                int y = j * dimension;
                grid[i][j] = new Rectangle(x, y, dimension, dimension);
                grid[i][j].setColor(Color.BLACK);
            }
        }
    }
}
