package com.example.administrator.mybezire;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

/**
 *      polygon用来实现一些很厉害的动画
 */
public class MyMatrixView extends View {
    private Matrix matrix;
    private Bitmap bitmap;
    private float[] src, dst;

    public MyMatrixView(Context context) {
        super(context);
        init();
    }

    public MyMatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        matrix = new Matrix();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=2;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bit,options);
        src = new float[]{0, 0,
                bitmap.getWidth(),0,
                bitmap.getWidth(), bitmap.getHeight(),
                0, bitmap.getHeight()};
        /*dst = new float[]{0, 0,
                bitmap.getWidth(),200 ,
                bitmap.getWidth(), bitmap.getHeight()-200,
                0, bitmap.getHeight() };*/
        dst = new float[]{0, 0,
                bitmap.getWidth(),0,
                bitmap.getWidth(), bitmap.getHeight(),
                0, bitmap.getHeight()};

        matrix.setPolyToPoly(src, 0, dst, 0, 3);
        matrix.postScale(0.5f, 0.5f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x= (int) event.getX();
        int y= (int) event.getY();

        float xy[]=new float[]{x,y};
        /*Log.e("yyy","x,y: "+ Arrays.toString(xy));
        matrix.reset();
        matrix.postScale(2f, 2f);
        matrix.mapPoints(xy);
        Log.e("yyy","after x,y: "+ Arrays.toString(xy));*/
        dst[0]=xy[0];
        dst[1]=xy[1];
        matrix.setPolyToPoly(src, 0, dst, 0, 3);
        matrix.postScale(0.5f, 0.5f);



        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, matrix, null);
    }
}
