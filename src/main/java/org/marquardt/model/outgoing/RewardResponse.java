package org.marquardt.model.outgoing;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.marquardt.jpa.Date;
import org.marquardt.jpa.Reward;
import org.marquardt.model.RewardState;

import java.util.List;

@Schema(name = "Reward response")
public class RewardResponse {

    @Schema(name = "Id of reward", required = true)
    private String id;
    @Schema(name = "State of reward", required = true)
    private RewardState state;
    @Schema(name = "List of dates", required = true)
    private List<DateResponse> dates;

    public RewardResponse(Reward reward) {
        this.id = reward.getId();
        this.state = reward.getState();
        this.dates = getDates(reward.getDates());
    }

    private List<DateResponse> getDates(List<Date> dates) {
        return dates.stream().map(DateResponse::new).toList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RewardState getState() {
        return state;
    }

    public void setState(RewardState state) {
        this.state = state;
    }

    public List<DateResponse> getDates() {
        return dates;
    }

    public void setDates(List<DateResponse> dates) {
        this.dates = dates;
    }
}
