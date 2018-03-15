/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : orderingsystem

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2017-08-21 20:31:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ordered
-- ----------------------------
DROP TABLE IF EXISTS `ordered`;
CREATE TABLE `ordered` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `insert_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordered
-- ----------------------------
INSERT INTO `ordered` VALUES ('115', '1', '2017-08-21');
INSERT INTO `ordered` VALUES ('116', '5', '2017-08-21');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `insert_time` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '1', '0000-00-00');
INSERT INTO `test` VALUES ('2', 'yang', '2017-08-21');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '魏颖康');
INSERT INTO `user` VALUES ('2', '杨文旭');
INSERT INTO `user` VALUES ('3', '况林');
INSERT INTO `user` VALUES ('4', '史文魁');
INSERT INTO `user` VALUES ('5', '东软');
INSERT INTO `user` VALUES ('6', '路志刚');
INSERT INTO `user` VALUES ('7', '张世龙');
INSERT INTO `user` VALUES ('8', '郭鹏伟');
INSERT INTO `user` VALUES ('9', '尚晨晨');
INSERT INTO `user` VALUES ('10', '梁小龙');
INSERT INTO `user` VALUES ('11', '王慧');
INSERT INTO `user` VALUES ('12', '杨德军');
INSERT INTO `user` VALUES ('13', '李凤麒');
