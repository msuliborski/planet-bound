package com.suliborski.planetbound.logic.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log implements Serializable {
    public enum Level {
        INFO, DEBUG
    }

    private Date date;
    private Level level;
    private String message;

    public Log(String message) {
        this.date = new Date();
        this.level = Level.INFO;
        this.message = message;
    }

    public Log(Level level, String message) {
        this.date = new Date(System.currentTimeMillis());
        this.level = level;
        this.message = message;
    }

    public Log(Date date, Level level, String message) {
        this.date = date;
        this.level = level;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "[" + level + "] " + date + ": " + message;
    }
}
