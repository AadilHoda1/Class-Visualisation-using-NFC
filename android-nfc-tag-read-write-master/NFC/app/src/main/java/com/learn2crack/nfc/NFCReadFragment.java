package com.learn2crack.nfc;


import android.app.DialogFragment;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class NFCReadFragment extends DialogFragment {

    private Ndef ndef;
    private Handler handler;
    private String s1;
    private DatabaseReference d1;
    private Context x;


    public static final String TAG = NFCReadFragment.class.getSimpleName();

    public static NFCReadFragment newInstance() {

        return new NFCReadFragment();
    }

    private TextView mTvMessage;
    private Listener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        handler = new Handler();
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {

        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MainActivity) context;
        mListener.onDialogDisplayed();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onDialogDismissed();
    }


    public void onNfcDetected(Ndef ndef, DatabaseReference d, String s, Context c) {
        this.ndef = ndef;
        this.s1 = s;
        this.d1 = d;
        this.x=c;
        handler.post(runnableCode);

    }

/*
    private void readFromNFC(Ndef ndef, DatabaseReference d, String s) {

        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            String message = new String(ndefMessage.getRecords()[0].getPayload());

            ArrayList<String> al=new ArrayList<String>();



            String new_message = message.substring(3);
            String[] words=new_message.split("\\s");
            al.add(words[0]);
            al.add(words[1]);
            al.add(words[2]);

            Random r = new Random();
            Integer t = r.nextInt(10) + 1;
            al.add(Integer.toString(t));

            d.child("Tags").child(s).setValue(al);


            Log.d(TAG, "readFromNFC: " + message);
            mTvMessage.setText(message);
            ndef.close();


        } catch (IOException | FormatException e) {
            e.printStackTrace();

        }

    }*/

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {

            if(ndef!=null){
                try{
                    ndef.connect();
                    NdefMessage ndefMessage = ndef.getNdefMessage();
                    String message = new String(ndefMessage.getRecords()[0].getPayload());

                    ArrayList<String> al=new ArrayList<String>();



                    String new_message = message.substring(3);
                    String[] words=new_message.split("\\s");
                    al.add(words[0]);
                    al.add(words[1]);
                    al.add(words[2]);

                    Random r = new Random();
                    Integer t = r.nextInt(10) + 1;
                    al.add(Integer.toString(t));

                    Toast.makeText(x,"Attention : "+ Integer.toString(t) , Toast.LENGTH_LONG).show();

                    d1.child("Tags").child(s1).setValue(al);


                    Log.d(TAG, "readFromNFC: " + message);
                    mTvMessage.setText(message);
                    ndef.close();
                    handler.postDelayed(runnableCode,60000);

                } catch (IOException | FormatException e) {
                   d1.child("Tags").child(s1).setValue(null);
                   Toast.makeText(x,"Phone removed from tag", Toast.LENGTH_LONG).show();
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(x, notification);
                    r.play();
                    e.printStackTrace();
                }

            }

        }
    };
}


