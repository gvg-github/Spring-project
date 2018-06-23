package net.zt.funcode.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;

import net.zt.funcode.domain.Article;
import net.zt.funcode.domain.Category;
import net.zt.funcode.service.ArticleService;
import net.zt.funcode.service.CategoryService;
import net.zt.funcode.web.ajax.ArticlesAjax;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    public ModelAndView selectAllCategories() {
        List<Category> categories = categoryService.getAll();
        ModelAndView view = new ModelAndView("editpages/editMain", "resultObject", categories);
        view.addObject("type", "category");
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addNewCategory() {
        ModelAndView view = new ModelAndView("editpages/editCategory", "category", null);
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addCategory(@RequestParam(value = "name") String name, @RequestParam(value = "id") Long id) {
//        Long id = Long.valueOf(idString);
        Category category = new Category();
        if (id != null){
            category = categoryService.get(id);
        }
//        if (category == null) {
//        }
        category.setName(name);
        categoryService.save(category);
        ModelAndView view = new ModelAndView("editpages/editMain", "category", category);
        return view;
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
    public ModelAndView deleteCategory(@RequestParam(value = "id") Long id) {
        Category category = categoryService.get(id);
        if (category != null) {
            try {
                categoryService.remove(category);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return selectAllCategories();
    }

    @RequestMapping(value = "/changeCategory", method = RequestMethod.POST)
    public ModelAndView changeCategory(@RequestParam(value = "id") Long id) {
        Category category = categoryService.get(id);
        if (category != null) {
            ModelAndView view = new ModelAndView("editpages/editCategory", "category", category);
            view.addObject("name", category.getName());
            view.addObject("id", category.getId());
            return view;
        }
        return selectAllCategories();
    }

    @RequestMapping(value = "/{id}/articles_ajax", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ArticlesAjax viewAjax(@PathVariable("id") Long id, @RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number, @RequestParam("order") String order, @RequestParam("orderBy") String orderBy) {
        Sort sort = null;
        if (order.equalsIgnoreCase("DESC")) {
            sort = new Sort(Sort.Direction.DESC, orderBy);
        } else {
            sort = new Sort(Sort.Direction.ASC, orderBy);
        }
        PageRequest pageable = new PageRequest(pageCounter, number, sort);
        Page<Article> articlePage = articleService.getByCategoryId(id, pageable);
        ArticlesAjax responsive = new ArticlesAjax();
        responsive.setArticles(Lists.newArrayList(articlePage.iterator()));
        return responsive;
    }
}
