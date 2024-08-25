package org.marquardt.api.model;

import org.marquardt.model.DateState;

import java.time.LocalDate;

public class DateResponse {
    public LocalDate date;
    public DateState state;

    public DateResponse(LocalDate date, DateState state) {
        this.date = date;
        this.state = state;
    }
}
