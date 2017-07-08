CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_title` varchar(100) DEFAULT NULL,
  `post_subtitle` varchar(100) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `post_content` mediumtext,
  `text_length` int(6) DEFAULT NULL,
  `read_quantity` int(11) NOT NULL DEFAULT '0',
  `comment` int(6) DEFAULT NULL,
  `like` int(10) DEFAULT NULL,
  `top` int(1) DEFAULT '1' COMMENT '1表示不置顶, 0表示置顶',
  `show_content` int(1) DEFAULT '1' COMMENT '1表示true , 0表示false',
  PRIMARY KEY (`id`,`read_quantity`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8

CREATE TABLE `blog_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) DEFAULT NULL,
  `tag` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='标签'


CREATE TABLE `contact_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `message` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8