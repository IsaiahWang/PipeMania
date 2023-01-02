package au.edu.jcu.pipemania;

import android.view.SurfaceHolder;

public class GameLoop extends Thread {
    private boolean isRunning = false;
    public GameLoop(GameView gameView, SurfaceHolder surfaceHolder) {

    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();
        while (isRunning) {

        }
    }
}
