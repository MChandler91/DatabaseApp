package com.example.chrisbennett.mylistview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class filterDetailView extends AppCompatActivity {


    ReviewDBHelper mDbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        TextView txtReviewer = (TextView) findViewById(R.id.txtReviewer);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        TextView txtDev = (TextView) findViewById(R.id.txtDev);
        TextView txtPub = (TextView) findViewById(R.id.txtPub);
        TextView txtRating = (TextView) findViewById(R.id.txtRating);
        TextView txtReview = (TextView) findViewById(R.id.txtReview);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",0);
        String filterText = intent.getStringExtra("filtered");

        mDbHelper = new ReviewDBHelper(this);
        db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "  + ReviewSchema.Review.TABLE_NAME + " WHERE " + ReviewSchema.Review.COLUMN_NAME_DEVELOPER + " = '" + filterText + "'", null);

        cursor.moveToPosition(position);

        String rev = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_REVIEWER));
        String t = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_TITLE));
        String dev = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_DEVELOPER));
        String pub = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_PUBLISHER));
        String rat = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_RATING));
        String review = cursor.getString(cursor.getColumnIndexOrThrow(ReviewSchema.Review.COLUMN_NAME_REVIEW));

        txtReviewer.setText(rev);
        txtTitle.setText(t);
        txtDev.setText(dev);
        txtPub.setText(pub);
        txtRating.setText(rat);
        txtReview.setText(review);
    }
}
