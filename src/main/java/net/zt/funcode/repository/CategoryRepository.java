package net.zt.funcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import net.zt.funcode.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long > {

	@Query("SELECT MAX(cat.id) FROM Category cat")
    Long findMaxId();

}
