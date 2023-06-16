package org.academiadecodigo.nanderthals.test;


import org.academiadecodigo.nanderthals.menu.Menu;

public class SnakeGame {
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.start();
    }

    public void start() {
        Menu menu = new Menu();
        menu.show();
        menu.waitForEnterKey();

        if (menu.shouldStartGame()) {
            Game game = new Game();
            game.start();
        }
    }
}
