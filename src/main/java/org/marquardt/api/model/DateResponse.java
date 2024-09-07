package org.marquardt.api.model;

import org.marquardt.model.DateState;

import java.time.LocalDate;

public class DateResponse {
    private String id;
    private LocalDate date;
    private DateState state;

    public DateResponse(String id, LocalDate date, DateState state) {
        this.id = id;
        this.date = date;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DateState getState() {
        return state;
    }

    public void setState(DateState state) {
        this.state = state;
    }
}
