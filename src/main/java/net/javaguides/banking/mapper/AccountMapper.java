package net.javaguides.banking.mapper;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
public class AccountMapper {
    public static Account mapToAccount(AccountDto dto){
        Account acc = new Account();
        acc.setId(dto.getId());
        acc.setAccountHolderName(dto.getAccountHolderName());
        acc.setBalance(dto.getBalance());
        return acc;
    }
    public static AccountDto mapToAccountDto(Account acc){
        return new AccountDto(acc.getId(), acc.getAccountHolderName(), acc.getBalance());
    }
}
