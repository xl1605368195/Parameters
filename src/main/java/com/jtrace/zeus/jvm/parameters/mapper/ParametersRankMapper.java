package com.jtrace.zeus.jvm.parameters.mapper;

import com.jtrace.zeus.jvm.parameters.parameters.ParametersRankEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xule05
 * @date 2020/1/30 下午12:31
 */
@Repository
@Mapper
public interface ParametersRankMapper {

    /**
     * 插入数据
     */
    @Insert("INSERT INTO `tb_jvm_parameters_rank`(`name`,`use_off_line`,`use_on_line`,`create_time`,`modify_time`) " +
            "VALUES(#{name},#{useOffLine}, #{useOnLine},now(), now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertOne(ParametersRankEntity entity);

    /**
     * 按照线下来排序
     */
    @Select("SELECT name,use_off_line from tb_jvm_parameters_rank order by use_off_line")
    List<ParametersRankEntity> rankOffLineParamters();

    /**
     * 按照线上来排序
     */
    @Select("SELECT name,use_on_line from tb_jvm_parameters_rank order by use_on_line")
    List<ParametersRankEntity> rankOnLineParamters();

    /**
     * 更新线下数据
     */
    @Update("UPDATE `tb_jvm_parameters_rank` SET use_off_line = #{useOffLine},update_time = now() WHERE name = #{name}")
    ParametersRankEntity updateOffLineCount(@Param("name") String name, @Param("useOffLine") int useOffLine);

    /**
     * 更新线上数据
     */
    @Update("UPDATE `tb_jvm_parameters_rank` SET use_on_line = #{useOnLine},update_time = now() WHERE name = #{name}")
    ParametersRankEntity updateOnLineCount(@Param("name") String name, @Param("useOnLine") int useOnLine);

}
