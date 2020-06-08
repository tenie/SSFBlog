package net.tenie.myblog.mapper;
 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import net.tenie.myblog.entity.Blog;

import java.util.List;
 
@Mapper
@Component
public interface BlogMapper {

 
    @Select("SELECT * FROM BLOG")
    List<Blog> findAll();
    
    @Select("SELECT * FROM BLOG where post_title like '%${val}%'")
    List<Blog> WhereAllPostTitle (@Param("val") String val);
    
    @Select("SELECT * FROM BLOG where post_title like '%${val}%'  and show_content = 1")
    List<Blog> WherePortionPostTitle(@Param("val") String val);
    
    @Select("SELECT * FROM BLOG where id = #{id} and show_content = 1")
    Blog findByIdWithShowContent(@Param("id") Long id);

    /**
     * 根据id查询 
     */
    @Select("SELECT * FROM BLOG WHERE id = #{id}")
    Blog findById(@Param("id") Long id);
    
    

    /**
     * 保存 
     */
    int saveBlog(@Param("blog") Blog blog);
    int update(@Param("blog") Blog blog);
    
    @Update("update blog set Post_Like = #{likes} where id = #{id}")
    int updateLikes(@Param("id")Long id, @Param("likes") int likes);
    
    @Update("update blog set show_content = #{val} where id = #{id}")
    int updateShowContent(@Param("id")Long id, @Param("val") int val);
    
    @Update("update blog set top = #{val} where id = #{id}")
    int updateTop(@Param("id")Long id, @Param("val") int val);
    

    /**
     * 删除用户
     * 
     */
    int deleteById(@Param("id") Long id);

}
