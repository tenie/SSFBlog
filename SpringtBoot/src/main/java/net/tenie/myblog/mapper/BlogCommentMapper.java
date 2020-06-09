package net.tenie.myblog.mapper;
 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import net.tenie.myblog.entity.BlogComment;

import java.util.List;
 
@Mapper
@Component
public interface BlogCommentMapper {

 
    @Select("SELECT * FROM BLOG_COMMENT")
    List<BlogComment> findAll();
    
    @Select("SELECT * FROM BLOG_COMMENT where post_id= #{id}  and parent_id is null ")
    List<BlogComment> findByPostId(@Param("id") Long id);
    
    @Select("SELECT * FROM BLOG_COMMENT where post_id= #{PostId}  and parent_id = #{ParentId}")
    List<BlogComment> findByPostIdWithParentId(@Param("PostId") Long PostId , @Param("ParentId") Long ParentId );

    /**
     * 根据id查询 
     */
    @Select("SELECT * FROM BLOG_COMMENT WHERE id = #{id}")
    BlogComment findById(@Param("id") Long id);

    /**
     * 保存 
     */
    int saveBlogComment(@Param("blogComment") BlogComment blogComment);
    int update(@Param("blogComment") BlogComment blogComment);

    /**
     * 删除用户
     * 
     */
    int deleteById(@Param("id") Long id);

}
