package com.shimh.repository;

import com.shimh.entity.ResponseInfo;
import com.shimh.repository.wrapper.ResponseWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<ResponseInfo, Integer>, ResponseWrapper {
    List<ResponseInfo> findByUserId(Integer userId);
    List<ResponseInfo> findByRequestId(Integer requestId);
}