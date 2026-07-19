package net.javaguides.banking.controller;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins="*")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService){ this.accountService=accountService; }
    @PostMapping public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto dto){ return new ResponseEntity<>(accountService.createAccount(dto), HttpStatus.CREATED); }
    @GetMapping("/{id}") public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){ return ResponseEntity.ok(accountService.getAccountById(id)); }
    @PutMapping("/{id}/deposit") public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> req){ return ResponseEntity.ok(accountService.deposit(id, req.get("amount"))); }
    @PutMapping("/{id}/withdraw") public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> req){ return ResponseEntity.ok(accountService.withdraw(id, req.get("amount"))); }
    @GetMapping public ResponseEntity<List<AccountDto>> getAllAccounts(){ return ResponseEntity.ok(accountService.getAllAccounts()); }
    @DeleteMapping("/{id}") public ResponseEntity<String> deleteAccount(@PathVariable Long id){ accountService.deleteAccount(id); return ResponseEntity.ok("Deleted"); }
}
