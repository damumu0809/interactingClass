/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : class

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-05-24 20:17:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `homework`
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `href` varchar(100) NOT NULL,
  `owner` varchar(20) NOT NULL,
  `time` datetime NOT NULL,
  `taskNum` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('00000000009', '1.ppt', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/1.ppt', 'xiaomu', '2016-05-23 20:59:57', '1');
INSERT INTO `homework` VALUES ('00000000010', 'json.jar', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/json.jar', 'xiaomu', '2016-05-24 00:00:15', '2');
INSERT INTO `homework` VALUES ('00000000011', 'scunet.cn.zip', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/scunet.cn.zip', 'xiaomu', '2016-05-24 20:12:54', '16');

-- ----------------------------
-- Table structure for `issuework`
-- ----------------------------
DROP TABLE IF EXISTS `issuework`;
CREATE TABLE `issuework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `deadLine` date NOT NULL,
  `time` datetime NOT NULL,
  `accessory` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of issuework
-- ----------------------------
INSERT INTO `issuework` VALUES ('1', '立项计划书', '2016-05-31', '2016-05-16 15:46:11', null);
INSERT INTO `issuework` VALUES ('2', '需求分析', '2016-06-01', '2016-05-17 13:17:16', null);
INSERT INTO `issuework` VALUES ('3', '概要设计', '2016-06-01', '2016-05-17 13:20:04', null);
INSERT INTO `issuework` VALUES ('4', '项目分工', '2016-06-01', '2016-05-17 13:20:14', null);
INSERT INTO `issuework` VALUES ('5', '周报', '2016-06-01', '2016-05-17 13:25:08', null);
INSERT INTO `issuework` VALUES ('14', '学习servlet', '2016-11-01', '2016-05-23 21:46:06', null);
INSERT INTO `issuework` VALUES ('15', '完成文件上传', '2016-08-08', '2016-05-24 00:01:10', null);
INSERT INTO `issuework` VALUES ('16', '学习ajax', '2016-09-01', '2016-05-24 20:12:03', null);

-- ----------------------------
-- Table structure for `vote`
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `theme` varchar(200) NOT NULL,
  `issuePerson` varchar(30) NOT NULL,
  `issueTime` datetime NOT NULL,
  `expireTime` date NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES ('1', '这个页面写的怎么样', '小木', '2016-05-21 22:06:18', '2016-05-31', '好看', '1', '不好看', '0', 'null', '0', 'null', '0', 'null', '0', 'null', '0', '0');
INSERT INTO `vote` VALUES ('2', '研究与开发课上你学到了什么', 'teacher', '2016-05-21 22:07:03', '2016-05-31', '项目开发流程', '1', '具体编程语言知识', '1', '代码经验', '1', '问题处理能力', '1', '微笑面对一切', '1', '更能熬夜了', '0', '1');
INSERT INTO `vote` VALUES ('3', '宋仲基帅还是鹿晗帅', 'teacher', '2016-05-23 23:58:41', '2016-11-01', '宋仲基', '1', '鹿晗', '0', 'null', '0', 'null', '0', 'null', '0', 'null', '0', '0');
INSERT INTO `vote` VALUES ('7', '世界上最好的语言是？', 'teacher', '2016-05-24 15:06:28', '2016-10-01', 'JAVA', '1', 'PHP', '0', 'C', '0', 'C++', '0', 'null', '0', 'null', '0', '1');
INSERT INTO `vote` VALUES ('8', '老师上课该不该发红包？', 'teacher', '2016-05-24 20:03:52', '2016-07-02', '该', '0', '不该', '0', '随意', '0', 'null', '0', 'null', '0', 'null', '0', '0');

-- ----------------------------
-- Table structure for `voterecord`
-- ----------------------------
DROP TABLE IF EXISTS `voterecord`;
CREATE TABLE `voterecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voteId` int(11) NOT NULL,
  `optionId` int(11) NOT NULL,
  `voteTime` datetime NOT NULL,
  `person` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of voterecord
-- ----------------------------
INSERT INTO `voterecord` VALUES ('1', '1', '1', '2016-05-23 15:01:44', 'xiaomu');
INSERT INTO `voterecord` VALUES ('3', '3', '1', '2016-05-23 23:58:52', 'xiaomu');
INSERT INTO `voterecord` VALUES ('7', '2', '1', '2016-05-24 11:41:38', 'xiaomu');
INSERT INTO `voterecord` VALUES ('8', '2', '2', '2016-05-24 11:41:38', 'xiaomu');
INSERT INTO `voterecord` VALUES ('9', '2', '3', '2016-05-24 11:41:38', 'xiaomu');
INSERT INTO `voterecord` VALUES ('10', '2', '4', '2016-05-24 11:41:38', 'xiaomu');
INSERT INTO `voterecord` VALUES ('11', '2', '5', '2016-05-24 11:41:38', 'xiaomu');
INSERT INTO `voterecord` VALUES ('12', '7', '1', '2016-05-24 20:11:23', 'xiaomu');
