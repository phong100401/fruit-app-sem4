package com.example.restapiprojectsem4.service.account;

import com.example.restapiprojectsem4.dto.AccountDTO;
import com.example.restapiprojectsem4.entity.Account;
import com.example.restapiprojectsem4.repository.AccountRepository;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account create(AccountDTO accountDTO){
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setRole(1);
        account.setName(accountDTO.getName());
        account.setPhone(accountDTO.getPhone());
        account.setAddress(accountDTO.getAddress());
        account.setStatus("1");

        return accountRepository.save(account);
    }

    public Page<Account> getAll(ObjectFilter objectFilter){
        PageRequest pageRequest = PageRequest.of(objectFilter.getPage() - 1, objectFilter.getPageSize());
        return accountRepository.findAll(pageRequest);
    }

    public Account accountDetail(int accountId){
        Optional<Account> account = accountRepository.findById(accountId);
        return account.orElse(null);
    }

    public Account updateAccount(int accountId, Account account){
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){
            optionalAccount.get().setName(account.getName());
            optionalAccount.get().setPhone(account.getPhone());
            return accountRepository.save(optionalAccount.get());
        }
        return null;
    }

    public Optional<Account> findByUserName(String username){
        return accountRepository.findByUsername(username);
    }

    public Optional<Account> getProfile(){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Optional<Account> account = accountRepository.findByUsername(principal.getName());
        if(account.isPresent()){
            return account;
        }
        return null;
    }

    public Account updateProfile(AccountDTO accountDTO){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Optional<Account> account = accountRepository.findByUsername(principal.getName());
        if(account.isPresent()){
            account.get().setName(accountDTO.getName());
            account.get().setPhone(accountDTO.getPhone());
            account.get().setAddress(accountDTO.getAddress());
            return accountRepository.save(account.get());
        }
        return null;
    }
}
