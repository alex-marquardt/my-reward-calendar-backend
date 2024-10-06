package org.marquardt.model.jpa;

import jakarta.persistence.*;
import org.marquardt.model.DateState;

import java.time.LocalDate;

@Entity
@Table(name = "DATE")
public class Date {

    @Id
    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "STATE", nullable = false)
    private DateState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REWARD")
    private Reward reward;

    public Date() {
    }

    public Date(LocalDate date, DateState state) {
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

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
}
