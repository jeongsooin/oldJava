package com.example.dailye;

public class WordItem {

    private String word;
    private String pronunciation;
    private String meaning;
    private Boolean isFavorite;

    public WordItem() {

    }

    public WordItem(String word, String pronunciation, String meaning) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
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


    public Boolean getFavorite() { return isFavorite; }

    public void setFavorite(Boolean favorite) { isFavorite = favorite; }
}
