package com.shimh.repository.impl;

import com.shimh.entity.ResponseInfo;
import com.shimh.repository.wrapper.ResponseWrapper;
import com.shimh.vo.PageVo;
import org.hibernate.Query;
import org.hibernate.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ResponseRepositoryImpl implements ResponseWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ResponseInfo> listResponses(PageVo page) {

        StringBuilder hql = new StringBuilder("from ResponseInfo");

        if (null != page.getName() && !"".equals(page.getName())) {
            hql.append(" order by ");
            hql.append(page.getName());
        }

        if (null != page.getSort() && !"".equals(page.getSort())) {
            hql.append(" ");
            hql.append(page.getSort());
        }

        Query query = getSession().createQuery(hql.toString());

        if (null != page.getPageNumber() && null != page.getPageSize()) {
            query.setFirstResult(page.getPageSize() * (page.getPageNumber() - 1));
            query.setMaxResults(page.getPageSize());
        }

        return query.list();
    }

    private Session getSession() {
        return em.unwrap(Session.class);
    }
}