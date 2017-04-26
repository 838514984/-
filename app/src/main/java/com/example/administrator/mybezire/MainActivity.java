package com.example.administrator.mybezire;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        float xx[]=new float[]{0,0,100,100};
        Log.e("Xxx","before scale: "+ Arrays.toString(xx));
        Matrix matrix=new Matrix();
        matrix.setTranslate(100,0);
        matrix.mapPoints(xx);
        Log.e("Xxx","after scale: "+ Arrays.toString(xx));
    }
}
