package org.marquardt.api.model;

import org.marquardt.model.DateState;

import java.time.LocalDate;

public class CreateDateRequest {
    public LocalDate date;
    public DateState state;

    public LocalDate getDate() {
        return date;
    }

    public DateState getState() {
        return state;
    }
}
