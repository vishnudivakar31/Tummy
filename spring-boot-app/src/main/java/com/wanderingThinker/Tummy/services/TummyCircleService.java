package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.TummyCircle;
import com.wanderingThinker.Tummy.supportingdocuments.Friend;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;

import java.util.List;
import java.util.Optional;

public interface TummyCircleService {
    Optional<TummyCircle> findByUsername(String username);

    TummyCircle saveCircle(TummyCircle secondaryUser);

    List<Friend> findAllFriends(String name);

    Friend findFriendByUsername(String name, String friendName) throws TummyException;

    List<Friend> unfriend(String name, String friendName);

    Boolean setBlock(String name, String friendName, Boolean block);

}
