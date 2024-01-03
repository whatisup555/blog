package com.shimh.repository.wrapper;

import com.shimh.entity.RequestInfo;
import com.shimh.vo.PageVo;
import java.util.List;

public interface RequestWrapper {
    List<RequestInfo> listRequests(PageVo page);
}