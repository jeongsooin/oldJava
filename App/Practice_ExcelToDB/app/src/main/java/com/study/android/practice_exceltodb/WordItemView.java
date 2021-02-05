package com.study.android.practice_exceltodb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WordItemView extends Activity {

    TextView textView1;
    TextView textView2;
    TextView textView3;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;

    WordListAdapter adapter;
    WordItem item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_item_view);

        Intent intent = getIntent();
        item = (WordItem) intent.getSerializableExtra("item");
        adapter = new WordListAdapter(this);

        textView1 = findViewById(R.id.word);
        textView2 = findViewById(R.id.pronunciation);
        textView3 = findViewById(R.id.meaning);


        textView1.setText(intent.getStringExtra("word"));
        textView2.setText(intent.getStringExtra("pronunciation"));
        textView3.setText(intent.getStringExtra("meaning"));

        imageView1 = findViewById(R.id.choose_before);
        imageView2 = findViewById(R.id.choose_done);
        imageView3 = findViewById(R.id.choose_favorite);
        imageView4 = findViewById(R.id.choose_after);
        imageView5 = findViewById(R.id.choose_done_check);
        imageView6 = findViewById(R.id.choose_favorite_check);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.choose_done).setVisibility(View.GONE);
                findViewById(R.id.choose_done_check).setVisibility(View.VISIBLE);
                item.setDone(true);
                adapter.notifyDataSetChanged();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.choose_favorite).setVisibility(View.GONE);
                findViewById(R.id.choose_favorite_check).setVisibility(View.VISIBLE);
                item.setFavorite(true);
                adapter.notifyDataSetChanged();
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.choose_done).setVisibility(View.VISIBLE);
                findViewById(R.id.choose_done_check).setVisibility(View.GONE);
                item.setDone(false);
                adapter.notifyDataSetChanged();
            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.choose_favorite).setVisibility(View.VISIBLE);
                findViewById(R.id.choose_favorite_check).setVisibility(View.GONE);
                item.setFavorite(false);
                adapter.notifyDataSetChanged();
            }
        });
    }

//    public void onBeforeClicked(int position) {
//        WordItem item = (WordItem)adapter.getItem(position);
//        Intent intent = new Intent(getApplicationContext(), WordItemView.class);
//        intent.putExtra("word", item.getWord());
//        intent.putExtra("meaning", item.getMeaning());
//        intent.putExtra("pronunciation", item.getPronunciation());
//        startActivity(intent);
//    }
//
//    public void onAfterClicked(int position) {
//        WordItem item = (WordItem)adapter.getItem(position);
//        Intent intent = new Intent(getApplicationContext(), WordItemView.class);
//        intent.putExtra("word", item.getWord());
//        intent.putExtra("meaning", item.getMeaning());
//        intent.putExtra("pronunciation", item.getPronunciation());
//        startActivity(intent);
//    }

}
