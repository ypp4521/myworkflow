/*
Navicat MySQL Data Transfer

Source Server         : 42.96.198.92
Source Server Version : 50173
Source Host           : 42.96.198.92:3306
Source Database       : workFlow

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-05 15:49:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meta_tenant
-- ----------------------------
DROP TABLE IF EXISTS `meta_tenant`;
CREATE TABLE `meta_tenant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenantId` varchar(32) DEFAULT NULL,
  `metaid` int(11) DEFAULT NULL,
  `modelname` varchar(50) DEFAULT NULL,
  `singler` varchar(32) DEFAULT NULL,
  `singletype` char(1) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `modelid` varchar(32) DEFAULT NULL,
  `modelkey` varchar(50) DEFAULT NULL,
  `deploystate` char(1) DEFAULT NULL COMMENT '是否已经部署',
  `year` varchar(10) DEFAULT NULL COMMENT '年度',
  `userId` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `userName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_tenant
-- ----------------------------
INSERT INTO `meta_tenant` VALUES ('83', '611', '32', '请假申请单', 'admin', '1', '2015-12-04 09:19:50', '请假申请单001', '1', 'Db5dfabed-a97f-424c-9815-3ba2888f0520', '1', null, null, null);
INSERT INTO `meta_tenant` VALUES ('93', '611', '42', '招聘申请单', 'admin', '1', '2015-12-04 10:55:16', '招聘申请单', '27', 'Dd8223ccd-0c6e-4581-a313-80f1c1c26a76', '1', null, null, null);
INSERT INTO `meta_tenant` VALUES ('94', '611', '36', '名片印刷申请单', 'admin', '1', '2015-12-04 11:11:12', '名片印刷申请单', '35', 'D4c09fec6-e315-4477-b727-90ddc6a95efd', '1', null, null, null);
INSERT INTO `meta_tenant` VALUES ('95', '611', '43', '离职申请单', 'admin', '1', '2015-12-04 11:17:42', '离职申请单', '42', 'D3571d21f-8961-4796-beab-84c6ec40c97a', '1', null, null, null);
INSERT INTO `meta_tenant` VALUES ('96', '611', '41', '员工异动申请单', 'admin', '1', '2015-12-04 11:27:42', '员工异动申请单', '49', 'Dfa7f6448-8ff4-4461-84e6-69b43dcef177', '1', null, null, null);
INSERT INTO `meta_tenant` VALUES ('97', '611', '40', '出差申请单', 'admin', '1', '2015-12-04 13:36:48', '出差申请单', '52', 'De5827c6e-d17b-46ff-8f20-a9196efd5431', '1', null, null, null);
INSERT INTO `meta_tenant` VALUES ('98', '611', '37', '外出申请单', 'admin', '1', '2015-12-04 13:44:24', '外出申请单', '60', 'D21f05053-9fa1-447d-b7c9-2a99f0148821', '1', null, null, null);
INSERT INTO `meta_tenant` VALUES ('100', '611', '33', '盖章申请单', 'admin', '1', '2015-12-04 13:52:27', '盖章申请单', '67', 'D3c3d4254-6f21-4851-ae14-aa2829979993', '1', null, null, null);
INSERT INTO `meta_tenant` VALUES ('101', '611', '38', '印刷品申请单', 'admin', '1', '2015-12-04 14:06:22', '印刷品申请单', '77', 'Dc9fb7ce8-ac53-4611-af2e-430ada42b98b', '1', null, null, null);
