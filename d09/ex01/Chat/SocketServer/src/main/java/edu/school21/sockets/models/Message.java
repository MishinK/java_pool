package edu.school21.sockets.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Message {
    private Long id;
    private String author;
    private String text;
    private Timestamp createdAt;

    public Message(Long id, String author, String text, Timestamp createdAt) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Message(String author, String text) {
        this.author = author;
        this.text = text;
		this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return (author + ": " + text + " (" + createdAt + ")");
    }
}
