package net.zt.funcode.web;

import com.google.common.collect.Lists;
import net.zt.funcode.domain.Article;
import net.zt.funcode.domain.Author;
import net.zt.funcode.domain.Category;
import net.zt.funcode.service.ArticleService;
import net.zt.funcode.service.AuthorService;
import net.zt.funcode.service.CategoryService;
import net.zt.funcode.web.ajax.ArticlesAjax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/getAuthors", method = RequestMethod.GET)
    public ModelAndView selectAllAuthors() {
        List<Author> authors = authorService.getAll();
        ModelAndView view = new ModelAndView("editpages/editMain", "resultObject", authors);
        view.addObject("type", "author");
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNewAuthor() {
        ModelAndView view = new ModelAndView("editpages/editAuthor", "author", null);
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addAuthor(@RequestParam(value = "firstname") String firstname,
                                  @RequestParam(value = "lastname") String lastname,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "id") Long id) {
        Author author = new Author();
        if (id != null) {
            author = authorService.get(id);
        }
        author.setFirstname(firstname);
        author.setLastname(lastname);
        author.setEmail(email);
        authorService.save(author);
        ModelAndView view = new ModelAndView("editpages/editMain", "author", author);
        return view;
    }

    @RequestMapping(value = "/deleteAuthor", method = RequestMethod.POST)
    public ModelAndView deleteAuthor(@RequestParam(value = "id") Long id) {
        Author author = authorService.get(id);
        if (author != null) {
            try {
                authorService.remove(author);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return selectAllAuthors();
    }

    @RequestMapping(value = "/changeAuthor", method = RequestMethod.POST)
    public ModelAndView changeAuthor(@RequestParam(value = "id") Long id) {
        Author author = authorService.get(id);
        if (author != null) {
            ModelAndView view = new ModelAndView("editpages/editAuthor", "author", author);
            view.addObject("firstname", author.getFirstname());
            view.addObject("lastname", author.getLastname());
            view.addObject("email", author.getEmail());
            view.addObject("id", author.getId());
            return view;
        }
        return selectAllAuthors();
    }
}
