package com.shimh.service;

import com.shimh.entity.ResponseInfo;
import java.util.List;

public interface ResponseService {

    ResponseInfo createResponse(ResponseInfo response);

    ResponseInfo getResponse(Integer id);

    ResponseInfo updateResponse(Integer id, ResponseInfo response);

    void deleteResponse(Integer id);

    List<ResponseInfo> listUserResponses(Integer userId);
}