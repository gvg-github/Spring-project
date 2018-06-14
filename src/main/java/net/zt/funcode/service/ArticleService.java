package net.zt.funcode.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.zt.funcode.domain.Article;

public interface ArticleService {
	
	public Page<Article> getAll(Pageable pageable);
	
	public Article get(Long id);
	
	public void save(Article article);
	
	public Page<Article> getByCategoryId(Long id, Pageable pageable);

}
