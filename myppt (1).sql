/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : myfirst

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2016-06-08 14:30:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `myppt`
-- ----------------------------
DROP TABLE IF EXISTS `myppt`;
CREATE TABLE `myppt` (
  `user` varchar(255) DEFAULT NULL,
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `fileName` varchar(255) NOT NULL DEFAULT '',
  `filePath` varchar(255) DEFAULT NULL,
  `selected` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of myppt
-- ----------------------------
INSERT INTO `myppt` VALUES (null, '44', '6aad442bjw1f07g03z7z6j20hs0hsq68.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '45', '005E3Cvkgw1f094ulq63oj31400qojv1.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '46', '6aad442bjw1f07g01dk5xj20hs0hs76x.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '47', '64329a42gw1f3ckg35w5ij20qo0xb0wq.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '48', 'flash4544.swf', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '49', 'flash4544.swf', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '50', 'test.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '51', '005E3Cvkgw1f094ulq63oj31400qojv1.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '52', '4c0be95djw1f3c4v81nlyj20zk0sgn2c.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '53', '4c0be95djw1f3craupboej20kn0ktdhk.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '54', '4c0be95djw1f3c4v81nlyj20zk0sgn2c.jpg', 'D:/demo/Smart/web/images', '0');
INSERT INTO `myppt` VALUES (null, '55', '4c0be95djw1f3c4v81nlyj20zk0sgn2c.jpg', 'D:/demo/Smart/web/images', '1');
INSERT INTO `myppt` VALUES (null, '56', '6aad442bjw1f07g03z7z6j20hs0hsq68.jpg', 'D:/demo/Smart/web/images', '0');
