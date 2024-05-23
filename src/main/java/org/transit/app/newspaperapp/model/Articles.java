package org.transit.app.newspaperapp.model;

import org.transit.app.newspaperapp.interfaces.articles;

import java.time.LocalDateTime;

public class Articles implements articles {
    private String headline;
    private String byline;
    private String publicationDate;
    private String content;
    private String category;
    private String imageLink;
    private String author_name;


    public Articles(String headline, String byline, String content, String category, LocalDateTime publicationDate, String imageLink) {
        this.headline = headline;
        this.byline = byline;
        this.content = content;
        this.category = category;
        this.publicationDate = String.valueOf(publicationDate);
        this.imageLink = imageLink;
    }

    public Articles(String headline, String byline, String content, String category, LocalDateTime publicationDate, String imageLink, String author_name) {
        this.headline = headline;
        this.byline = byline;
        this.content = content;
        this.category = category;
        this.publicationDate = String.valueOf(publicationDate);
        this.imageLink = imageLink;
        this.author_name = author_name;
    }


    @Override
    public String headline() {
        return getHeadline();
    }

    @Override
    public String byline() {
        return getByline();
    }

    @Override
    public String publicationDate() {
        return getPublicationDate();
    }

    @Override
    public String content() {
        return getContent();
    }

    @Override
    public String category() {
        return getCategory();
    }

    @Override
    public String imageLink() {
        return getImageLink();
    }



    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
