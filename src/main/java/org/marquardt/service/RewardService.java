package org.marquardt.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.marquardt.api.model.RewardRequest;
import org.marquardt.api.model.RewardResponse;
import org.marquardt.model.jpa.Reward;
import org.marquardt.model.jpa.RewardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ApplicationScoped
public class RewardService {

    @Inject
    RewardRepository rewardRepository;
    @Inject
    RewardBuilder rewardBuilder;

    public List<RewardResponse> getAllRewards() {
        buildRewards();
        List<Reward> rewardsFromDB = rewardRepository.listAll();
        return mapRewards(rewardsFromDB);
    }

    @Transactional
    public RewardResponse updateReward(String id, RewardRequest request) {
        Optional<Reward> optionalReward = rewardRepository.find("id", id).firstResultOptional();
        if (optionalReward.isEmpty()) {
            throw new NoSuchElementException("Reward with id " + id + " does not exist");
        }
        Reward reward = optionalReward.get();
        reward.setState(request.getState());
        return new RewardResponse(reward);
    }

    public List<RewardResponse> buildRewards() {
        rewardBuilder.buildRewards();
        List<Reward> rewards = rewardRepository.listAll();
        return mapRewards(rewards);
    }

    private List<RewardResponse> mapRewards(List<Reward> rewardsFromDB) {
        List<RewardResponse> allRewards = new ArrayList<>();
        for (Reward reward : rewardsFromDB) {
            allRewards.add(new RewardResponse(reward));
        }
        return allRewards;
    }
}
