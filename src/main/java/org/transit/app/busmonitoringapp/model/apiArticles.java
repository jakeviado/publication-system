package org.transit.app.busmonitoringapp.model;

import org.transit.app.busmonitoringapp.interfaces.apiArticleInterface;

public class apiArticles implements apiArticleInterface {
    private String title;
    private String url;
    private String description;
    private String author;
    private String publishedAt;

    public apiArticles() {

    }

    public apiArticles(String title, String description, String author, String publishedAt) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.publishedAt = publishedAt;
    }


    @Override
    public String title() {
        return getTitle();
    }

    @Override
    public String url() {
        return getUrl();
    }

    @Override
    public String description() {
        return getDescription();
    }

    @Override
    public String author() {
        return getAuthor();
    }

    @Override
    public String publishedAt() {
        return getPublishedAt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
