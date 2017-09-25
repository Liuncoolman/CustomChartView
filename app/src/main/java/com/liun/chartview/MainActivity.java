package com.liun.chartview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liun.chartview.view.ChartView;

/**
 * https://github.com/SunnyLy/muke  参照慕课网制作
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ChartView charView = (ChartView) findViewById(R.id.chart_view);
        charView.setAxisDivideSizeX(7);
        charView.setAxisDivideSizeY(8);
        charView.setAxisScaleMarkValueX(new String[]{"1", "2", "3", "4", "5", "6"});
        charView.setAxisScaleMarkValueY(new String[]{"1000", "2000", "3000", "4000", "5000", "6000", "7000"});
        charView.setColumnInfo(new int[][]{
                {3500, getResources().getColor(android.R.color.holo_green_dark)},
                {1500, getResources().getColor(android.R.color.holo_red_dark)},
                {4000, getResources().getColor(android.R.color.holo_green_dark)},
                {5000, getResources().getColor(android.R.color.holo_green_light)},
                {2500, getResources().getColor(android.R.color.holo_orange_dark)},
                {3000, getResources().getColor(android.R.color.holo_orange_light)}
        });
    }
}
