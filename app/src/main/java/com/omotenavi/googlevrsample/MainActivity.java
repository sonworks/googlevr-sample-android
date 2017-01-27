package com.omotenavi.googlevrsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout cardLinear = (LinearLayout) findViewById(R.id.cardLinear);
        cardLinear.removeAllViews();

        final int MAX_CNT = 3;
        for (int i = 0; i < MAX_CNT; i++) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.custom_card_view, null);
            CardView cardView = (CardView) linearLayout.findViewById(R.id.cardView);
            TextView textBox = (TextView) linearLayout.findViewById(R.id.textBox);
            textBox.setText("Panorama View " + i);
            cardView.setTag(i);
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Integer tagId = Integer.valueOf(view.getTag().toString());
                    if(tagId >= 0) {
                        Intent intent = new Intent(MainActivity.this, VrPanoramaActivity.class);
                        intent.putExtra("tagId", tagId);
                        startActivity(intent);
                    }
                }
            });
            cardLinear.addView(linearLayout, i);
        }
    }
}
