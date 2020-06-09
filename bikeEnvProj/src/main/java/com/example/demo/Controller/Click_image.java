package com.example.demo.Controller;


import com.example.demo.beans.image;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.service.imageService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/getimg/*")
public class Click_image {
    @Autowired
    private imageService imgs;
    //获得请求为http://localhost:8081/getimg/img?location=114.269968,30.617831&position=0类型的参数
    //测试：114.269968，30.617831
    @RequestMapping("img")
    @ResponseBody
    public String getimg(HttpServletRequest request, HttpServletResponse response, @RequestParam("location")String location,@RequestParam("position")String position) throws IOException {
        String strs[]=location.split(",");
        String Filename=strs[0]+"_"+strs[1]+"_"+position+"_0.png";
        List<image> img=imgs.get_image(Filename);
        byte[]buffer= (byte[]) img.get(0).getImage();
        OutputStream  out= response.getOutputStream();
        out.write(buffer);
        return img.toString();

    }
    @RequestMapping("name")
    @ResponseBody
    public String getname(){
         String name=imgs.get_name(2);
        return name;
    }
}
