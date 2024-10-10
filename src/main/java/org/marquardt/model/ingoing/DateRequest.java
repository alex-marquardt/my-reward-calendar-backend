package org.marquardt.model.ingoing;

import org.marquardt.model.DateState;

import java.time.LocalDate;

public class DateRequest {

    private LocalDate date;
    private DateState state;

    public LocalDate getDate() {
        return date;
    }

    public DateState getState() {
        return state;
    }
}
