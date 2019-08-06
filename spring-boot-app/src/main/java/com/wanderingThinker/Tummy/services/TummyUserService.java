package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.TummyUser;

public interface TummyUserService {
    TummyUser findUserByUsername(String username);
}
