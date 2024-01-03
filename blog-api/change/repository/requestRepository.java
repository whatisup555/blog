package com.shimh.repository;

import com.shimh.entity.RequestInfo;
import com.shimh.repository.wrapper.RequestWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestInfo, Integer>, RequestWrapper {
    List<RequestInfo> findByUserId(Integer userId);
}