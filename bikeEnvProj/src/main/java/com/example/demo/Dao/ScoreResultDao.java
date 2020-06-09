package com.example.demo.Dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.beans.ScoreResult;

import java.util.List;

@Mapper
@Repository
public interface ScoreResultDao {
    public List<ScoreResult> select_score(@Param("Filename")String Filename);
    public List<String> select_all();
    public List<String>select_location_by_beauty(@Param("score")String score);
    public List<String>select_location_by_sec(@Param("score")String score);
    public List<String>select_location_by_comf(@Param("score")String score);
    public List<String>select_location_by_faci(@Param("score")String score);
    public List<String>select_location_by_total(@Param("score_a")String score_a,@Param("score_b")String score_b);
}
