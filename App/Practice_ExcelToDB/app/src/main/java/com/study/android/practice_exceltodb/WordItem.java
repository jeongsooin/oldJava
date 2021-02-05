package com.study.android.practice_exceltodb;

import java.io.Serializable;

public class WordItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private String word;
    private String pronunciation;
    private String meaning;
    private Boolean isDone;
    private Boolean isFavorite;

    public WordItem() {

    }

    public WordItem(String word, String pronunciation, String meaning) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
        this.isDone = false;
        this.isFavorite = false;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Boolean getDone() { return isDone; }

    public void setDone(Boolean done) { isDone = done; }

    public Boolean getFavorite() { return isFavorite; }

    public void setFavorite(Boolean favorite) { isFavorite = favorite; }
}
