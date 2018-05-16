package com.example.madcat.androidlivedataexample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button addButton;
    EditText newText;
    EditText viewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        newText = findViewById(R.id.newText);
        viewText = findViewById(R.id.viewText);

        addButton.setOnClickListener(this);

        LiveData<String> liveData = DataController.getInstance().getData();

        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String value) {
                StringBuilder buffer = new StringBuilder();

                buffer.append(viewText.getText().toString());
                buffer.append("\n").append(value);

                viewText.setText(buffer.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        DataController.getInstance().addData(newText.getText().toString());
        newText.setText("");
    }
}
