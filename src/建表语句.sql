CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_title` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `post_subtitle` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `time` date DEFAULT NULL,
  `post_content` mediumtext COLLATE utf8mb4_bin,
  `text_length` int(6) DEFAULT '0',
  `read_quantity` int(11) NOT NULL DEFAULT '0',
  `comment` int(6) DEFAULT NULL,
  `post_like` int(10) DEFAULT '0',
  `top` int(1) DEFAULT '1' COMMENT '1表示不置顶, 0表示置顶',
  `show_content` int(1) DEFAULT '1' COMMENT '1表示true , 0表示false',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `RECORD_VERSION` int(11) DEFAULT '0',
  PRIMARY KEY (`id`,`read_quantity`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin


CREATE TABLE `blog_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '有父id的评论就是子评论',
  `name` varchar(40) DEFAULT NULL,
  `comment` varchar(4000) DEFAULT NULL,
  `url` varchar(80) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `comment_like` int(10) DEFAULT '0' COMMENT '评论里的赞',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `RECORD_VERSION` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4

CREATE TABLE `blog_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) DEFAULT NULL,
  `tag` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `RECORD_VERSION` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin




CREATE TABLE `contact_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL,
  `email` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  `message` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `RECORD_VERSION` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin




