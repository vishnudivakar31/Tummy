package com.wanderingThinker.Tummy.services;

import com.wanderingThinker.Tummy.documents.Notifications;
import com.wanderingThinker.Tummy.documents.Request;
import com.wanderingThinker.Tummy.repositories.RequestRepository;
import com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus;
import com.wanderingThinker.Tummy.supportingdocuments.TummyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.NotificationStatus.*;
import static com.wanderingThinker.Tummy.supportingdocuments.TummyDatatypes.RequestStatus.*;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<Request> findAllRequests(String username) {
        List<Request> requests = requestRepository.findByUsername(username, PageRequest.of(0, Integer.MAX_VALUE,
                Sort.Direction.DESC, "updatedTime"));
        return requests.stream().filter(i -> !i.getRequestStatus().equals(CLOSED)).collect(Collectors.toList());
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
        request = requestRepository.save(request);
        notifyRequest(request, REQUESTED);
    }

    @Override
    public void updateRequest(Request request) {
        request.setUpdatedTime(new Date(System.currentTimeMillis()));
        request.setRequestStatus(UPDATED);
        request = requestRepository.save(request);
        notifyRequest(request, REQUESTED);
    }

    @Override
    public void closeRequest(Request request) {
        request.setUpdatedTime(new Date(System.currentTimeMillis()));
        request.setRequestStatus(CLOSED);
        requestRepository.save(request);
    }

    private void notifyRequest(Request request, NotificationStatus status) {
        Notifications notification = new Notifications();
        notification.setNotificationType(request.getRequestType());
        notification.setNoticationStatus(status);
        notification.setUsername(request.getRequestedTo());
        notification.setNotifiedBy(request.getUsername());
        notification.setId(request.getId());
        notificationService.notifyUsers(notification);
    }
}
