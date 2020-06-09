package net.tenie.myblog.mapper;
 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import net.tenie.myblog.entity.ContactData;

import java.util.List;
 
@Mapper
@Component
public interface ContactDataMapper {

 
    @Select("SELECT * FROM CONTACT_DATA")
    List<ContactData> selectAllContactData();

    /**
     * 根据id查询 
     */
    @Select("SELECT * FROM CONTACT_DATA WHERE id = #{id}")
    ContactData selectContactDataById(@Param("id") Long id);

    /**
     * 保存 
     */
    int saveContactData(@Param("contactData") ContactData contactData);

    /**
     * 删除用户
     * 
     */
    int deleteById(@Param("id") Long id);

}
