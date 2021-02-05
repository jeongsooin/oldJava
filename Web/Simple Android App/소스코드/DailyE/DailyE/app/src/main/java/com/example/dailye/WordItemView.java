package com.example.dailye;

import android.content.Context;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WordItemView extends ConstraintLayout {

    TextView textView1;
    TextView textView2;
    TextView textView3;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;

    public FavoriteListDbAdapter favoriteListDbAdapter;

    public FavoriteItemAdapter favoriteItemAdapter;

    public WordItemView(Context context) {
        super(context);

        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_word_item_view, this, true);

        favoriteListDbAdapter = MainActivity.favoriteListDbAdapter;
        favoriteItemAdapter = MainActivity.favoriteItemAdapter;

        textView1 = findViewById(R.id.word);
        textView2 = findViewById(R.id.pronunciation);
        textView3 = findViewById(R.id.meaning);

        imageView1 = findViewById(R.id.choose_show_word);
        imageView2 = findViewById(R.id.choose_tts);
        imageView3 = findViewById(R.id.choose_favorite);
        imageView4 = findViewById(R.id.choose_show_meaning);
        imageView5 = findViewById(R.id.choose_favorite_check);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.word_not_show).isShown()) {
                    findViewById(R.id.word_not_show).setVisibility(View.GONE);
                    findViewById(R.id.pronunciation_not_show).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.word_not_show).setVisibility(View.VISIBLE);
                    findViewById(R.id.pronunciation_not_show).setVisibility(View.VISIBLE);
                }
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TTS Code
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.choose_favorite).setVisibility(View.GONE);
                findViewById(R.id.choose_favorite_check).setVisibility(View.VISIBLE);
                favoriteListDbAdapter.open();
                favoriteListDbAdapter.createNote(textView1.getText().toString(), textView2.getText().toString(), textView3.getText().toString());
                favoriteListDbAdapter.close();
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.meaning_not_show).isShown())
                    findViewById(R.id.meaning_not_show).setVisibility(View.GONE);
                else
                    findViewById(R.id.meaning_not_show).setVisibility(View.VISIBLE);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.choose_favorite).setVisibility(View.VISIBLE);
                findViewById(R.id.choose_favorite_check).setVisibility(View.GONE);
                favoriteListDbAdapter.open();
                favoriteListDbAdapter.deleteNote(textView1.getText().toString());
                favoriteListDbAdapter.close();
            }
        });

    }

    public void setWord(String word) {
        textView1.setText(word);
    }

    public void setPronunciation(String pronunciation) {
        textView2.setText(pronunciation);
    }

    public void setMeaning(String meaning) {
        textView3.setText(meaning);
    }

    public FavoriteItemAdapter getDbAdapter() {return MainActivity.favoriteItemAdapter; }

    public void makeFavoriteItemList() {
        favoriteListDbAdapter.open();
        Cursor result = favoriteListDbAdapter.fetchAllNotes();
        result.moveToFirst();
        while (!result.isAfterLast()) {
            String word = result.getString(1);
            String pronunciation  = result.getString(2);
            String meaning  = result.getString(3);
            WordItem item = new WordItem(word, pronunciation, meaning);
            MainActivity.favoriteItemAdapter.addItem(item);
            result.moveToNext();
        }

        result.close();
        favoriteListDbAdapter.close();
    }
}
