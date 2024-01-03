package com.shimh.service.impl;

import com.shimh.entity.ResponseInfo;
import com.shimh.repository.ResponseRepository;
import com.shimh.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Override
    @Transactional
    public ResponseInfo createResponse(ResponseInfo response) {
        return responseRepository.save(response);
    }

    @Override
    public ResponseInfo getResponse(Integer id) {
        return responseRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseInfo updateResponse(Integer id, ResponseInfo response) {
        ResponseInfo existingResponse = responseRepository.findById(id).orElse(null);
        if (existingResponse != null) {
            existingResponse.setTitle(response.getTitle());
            existingResponse.setDescription(response.getDescription());
            existingResponse.setLocation(response.getLocation());
            existingResponse.setDatetime(response.getDatetime());
            responseRepository.save(existingResponse);
        }
        return existingResponse;
    }

    @Override
    @Transactional
    public void deleteResponse(Integer id) {
        responseRepository.deleteById(id);
    }

    @Override
    public List<ResponseInfo> listUserResponses(Integer userId) {
        return responseRepository.findByUserId(userId);
    }
}