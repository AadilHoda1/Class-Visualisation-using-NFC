package com.learn2crack.nfc;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import  android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ClassView extends AppCompatActivity {

    public Integer r;
    public Integer c;
    public Integer width;
    public Integer height;
    public Integer left;
    public Integer top;
    public Integer gaph;
    public Integer gapv;
    public String room;
    public String [][] mobile;
    public Integer [][] att;
    public String Name;
    public String Roll_No;
    MyView draw;
    HorizontalScrollView h_scroll_view;
    ScrollView scrollView;
    private long startClickTime;
    public Float mDownX;
    public Float mDownY;
    public boolean mSwiping;
    private RelativeLayout x;
    private Handler handler;
    private Long n;
    private Long counter;
    private Integer check;




    Thread t = null;
    Thread t1=null;
    Context ct;

//    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();
        setContentView(R.layout.activity_class_view);
        Intent intent = getIntent();


        String message = intent.getStringExtra(ProfActivity.EXTRA_MESSAGE1);
        String[] words = message.split("\\s");
        r = Integer.parseInt(words[0]);
        c = Integer.parseInt(words[1]);
        room = words[2];
        ct=this;
        check=0;

        handler.post(repeat);






    }

    private Runnable repeat = new Runnable() {
        @Override
        public void run() {

            setContentView(R.layout.activity_class_view);
            TextView textView = (TextView) findViewById(R.id.textView2);
            textView.setText("Preparing Class View.....");

            ProgressBar spinner;
            spinner = (ProgressBar)findViewById(R.id.progressBar1);
            spinner.setVisibility(View.GONE);
            spinner.setVisibility(View.VISIBLE);

            if(check!=0)
                Toast.makeText(ct,"Class view refreshed!", Toast.LENGTH_LONG).show();
            else
                check=check+1;




            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;

            mobile  = new String[r][c];
            att  = new Integer[r][c];

            Integer a;
            Integer b;

            for(a=0;a<r;a++)
            {
                for(b=0;b<c;b++)
                {
                    att[a][b]=-1;
                    mobile[a][b]="";
                }
            }


            FirebaseDatabase db2 = FirebaseDatabase.getInstance();
            DatabaseReference refer = db2.getReference();
            DatabaseReference testref = refer.child("Tags");

            counter=0L;


            testref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    n=dataSnapshot.getChildrenCount();


                    if (dataSnapshot.exists()) {
                        for(DataSnapshot snap : dataSnapshot.getChildren()){
                            counter+=1;
                            if(snap.child("0").getValue().toString().equals(room))
                            {
                                Log.e("po","Counter");
                                Integer r1 = Integer.parseInt(snap.child("1").getValue().toString());
                                Integer c1 = Integer.parseInt(snap.child("2").getValue().toString());
                                Integer at = Integer.parseInt(snap.child("3").getValue().toString());

                                att[r1-1][c1-1]=at;
                                mobile[r1-1][c1-1]=snap.getKey().toString();

                            }

                            if(counter==n)
                                doafter2();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {


                }


            });



            handler.postDelayed(repeat, 60000);
        }
    };

    public void doafter2(){
        draw = new MyView(ct);
        scrollView = new ScrollView(ct);

        h_scroll_view = new HorizontalScrollView(ct);


        h_scroll_view.addView(draw);
        scrollView.addView(h_scroll_view);

        setContentView(scrollView);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {



                if (event.getAction()==MotionEvent.ACTION_DOWN)
                    startClickTime = System.currentTimeMillis();
                else if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (System.currentTimeMillis() - startClickTime < ViewConfiguration.getTapTimeout()) {

                        // Touch was a simple tap. Do whatever.

                        Float x1 = event.getX();
                        Float y1 = event.getY();

                        Integer col = -1,z,lc,rc;
                        for(z=1;z<=c;z++)
                        {
                            lc = 30 + (150+gaph)*(z-1);
                            rc = lc + 150;

                            if(x1>=lc && x1<=rc)
                            {
                                col = z;
                                break;
                            }

                        }

                        Integer row = -1,y,ur,dr;
                        for(y=1;y<=r;y++)
                        {

                            ur = 62 + (150+gapv)*(y-1);
                            dr = ur + 150;

                            if(y1>=ur && y1<=dr)
                            {
                                row = y;
                                break;
                            }
                        }



                        if(row!=-1 && col!=-1 && !mobile[r-row][col-1].equals("")) {

                            Toast.makeText(ct,"Processing Student's Info......", Toast.LENGTH_LONG).show();
                            FirebaseDatabase db3 = FirebaseDatabase.getInstance();
                            DatabaseReference refer1 = db3.getReference();
                            DatabaseReference testref1 = refer1.child("Student").child(mobile[r-row][col-1]);
                            testref1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {


                                    if (dataSnapshot.exists()) {

                                        Name = dataSnapshot.child("0").getValue().toString();
                                        Roll_No = dataSnapshot.child("1").getValue().toString();
                                        doafter1();



                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {


                                }
                            });



                        }
                        else
                            Toast.makeText(ct,"Invalid Touch", Toast.LENGTH_LONG).show();


                    } else {

                        // Touch was a not a simple tap
                        Toast.makeText(ct,"Scrolling", Toast.LENGTH_LONG).show();

                    }

                }





                return false;
            }
        });

        h_scroll_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction()==MotionEvent.ACTION_DOWN)
                    startClickTime = System.currentTimeMillis();
                else if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if (System.currentTimeMillis() - startClickTime < ViewConfiguration.getTapTimeout()) {

                        // Touch was a simple tap. Do whatever.
                        Float x1 = event.getX();
                        Float y1 = event.getY();

                        Integer col = -1,z,lc,rc;
                        for(z=1;z<=c;z++)
                        {
                            lc = 30 + (150+gaph)*(z-1);
                            rc = lc + 150;

                            if(x1>=lc && x1<=rc)
                            {
                                col = z;
                                break;
                            }

                        }

                        Integer row = -1,y,ur,dr;
                        for(y=1;y<=r;y++)
                        {

                            ur = 62 + (150+gapv)*(y-1);
                            dr = ur + 150;

                            if(y1>=ur && y1<=dr)
                            {
                                row = y;
                                break;
                            }
                        }




                        if(row!=-1 && col!=-1 && !mobile[r-row][col-1].equals("")) {

                            Toast.makeText(ct,"Processing Student's Info......", Toast.LENGTH_LONG).show();
                            FirebaseDatabase db3 = FirebaseDatabase.getInstance();
                            DatabaseReference refer1 = db3.getReference();
                            DatabaseReference testref1 = refer1.child("Student").child(mobile[r-row][col-1]);
                            testref1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {


                                    if (dataSnapshot.exists()) {

                                        Name = dataSnapshot.child("0").getValue().toString();
                                        Roll_No = dataSnapshot.child("1").getValue().toString();



                                    }
                                    doafter1();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {


                                }
                            });




                        }
                        else
                            Toast.makeText(ct,"Invalid Touch", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(ct,"Scrolling", Toast.LENGTH_LONG).show();
                        // Touch was a not a simple tap.

                    }

                }

                return false;
            }
        });





    }

    public void doafter1(){
        Intent in = new  Intent(ClassView.this,Pop.class);
        in.putExtra("info",Name+" "+Roll_No);
        startActivity(in);

    }




    public class MyView extends View {

        Paint paint;
        Path path;

        public MyView(Context context) {
            super(context);
            init();
        }

        public MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MyView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            init();
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // Compute the height required to render the view
            // Assume Width will always be MATCH_PARENT.
            int width = 150*c + 60 + 30*(c-1);
            int height = 150*r + 60 + 30*(r-1);
            setMeasuredDimension(width, height);
        }

        private void init(){
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub


            // add marker
            Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_name);
            Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.red_cross);
            Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.tick);

            super.onDraw(canvas);

            Integer i;
            Integer j;

//            if(c!=1)
//            gaph = (width-60-100*c)/(c-1);
//            else
//            gaph=30;
//
//            if(r!=1)
//                gapv = (height-460-100*r)/(r-1);
//            else
//                gapv=30;


            gaph = 30;
            gapv = 30;




            left=30;
            top=30;
            for(j=0;j<r;j++) {
                for (i = 0; i < c; i++) {
                    canvas.drawRect(left, top, left + 150, top + 150, paint);

                    Log.e("po",Integer.toString(att[r-j-1][i]));

                    if(att[r-j-1][i]>=1 && att[r-j-1][i]<=4)
                    {canvas.drawBitmap(bmp2,left+30,top+30, null);}
                    else if(att[r-j-1][i]>=5 && att[r-j-1][i]<=7)
                    {canvas.drawBitmap(bmp3,left+30,top+30, null);}
                    else if(att[r-j-1][i]>=8 && att[r-j-1][i]<=10)
                    {canvas.drawBitmap(bmp1,left+30,top+30, null);}


                    left = left + 150+gaph;//drawRect(left, top, right, bottom, paint)
                }
                top = top +150+gapv;
                left=30;
            }

        }

    }
}
