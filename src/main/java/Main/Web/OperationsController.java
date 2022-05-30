package Main.Web;

import Main.Entity.Articles;
import Main.Entity.Balance;
import Main.Entity.Operations;
import Main.Services.ArticlesServices;
import Main.Services.BalanceServices;
import Main.Services.OperationsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operations")
public class OperationsController
{
  @Autowired
  private ArticlesServices articlesServices;

  @Autowired
  private BalanceServices balanceServices;

  @Autowired
  private OperationsServices operationsServices;

  public OperationsController(ArticlesServices articlesServices,
                               BalanceServices balanceServices, OperationsServices operationsServices)
  {
    this.articlesServices = articlesServices;
    this.balanceServices = balanceServices;
    this.operationsServices = operationsServices;
  }

  @GetMapping("/operations")
  public String getOperations(Model model)
  {
    model.addAttribute("operations", operationsServices.readOperations());
    return "/operations/operations";
  }

  @GetMapping("/getOperation/{id}")
  public String getById(@PathVariable("id") Integer id, Model model)
  {
    model.addAttribute("operation", operationsServices.findOperation(id));
    return "/operations/operation";
  }

  @GetMapping("/updateOperation/{id}")
  public String updateOperation(Model model, @PathVariable("id") Integer id)
  {
    model.addAttribute("operation", operationsServices.findOperation(id));
    model.addAttribute("articles", articlesServices.readArticles());
    model.addAttribute("balances", balanceServices.readBalances());
    return "/operations/updateOperation";
  }

  @PostMapping("/updateOperation/{id}")
  public String updateOperationReal(@ModelAttribute("operation") Operations operation,
                                    @PathVariable("id") Integer id)
  {
    operationsServices.updateOperation(id, operation);
    return "redirect:/operations/operations";
  }

  @PostMapping("/delete")
  public String deleteOperations()
  {
    operationsServices.deleteOperations();
    return "redirect:/operations/operations";
  }

  @PostMapping("/deleteOperation/{id}")
  public String deleteById(@PathVariable("id") Integer id)
  {
    operationsServices.deleteOperationById(id);
    return "redirect:/operations/operations";
  }

  @GetMapping("/addOperation")
  public String addOperation(@ModelAttribute("operation") Operations operation, Model model)
  {
    model.addAttribute("articles", articlesServices.readArticles());
    model.addAttribute("balances", balanceServices.readBalances());
    return "/operations/addOperation";
  }

  @PostMapping("/addOperation")
  public String addOperationReal(@ModelAttribute("operation") Operations operation,
                                 @ModelAttribute("articles") Articles article,
                                 @ModelAttribute("balances") Balance balance)
  {
    operationsServices.createOperation(operation);
    return "redirect:/operations/operations";
  }

  @PostMapping("/getByArticleName")
  public String getByArticleName(@RequestParam String name, Model model)
  {
    model.addAttribute("operations", operationsServices.findOperationsByArticleName(name));
    return "/operations/operations";
  }

  @GetMapping("/getByGreaterDebit")
  public String getByGreaterDebit(Model model)
  {
    model.addAttribute("operations", operationsServices.findOperationsByGreaterDebit());
    return "/operations/operations";
  }

  @GetMapping("/getByGreaterCredit")
  public String getByGreaterCredit(Model model)
  {
    model.addAttribute("operations", operationsServices.findOperationsByGreaterCredit());
    return "/operations/operations";
  }

  @PostMapping("/deleteByArticleName")
  public String deleteByArticleName(@RequestParam String name)
  {
    operationsServices.deleteOperationByArticle(name);
    return "redirect:/operations/operations";
  }

  @PostMapping("/getByArticleId")
  public String getByArticleId(@RequestParam Integer id, Model model)
  {
    model.addAttribute("operations", operationsServices.findOperationsByArticleId(id));
    model.addAttribute("articles", articlesServices.readArticles());
    return "/operations/operations";
  }

  @PostMapping("/getAmountById")
  public String getAmountById(@RequestParam Integer id, Model model)
  {
    model.addAttribute("operation", operationsServices.findAmountByOperationId(id));
    return "/operations/operationAmount";
  }
}