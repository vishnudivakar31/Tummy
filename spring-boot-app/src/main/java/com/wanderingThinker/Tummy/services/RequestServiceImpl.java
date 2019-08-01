package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Request;
import com.wanderingThinker.Tummy.repositories.RequestRepository;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.RequestStatus.*;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> findAllRequests(String username) {
        return requestRepository.findByUsername(i -> i.getUsername().equals(username) &&
                !i.getRequestStatus().equals(CLOSED),
                PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "updatedTime"));
    }

    @Override
    public Request findRequestById(String username, String id) throws TummyException {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if(optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            if(!request.getRequestStatus().equals(CLOSED) && request.getUsername().equals(username)) {
                return request;
            }
            throw new TummyException("request closed or user doesn't have privileges.");
        }
        throw new TummyException("no request found");
    }

    @Override
    public void postRequest(Request request) {
        request.setRequestedTime(new Date(System.currentTimeMillis()));
        request.setUpdatedTime(new Date(System.currentTimeMillis()));
        request.setRequestStatus(NEW);
        requestRepository.save(request);
    }

    @Override
    public void updateRequest(Request request) {
        request.setUpdatedTime(new Date(System.currentTimeMillis()));
        request.setRequestStatus(UPDATED);
        requestRepository.save(request);
    }

    @Override
    public void closeRequest(Request request) {
        request.setUpdatedTime(new Date(System.currentTimeMillis()));
        request.setRequestStatus(CLOSED);
        requestRepository.save(request);
    }
}
