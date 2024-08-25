package org.marquardt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Entity
public class Date {
    @Id
    @Column(name = "ID")
    public LocalDate date;
    @Column(name = "STATE")
    public DateState state;

    public Date() {
        this.date = LocalDate.now();
        this.state = DateState.UNKNOWN;
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
