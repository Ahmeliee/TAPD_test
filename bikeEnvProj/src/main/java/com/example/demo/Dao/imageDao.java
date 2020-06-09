package com.example.demo.Dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.beans.image;

import java.util.List;

@Mapper
@Repository
public interface imageDao {
    public List<image> get_image(@Param("Filename")String Filename);
    public String get_name(@Param("Image_Id")Integer Image_Id);
    public List<String>get_all();
}
