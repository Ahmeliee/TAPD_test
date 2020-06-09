package com.example.demo.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.beans.user;
@Mapper
@Repository
public interface loginDao {
    public  user select_user(@Param("username")String username);
    public  void insert_user(@Param("username")String username,@Param("password")String password);
}
