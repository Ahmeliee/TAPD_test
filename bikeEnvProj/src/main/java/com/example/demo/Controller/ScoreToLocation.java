package com.example.demo.Controller;


import com.example.demo.beans.ScoreResult;
import com.example.demo.service.ScoreResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/ScoreToLocation/*")
public class ScoreToLocation {
    @Autowired
    private ScoreResultService srs;
    private ScoreResult sr;
    //获得请求为http://localhost:8081/ScoreToLocation/getLocation?type=Beauty&score=9.2类型的参数
    @RequestMapping("getLocation")
    @ResponseBody
    public List<Map>getLocation(HttpServletRequest request){
        String score_a=request.getParameter("score_a").replaceAll("\"","");
        String score_b=request.getParameter("score_b").replaceAll("\"","");

        List<String>l=srs.select_location_by_total(score_a,score_b);

        List<Map>ls=new LinkedList<>();
        for(int i=0;i<l.size();i++){
            String name=l.get(i);
            String longtitude=name.split("_")[0];
            String latitude=name.split("_")[1];
            String point=longtitude+"|"+latitude;
            String pic=longtitude+","+latitude;
            Map icon=new HashMap();
            icon.put("w",21);
            icon.put("h",21);
            icon.put("l",0);
            icon.put("t",0);
            icon.put("x",6);
            icon.put("lb",5);
            //{title:"街景1",point:"114.407644|30.524347",pic:"test1.png",icon:{w:21,h:21,l:0,t:0,x:6,lb:5}
            Map map=new HashMap();
            map.put("title","街景");
            map.put("point",point);
            map.put("pic",pic);
            map.put("icon",icon);
            ls.add(map);
        }
        return ls;
    }
}
