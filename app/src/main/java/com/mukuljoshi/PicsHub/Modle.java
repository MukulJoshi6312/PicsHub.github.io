package com.mukuljoshi.PicsHub;

public class Modle {

    private String image,tag;
    private int lick;

    public Modle(String image, String tag, int lick) {
        this.image = image;
        this.tag = tag;
        this.lick = lick;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLick() {
        return lick;
    }

    public void setLick(int lick) {
        this.lick = lick;
    }
}
