package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Dao.ScoreResultDao;
import com.example.demo.beans.ScoreResult;

import java.util.List;

@Service
public class ScoreResultService {
    @Autowired
    private ScoreResultDao srDao;
    public List<ScoreResult> select(String Filename){
        List<ScoreResult> sr=srDao.select_score(Filename);
        return sr;
    }
    public List<String> select_all(){
        List<String>l=srDao.select_all();
        return l;
    }
    public List<String>select_location_by_beauty(String score){
        List<String>l=srDao.select_location_by_beauty(score);
        return l;
    }
    public List<String>select_location_by_sec(String score){
        List<String>l=srDao.select_location_by_sec(score);
        return l;
    }
    public List<String>select_location_by_comf(String score){
        List<String>l=srDao.select_location_by_comf(score);
        return l;
    }
    public List<String>select_location_by_faci(String score){
        List<String>l=srDao.select_location_by_faci(score);
        return l;
    }
    public List<String>select_location_by_total(String score_a,String score_b){
        List<String>l=srDao.select_location_by_total(score_a,score_b);
        return l;
    }
}
