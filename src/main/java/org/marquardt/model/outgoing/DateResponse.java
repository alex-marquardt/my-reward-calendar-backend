package org.marquardt.model.outgoing;

import org.marquardt.model.DateState;
import org.marquardt.jpa.Date;

import java.time.LocalDate;

public class DateResponse {

    private String id;
    private LocalDate date;
    private DateState state;

    public DateResponse(Date date) {
        this.id = date.getId();
        this.date = date.getDate();
        this.state = date.getState();
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
