/*
Navicat MySQL Data Transfer

Source Server         : 42.96.198.92
Source Server Version : 50173
Source Host           : 42.96.198.92:3306
Source Database       : workFlow

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-05 15:49:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bpm_approve
-- ----------------------------
DROP TABLE IF EXISTS `bpm_approve`;
CREATE TABLE `bpm_approve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instanceid` varchar(50) DEFAULT NULL,
  `taskid` varchar(50) DEFAULT NULL,
  `approve` varchar(30) DEFAULT NULL,
  `approveresult` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `apptype` varchar(50) DEFAULT NULL,
  `tenantId` varchar(50) DEFAULT NULL,
  `targetkey` varchar(50) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL COMMENT '年度',
  `userId` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `userName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=541 DEFAULT CHARSET=utf8;
