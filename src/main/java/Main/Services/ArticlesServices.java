package Main.Services;

import Main.Entity.Articles;

import java.util.List;

public interface ArticlesServices
{
  Articles createArticle(String name);
  void createArticle(Articles article);
  List<Articles> readArticles();
  Articles findArticle(Integer id);
  public void updateArticle(Integer id, Articles article);
  void deleteArticles();
  void deleteByArticleId(Integer id);
  Articles findArticlesById(Integer id);
}
