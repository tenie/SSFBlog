package net.tenie.myblog.mapper;
 
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import net.tenie.myblog.entity.BlogTag; 

import java.util.List;
 
@Mapper
@Component
public interface BlogTagMapper {

 
    @Select("SELECT * FROM BLOG_TAG")
    List<BlogTag> selectAllBlogTag();

    /**
     * 根据id查询 
     */
    @Select("SELECT * FROM BLOG_TAG WHERE id = #{id}")
    BlogTag selectBlogTagById(@Param("id") Long id);
    
    @Select("SELECT * FROM BLOG_TAG WHERE blog_id = #{id}")
    List<BlogTag> findByBlogId(@Param("id") Long id);

    /**
     * 保存 
     */
    int saveBlogTag(@Param("blogtag") BlogTag blogtag);

    /**
     * 删除 
     * 
     */
    int deleteById(@Param("id") Long id);
    @Delete("delete from BLOG_TAG where BLOG_ID = #{id}")
    int deleteByBlogId(@Param("id") Long id); 

}
