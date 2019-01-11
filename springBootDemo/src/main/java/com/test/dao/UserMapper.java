package com.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.entity.User;

/**
 * 数据库映射
 * @author john
 *
 */
@Mapper
public interface UserMapper {
	/**
	 * 模糊查询
	 * @param username 用户名
	 * @return
	 */
	@Select("SELECT * FROM USER WHERE USERNAME like CONCAT('%',#{username},'%') ")
    User select(@Param("username") String username);
	
	/**
	 * 根据用户名查询 
	 * @param username
	 * @return
	 */
	@Select("SELECT * FROM USER WHERE USERNAME = #{username} ")
    User selectSingle(@Param("username") String username);

    @Insert("INSERT INTO USER(USERNAME, PASSWORD) VALUES(#{username}, #{password})")
    int insert(@Param("username") String username, @Param("password") String password);
    
    @Delete("DELETE FROM USER WHERE ID =(#{id})")
    int delete(@Param("id")String id);
    
    @Update("UPDATE USER SET PASSWORD=#{password} where id=#{id}")
    int update(@Param("id")String id,@Param("password")String password);
    
    @Select("select * from user")
    List<User> selectAll();
}
