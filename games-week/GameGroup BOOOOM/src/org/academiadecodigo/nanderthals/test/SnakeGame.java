package org.academiadecodigo.nanderthals.test;

import org.academiadecodigo.nanderthals.menu.Menu;


public class SnakeGame {
    public static void main(String[] args) {
        // O menu "crasha" a build, aqui só devemos invocar o game.start

        Menu menu = new Menu();
        menu.show();
        menu.waitForEnterKey();
        if (menu.shouldStartGame()) {
            Game game = new Game();
            game.start();
        }
    }
}
