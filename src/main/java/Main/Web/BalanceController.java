package Main.Web;

import Main.Entity.Balance;
import Main.Services.BalanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/balance")
public class BalanceController
{
  @Autowired
  private BalanceServices balanceService;

  public BalanceController(BalanceServices balanceService)
  {
    this.balanceService = balanceService;
  }

  @GetMapping("/balances")
  public String getBalances(Model model)
  {
    model.addAttribute("balances", balanceService.readBalances());
    return "/balance/balances";
  }

  @GetMapping("/getBalance/{id}")
  public String getById(@PathVariable("id") Integer id, Model model)
  {
    model.addAttribute("balance", balanceService.findBalance(id));
    return "/balance/balance";
  }

  @GetMapping("/updateBalance/{id}")
  public String updateBalance(@PathVariable("id") Integer id, Model model)
  {
    model.addAttribute("balance", balanceService.findBalance(id));
    return "/balance/updateBalance";
  }

  @PostMapping("/updateBalance/{id}")
  public String updateBalanceReal(@ModelAttribute("balance") Balance balance, @PathVariable("id") Integer id)
  {
    balanceService.updateBalance(id, balance);
    return "redirect:/balance/balances";
  }

  @PostMapping("/delete")
  public String deleteBalances()
  {
    balanceService.deleteBalances();
    return "redirect:/balance/balances";
  }

  @PostMapping("/deleteBalance/{id}")
  public String deleteById(@PathVariable("id") Integer id)
  {
    balanceService.deleteByBalanceId(id);
    return "redirect:/balance/balances";
  }

  @GetMapping("/addBalance")
  public String addBalance(@ModelAttribute("balance") Balance balance)
  {
    return "/balance/addBalance";
  }

  @PostMapping("/addBalance")
  public String addBalanceReal(@ModelAttribute("balance") Balance balance)
  {
    balanceService.createBalance(balance);
    return "redirect:/balance/balances";
  }
}
