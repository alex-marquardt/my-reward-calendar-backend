package org.marquardt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.marquardt.api.model.RewardResponse;
import org.marquardt.api.model.UpdateRewardRequest;
import org.marquardt.model.jpa.Reward;
import org.marquardt.model.jpa.RewardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ApplicationScoped
public class RewardService {

    @Inject
    RewardRepository rewardRepository;
    @Inject
    RewardBuilder rewardBuilder;

    public List<RewardResponse> getAllRewards() {
        // todo call buildRewards()
        List<Reward> rewardsFromDB = rewardRepository.listAll();
        return mapRewards(rewardsFromDB);
    }

    @Transactional
    public RewardResponse updateReward(String id, UpdateRewardRequest request) {
        Reward rewardFromDB = rewardRepository.find("id", id).firstResult();
        if (rewardFromDB == null) {
            throw new NoSuchElementException("Reward with id " + id + " does not exist");
        }
        rewardFromDB.setState(request.getState());
        return new RewardResponse(rewardFromDB.getId(), rewardFromDB.getType(), rewardFromDB.getState());
    }

    public List<RewardResponse> buildRewards() {
        // todo check old rewards and new rewards match
        List<Reward> rewards = rewardBuilder.buildRewards();
        return mapRewards(rewards);
    }

    private List<RewardResponse> mapRewards(List<Reward> rewardsFromDB) {
        List<RewardResponse> allRewards = new ArrayList<>();
        for (Reward reward : rewardsFromDB) {
            allRewards.add(new RewardResponse(reward.getId(), reward.getType(), reward.getState()));
        }
        return allRewards;
    }
}
