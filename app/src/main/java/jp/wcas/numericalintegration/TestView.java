package jp.wcas.numericalintegration;

/**
 * Created by strea on 2016/07/16.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntegerRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.text.SpannableStringBuilder;

public class TestView extends View {

    Paint paint;


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
        canvas.drawColor(Color.argb(0, 255, 255, 255));

        paint.setStrokeWidth(1);
        paint.setColor(Color.GRAY);

        final String TAG = "TestView";

        // x軸を描く
        canvas.drawLine(0, -y0, 0, y0, paint);
        Log.d(TAG, "X drawn");
        // y軸を描く
        canvas.drawLine(-x0, 0, x0, 0, paint);
        Log.d(TAG, "Y drawn");

        int x = -20, x_finish = Math.abs(x);
        int x_magni = 2, y_magni = 1;

        int f_x2 = Integer.parseInt((MainActivity.x2).toString());
        int f_x = Integer.parseInt((MainActivity.x).toString());
        int f_real = Integer.parseInt(MainActivity.real.toString());



        int x_previous = x * x_magni, y_previous = ((x*x)) * (-1) * y_magni;
        paint = new Paint();

        {
            paint.setColor(Color.RED);
            paint.setStrokeWidth(1);
            paint.setAntiAlias(true);
        }

        for (int n = x; n <= x_finish; n++) {
            int y = ((n * n)) * (-1);
            Log.d(TAG, (n) + ":" + (y));
            canvas.drawLine(x_previous, y_previous, (n * x_magni), (y * y_magni), paint);
            x_previous = n * x_magni;
            y_previous = y * y_magni;
        }


    }


}