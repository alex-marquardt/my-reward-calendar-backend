package org.marquardt.model.ingoing;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.marquardt.model.RewardState;

@Schema(name = "Reward request")
public class RewardRequest {

    @Schema(name = "State of date", required = true)
    private RewardState state;

    public RewardState getState() {
        return state;
    }
}
