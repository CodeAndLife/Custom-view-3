package com.example.android.customviewp2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LineChartView lineChart = (LineChartView) findViewById(R.id.linechart);
        lineChart.setChartData(getWalkingData());

        findViewById(R.id.walking_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChart.setChartData(getWalkingData());
            }
        });
        findViewById(R.id.running_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChart.setChartData(getRuningData());
            }
        });
        findViewById(R.id.cycling_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChart.setChartData(getCyclingData());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private float[] getWalkingData() {
        return new float[] { 10, 12, 7, 14, 15, 19, 13, 2, 10, 13, 13, 10, 15, 14 };
    }

    private float[] getRuningData() {
        return new float[] { 22, 14, 20, 25, 32, 27, 26, 21, 19, 26, 24, 30, 29, 19 };
    }

    private float[] getCyclingData() {
        return new float[] { 0, 0, 0, 10, 14, 23, 40, 35, 32, 37, 41, 32, 18, 39 };
    }
}
