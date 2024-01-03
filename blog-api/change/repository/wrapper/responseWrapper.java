package com.shimh.repository.wrapper;

import com.shimh.entity.ResponseInfo;
import com.shimh.vo.PageVo;
import java.util.List;

public interface ResponseWrapper {
    List<ResponseInfo> listResponses(PageVo page);
}