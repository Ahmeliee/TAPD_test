package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.Utils.regexUtils;
import com.example.demo.service.loginService;
import com.example.demo.Dao.loginDao;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login/*")

public class login {
    @Autowired
    private loginService ls;
    private loginDao ld;
    //获得请求为http://localhost:8081/login/siginin?username=**&password=*类型的参数

    @RequestMapping(value="signin",method ={RequestMethod.POST})
    @ResponseBody
    public String sign_in(HttpServletRequest request){
        String username=request.getParameter("username").replaceAll("\"","");
        String password=request.getParameter("password").replaceAll("\"","");;
        System.out.println(username+","+password);
        regexUtils ru=new regexUtils();
        if(username.equals(""))return "username is null";
        if(password.equals(""))return "password is null";
        if(ru.isContainChinese(username))return "username is illegal";
        if(ru.isContainChinese(password))return "password is illegal";
        if(username.length()>20)return "username is illegal";
        if(password.length()>10)return "password is illegal";
        if(ls.select_user(username,password)==2){
            request.getSession().setAttribute("user",username);
            return "success";
        }
        else return"user is not exist";
    }
    @RequestMapping("loginhtml")
    public String login(){
        return "主界面";
    }
    //获得请求为http://localhost:8081/login/signup?username=**&password=*类型的参数
    @RequestMapping(value="signup",method ={RequestMethod.POST})
    @ResponseBody
    public String sign_up(HttpServletRequest request){
        String username=request.getParameter("username").replaceAll("\"","");
        String password=request.getParameter("password").replaceAll("\"","");
        String againpsd=request.getParameter("againpsd").replaceAll("\"","");

        regexUtils ru=new regexUtils();
        if(username.equals(""))return "username is null";
        if(password.equals(""))return "password is null";
        if(ru.isContainChinese(username))return "username is illegal";
        if(ru.isContainChinese(password))return "password is illegal";
        if(username.length()>20)return "username is illegal";
        if(password.length()>10)return "password is illegal";
        if(!password.equals(againpsd))return "not equal";
        if(ls.select_user(username,password)==1 || ls.select_user(username,password)==2)return "user is exist";
        ls.insert_user(username,password);
        return "success";
    }
    @RequestMapping(value="verify",method ={RequestMethod.GET})
    @ResponseBody
    public String verify(HttpServletRequest request){
        String user="";
        user=(String) request.getSession().getAttribute("user");
        return user;
    }
    @RequestMapping(value="log_out",method ={RequestMethod.GET})
    @ResponseBody
    public void log_out(HttpServletRequest request){

         request.getSession().removeAttribute("user");

    }
    @RequestMapping(value="env_audit",method ={RequestMethod.GET})
    public String env_audit(){
        return "骑行环境审计";
    }
    @RequestMapping(value="road_width",method ={RequestMethod.GET})
    public String road_width(){
        return "道路宽度评价";
    }
    @RequestMapping(value="road_green",method ={RequestMethod.GET})
    public String road_green(){
        return "道路绿化评价";
    }
    @RequestMapping(value="road_illegal",method ={RequestMethod.GET})
    public String road_illegal(){
        return "非法占用评价";
    }
    @RequestMapping(value="zizhu",method ={RequestMethod.GET})
    public String zizhu(){
        return "自助街景评分";
    }
    @RequestMapping(value="refer",method ={RequestMethod.GET})
    public String refer(){
        return "参考文档";
    }


}
