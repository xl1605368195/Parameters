package com.jtrace.zeus.jvm.parameters.mapper;

import com.jtrace.zeus.jvm.parameters.parameters.JvmParameterEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @author xule05
 * @date 2020/1/27 下午8:01
 */
@Repository
@Mapper
public interface JvmParametersMapper {

    @Select("SELECT * from tb_jvm_parameters where name=#{name}")
    JvmParameterEntity selectOneByName(@Param("name") String name);

//    @Select("SELECT * from user where username=#{username}")
//    JvmParameterEntity selectOneByUsername(@Param("username") String username);
//
//    @Select("SELECT * from user where id=#{id}")
//    JvmParameterEntity selectOneById(@Param("username") int id);
//
//    @Select("SELECT * from user where uuid=#{uuid}")
//    JvmParameterEntity selectOneByUuid(@Param("uuid") String uuid);
//
//    @Select("SELECT * from user")
//    List<JvmParameterEntity> selectAll();
//
//    @Insert("INSERT INTO `user`(`username`, `password`, `uuid`, `create_time`, `update_time`) " +
//            "VALUES(#{username},#{password}, #{uuid}, now(), now())")
//    @Options(useGeneratedKeys = true, keyColumn = "id")
//    int insertOne(JvmParameterEntity user);
//
//    @Update("UPDATE `user` SET password = #{password},update_time = now() WHERE id = #{id}")
//    JvmParameterEntity updatePasswordById(@Param("id") int id, @Param("password") String password);
//
//    @Update("UPDATE `user` SET password = #{password},update_time = now() WHERE uuid = #{uuid}")
//    JvmParameterEntity updatePasswordByUUID(@Param("uuid") String uuid, @Param("password") String password);
//
//    @Update("UPDATE `user` SET password = #{password},update_time = now() WHERE username = #{username}")
//    JvmParameterEntity updatePasswordByName(@Param("userName") String username, @Param("password") String password);

}
