package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long  id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime dateTime;

    public Message(Long  id, User author, Chatroom room, String text, LocalDateTime dateTime) {
		this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long  getId() {
        return this.id;
    }

    public User getAuthor() {
        return this.author;
    }

    public Chatroom getRoom() {
        return this.room;
    }

    public String getText() {
        return this.text;
    }

    public LocalDateTime getDate() {
        return this.dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id.equals(message.id) && author.equals(message.author) && room.equals(message.room) && text.equals(message.text) && dateTime.equals(message.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, dateTime);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
