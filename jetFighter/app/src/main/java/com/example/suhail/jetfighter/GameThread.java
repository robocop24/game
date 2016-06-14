package com.example.suhail.jetfighter;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by Suhail on 14-06-2016.
 */
public class GameThread extends Thread {
    private GamePlay game;
    private Boolean running = false;

    public GameThread(GamePlay game) {
        this.game = game;
    }

    public void setRunning(boolean run) {
        this.running = run;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        while (running) {
            Canvas c = null;
            try {
                c = game.getHolder().lockCanvas();
                synchronized (game.getHolder()) {
                    game.onDraw(c);

                }
            } finally {
                if (c != null) {
                    game.getHolder().unlockCanvasAndPost(c);
                }
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}