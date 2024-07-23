package com.example.githubprojectsearch;

public class Repository {
    String name;
    String owner;
    String language;
    private String starsCount;

    private final String nameStart="Name: ";
    private final String ownerStart="Owner: ";
    private final String languageStart="Language: ";
    private final String starStart="Stars Number: ";

    public Repository(String name, String owner, String language, String starsCount) {
        this.name = name;
        this.owner = owner;
        this.language = language;
        this.starsCount = starsCount;
    }

    public String getStarsCount() {
        return this.starStart + starsCount;
    }

    public void setStarsCount(String starsCount) {
        this.starsCount = starsCount;
    }

    public String getNameStart() {
        return this.nameStart+ nameStart;
    }

    public String getOwnerStart() {
        return this.ownerStart+ ownerStart;
    }

    public String getLanguageStart() {
        return this.languageStart+languageStart;
    }
}
