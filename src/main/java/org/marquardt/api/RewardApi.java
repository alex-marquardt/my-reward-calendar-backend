package org.marquardt.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.marquardt.model.outgoing.RewardResponse;
import org.marquardt.model.ingoing.RewardRequest;
import org.marquardt.service.RewardService;

import java.util.List;

@ApplicationScoped
@Path("/reward")
@Produces(MediaType.APPLICATION_JSON)
public class RewardApi {

    @Inject
    RewardService rewardService;

    @GET
    @Path("/build")
    public List<RewardResponse> buildRewards() {
        return rewardService.buildRewards();
    }

    @GET
    @Path("/all")
    public List<RewardResponse> getRewards() {
        return rewardService.getAllRewards();
    }

    @PUT
    @Path("/{id}")
    public RewardResponse updateReward(String id, RewardRequest request) {
        return rewardService.updateReward(id, request);
    }
}
