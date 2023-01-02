package au.edu.jcu.pipemania;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView {
    private Bitmap bitmap;
    private SurfaceHolder surfaceHolder;
    private final Paint paint = new Paint();
    private float rectWidth;
    private float rectHeight;
    private float rectGap;
    private float progressBarHeight;
    private float appBarHeight;
    private RectF waterRect;
    private RectF levelRect;
    private RectF progressBarRect;
    private RectF backRect;
    private RectF appBarRect;

    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                Canvas canvas = surfaceHolder.lockCanvas(null);
                draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectWidth = getWidth() / 6;
        rectHeight = rectWidth;
        rectGap = rectWidth / 12;
        progressBarHeight = rectHeight / 3;
        appBarHeight = getHeight() / 11;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawBg(canvas);
        drawAppBar(canvas);
        drawRect(canvas);
        drawProgressBar(canvas);
        drawButtons(canvas);
    }

    public void drawBg(Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.bg_blue));
    }

    public void drawAppBar(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.rect_blue));
        appBarRect = new RectF(0, 0, getWidth(), appBarHeight);
        canvas.drawRect(appBarRect, paint);
        paint.setColor(getResources().getColor(R.color.bg_blue));
        backRect = new RectF(rectWidth / 10, rectWidth / 10, appBarHeight - rectHeight / 10, appBarHeight - rectHeight / 10);
        canvas.drawRect(backRect, paint);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_left_arrow);
        canvas.drawBitmap(bitmap, null, backRect, null);
        waterRect = new RectF(getWidth() / 4, rectWidth / 6, getWidth() / 4 + getWidth() / 3, appBarHeight - rectHeight / 6);
        levelRect = new RectF(getWidth() / 4 + getWidth() / 3 + rectWidth / 6, rectWidth / 6, getWidth() / 4 + getWidth() / 3 + rectWidth / 6 + getWidth() / 3, appBarHeight - rectHeight / 6);
        canvas.drawRect(waterRect, paint);
        canvas.drawRect(levelRect, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize((appBarHeight - rectHeight / 3) * 2 / 3);
        canvas.drawText("Water: 2", waterRect.centerX() - paint.getTextSize() - paint.getTextSize() * 3 / 4, waterRect.centerY() + paint.getTextSize() / 3, paint);
        canvas.drawText("Level: 5", levelRect.centerX() - paint.getTextSize() - paint.getTextSize() * 3 / 4, levelRect.centerY() + paint.getTextSize() / 3, paint);
    }

    public void drawRect(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.rect_blue));
        float startX = (getWidth() - 5 * rectWidth - 4 * rectGap) / 2;
        float startY = getHeight() / 14 + appBarHeight;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                canvas.drawRoundRect(startX, startY, startX + rectWidth, startY + rectHeight, rectWidth / 12, rectHeight / 12, paint);
                startX = startX + rectWidth + rectGap;
            }
            startX = (getWidth() - 5 * rectWidth - 4 * rectGap) / 2;
            startY = startY + rectHeight + rectGap;
        }
    }

    public void drawProgressBar(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.bar_blue));
        float left = (getWidth() - 5 * rectWidth - 4 * rectGap) / 2;
        float top = getHeight() / 14 + rectHeight * 6 + rectGap * 5 + progressBarHeight + appBarHeight;
        float right = getWidth() - left;
        float bottom = top + progressBarHeight;
        progressBarRect = new RectF(left, top, right, bottom);
        canvas.drawRoundRect(progressBarRect, progressBarHeight / 2, progressBarHeight / 2, paint);
    }

    public void drawButtons(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.rect_blue));
        float startX = (getWidth() - rectWidth * 14 / 3 ) / 2;
        float startY = progressBarRect.bottom + progressBarHeight;

        for (int i = 0; i < 5; i++) {
            canvas.drawRect(startX, startY, startX + rectWidth * 2 / 3, startY + rectWidth * 2 / 3, paint);
            switch (i) {
                case 0:
                    RectF homeRect = new RectF(startX, startY, startX + rectWidth * 2 / 3, startY + rectWidth * 2 / 3);
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_home);
                    canvas.drawBitmap(bitmap, null, homeRect, null);
                    break;
                case 1:
                    RectF menuRect = new RectF(startX, startY, startX + rectWidth * 2 / 3, startY + rectWidth * 2 / 3);
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu);
                    canvas.drawBitmap(bitmap, null, menuRect, null);
                    break;
                case 2:
                    RectF previousRect = new RectF(startX, startY, startX + rectWidth * 2 / 3, startY + rectWidth * 2 / 3);
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_previous);
                    canvas.drawBitmap(bitmap, null, previousRect, null);
                    paint.setColor(getResources().getColor(R.color.run_green));
                    break;
                case 3:
                    RectF runRect = new RectF(startX, startY, startX + rectWidth * 2 / 3, startY + rectWidth * 2 / 3);
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_double_right);
                    canvas.drawBitmap(bitmap, null, runRect, null);
                    paint.setColor(getResources().getColor(R.color.restart_yellow));
                    break;
                case 4:
                    RectF restartRect = new RectF(startX, startY, startX + rectWidth * 2 / 3, startY + rectWidth * 2 / 3);
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_restart);
                    canvas.drawBitmap(bitmap, null, restartRect, null);
                    break;
            }
            startX += rectWidth;
        }

    }

}
