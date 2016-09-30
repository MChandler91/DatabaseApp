package com.example.chrisbennett.mylistview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Admin on 9/27/2016.
 */

public class filter_by_dev extends AppCompatActivity{
    ReviewDBHelper mDbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_by_dev);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String filterText = bundle.getString("filter text");

        mDbHelper = new ReviewDBHelper(this);
        db = mDbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "  + ReviewSchema.Review.TABLE_NAME + " WHERE " + ReviewSchema.Review.COLUMN_NAME_DEVELOPER + " = '" + filterText + "'", null);
        ReviewCursorAdapter adapter = new ReviewCursorAdapter(this,c);

        ListView listview = (ListView) findViewById(R.id.filterByDevView);

        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create intent
                Intent intent = new Intent(view.getContext(), filterDetailView.class);

                intent.putExtra("filtered", filterText);
                //pack in info
                intent.putExtra("position",position);

                //start activity
                startActivity(intent);
            }


        });

    }
}
