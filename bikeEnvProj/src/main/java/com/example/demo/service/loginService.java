package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.loginDao;
import com.example.demo.beans.user;
@Service
public class loginService {
    @Autowired
    private loginDao ld;

    public int select_user(String username,String password){

        user u=ld.select_user(username);
        if(u==null)return 0;
        if(!u.getPassword().equals(password))return 1;
        return 2;
    }
    public void insert_user(String username,String password){

        ld.insert_user(username,password);

    }

}
