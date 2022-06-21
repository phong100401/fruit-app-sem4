package com.example.restapiprojectsem4.config;

import com.example.restapiprojectsem4.entity.Account;
import com.example.restapiprojectsem4.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if(!optionalAccount.isPresent()){
            return null;
        }
        Account account = optionalAccount.get();
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if(account.getRole() == 1){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        }else if (account.getRole() == 2){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        UserDetails userDetails = new User(account.getUsername(), account.getPassword(), simpleGrantedAuthorities);
        return userDetails;
    }
}
