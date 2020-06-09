package com.example.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.ScoreResultService;
import com.example.demo.beans.ScoreResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
@RequestMapping("/green/*")
public class Green {
    private String path="C:\\pic\\";
    private String filename="";

    @RequestMapping(value = "upload",method ={RequestMethod.POST})
    @ResponseBody
    public String upload(HttpServletRequest request){
        String user=(String) request.getSession().getAttribute("user");
        String path = "C:\\pic\\"+user+"\\";		//要遍历的路径
        MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("picture");//file是form-data中二进制字段对应的name
        //判断文件夹是否存在,不存在则创建
        File file=new File(path);

        if(!file.exists()){
            file.mkdirs();
        }
        String originalFileName = multipartFile.getOriginalFilename();//获取原始图片的扩展名
        String newFileName = originalFileName;
        String newFilePath=path+newFileName; //新文件的路径
        filename=newFilePath;


        try {
            multipartFile.transferTo(new File(newFilePath));//将传来的文件写入新建的文件
            System.out.println("上传图片成功进行上传文件测试");
            return newFileName;
        }catch (IllegalStateException e ) {
            //处理异常
        }catch(IOException e1){
            //处理异常
        }
        return "success";


    }


    @RequestMapping("/score")
    @ResponseBody
    public String getScore(){
        String[] arguments = new String[] {"python", "D:\\Course\\产学研\\FCN.tensorflow-master\\FCN.tensorflow-master\\FCN.py",filename};
        String out="";
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String line = null;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if(line.contains("评分结果"))out=line.split(":")[1];
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
      return out;
    }
}
