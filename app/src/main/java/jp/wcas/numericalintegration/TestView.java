package jp.wcas.numericalintegration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {

    private Paint paint;

    public TestView(Context context, AttributeSet attrs) {

        super(context, attrs);
        paint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int x0 = getWidth() / 2;
        int y0 = getHeight() / 2;
        // 原点変更
        canvas.translate(x0, y0);
        // 背景
        canvas.drawColor(Color.WHITE);

        paint.setStrokeWidth(1);
        paint.setColor(Color.GRAY);

        // x軸を描く
        canvas.drawLine(0, -y0, 0, y0, paint);
        // y軸を描く
        canvas.drawLine(-x0, 0, x0, 0, paint);

        int x = -(getWidth()), x_finish = getWidth();

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


        float x_previous = x, y_previous = ((f_x2 * x * x) + (f_x * x) + (f_real)) * (-1);

        {
            paint.setColor(Color.RED);
            paint.setStrokeWidth(1);
            paint.setAntiAlias(true);
        }

        for (float n = x; n <= x_finish; n = (n + (0.25F))) {
            float y = ((f_x2 * n * n) + (f_x * n) + f_real) * (-1);
            canvas.drawLine(x_previous, y_previous, n, y, paint);
            x_previous = n;
            y_previous = y;
        }

        float gridY = (((f_x2 * 1) + (f_x * 1) + (f_real)) * (-1));
        float gridminusY = ((f_x2) + (f_x * -1) + (f_real)) * (-1);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(false);
        canvas.drawLine(10, gridY, 10, 0, paint);
        canvas.drawLine(-10, gridminusY, -10, 0, paint);

    }
}