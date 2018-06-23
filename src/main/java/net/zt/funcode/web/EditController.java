package net.zt.funcode.web;

import net.zt.funcode.domain.Article;
import net.zt.funcode.domain.Author;
import net.zt.funcode.domain.Category;
import net.zt.funcode.service.ArticleService;
import net.zt.funcode.service.AuthorService;
import net.zt.funcode.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 21.06.2018.
 */

@Controller
@RequestMapping("/edit")
public class EditController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model uiModel) {
        List<Category> categories = categoryService.getAll();
        uiModel.addAttribute("categories", categories);

        List<Author> authors = authorService.getAll();
        uiModel.addAttribute("authors", authors);

        List<Article> articles = articleService.getAll();
        uiModel.addAttribute("articles", articles);

        return "editpages/editMain";
//        return "editpages/editpage";

    }
}
