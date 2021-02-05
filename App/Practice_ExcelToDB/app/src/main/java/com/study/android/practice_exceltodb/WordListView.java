package com.study.android.practice_exceltodb;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WordListView extends ConstraintLayout {

    TextView textView1;
    TextView textView2;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    Boolean isDone = false;
    Boolean isFavorite = false;

    public WordListView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_word_list_view, this, true);

        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        imageView1 = findViewById(R.id.done_not);
        imageView2 = findViewById(R.id.done_check);
        imageView3 = findViewById(R.id.favorite_not);
        imageView4 = findViewById(R.id.favorite_check);

        if (!isDone) {
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.GONE);
        } else {
            imageView1.setVisibility(View.GONE);
            imageView2.setVisibility(View.VISIBLE);
        }
        if (!isFavorite) {
            imageView3.setVisibility(View.VISIBLE);
            imageView4.setVisibility(View.GONE);
        } else {
            imageView3.setVisibility(View.GONE);
            imageView4.setVisibility(View.VISIBLE);
        }
    }

    public void setWord(String word) { textView1.setText(word);}

    public void setMeaning(String meaning) { textView2.setText(meaning); }

    public void setDone(Boolean done) { this.isDone = done; }

    public void setFavorite(Boolean favorite) { this.isFavorite = favorite; }

}
