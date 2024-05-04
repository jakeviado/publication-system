package org.transit.app.newspaperapp.model;

import org.transit.app.newspaperapp.interfaces.articles;

public class Articles implements articles {
    private String headline;

    private String byline;

    private String publicationDate;

    private String content;

    public Articles(String headline, String byline, String publicationDate, String content) {
        this.headline = headline;
        this.byline = byline;
        this.publicationDate = publicationDate;
        this.content = content;
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
}
