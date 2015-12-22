/*
Navicat MySQL Data Transfer

Source Server         : 42.96.198.92
Source Server Version : 50173
Source Host           : 42.96.198.92:3306
Source Database       : workFlow

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-05 15:49:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meta_custom
-- ----------------------------
DROP TABLE IF EXISTS `meta_custom`;
CREATE TABLE `meta_custom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `metaname` varchar(50) DEFAULT NULL,
  `metacode` varchar(30) DEFAULT NULL,
  `classpath` varchar(50) DEFAULT NULL,
  `metaService` varchar(100) DEFAULT NULL,
  `functionid` varchar(32) DEFAULT NULL,
  `parentid` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `tenantId` varchar(32) DEFAULT NULL,
  `querymethod` varchar(100) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL COMMENT '年度',
  `userId` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `userName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_custom
-- ----------------------------
INSERT INTO `meta_custom` VALUES ('32', '请假审批流程', '001', null, 'http://192.168.1.185/emoa/workFlow', '001', '-1', '2015-11-25 17:07:06', '请假审批流程', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('33', '盖章申请单', '002', null, 'http://192.168.1.185/emoa/workFlow', null, '-1', '2015-11-27 09:57:05', '盖章申请单', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('36', '名片印刷申请单', '003', null, 'http://192.168.1.185/emoa/workFlow', null, '-1', '2015-12-01 09:58:26', '名片印刷申请单', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('37', '外出申请单', '004', null, 'http://192.168.1.185/emoa/workFlow', null, '-1', '2015-12-01 12:53:22', '外出申请单', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('38', '印刷品申请单', '005', null, 'http://192.168.1.185/emoa/workFlow', null, '-1', '2015-12-01 13:01:41', '印刷品申请单', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('40', '出差申请单', '008', null, 'http://192.168.1.185/emoa/workFlow', null, '-1', '2015-12-02 18:11:41', '出差申请单008', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('41', '员工异动申请单', '006', null, 'http://192.168.1.185/emoa/workFlow', null, '-1', '2015-12-02 19:26:42', '员工异动申请单006', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('42', '招聘申请单', '007', null, 'http://192.168.1.185/emoa/workFlow', null, '-1', '2015-12-02 21:08:55', '招聘申请单007', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('43', '离职申请单', '009', null, 'http://192.168.1.185/emoa/workFlow', null, '-1', '2015-12-03 15:41:37', '离职申请单', '611', 'http://192.168.1.185/emoa/workFlow', '2015', 'admin', 'admin');
INSERT INTO `meta_custom` VALUES ('44', null, null, null, null, null, null, null, null, null, null, null, null, null);
