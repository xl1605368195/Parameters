package com.jtrace.zeus.jvm.parameters.mapper;

import com.jtrace.zeus.jvm.parameters.dao.GroupByDate;
import com.jtrace.zeus.jvm.parameters.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xule05
 * @date 2020/3/10 上午10:46
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * 插入数据
     */
    @Insert("INSERT INTO `tb_user_info`(`ip`,`datetime`,`location`,`create_time`,`modify_time`) " +
            "VALUES(#{ip},#{datetime}, #{location},now(), now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertOne(User user);

    /**
     * 批量导入数据
     */
    @Insert({
            "<script>",
            "insert into tb_user_info(ip, datetime, location,create_time,modify_time) values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{item.ip}, #{item.datetime}, #{item.location},now(),now())",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param(value = "list") List<User> list);

    @Select("SELECT date(datetime) AS day, count(*) AS count FROM tb_user_info GROUP BY day limit #{limitCount}")
    List<GroupByDate> groupByDay(int limitCount);
}
