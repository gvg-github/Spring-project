package net.zt.funcode.web;

import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import net.zt.funcode.domain.Article;
import net.zt.funcode.domain.Author;
import net.zt.funcode.domain.Category;
import net.zt.funcode.service.ArticleService;
import net.zt.funcode.service.CategoryService;
import net.zt.funcode.web.ajax.ArticlesAjax;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Метод перенаправляет клиента с адреса https://localhost:8080/lesson6/articles
     * на https://localhost:8080/lesson6 (необходим для следования стилю REST)
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "redirect:/";
    }

    /**
     * @param id      - идентификатор статьи
     * @param uiModel - данные
     * @return путь к странице отображения статьи
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model uiModel) {
        Article article = articleService.get(id);
        uiModel.addAttribute("article", article);
        return "article/view";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(Model uiModel) {
        //создание пустого объекта
        Article article = new Article();
        article.setAuthor(new Author());
        //получение списка всех категорий для возможности выбора категории, к которо будет принадлежать создаваемая статья
        List<Category> categories = categoryService.getAll();
        //связывание объекта статьи с формой и добавление списка категорий на страницу
        uiModel.addAttribute("article", article).addAttribute("categories", categories);
        return "article/add";
    }

    @RequestMapping(method = RequestMethod.POST)
//    public String add(@ModelAttribute("article") Article article, BindingResult bindingResult, @RequestParam("categoryId") Long categoryId) {
    public String add(Model uiModel, @ModelAttribute("article") @Valid Article article, BindingResult bindingResult, @RequestParam("categoryId") Long categoryId, Locale locale, RedirectAttributes redirectAttributes) {
        Category category = categoryService.get(categoryId);
        if (bindingResult.hasErrors() || category == null) {
            uiModel.addAttribute("article", article)
                    //список категорий для выбора категорий в форме
                    .addAttribute("categories", categoryService.getAll())
                    //и добавляем сообщение о результате добавления статьи
                    .addAttribute("message", messageSource.getMessage("article_create_fail", new Object[]{}, locale));
            return "redirect:/articles/add";
        }
        article.setCategory(category);
        articleService.save(article);
        redirectAttributes.addFlashAttribute("message", messageSource.getMessage("article_create_success", new Object[]{}, locale));
        return "redirect:/";
    }

    /**
     * Метод обрабатывающий асинхронный запрос
     */
    @RequestMapping(value = "/articles_ajax", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    /**
     * @param pageCounter-текущая страница(блок из number статей)
     * @param number - количество статей в одном блоке
     * @param order - порядок сортировки(ASC-прямая, DESC-обратная)
     * @param orderBy - поле по которому происходит сортировка
     * @return объект класса ArticlesAjax, который содержит список статей,
     * данный объект преобразовывается в JSON-формат
     */
    public ArticlesAjax listAjax(@RequestParam("pageCounter") Integer pageCounter, @RequestParam("number") Integer number, @RequestParam("order") String order, @RequestParam("orderBy") String orderBy) {
        //объект, который будет содержать информацию о сортировке
        Sort sort = null;
        if (order.equalsIgnoreCase("DESC")) {
            //конструктор Sort принимает в качестве параметров тип сортировки и поле,
            //по которому будет происходить соритровка
            sort = new Sort(Sort.Direction.DESC, orderBy);
        } else {
            sort = new Sort(Sort.Direction.ASC, orderBy);
        }
        //конструктор принимает полную информацию о текущем блоке,количестве статей и сортировке
        PageRequest pageable = new PageRequest(pageCounter, number, sort);
        Page<Article> articlePage = articleService.getAll(pageable);
        ArticlesAjax responsive = new ArticlesAjax();
        //из объекта Page возвращаем итератор и с помощью библиотеки google guava создаем списочный массив
        responsive.setArticles(Lists.newArrayList(articlePage.iterator()));
        return responsive;
    }
}
