package de.leghast.showcase.ui;

public enum Page {

    POSITION("§eAdjust the item displays position"),
    ROTATION("§eAdjust the item displays rotation");

    private String title;

    Page(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}
