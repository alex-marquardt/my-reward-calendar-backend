package org.marquardt.model.ingoing;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.marquardt.model.DateState;

import java.time.LocalDate;

@Schema(name = "Date request")
public class DateRequest {

    @Schema(name = "Specific date", required = true)
    private LocalDate date;
    @Schema(name = "State of date", required = true)
    private DateState state;

    public LocalDate getDate() {
        return date;
    }

    public DateState getState() {
        return state;
    }
}
