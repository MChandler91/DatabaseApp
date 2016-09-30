package com.example.chrisbennett.mylistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void viewRecords(View v) {
        Intent intent = new Intent(this,SummaryView.class);
        startActivity(intent);
    }

    protected void addRecord(View v) {

        Intent intent = new Intent(this,AddRecord.class);
        startActivity(intent);
    }

    protected void filterResults(View v) {
        Intent intent = new Intent(this,filter_by_dev.class);
        Bundle bundle = new Bundle();
        String filterText = ((TextView) findViewById(R.id.filterText)).getText().toString();
        bundle.putString("filter text", filterText);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
