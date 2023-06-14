package org.academiadecodigo.nanderthals;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Menu implements KeyboardHandler {

    private boolean shouldStartGame;
    private boolean keyPressed;
    private Rectangle background;

    public Menu() {
        shouldStartGame = false;
        keyPressed = false;
        background = new Rectangle(10, 10, Game.WIDTH * Game.DIMENSION, Game.HEIGHT * Game.DIMENSION);
        background.setColor(Color.BLACK);

        Keyboard keyboard = new Keyboard(this);
        registerEnterKey(keyboard);
    }

    public void show() {
        background.fill();
    }

    public boolean shouldStartGame() {
        return shouldStartGame;
    }

    private void registerEnterKey(Keyboard keyboard) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_ENTER);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);
    }

    @Override
    public void keyPressed(KeyboardEvent event) {
        int keyCode = event.getKey();

        if (keyCode == KeyboardEvent.KEY_ENTER) {
            shouldStartGame = true;
            keyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent event) {
        // Handle key-released events
    }

    public void waitForEnterKey() {
        while (!keyPressed) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
