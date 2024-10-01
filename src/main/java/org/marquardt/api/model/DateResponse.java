package org.marquardt.api.model;

import org.marquardt.model.DateState;

import java.time.LocalDate;

public class DateResponse {
    private LocalDate date;
    private DateState state;

    public DateResponse(LocalDate date, DateState state) {
        this.date = date;
        this.state = state;
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
