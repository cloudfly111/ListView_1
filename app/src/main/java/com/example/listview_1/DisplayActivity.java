package com.example.listview_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {


    private TextView textViewName;
    private ImageView imageViewPic;
    private int[] foodPic = {
      R.drawable.coca_cola_light,
      R.drawable.coca_cola_zero,
      R.drawable.coca_cola,
      R.drawable.french_fries,
      R.drawable.hamburger,
      R.drawable.kfc
    };
    private TypedArray pictureList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ActionBar actBar = getSupportActionBar();
        actBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("city");
        setTitle(title);
        String foodName = intent.getStringExtra("food");

        textViewName= (TextView)findViewById(R.id.textView_food);
        textViewName.setText(foodName);

        int index = intent.getIntExtra("index", 0);
//      Two methods to use index to choose picture and set imageView :s
//      1.int[] foodPic
        imageViewPic = (ImageView) findViewById(R.id.imageView_id);
        imageViewPic.setImageResource(foodPic[index]);
//      2.strings.xml => TypedArray pictureList
        pictureList = getResources().obtainTypedArray(R.array.picture);
        imageViewPic.setImageResource((pictureList.getResourceId(index,0)));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}