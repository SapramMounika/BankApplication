package Service;

import Entity.Account;
import Repository.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "accountService")
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @PostConstruct
    public void cr() {
        Account sc = new Account();
        sc.setAccountHolderName("Some name"); sc.setBalance(450);
        Account cr = createAccount(sc);
        System.out.println(cr);
    }

    public Account deposit(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }
    public Account withdraw(Long id, double amount){
        Account account=getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds ");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }
}
