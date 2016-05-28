/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : class

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-05-29 00:50:07
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
  `time` bigint(20) NOT NULL,
  `taskNum` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('00000000001', '移动互动课堂组.doc', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/移动互动课堂组.doc', 'xiaomu', '1464437134698', '1');
INSERT INTO `homework` VALUES ('00000000002', '2.ppt', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/2.ppt', 'xiaomu', '1464440148418', '2');
INSERT INTO `homework` VALUES ('00000000003', '3.ppt', 'E:/apache-tomcat-8.0.33-windows-x64/apache-tomcat-8.0.33/webapps/data/3.ppt', 'xiaomu', '1464453933108', '3');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of issuework
-- ----------------------------
INSERT INTO `issuework` VALUES ('1', '周报', '1464710400000', '1464436429197', null);
INSERT INTO `issuework` VALUES ('2', 'PPT', '1469980800000', '1464439904160', null);
INSERT INTO `issuework` VALUES ('3', 'PPT', '1464710400000', '1464440932633', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES ('1', '世界上最好的语言是？', 'teacher', '1464428333365', '1462204800000', 'C', '0', 'C++', '0', 'JAVA', '0', 'PHP', '0', 'null', '0', 'null', '0', '0');
INSERT INTO `vote` VALUES ('2', '移动互动课堂组棒不棒？', 'teacher', '1464428416831', '1467338400000', '非常棒', '1', '很棒', '0', 'null', '0', 'null', '0', 'null', '0', 'null', '0', '0');
INSERT INTO `vote` VALUES ('3', '研究与开发课上你收获了什么？', 'teacher', '1464451207716', '1467302400000', '项目流程', '1', '代码量', '1', '具体编程知识', '1', '微笑面对一切', '1', '熬夜', '0', 'null', '0', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of voterecord
-- ----------------------------
INSERT INTO `voterecord` VALUES ('1', '2', '1', '1464432855935', 'xiaomu');
INSERT INTO `voterecord` VALUES ('2', '3', '1', '1464452556305', 'xiaomu');
INSERT INTO `voterecord` VALUES ('3', '3', '2', '1464452556305', 'xiaomu');
INSERT INTO `voterecord` VALUES ('4', '3', '3', '1464452556305', 'xiaomu');
INSERT INTO `voterecord` VALUES ('5', '3', '4', '1464452556305', 'xiaomu');
