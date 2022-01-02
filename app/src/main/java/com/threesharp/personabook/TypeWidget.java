package com.threesharp.personabook;

import android.graphics.Color;

public class TypeWidget {
    private String type;
    private String number;
    private int background;

    public void setBackground(int backgroundColor) {
        background = backgroundColor;
    }
    public void setType(String typeText) {
        type = typeText;
    }
    public void setNumber(String numberText) {
        number = numberText;
    }
    public int getBackground() {
        return this.background;
    }
    public String getType() {
        return this.type;
    }
    public String getNumber() {
        return this.number;
    }
}