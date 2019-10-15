package com.example.fragtake2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag extends Fragment {
    private TextView textView;
    private static String text="";
    private static String retText;
    private Button retButton;
    private static final String KEY = "502";
    private static final String retKEY = "502ret";
    private Bundle args;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            args = getArguments();
            text = args.getString(KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        textView = (TextView) view.findViewById(R.id.output);
        retButton = (Button) view.findViewById(R.id.retButton);
        textView.setText(text);
        retButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcast(v);
            }
        });
        return view;
    }

    private static void broadcast(View v) {
        retText = text.toUpperCase();
        Intent i = new Intent("Filter");
        i.putExtra(retKEY, retText);
        v.getContext().sendBroadcast(i);
    }

    static Frag newInstance(String key, String s) {
        Bundle args = new Bundle();
        args.putString(key, s);
        Frag newFrag = new Frag();
        newFrag.setArguments(args);
        return newFrag;
    }
}
