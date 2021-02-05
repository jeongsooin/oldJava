package com.example.dailye;

public class SpeechItem {

    private String sentence;
    private String meaning;

    public SpeechItem() {

    }

    public SpeechItem(String sentence, String meaning) {
        this.sentence = sentence;
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
