package com.shimh.repository.impl;

import com.shimh.entity.Article;
import com.shimh.repository.wrapper.ArticleWrapper;
import com.shimh.vo.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ArticleRepositoryImpl implements ArticleWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Article> listArticles(PageVo page) {

        StringBuilder hql = new StringBuilder("from Article");

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

    @Override
    public List<Article> listArticles(ArticleVo article, PageVo page) {

        StringBuilder hql = new StringBuilder("from Article a ");

        if (null != article.getTagId()) {

            hql.append(" inner join fetch a.tags t");
        }

        hql.append(" where 1=1 ");
        hql.append(" and (a.invalidDate is null or a.invalidDate > :now or a.category.id = 2)");

        if (null != article.getAuthorId()){
            if(null != article.getCategoryId() || article.getTagId() != null)
                hql.append(" and a.author.id != :authorId");
            else
                hql.append(" and a.author.id = :authorId");
        }
        hql.append(" and a.queryId is not null");
        hql.append(" and a.status = 0");
        if (null != article.getCategoryId()) {

            hql.append(" and a.category.id = :categoryId");
            if(article.getCategoryId() == 2){
                hql.append(" and a.queryId <> a.id");
                if(null != article.getAuthorId()){
                    hql.append(" and (select author.id from Article where id = a.queryId) = :authorId");
                }
            }
        }

        if (null != article.getTagId()) {
            hql.append(" and a.category.id = 1");
            hql.append(" and t.id = :tagId");
        }


        if (null != article.getYear() && !"".equals(article.getYear())) {
            hql.append(" and YEAR(a.createDate) = :year");
        }

        if (null != article.getMonth() && !"".equals(article.getMonth())) {
            hql.append(" and MONTH(a.createDate) = :month");
        }

        if (null != page.getName() && !"".equals(page.getName())) {
            hql.append(" order by ");
            hql.append(page.getName());
        }

        if (null != page.getSort() && !"".equals(page.getSort())) {
            hql.append(" ");
            hql.append(page.getSort());
        }


        Query query = getSession().createQuery(hql.toString());


        if (null != article.getYear() && !"".equals(article.getYear())) {
            query.setParameter("year", article.getYear());
        }

        if (null != article.getMonth() && !"".equals(article.getMonth())) {
            query.setParameter("month", article.getMonth());
        }

        if (null != article.getTagId()) {
            query.setParameter("tagId", article.getTagId());
        }

        if (null != article.getCategoryId()) {
            query.setParameter("categoryId", article.getCategoryId());
        }

        if(null != article.getAuthorId()){
            query.setParameter("authorId", article.getAuthorId());
        }

        if (null != page.getPageNumber() && null != page.getPageSize()) {
            query.setFirstResult(page.getPageSize() * (page.getPageNumber() - 1));
            query.setMaxResults(page.getPageSize());
        }

        query.setParameter("now", new Date());
        return query.list();

//        StringBuilder hql = new StringBuilder("select a.* from me_article a ");
//
//        if (null != article.getTagId()) {
//
//            hql.append(" left join me_article_tag at on at.article_id = a.id");
//        }
//
//        hql.append(" where 1=1 ");
//
//        if (null != article.getCategoryId()) {
//
//            hql.append(" and a.category_id = :categoryId");
//        }
//
//        if (null != article.getTagId()) {
//
//            hql.append(" and at.tag_id = :tagId");
//        }
//
//
//        if (null != article.getYear() && !"".equals(article.getYear())) {
//            hql.append(" and YEAR(a.createDate) = :year");
//        }
//
//        if (null != article.getMonth() && !"".equals(article.getMonth())) {
//            hql.append(" and MONTH(a.createDate) = :month");
//        }
//
//        if (null != page.getName() && !"".equals(page.getName())) {
//            hql.append(" order by ");
//            hql.append(page.getName());
//        }
//
//        if (null != page.getSort() && !"".equals(page.getSort())) {
//            hql.append(" ");
//            hql.append(page.getSort());
//        }
//
//
//        SQLQuery query = getSession().createSQLQuery(hql.toString());
//
//
//        if (null != article.getYear() && !"".equals(article.getYear())) {
//            query.setParameter("year", article.getYear());
//        }
//
//        if (null != article.getMonth() && !"".equals(article.getMonth())) {
//            query.setParameter("month", article.getMonth());
//        }
//
//        if (null != article.getTagId()) {
//            query.setParameter("tagId", article.getTagId());
//        }
//
//        if (null != page.getPageNumber() && null != page.getPageSize()) {
//            query.setFirstResult(page.getPageSize() * (page.getPageNumber() - 1));
//            query.setMaxResults(page.getPageSize());
//        }
//
//        query.addEntity(Article.class);
//
//        return query.list();

    }

    @Override
    public List<ArticleVo> listArchives() {

        String sql = "select year(create_date) as year,month(create_date) as month,count(*) as count from me_article group by year(create_date),month(create_date)";

        SQLQuery query = getSession().createSQLQuery(sql);
        query.addScalar("year");
        query.addScalar("month");
        query.addScalar("count", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(ArticleVo.class));
        return query.list();
    }

    @Override
    public List<ArticleVo> listMoney(String startTime, String endTime,String city)  {

        String sql = "select year(create_date) as year, month(create_date) as month, count(*) as count " +
                "from me_article " +
                "where end_date between :startTime and :endTime AND id <> query_id AND status = 1 " +
                "group by year(create_date), month(create_date)";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start_date = null;
        try {
            start_date = inputFormat.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date end_date = null;
        try {
            end_date = inputFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String start_formattedDate = outputFormat.format(start_date);
        String end_formattedDate = outputFormat.format(end_date);
        System.out.println(start_formattedDate);
        System.out.println(end_formattedDate);

        SQLQuery query = getSession().createSQLQuery(sql);
        query.setParameter("startTime", start_formattedDate);
        query.setParameter("endTime", end_formattedDate);
        query.addScalar("year");
        query.addScalar("month");
        query.addScalar("count", IntegerType.INSTANCE);
        query.setResultTransformer(Transformers.aliasToBean(ArticleVo.class));
        return query.list();
    }

    private Session getSession() {
        return em.unwrap(Session.class);
    }


}
