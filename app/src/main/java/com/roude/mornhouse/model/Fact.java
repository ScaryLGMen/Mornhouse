package com.roude.mornhouse.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Fact {
    @PrimaryKey
    private int id;
    private String text;

    public Fact(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

