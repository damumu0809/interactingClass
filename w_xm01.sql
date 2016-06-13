/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : w_xm01

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-06-13 23:00:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` bigint(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `topic` varchar(100) NOT NULL,
  `text` varchar(5000) NOT NULL,
  `like` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `comment` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `hasAcc` int(11) NOT NULL COMMENT '0没有 1有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '1465813430004', '1111', '1', 'mmmmm', '0', '0', '1');
INSERT INTO `blog` VALUES ('2', '1465818736959', '1111', '呃呃呃', '方法烦烦烦', '0', '0', '1');
INSERT INTO `blog` VALUES ('3', '1465821254345', '1111', 'g', '官方官方vcd', '0', '0', '1');
INSERT INTO `blog` VALUES ('4', '1465821389098', '1111', '图片', 'nnnn', '0', '0', '1');
INSERT INTO `blog` VALUES ('5', '1465821484266', '1111', '11', 'ddsd', '0', '0', '1');

-- ----------------------------
-- Table structure for `blog_accessory`
-- ----------------------------
DROP TABLE IF EXISTS `blog_accessory`;
CREATE TABLE `blog_accessory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) NOT NULL,
  `type` varchar(20) NOT NULL COMMENT '视频 音频  图片',
  `href` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_accessory
-- ----------------------------
INSERT INTO `blog_accessory` VALUES ('1', '1', 'video', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/[周杰伦-袁咏琳]画沙.mp4');
INSERT INTO `blog_accessory` VALUES ('2', '2', 'video', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/1(1)(1).mp4');
INSERT INTO `blog_accessory` VALUES ('3', '3', 'video', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/20141105_104950.mp4');
INSERT INTO `blog_accessory` VALUES ('4', '4', 'picture', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/2015-01-01 18-14-08.bmp');
INSERT INTO `blog_accessory` VALUES ('5', '4', 'picture', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/2015-01-01 18-17-10.bmp');
INSERT INTO `blog_accessory` VALUES ('6', '5', 'picture', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/00.jpg');
INSERT INTO `blog_accessory` VALUES ('7', '5', 'picture', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/0.gif');
INSERT INTO `blog_accessory` VALUES ('8', '5', 'picture', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/0.jpg');
INSERT INTO `blog_accessory` VALUES ('9', '5', 'picture', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/0.png');
INSERT INTO `blog_accessory` VALUES ('10', '5', 'picture', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/1.gif');

-- ----------------------------
-- Table structure for `blog_comment`
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) NOT NULL,
  `person` varchar(20) NOT NULL,
  `time` bigint(20) NOT NULL,
  `reply_id` int(11) NOT NULL COMMENT '回复哪条评论，若是评论该篇博客，为0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `blog_like`
-- ----------------------------
DROP TABLE IF EXISTS `blog_like`;
CREATE TABLE `blog_like` (
  `id` int(11) NOT NULL,
  `blog_id` int(11) NOT NULL,
  `like_person` varchar(20) NOT NULL,
  `like_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_like
-- ----------------------------

-- ----------------------------
-- Table structure for `getbackinfo`
-- ----------------------------
DROP TABLE IF EXISTS `getbackinfo`;
CREATE TABLE `getbackinfo` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `bkey` varchar(150) DEFAULT NULL,
  `timeapplied` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of getbackinfo
-- ----------------------------
INSERT INTO `getbackinfo` VALUES ('3', 'azhouqingyu95', '358323d3efbe5189fa2cf6b242123c02', '2016-05-25 14:58:01');

-- ----------------------------
-- Table structure for `homework`
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `href` varchar(100) NOT NULL,
  `owner` varchar(20) NOT NULL,
  `time` bigint(20) NOT NULL,
  `taskNum` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('00000000001', '移动互动课堂组.doc', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/移动互动课堂组.doc', 'xiaomu', '1464437134698', '1');
INSERT INTO `homework` VALUES ('00000000002', '2.ppt', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/2.ppt', 'xiaomu', '1464440148418', '2');

-- ----------------------------
-- Table structure for `issuework`
-- ----------------------------
DROP TABLE IF EXISTS `issuework`;
CREATE TABLE `issuework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `deadLine` bigint(20) NOT NULL,
  `time` bigint(20) NOT NULL,
  `accessory` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of issuework
-- ----------------------------
INSERT INTO `issuework` VALUES ('1', '周报', '1464710400000', '1464436429197', null);
INSERT INTO `issuework` VALUES ('2', 'PPT', '1469980800000', '1464439904160', null);
INSERT INTO `issuework` VALUES ('3', 'PPT', '1464710400000', '1464440932633', null);
INSERT INTO `issuework` VALUES ('4', 'ss', '1469980800000', '1464768194176', null);
INSERT INTO `issuework` VALUES ('5', '我是智障', '1467302400000', '1465208241453', null);

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(36) NOT NULL,
  `IDENTITY` varchar(40) NOT NULL,
  `MENU_NAME` varchar(32) NOT NULL,
  `MENU_HREF` varchar(40) DEFAULT NULL,
  `LI_CLASS` varchar(50) DEFAULT NULL,
  `SPAN_CLASS` varchar(50) DEFAULT NULL,
  `A_CLASS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('13', '学生', '签到', null, null, null, 'scroll');
INSERT INTO `menu` VALUES ('14', '学生', '作业中心', null, null, null, 'scroll');
INSERT INTO `menu` VALUES ('15', '学生', '博客', null, null, null, 'scroll');
INSERT INTO `menu` VALUES ('16', '教师', '课件管理', null, null, null, 'scroll');
INSERT INTO `menu` VALUES ('17', '教师', '签到结果', null, null, null, 'scroll');
INSERT INTO `menu` VALUES ('18', '教师', '作业情况', null, null, null, 'scroll');
INSERT INTO `menu` VALUES ('19', '教师', '博客', null, null, null, 'scroll');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(36) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `sex` varchar(7) DEFAULT NULL,
  `mailaddr` varchar(50) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `remarks` varchar(2014) DEFAULT NULL,
  `identity` varchar(50) DEFAULT NULL,
  `loginstatus` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'admin', '2c2439e543b495f6000ef81b276f911e', '我是老大', null, null, '2016-05-09 21:26:01', '管理员帐号', '管理员', '0');
INSERT INTO `userinfo` VALUES ('6', '125889', 'e3ceb5881a0a1fdaad01296d7554868d', '23', 'male', '135@15.s', '2016-05-17 19:38:17', null, '游客', '0');
INSERT INTO `userinfo` VALUES ('7', 'azhouqingyu95', 'e10adc3949ba59abbe56e057f20f883e', 'aMinO', 'male', '752458950@qq.com', '2016-05-17 20:06:13', null, '学生', '0');
INSERT INTO `userinfo` VALUES ('8', 'damumu', '8f36600e13af12dac02e91d2bd881fb8', 'xiaomu', 'female', 'a@b.c', '2016-06-01 14:58:45', null, '教师', '0');
INSERT INTO `userinfo` VALUES ('9', '1111', 'fcea920f7412b5da7be0cf42b8c93759', '1111', 'male', 'ac@c.v', '2016-06-04 22:55:57', null, '学生', '0');

-- ----------------------------
-- Table structure for `vote`
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(200) NOT NULL,
  `issuePerson` varchar(30) NOT NULL,
  `issueTime` bigint(20) NOT NULL,
  `expireTime` bigint(20) NOT NULL,
  `option1` varchar(200) NOT NULL,
  `number1` int(11) NOT NULL,
  `option2` varchar(200) NOT NULL,
  `number2` int(11) NOT NULL,
  `option3` varchar(200) DEFAULT NULL,
  `number3` int(11) DEFAULT NULL,
  `option4` varchar(200) DEFAULT NULL,
  `number4` int(11) DEFAULT NULL,
  `option5` varchar(200) DEFAULT NULL,
  `number5` int(11) DEFAULT NULL,
  `option6` varchar(200) DEFAULT NULL,
  `number6` int(11) DEFAULT NULL,
  `multipleChoice` tinyint(4) NOT NULL COMMENT '0 单选  1 多选',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES ('1', '世界上最好的语言是？', 'teacher', '1464428333365', '1462204800000', 'C', '0', 'C++', '0', 'JAVA', '0', 'PHP', '0', 'null', '0', 'null', '0', '0');
INSERT INTO `vote` VALUES ('2', '移动互动课堂组棒不棒？', 'teacher', '1464428416831', '1467338400000', '非常棒', '1', '很棒', '1', 'null', '0', 'null', '0', 'null', '0', 'null', '0', '0');
INSERT INTO `vote` VALUES ('3', '研究与开发课上你收获了什么？', 'teacher', '1464451207716', '1467302400000', '项目流程', '1', '代码量', '1', '具体编程知识', '1', '微笑面对一切', '1', '熬夜', '0', 'null', '0', '1');
INSERT INTO `vote` VALUES ('4', '小木美不美', 'teacher', '1464514875571', '1467302400000', '美', '0', '超美', '1', 'null', '0', 'null', '0', 'null', '0', 'null', '0', '0');

-- ----------------------------
-- Table structure for `voterecord`
-- ----------------------------
DROP TABLE IF EXISTS `voterecord`;
CREATE TABLE `voterecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voteId` int(11) NOT NULL,
  `optionId` int(11) NOT NULL,
  `voteTime` bigint(20) NOT NULL,
  `person` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of voterecord
-- ----------------------------
INSERT INTO `voterecord` VALUES ('1', '2', '1', '1464432855935', 'xiaomu');
INSERT INTO `voterecord` VALUES ('2', '3', '1', '1464452556305', 'xiaomu');
INSERT INTO `voterecord` VALUES ('3', '3', '2', '1464452556305', 'xiaomu');
INSERT INTO `voterecord` VALUES ('4', '3', '3', '1464452556305', 'xiaomu');
INSERT INTO `voterecord` VALUES ('5', '3', '4', '1464452556305', 'xiaomu');
INSERT INTO `voterecord` VALUES ('6', '4', '2', '1464515939634', 'xiaomu');
INSERT INTO `voterecord` VALUES ('7', '2', '2', '1465043345571', 'damumu');
