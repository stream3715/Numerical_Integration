package jp.wcas.numericalintegration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

public class TestView extends View {

    private Paint paint;


    public TestView(Context context, AttributeSet attrs) {

        super(context, attrs);
        paint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int x0 = getWidth() / 2;
        final int y0 = getHeight() / 2;
        // 原点変更
        canvas.translate(x0, y0);
        // 背景
        canvas.drawColor(Color.WHITE);

        paint.setStrokeWidth(1);
        paint.setColor(Color.GRAY);

        final String TAG = "TestView";

        // x軸を描く
        canvas.drawLine(0, -y0, 0, y0, paint);
        Log.d(TAG, "X drawn");
        // y軸を描く
        canvas.drawLine(-x0, 0, x0, 0, paint);
        Log.d(TAG, "Y drawn");

        int x = -(10), x_finish = Math.abs(x);


        double f_x2, f_x, f_real;

        try {
            f_x2 = MainActivity.x2;
        } catch (NullPointerException e) {
            f_x2 = 0;
        }

        try {
            f_x = MainActivity.x;
        } catch (NullPointerException e) {
            f_x = 0;
        }

        try {
            f_real = MainActivity.real;
        } catch (NullPointerException e) {
            f_real = 0;
        }

        double[] checkmagni = {Math.abs(f_x2), Math.abs(f_x), Math.abs(f_real)};
        Arrays.sort(checkmagni);
        Log.d(TAG, checkmagni[0] + " " + checkmagni[1] + " " + checkmagni[2]);

        int magni = 1;
        double magni_d = 1;
        boolean magni0 = false;

        Log.d(TAG, "magni check started..");

        for (int i = 0; i <= 2; ++i) {
            if (checkmagni[i] == 0.0) {
                magni0 = true;
            }
            if (!magni0) {
                magni = (int) (1 / checkmagni[i]);
                magni_d = (1 / checkmagni[i]);
                Log.d(TAG, magni_d + "");
            }
            magni0 = false;
            Log.d(TAG, i + " Finish");
        }

        Log.d(TAG, "magni check finished..");

        int x_magni = magni, y_magni = magni;

        double x_previous = (x * x_magni), y_previous = ((f_x2 * x * x) + (f_x * x) + (f_real)) * (-1) * y_magni;
        paint = new Paint();

        {
            paint.setColor(Color.RED);
            paint.setStrokeWidth(1);
            paint.setAntiAlias(true);
        }

        for (int n = x; n <= x_finish; n++) {
            double y = ((f_x2 * n * n) + (f_x * n) + f_real) * (-1);
            Log.d(TAG, (n) + ":" + (y) + ", magni:" + magni + ", magni_d:" + magni_d);
            canvas.drawLine((int) x_previous, (int) y_previous, n * x_magni, (int) (y * y_magni), paint);
            x_previous = n * x_magni;
            y_previous = y * y_magni;
        }

        int gridX = x_magni;
        int gridY = (int) (((f_x2 * 1) + (f_x * 1) + (f_real)) * (-1) * y_magni);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(false);
        canvas.drawLine(gridX, gridY, gridX, 0, paint);
        canvas.drawLine(-gridX, gridY, -gridX, 0, paint);
        canvas.drawLine(gridX, gridY, -gridX, gridY, paint);

        paint.setTextSize(30);
        for (int i = 0; i < 1000; i = i + 2) {
            canvas.drawText("" + i, gridX * i, -10, paint);
        }

        for (int i = -2; i > -1000; i = i - 2) {
            canvas.drawText("" + i, gridX * i, -10, paint);
        }
    }
}