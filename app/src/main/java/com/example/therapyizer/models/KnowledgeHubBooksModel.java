package com.example.therapyizer.models;

public class KnowledgeHubBooksModel {

    private String BookTitle;
    private String AuthorName;
    private String BookDescription;


    public KnowledgeHubBooksModel(String bookTitle, String authorName, String bookDescription) {
        BookTitle = bookTitle;
        AuthorName = authorName;
        BookDescription = bookDescription;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public String getBookDescription() {
        return BookDescription;
    }
}
