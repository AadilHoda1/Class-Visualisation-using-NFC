package com.learn2crack.nfc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by shaurya98 on 27/3/18.
 */

public class Pop extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);

        Intent t = getIntent();
        String s = t.getStringExtra("info");

        TextView textView = (TextView) findViewById(R.id.textView4);
        textView.setText(s);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.3));
        getWindow().setBackgroundDrawable(new ColorDrawable(0xff3F51B5));

    }
}
