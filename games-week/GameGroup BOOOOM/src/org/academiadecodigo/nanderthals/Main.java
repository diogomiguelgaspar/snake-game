package org.academiadecodigo.nanderthals;

public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.show();
        menu.waitForEnterKey();
        if (menu.shouldStartGame()) {
            menu.clear();
            Game game = new Game();
            game.start();
        }
    }
}
