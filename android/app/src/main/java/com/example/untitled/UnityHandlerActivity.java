package com.example.untitled;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayerActivity;

public class UnityHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unity_handler_activity);

        Button buttonClick = findViewById(R.id.buttonClick);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UnityHandlerActivity.this, UnityPlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}