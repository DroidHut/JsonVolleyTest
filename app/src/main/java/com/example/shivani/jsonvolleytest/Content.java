package com.example.shivani.jsonvolleytest;

public class Content {
    String icon;
    String title;
    String date;
    String link;
    String author;
    String description;
  

    public Content(String author, String title,String description,String link, String icon,String date) {
        
        this.link=link;
        this.icon = icon;
        this.title= title;
        this.date=date;
        this.author=author;
        this.description=description;
      

    }

    public Content() {
        
    }

    public String getIcon() {
        return icon;
    }
    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }
    public String getLink() {
        return link;
    }
    public String getAuthor() {return author;}
    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

