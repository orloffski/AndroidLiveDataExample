package com.example.madcat.androidlivedataexample;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
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

        LiveData<User> userLiveData = Transformations.switchMap(liveData, new Function<String, LiveData<User>>() {
            @Override
            public LiveData<User> apply(String input) {
                UserDataController.getInstance().addData(input);

                return UserDataController.getInstance().getData();
            }
        });

        userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                StringBuilder buffer = new StringBuilder();

                buffer.append(viewText.getText().toString());
                buffer.append("user id: ").append(user.getUserId()).append(" user name: ").append(user.getName()).append("\n");

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
