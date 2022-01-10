package com.threesharp.personabook;

public class TypeWidget {
    private String type;
    private String number;
    private int background;
    private int relationBackground;

    public void setBackground(int backgroundColor) {
        background = backgroundColor;
    }
    public void setRelationBackground(int relationBackground) {
        this.relationBackground = relationBackground;
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
    public int getRelationBackground() {
        return this.relationBackground;
    }
    public String getType() {
        return this.type;
    }
    public String getNumber() {
        return this.number;
    }
}