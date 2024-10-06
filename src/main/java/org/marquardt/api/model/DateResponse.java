package org.marquardt.api.model;

import org.marquardt.model.DateState;
import org.marquardt.model.jpa.Date;

import java.time.LocalDate;

public class DateResponse {

    private LocalDate date;
    private DateState state;

    public DateResponse(Date date) {
        this.date = date.getDate();
        this.state = date.getState();
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
