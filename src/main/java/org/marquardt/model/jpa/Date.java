package org.marquardt.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.marquardt.model.DateState;

import java.time.LocalDate;

@Entity
public class Date {
    @Id
    @Column(name = "DATE")
    LocalDate date;
    @Column(name = "STATE")
    DateState state;

    public Date() {
    }

    public Date(LocalDate date, DateState state) {
        this.date = date;
        this.state = state;
    }

    public LocalDate getDate() {
        return date;
    }

    public DateState getState() {
        return state;
    }

    public void setState(DateState state) {
        this.state = state;
    }
}
