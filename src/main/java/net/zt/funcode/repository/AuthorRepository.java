package net.zt.funcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.zt.funcode.domain.Author;
import net.zt.funcode.service.AuthorService;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT MAX(a.id) FROM Author a")
    Long findMaxId();
}
