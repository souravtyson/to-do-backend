package com.sourav.todobackend.model;

public record Task(
        String id,
        String title,
        boolean completed
) {
}
