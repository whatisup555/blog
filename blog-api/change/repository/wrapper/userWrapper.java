package com.shimh.repository.wrapper;

import com.shimh.entity.User;
import com.shimh.vo.PageVo;
import java.util.List;

public interface UserWrapper {
    List<User> listUsers(PageVo page);
}