package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.imageDao;
import com.example.demo.beans.image;

import java.util.List;

@Service
public class imageService {
    @Autowired
    private imageDao imaged;
    public List<image> get_image(String Filename){
        List<image> img=imaged.get_image(Filename);
        return img;
    }
    public String get_name(Integer image_id){
        String name=imaged.get_name(image_id);
        return name;
    }
    public List<String>get_all(){
        List<String>images=imaged.get_all();
        return images;
    }
}
