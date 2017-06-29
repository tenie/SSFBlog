package net.tenie.pojo;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.javalite.activejdbc.annotations.Table;


@Table("blog_tag")	//指定对应数据的表面, 可以使用默认的如类名为Foo ,对应表名为foos
public class BlogTag  extends Model {}
 
 
