package com.example.fragtake2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private TextView mainTextView;
    private final String KEY = "502";
    private static final String retKEY = "502ret";
    private FragmentManager fM = getSupportFragmentManager();
    private String retText;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            retText = intent.getExtras().getString(retKEY);
            if (!retText.equals(""))
                mainTextView.setText(retText);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView = (TextView) findViewById(R.id.mainText);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        registerReceiver(broadcastReceiver, new IntentFilter("Filter"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")) {
                    Frag fragment = Frag.newInstance(KEY, editText.getText().toString());
                    editText.setText("");
                    FragmentTransaction fT = fM.beginTransaction();
                    fT.replace(R.id.fragment, fragment);
                    fT.commit();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver != null)
            unregisterReceiver(broadcastReceiver);
    }
}
