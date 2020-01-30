package com.jtrace.zeus.jvm.parameters.mapper;

import com.jtrace.zeus.jvm.parameters.parameters.JavaInfoEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xule05
 * @date 2020/1/30 下午1:58
 */
@Repository
@Mapper
public interface JavaInfoMapper {
    @Insert("INSERT INTO `tb_java_info`(`hostname`, `java_info`,`env`,`create_time`,`modify_time`) VALUES(#{hostname},#{javaInfo},#{env},now(),now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertOne(JavaInfoEntity entity);

    @Select("SELECT * from tb_java_info where hostname=#{hostname}")
    JavaInfoEntity selectOneByName(@Param("hostname") String hostname);

    @Select("SELECT * from tb_java_info")
    List<JavaInfoEntity> selectAll();
}
