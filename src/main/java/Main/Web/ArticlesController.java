package Main.Web;

import Main.Entity.Articles;
import Main.Services.ArticlesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
public class ArticlesController
{
  @Autowired
  private ArticlesServices articleService;

  public ArticlesController(ArticlesServices articleService)
  {
    this.articleService = articleService;
  }

  @GetMapping("/articles")
  public String getArticles(Model model)
  {
    model.addAttribute("articles", articleService.readArticles());
    return "/articles/articles";
  }

  @GetMapping("/getArticle/{id}")
  public String getById(@PathVariable("id") Integer id, Model model)
  {
    model.addAttribute("article", articleService.findArticle(id));
    return "/articles/article";
  }

  @GetMapping("/updateArticle/{id}")
  public String updateArticle(@PathVariable("id") Integer id, Model model)
  {
    model.addAttribute("article", articleService.findArticle(id));
    return "/articles/updateArticle";
  }

  @PostMapping("/updateArticle/{id}")
  public String updateArticleReal(@ModelAttribute("article") Articles article, @PathVariable("id") Integer id)
  {
    articleService.updateArticle(id, article);
    return "redirect:/articles/articles";
  }

  @PostMapping("/delete")
  public String deleteArticles()
  {
    articleService.deleteArticles();
    return "redirect:/articles/articles";
  }

  @PostMapping("/deleteArticle/{id}")
  public String deleteById(@PathVariable("id") Integer id)
  {
    articleService.deleteByArticleId(id);
    return "redirect:/articles/articles";
  }

  @GetMapping("/addArticle")
  public String addArticle(@ModelAttribute("article") Articles article)
  {
    return "/articles/addArticle";
  }

  @PostMapping("/addArticle")
  public String addArticleReal(@ModelAttribute("article") Articles article)
  {
    articleService.createArticle(article);
    return "redirect:/articles/articles";
  }
}
