package com.akiva.gameloop;

import com.akiva.graphics.GameLoopDrawable;
import com.akiva.logic.GameLoopUpdatable;

public class GameLoop {

    private static final double FPS = 60;
    private static final int NANO_SECONDS_IN_SECOND = 1000000000;
    private static final double TIME_PER_UPDATE = NANO_SECONDS_IN_SECOND / FPS;

    private final GameLoopDrawable drawable;
    private final GameLoopUpdatable updatable;
    private boolean runGameLoop;

    public GameLoop(GameLoopDrawable drawable, GameLoopUpdatable updatable) {
        this.runGameLoop = false;
        this.drawable = drawable;
        this.updatable = updatable;
    }

    public void run() {
        double timeFromLastRender = 0;
        long lastRenderTime = System.nanoTime();
        long currentTime;
        int renderCount = 0;
        this.runGameLoop = true;
        while (this.runGameLoop) {
            currentTime = System.nanoTime();
            timeFromLastRender += (currentTime - lastRenderTime) / TIME_PER_UPDATE;
            lastRenderTime = currentTime;
            if (timeFromLastRender >= 1) {
                this.drawable.render();
                renderCount++;
                timeFromLastRender--;
            }
            if (renderCount >= FPS) {
                this.updatable.updateEachSecond();
                renderCount = 0;
            }
        }
    }

    public void stop(){
        this.runGameLoop = false;
    }

}
