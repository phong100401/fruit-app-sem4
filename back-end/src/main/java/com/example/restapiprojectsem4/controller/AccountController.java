package com.example.restapiprojectsem4.controller;

import com.example.restapiprojectsem4.dto.AccountDTO;
import com.example.restapiprojectsem4.entity.Account;
import com.example.restapiprojectsem4.response.RESTPagination;
import com.example.restapiprojectsem4.response.RESTResponse;
import com.example.restapiprojectsem4.service.account.AccountService;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST, path = "register")
    public Account register(@RequestBody AccountDTO accountDTO){
        return accountService.create(accountDTO);
    }

    @RequestMapping(method = RequestMethod.GET, path = "list")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                   @RequestParam(name = "pageSize", defaultValue = "4") int pageSize
    ){
        ObjectFilter filter = ObjectFilter.ObjectFilterBuilder.anObjectFilter()
                .withPage(page)
                .withPageSize(pageSize)
                .build();
        Page<Account> listAcc = accountService.getAll(filter);
        List<Account> list = new ArrayList<>();
        for (Account a:
             listAcc.getContent()) {
            list.add(a);
        }
        if(listAcc.getContent().size() != 0){
            return new ResponseEntity<>(new RESTResponse.Success()
                    .addDatas(list)
                    .setPagination(new RESTPagination(listAcc.getNumber() + 1,listAcc.getSize() ,listAcc.getTotalPages(), listAcc.getTotalElements()))
                    .setStatus(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(method = RequestMethod.GET,path = "detail/{id}")
    public ResponseEntity<?> getUserDetail(@PathVariable int id){
        Account account = accountService.accountDetail(id);
        if (account != null){
            return new ResponseEntity<>(account,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(method = RequestMethod.GET,path = "update")
//    public ResponseEntity<?> updateUser(@RequestParam(name = "accountId") int accountId, @RequestBody Account account){
//        return new ResponseEntity<>(accountService.updateAccount(accountId,account),HttpStatus.BAD_REQUEST);
//    }
    @RequestMapping(method = RequestMethod.GET, path = "profile")
    public ResponseEntity<?> getProfile(){
        return new ResponseEntity<>(accountService.getProfile(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "upProfile")
    public ResponseEntity<?> updateProfile(@RequestBody AccountDTO accountDTO){
        Account account = accountService.updateProfile(accountDTO);
        if(account == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update failed");
        }else {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
    }
}
