/*
Navicat MySQL Data Transfer

Source Server         : 42.96.198.92
Source Server Version : 50173
Source Host           : 42.96.198.92:3306
Source Database       : workFlow

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-05 15:49:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bpm_todo
-- ----------------------------
DROP TABLE IF EXISTS `bpm_todo`;
CREATE TABLE `bpm_todo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `instanceid` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `metaid` varchar(50) DEFAULT NULL,
  `createtime` varchar(50) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `tenantId` varchar(50) DEFAULT NULL,
  `busiid` varchar(50) DEFAULT NULL,
  `businessKey` varchar(50) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL COMMENT '年度',
  `userId` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `userName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  `isback` varchar(2) DEFAULT '0' COMMENT '是否被退回单据',
  `isrevoke` varchar(2) DEFAULT '0' COMMENT '是否是我收回的单据',
  `busicode` varchar(100) DEFAULT NULL COMMENT '模版类型 如001请假',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=349 DEFAULT CHARSET=utf8;
