package Main.Services;

import Main.Entity.Articles;
import Main.Exceptions.ArticleNotFoundException;
import Main.Repository.ArticlesRepository;
import Main.Repository.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlesServicesImpl implements ArticlesServices
{
  @Autowired
  private ArticlesRepository articlesRepository;

  @Autowired
  private OperationsRepository operationsRepository;

  @Override
  public Articles createArticle(String name)
  {
    return articlesRepository.save(new Articles(name));
  }

  @Override
  public void createArticle(Articles article)
  {
    articlesRepository.save(article);
  }

  @Override
  public List<Articles> readArticles()
  {
    return (List<Articles>) articlesRepository.findAll();
  }

  @Override
  public Articles findArticle(Integer id)
  {
    Articles desiredArt = findArticlesById(id);
    return desiredArt;
  }

  @Override
  public void updateArticle(Integer id, Articles article)
  {
    Articles articleOld = articlesRepository.findById(id)
            .orElseThrow(() -> new ArticleNotFoundException("Invalid ID."));
    articleOld.setName(article.getName());
    articlesRepository.save(articleOld);
  }

  @Override
  public void deleteArticles()
  {
    operationsRepository.deleteAll();
    articlesRepository.deleteAll();
  }

  @Override
  public void deleteByArticleId(Integer id)
  {
    articlesRepository.deleteOperationsByArticleId(id);
    articlesRepository.deleteById(id);
  }

  @Override
  public Articles findArticlesById(Integer id)
  {
    Optional<Articles> optionalArt = articlesRepository.findById(id);
    if(optionalArt.isPresent())
    {
      return optionalArt.get();
    }
    else
    {
      throw new ArticleNotFoundException("Can't find the article.");
    }
  }
}
