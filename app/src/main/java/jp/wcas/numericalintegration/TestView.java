package jp.wcas.numericalintegration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestView extends View {

    private Paint paint;
    List magnilist = new ArrayList();


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

        int x = -(getWidth()), x_finish = Math.abs(x);
        //int x = -10, x_finish = Math.abs(x);

        float f_x2, f_x, f_real;

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


        Collections.addAll(magnilist, Math.abs(f_x2), Math.abs(f_x), Math.abs(f_real));
        Collections.sort(magnilist);
        Log.d(TAG, magnilist.get(0) + " " + magnilist.get(1) + " " + magnilist.get(2));

        float magni_d = 1;


        Log.d(TAG, "magni check started..");

        if (Math.abs(f_x2) < 1 | Math.abs(f_x) < 1 | Math.abs(f_real) < 1) {
            boolean no_magni = false;
            for (int i = 0; i <= 2; ++i) {
                if ((float) magnilist.get(i) == 0.0) {
                    no_magni = true;
                }
                if (!no_magni) {
                    magni_d = (1 / (float) magnilist.get(i));
                    Log.d(TAG, magni_d + "");
                }
                no_magni = false;
                Log.d(TAG, i + " Finish");
            }

            Log.d(TAG, "magni check finished..");
        }

        if (Math.abs(f_x2) > 1 | Math.abs(f_x) > 1 | Math.abs(f_real) > 1) {
            Collections.reverse(magnilist);
            if ((float) magnilist.get(0) != 0.0) {
                magni_d = (1 / (float) magnilist.get(0));
                Log.d(TAG, magni_d + "");
            }
            Log.d(TAG, " Finish");


            Log.d(TAG, "magni check finished..");
        }

        float x_magni = 50 * magni_d, y_magni = 50 * magni_d;

        float x_previous = (x * x_magni), y_previous = ((f_x2 * x * x) + (f_x * x) + (f_real)) * (-1) * y_magni;

        {
            paint.setColor(Color.RED);
            paint.setStrokeWidth(1);
            paint.setAntiAlias(true);
        }

        for (float n = x; n <= x_finish; n = (float) (n + (0.1))) {
            float y = ((f_x2 * n * n) + (f_x * n) + f_real) * (-1);
            Log.d(TAG, (n) + ":" + (y) + ", magni_d:" + magni_d);
            canvas.drawLine(x_previous, y_previous, (n * x_magni), (y * y_magni), paint);
            x_previous = n * x_magni;
            y_previous = y * y_magni;
        }

        int gridY = (int) (((f_x2 * 1) + (f_x * 1) + (f_real)) * (-1) * y_magni);
        int gridminusY = (int) (((f_x2) + (f_x * -1) + (f_real)) * (-1) * y_magni);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(false);
        canvas.drawLine(x_magni, gridY, x_magni, 0, paint);
        canvas.drawLine(-x_magni, gridminusY, -x_magni, 0, paint);

        paint.setTextSize(30);

        Collections.sort(magnilist);
        if (Math.abs(f_x2) != 0) {

            if (Math.abs(f_x2) == 1) {
                for (int i = -1000; i < 1000; i = i + 5) {
                    canvas.drawText("" + i, x_magni * i, -10, paint);
                }
            } else if (Math.abs(f_x2) < 1) {
                for (int i = -1000; i < 1000; i = i + 5) {
                    canvas.drawText("" + i, x_magni * i, -10, paint);
                }
            } else if (Math.abs(f_x2) > 1 && Math.abs(f_x2) < 10) {
                for (float i = -1000; i < 1000; i = i + 5) {
                    Collections.reverse(magnilist);
                    canvas.drawText("" + ((float) magnilist.get(0) * i), (float) magnilist.get(0) * x_magni * i, -10, paint);
                }
            } else if (Math.abs(f_x2) >= 10) {
                for (float i = -1000; i < 1000; i = i + 5) {
                    Collections.reverse(magnilist);
                    canvas.drawText("" + ((float) magnilist.get(0) * i), (float) magnilist.get(0) * x_magni * i, -10, paint);
                }
            }

        } else if (Math.abs(f_x2) == 0) {
            for (float i = -1000; i < 1000; i = i + 5) {
                Collections.reverse(magnilist);
                canvas.drawText("" + ((float) magnilist.get(0) * i), (float) magnilist.get(0) * x_magni * i, -10, paint);
            }
        }
        Log.d(TAG, "draw finished");
    }


}