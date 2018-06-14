package net.zt.funcode.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.zt.funcode.domain.Article;
import net.zt.funcode.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long > {

	

}
