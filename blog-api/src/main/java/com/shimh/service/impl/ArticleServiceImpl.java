package com.shimh.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.shimh.vo.ArticleVo;
import com.shimh.vo.MoneyVO;
import com.shimh.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shimh.common.util.UserUtils;
import com.shimh.entity.Article;
import com.shimh.entity.Category;
import com.shimh.entity.Tag;
import com.shimh.entity.User;
import com.shimh.repository.ArticleRepository;
import com.shimh.service.ArticleService;

/**
 * @author shimh
 * <p>
 * 2018年1月25日
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public List<Article> listArticles(PageVo page) {

        return articleRepository.listArticles(page);
    }

    @Override
    public List<Article> listArticles(ArticleVo article, PageVo page) {

        return articleRepository.listArticles(article, page);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleRepository.getOne(id);
    }

    @Override
    @Transactional
    public Integer publishArticle(Article article) {
        //TODO: 设置时间
        article.setStatus(ArticleService.STATUS_NOT_DOWN);

        if(null != article.getId()){
            return this.updateArticle(article);
        }else{
            Integer qid = article.getQueryId();
            if (null != qid){
                return this.saveArticle(article);
            }else{
                qid = this.saveArticle(article);
                article.setQueryId(qid);
                return this.saveArticle(article);
            }

        }

    }

    @Override
    @Transactional
    public Integer acceptArticle(Integer id){
        Article article = articleRepository.getOne(id);
        if (null == article){
            return -1;
        }
        Integer qid = article.getQueryId();
        if (null == qid){
            return -1;
        }
        Article query = articleRepository.getOne(qid);
        if (null == query){
            return -1;
        }
        Integer status = query.getStatus();
        if (ArticleService.STATUS_DOWN == status){
            return -1;
        }
        article.setStatus(ArticleService.STATUS_DOWN);
        query.setStatus(ArticleService.STATUS_DOWN);
        //获取当前时间
        Date date = new Date();
        article.setEndDate(date);
        query.setEndDate(date);

        articleRepository.save(query);
        return articleRepository.save(article).getId();
    }

    @Override
    @Transactional
    public Integer saveArticle(Article article) {

        User currentUser = UserUtils.getCurrentUser();

        if (null != currentUser) {
            article.setAuthor(currentUser);
        }
        article.setCreateDate(new Date());
        //结束时间为创建时间+article.getTime()天
        if (null != article.getTime()){
            Date date = new Date();
            date.setTime(date.getTime() + article.getTime() * 24 * 60 * 60 * 1000);
            article.setInvalidDate(date);
        }
        article.setWeight(Article.Article_Common);

        return articleRepository.save(article).getId();
    }

    @Override
    @Transactional
    public Integer updateArticle(Article article) {
        Article oldArticle = articleRepository.getOne(article.getId());

        oldArticle.setTitle(article.getTitle());
        oldArticle.setSummary(article.getSummary());
        oldArticle.setBody(article.getBody());
        oldArticle.setCategory(article.getCategory());
        oldArticle.setTags(article.getTags());

        return oldArticle.getId();
    }

    @Override
    @Transactional
    public void deleteArticleById(Integer id) {
        articleRepository.delete(id);
    }

    @Override
    public List<Article> listArticlesByTag(Integer id) {
        Tag t = new Tag();
        t.setId(id);
        return articleRepository.findByTags(t);
    }

    @Override
    public List<Article> listArticlesByCategory(Integer id) {
        Category c = new Category();
        c.setId(id);

        return articleRepository.findByCategory(c);
    }

    @Override
    @Transactional
    public Article getArticleAndAddViews(Integer id) {
        int count = 1;
        Article article = articleRepository.getOne(id);
        article.setViewCounts(article.getViewCounts() + count);
        return article;
    }

    @Override
    public List<Article> listHotArticles(int limit) {

        return articleRepository.findOrderByViewsAndLimit(limit);
    }

    @Override
    public List<Article> listNewArticles(int limit) {

        return articleRepository.findOrderByCreateDateAndLimit(limit);
    }

    @Override
    public List<ArticleVo> listArchives() {

        return articleRepository.listArchives();
    }

    @Override
    public List<ArticleVo> listMoney(String startTime, String endTime,String city) {
        List<ArticleVo> temp =articleRepository.listMoney(startTime, endTime,city);

        return temp;
    }
}
