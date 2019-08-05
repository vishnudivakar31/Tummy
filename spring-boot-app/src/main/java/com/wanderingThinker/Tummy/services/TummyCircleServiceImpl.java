package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.TummyCircle;
import com.wanderingThinker.Tummy.repositories.TummyCircleRepository;
import com.wanderingThinker.Tummy.supportingdocuments.Friend;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TummyCircleServiceImpl implements TummyCircleService {

    @Autowired
    private TummyCircleRepository tummyCircleRepository;

    @Override
    public Optional<TummyCircle> findByUsername(String username) {
        return tummyCircleRepository.findByUsername(username);
    }

    @Override
    public TummyCircle saveCircle(TummyCircle tummyCircle) {
        return tummyCircleRepository.save(tummyCircle);
    }

    @Override
    public List<Friend> findAllFriends(String username) {
        Optional<TummyCircle> optionalTummyCircle = tummyCircleRepository.findByUsername(username);
        if(optionalTummyCircle.isPresent()) {
            return optionalTummyCircle.get().getFriends();
        }
        return new ArrayList<>();
    }

    @Override
    public Friend findFriendByUsername(String username, String friendName) throws TummyException {
        Optional<TummyCircle> optionalTummyCircle = tummyCircleRepository.findByUsername(username);
        if(optionalTummyCircle.isPresent()) {
            TummyCircle tummyCircle = optionalTummyCircle.get();
            Optional<Friend> optionalFriend = tummyCircle.getFriends()
                    .stream()
                    .filter(i -> i.getUsername().equals(friendName))
                    .findFirst();
            if(optionalFriend.isPresent()) {
                return optionalFriend.get();
            }
            throw new TummyException("friend record not found");
        }
        throw new TummyException("circle not formed");
    }

    @Override
    public List<Friend> unfriend(String username, String friendName) {
        Optional<TummyCircle> optionalPrimaryCircle = tummyCircleRepository.findByUsername(username);
        Optional<TummyCircle> optionalSecondaryCicle = tummyCircleRepository.findByUsername(friendName);
        TummyCircle primaryCircle = new TummyCircle();
        if(optionalPrimaryCircle.isPresent()) {
            primaryCircle = optionalPrimaryCircle.get();
            primaryCircle.getFriends().removeIf(i -> i.getUsername().equals(friendName));
            primaryCircle = tummyCircleRepository.save(primaryCircle);
        }
        if(optionalSecondaryCicle.isPresent()) {
            TummyCircle secondaryCircle = optionalSecondaryCicle.get();
            secondaryCircle.getFriends().removeIf(i -> i.getUsername().equals(username));
            tummyCircleRepository.save(secondaryCircle);
        }
        return primaryCircle.getFriends();
    }

    @Override
    public Boolean setBlock(String username, String friendName, Boolean block) {
        Optional<TummyCircle> optionalSecondaryCicle = tummyCircleRepository.findByUsername(friendName);
        if(optionalSecondaryCicle.isPresent()) {
            TummyCircle secondaryCircle = optionalSecondaryCicle.get();
            secondaryCircle
                    .getFriends()
                    .stream()
                    .forEach(i -> {
                        if(i.getUsername().equals(username)) {
                            i.setBlocked(block);
                        }
                    });
            tummyCircleRepository.save(secondaryCircle);
        }
        return true;
    }
}
