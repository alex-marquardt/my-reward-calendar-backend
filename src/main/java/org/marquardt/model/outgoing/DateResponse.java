package org.marquardt.model.outgoing;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.marquardt.model.DateState;
import org.marquardt.jpa.Date;

import java.time.LocalDate;

@Schema(name = "Date response")
public class DateResponse {

    @Schema(name = "Id of date", required = true)
    private String id;
    @Schema(name = "Specific date", required = true)
    private LocalDate date;
    @Schema(name = "State of date", required = true)
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
