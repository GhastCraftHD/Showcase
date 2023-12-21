package de.leghast.showcase.ui;

public enum Page {

    POSITION("§eAdjust the displays position"),
    SIZE("§eAdjust the displays size"),
    ROTATION("§eAdjust the displays rotation"),
    TRANSFORM("§eAdjust the displays transform");

    private String title;

    Page(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

}
