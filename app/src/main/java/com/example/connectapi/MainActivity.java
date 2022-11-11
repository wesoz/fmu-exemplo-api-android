package com.example.connectapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTest = super.findViewById(R.id.btnTest);
        final TextView txtDraw = super.findViewById(R.id.txtDraw);
        final TextView txtGames = super.findViewById(R.id.txtGames);
        final TextView txtLoose = super.findViewById(R.id.txtLoose);
        final TextView txtWin = super.findViewById(R.id.txtWin);

        final TextView txtTimeA = super.findViewById(R.id.txtTimeA);
        final TextView txtTimeB = super.findViewById(R.id.txtTimeB);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskAPI taskAPI = new TaskAPI(MainActivity.this, txtTimeA.getText().toString(), txtTimeB.getText().toString(),
                new TaskAPI.AsyncResponse() {
                    @Override
                    public void processFinish(GameResult gameResult) {
                        txtDraw.setText(String.valueOf(gameResult.getDraw()));
                        txtGames.setText(String.valueOf(gameResult.getGames()));
                        txtLoose.setText(String.valueOf(gameResult.getLoose()));
                        txtWin.setText(String.valueOf(gameResult.getWin()));
                    }
                });

                taskAPI.execute();
            }
        });
    }
}