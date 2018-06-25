package net.zt.funcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.zt.funcode.domain.Author;
import net.zt.funcode.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    @Transactional(readOnly = true)
    public Author get(Long id) {
        return authorRepo.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    @Override
    @Transactional
    public void save(Author author) {
        if (author.getId() == null) {
            setId(author);
        }
        authorRepo.save(author);
    }

    @Override
    @Transactional
    public void remove(Author author) {
        authorRepo.delete(author);
    }

    @Transactional()
    public void setId(Author author) {
        Long id = authorRepo.findMaxId();
        id++;
        author.setId(id);
    }
}
