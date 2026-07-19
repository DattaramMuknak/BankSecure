package net.javaguides.banking.service.impl;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) { this.accountRepository = accountRepository; }
    @Override public AccountDto createAccount(AccountDto dto){ Account acc = AccountMapper.mapToAccount(dto); return AccountMapper.mapToAccountDto(accountRepository.save(acc)); }
    @Override public AccountDto getAccountById(Long id){ Account acc = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found")); return AccountMapper.mapToAccountDto(acc); }
    @Override public AccountDto deposit(Long id, double amount){ Account acc = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found")); acc.setBalance(acc.getBalance()+amount); return AccountMapper.mapToAccountDto(accountRepository.save(acc)); }
    @Override public AccountDto withdraw(Long id, double amount){ Account acc = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found")); if(acc.getBalance()<amount) throw new RuntimeException("Insufficient"); acc.setBalance(acc.getBalance()-amount); return AccountMapper.mapToAccountDto(accountRepository.save(acc)); }
    @Override public List<AccountDto> getAllAccounts(){ return accountRepository.findAll().stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList()); }
    @Override public void deleteAccount(Long id){ accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found")); accountRepository.deleteById(id); }
}
