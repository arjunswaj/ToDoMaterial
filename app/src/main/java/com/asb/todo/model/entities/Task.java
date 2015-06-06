package com.asb.todo.model.entities;

/**
 * Created by arjun on 06/06/15.
 */
public class Task {
    private long id;
    private String name;
    private String description;
    private long startTime;
    private long endTime;
    private boolean completed;

    private Task(long id, String name, String description, long startTime, long endTime,
                 boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public static class Builder {

        private long id;
        private String name;
        private String description;
        private long startTime;
        private long endTime;
        private boolean completed = false;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setStartTime(long startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(long endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Task build() {
            return new Task(id, name, description, startTime, endTime, completed);
        }
    }
}
