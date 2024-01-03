package com.shimh.service;

import com.shimh.entity.RequestInfo;
import java.util.List;

public interface RequestService {

    RequestInfo createRequest(RequestInfo request);

    RequestInfo getRequest(Integer id);

    RequestInfo updateRequest(Integer id, RequestInfo request);

    void deleteRequest(Integer id);

    List<RequestInfo> listUserRequests(Integer userId);
}