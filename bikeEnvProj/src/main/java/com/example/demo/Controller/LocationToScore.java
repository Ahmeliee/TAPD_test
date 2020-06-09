package com.example.demo.Controller;

import com.example.demo.beans.ScoreResult;
import com.example.demo.beans.image;
import com.example.demo.service.ScoreResultService;
import com.example.demo.service.imageService;
import javafx.scene.chart.Axis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import com.example.demo.Utils.CSVutils;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/locationScore/*")
public class LocationToScore {

    @Autowired
    private ScoreResultService srs;
    private imageService imgsr;
    @RequestMapping("map")
    public String show_map(){
        return "map";
    }
    @RequestMapping("handle")
    @ResponseBody
    public void dataHandle(){
        List<String> l=srs.select_all();

        int num=l.size();
        CSVutils csvUtils=new CSVutils();
        List<List<String>> locs=new ArrayList<List<String>>();

        for(int i=0;i<num;i++){
            List<String> list=new ArrayList<>();
            String loc=l.get(i);
            String [] temp=loc.split("_");
            String lng=temp[0];
            String lat=temp[1];
            list.add(lng);
            list.add(lat);
            if(!locs.contains(list)) {
                locs.add(list);
            }
        }
        System.out.println(locs);
        num=locs.size();
        csvUtils.createCSVFile(locs,"D:/","pngLoc");
    }
    //获得请求为http://localhost:8081/locationScore/selectScore？location=xxx类型的参数
    //测试：114.269968，30.617831
    @RequestMapping(value="selectScore",method ={RequestMethod.POST})
    @ResponseBody
    public Map selectScore(HttpServletRequest request){
        String longtitude=request.getParameter("long").replaceAll("\"","");
        String latitude=request.getParameter("lati").replaceAll("\"","");
        longtitude=longtitude.replaceAll(" ","");
        latitude=latitude.replaceAll(" ","");
        String location=longtitude+","+latitude;
        System.out.println(location);
        String strs[]=location.split(",");
        String Filename=strs[0]+"_"+strs[1];
        List<ScoreResult>l=srs.select(Filename);
        int num=l.size();
        float beauty = 0;
        float facility = 0;
        float sec = 0;
        float comf = 0;
        for(int i=0;i<num;i++){
            beauty=beauty+Float.valueOf(l.get(i).getBeauty());
            comf=comf+Float.valueOf(l.get(i).getComfort());
            sec=sec+Float.valueOf(l.get(i).getSecurity());
            facility=facility+Float.valueOf(l.get(i).getFacility_availability());
        }
        beauty=beauty/num;
        comf=comf/num;
        sec=sec/num;
        facility=facility/num;
        Map map=new HashMap();
        map.put("beauty",Float.toString(beauty));
        map.put("comfort",Float.toString(comf));
        map.put("security",Float.toString(sec));
        map.put("facility",Float.toString(facility));
        System.out.println(map);
        return map;
    }
    @RequestMapping(value="marker",method ={RequestMethod.GET})
    @ResponseBody
    public List<Map>get_marker(){
        List<String>images=srs.select_all();
        List<Map>l=new LinkedList<>();
        for(int i=0;i<images.size();i++){
            String name=images.get(i);
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
            l.add(map);
        }
        return l;
    }



}
