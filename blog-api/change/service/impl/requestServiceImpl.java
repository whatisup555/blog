package com.shimh.service.impl;

import com.shimh.entity.RequestInfo;
import com.shimh.repository.RequestRepository;
import com.shimh.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    @Transactional
    public RequestInfo createRequest(RequestInfo request) {
        return requestRepository.save(request);
    }

    @Override
    public RequestInfo getRequest(Integer id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public RequestInfo updateRequest(Integer id, RequestInfo request) {
        RequestInfo existingRequest = requestRepository.findById(id).orElse(null);
        if (existingRequest != null) {
            existingRequest.setTitle(request.getTitle());
            existingRequest.setDescription(request.getDescription());
            existingRequest.setLocation(request.getLocation());
            existingRequest.setDatetime(request.getDatetime());
            requestRepository.save(existingRequest);
        }
        return existingRequest;
    }

    @Override
    @Transactional
    public void deleteRequest(Integer id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<RequestInfo> listUserRequests(Integer userId) {
        return requestRepository.findByUserId(userId);
    }
}