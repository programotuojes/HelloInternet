package com.programotuojes.hellointernet.web;

import java.io.Serializable;

public class Entry implements Serializable {

    private String title;
    private String date;
    private String audio;
    private String showNotes;

    Entry(String title, String date, String audio, String showNotes) {
        this.title = title;
        this.date = date;
        this.audio = audio;
        this.showNotes = showNotes;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAudio() {
        return audio;
    }

    public String getShowNotes() {
        return showNotes;
    }
}
