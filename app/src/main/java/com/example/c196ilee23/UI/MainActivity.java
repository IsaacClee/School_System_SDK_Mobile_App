package com.example.c196ilee23.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196ilee23.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void EnterHere(View view){
        Intent intent= new Intent(MainActivity.this, ProductList.class );
        startActivity(intent);
    }

}