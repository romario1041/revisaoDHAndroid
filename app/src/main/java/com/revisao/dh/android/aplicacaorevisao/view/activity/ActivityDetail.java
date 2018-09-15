package com.revisao.dh.android.aplicacaorevisao.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.revisao.dh.android.aplicacaorevisao.R;
import com.squareup.picasso.Picasso;

public class ActivityDetail extends BaseActivity {

    ImageView imageMovie;
    TextView title;
    TextView data;
    TextView detail;
    public static final String PATH_IMAGE = "https://image.tmdb.org/t/p/w780";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageMovie = findViewById(R.id.imageMovie);
        title = findViewById(R.id.title);
        data = findViewById(R.id.data);
        detail = findViewById(R.id.detail);
        Bundle extras = getIntent().getExtras();
        Picasso.get().load(PATH_IMAGE + extras.getString("image")).into(imageMovie);
        title.setText(extras.getString("title"));
        data.setText(extras.getString("data"));
        detail.setText(extras.getString("desc"));

    }
}
