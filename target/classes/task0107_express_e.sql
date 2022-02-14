/*
Navicat MySQL Data Transfer

Source Server         : mysql8
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : task0107_express_e

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2021-12-01 20:32:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `eadmin`
-- ----------------------------
DROP TABLE IF EXISTS `eadmin`;
CREATE TABLE `eadmin` (
  `id` tinyint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `userphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `cardid` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sendexpress` int NOT NULL,
  `createtime` timestamp NOT NULL,
  `logintime` timestamp NOT NULL,
  `loginip` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Records of eadmin
-- ----------------------------
INSERT INTO `eadmin` VALUES ('1', '张三', '18888888888', '440123456789', '123456', '0', '2021-11-16 20:41:00', '2021-12-01 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `eadmin` VALUES ('2', '李四', '17777777777', '441123456789012345', '654321', '0', '2021-11-28 14:15:38', '2021-12-01 00:00:00', '0:0:0:0:0:0:0:1');
INSERT INTO `eadmin` VALUES ('3', '王五', '13333333333', '442123456789', '987654', '0', '2021-11-28 14:18:25', '2021-11-28 00:00:00', '172.17.100.249');
INSERT INTO `eadmin` VALUES ('5', '周八0', '18755555531', '446123456789', '741852', '0', '2021-11-28 18:09:36', '2021-11-28 00:00:00', '127.0.0.1');
INSERT INTO `eadmin` VALUES ('6', '吴九', '15266666674', '452123456789', '852963', '0', '2021-11-28 18:09:36', '2021-11-28 00:00:00', '127.0.0.1');
INSERT INTO `eadmin` VALUES ('7', '周八2', '18755555533', '446123456789', '741852', '0', '2021-11-28 18:09:36', '2021-11-28 00:00:00', '127.0.0.1');
INSERT INTO `eadmin` VALUES ('9', '孙七', '13755555535', '491123456789', '963852', '0', '2021-11-28 18:09:36', '2021-11-28 00:00:00', '127.0.0.1');
INSERT INTO `eadmin` VALUES ('10', '郑十', '15966666632', '452321654987', '753951', '0', '2021-11-28 20:46:03', '2021-11-28 20:46:03', '0:0:0:0:0:0:0:1');
INSERT INTO `eadmin` VALUES ('15', '赵六', '13665644852', '440123456789123456', '741852', '0', '2021-11-29 18:58:06', '2021-11-29 18:58:06', '0:0:0:0:0:0:0:1');
INSERT INTO `eadmin` VALUES ('17', '张三3', '13448522167', '885467155893485761', '321951', '0', '2021-12-01 20:29:16', '2021-12-01 20:29:16', '0:0:0:0:0:0:0:1');

-- ----------------------------
-- Table structure for `express`
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `userphone` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `company` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `intime` timestamp NOT NULL,
  `outtime` timestamp NULL DEFAULT NULL,
  `status` int NOT NULL,
  `sysPhone` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

-- ----------------------------
-- Records of express
-- ----------------------------
INSERT INTO `express` VALUES ('1', '321', '王二', '13843838438', '天天快递', '123456', '2021-11-17 19:38:03', null, '1', '12345678910');
INSERT INTO `express` VALUES ('2', '124', '张三', '13843838438', '京东快递', '321654', '2021-11-17 19:40:35', null, '0', '12345678910');
INSERT INTO `express` VALUES ('3', '125', '张三', '13843838438', '京东快递', '654321', '2021-11-10 19:41:33', null, '0', '12345678910');
INSERT INTO `express` VALUES ('4', '122', '张三', '13843838438', '京东快递', null, '2021-11-19 19:45:11', '2021-11-18 19:15:51', '1', '12345678910');
INSERT INTO `express` VALUES ('9', '1233', '李伟杰', '18516955565', '顺丰快递', '128563', '2021-11-19 20:16:34', null, '0', '666666');
INSERT INTO `express` VALUES ('11', '123110', '李伟杰', '13660200576', '申通快递', '666000', '2021-11-22 19:37:26', null, '0', '18888888888');
INSERT INTO `express` VALUES ('12', '123111', '李伟杰', '13660200576', '申通快递', '666001', '2021-11-22 19:37:26', null, '0', '18888888888');
INSERT INTO `express` VALUES ('13', '123112', '李伟杰', '13660200576', '申通快递', '666002', '2021-11-22 19:37:26', null, '0', '18888888888');
INSERT INTO `express` VALUES ('14', '123113', '李伟杰', '13660200576', '申通快递', '666003', '2021-11-22 19:37:26', null, '0', '18888888888');
INSERT INTO `express` VALUES ('15', '123114', '李伟杰', '13660200576', '申通快递', '666004', '2021-11-22 19:37:26', null, '0', '18888888888');
INSERT INTO `express` VALUES ('16', '123115', '李伟杰', '13660200576', '申通快递', '666005', '2021-11-22 19:37:26', null, '0', '18888888888');
