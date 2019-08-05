package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Request;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;

import java.util.List;

public interface RequestService {
    List<Request> findAllRequests(String username);
    Request findRequestById(String username, String id) throws TummyException;
    void postRequest(Request request);
    void updateRequest(Request request);
    void closeRequest(Request request);
}
