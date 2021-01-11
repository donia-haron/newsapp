package com.example.anrdoid.lastapp;

public class News {
    private String Title;
    private String section;
    private String Date;
    private String Url;
    private String pillar;
    private String Type;
    private String auther;

    public News(String Title, String section, String Date, String Url, String pillar, String Type) {
        this.Title = Title;
        this.section = section;
        this.Date = Date;
        this.Url = Url;
        this.pillar = pillar;
        this.Type = Type;
    }
    public News(String Title, String section, String Date, String Url, String pillar, String Type,String auther) {
        this.Title = Title;
        this.section = section;
        this.auther=auther;
        this.Date = Date;
        this.Url = Url;
        this.pillar = pillar;
        this.Type = Type;
    }
    public String getUrl() {
        return Url;
    }

    public String getAuther() { return auther; }

    public String getType() {
        return Type;
    }

    public String getpillar() {
        return pillar;
    }

    public String getTitle() {
        return Title;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return Date;
    }
}
