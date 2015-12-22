/*
Navicat MySQL Data Transfer

Source Server         : 42.96.198.92
Source Server Version : 50173
Source Host           : 42.96.198.92:3306
Source Database       : workFlow

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-05 15:50:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wf_meta_flow_field
-- ----------------------------
DROP TABLE IF EXISTS `wf_meta_flow_field`;
CREATE TABLE `wf_meta_flow_field` (
  `id` int(225) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号id',
  `wf_node_id` varchar(500) DEFAULT NULL COMMENT '当前流程节点id',
  `wf_node_name` varchar(200) DEFAULT NULL COMMENT '当前流程节点name',
  `wf_meta_busi_id` varchar(100) DEFAULT NULL COMMENT '对应流程模版id',
  `wf_tenantId` varchar(100) DEFAULT NULL COMMENT '租户id',
  `wf_meta_custom_field_code` varchar(200) DEFAULT NULL COMMENT '意见填充字段编码',
  `wf_meta_custom_field_name` varchar(200) DEFAULT NULL COMMENT '意见填充字段名称',
  `wf_modelkey` varchar(200) DEFAULT NULL COMMENT '流程部署key',
  `wf_meta_customs_id` varchar(100) DEFAULT NULL COMMENT '对应流程模版类型metaid',
  `wf_meta_customs_code` varchar(100) DEFAULT NULL COMMENT '对应流程模版类型meatacode',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wf_meta_flow_field
-- ----------------------------
INSERT INTO `wf_meta_flow_field` VALUES ('62', 'sid-A3EC8D9D-6D6E-43D5-968B-819A18EE6999', '部门领导审批', '53', '611', 'dept_opinion', '部门领导意见', 'Db5dfabed-a97f-424c-9815-3ba2888f0520:1:9', '32', '001');
INSERT INTO `wf_meta_flow_field` VALUES ('63', 'sid-EE5F43C0-FF3E-4AE4-A295-98B2C4C5547A', '直属副总审批', '53', '611', 'dgm_opinion', '直属副总意见', 'Db5dfabed-a97f-424c-9815-3ba2888f0520:1:9', '32', '001');
INSERT INTO `wf_meta_flow_field` VALUES ('64', 'sid-66728325-FC78-4E21-A09C-C22B19316D7C', '人力资源审批', '53', '611', 'hr_opinion', '人力资源部意见', 'Db5dfabed-a97f-424c-9815-3ba2888f0520:1:9', '32', '001');
INSERT INTO `wf_meta_flow_field` VALUES ('65', 'sid-6DF7B7B4-DEC7-40B9-B735-23C61C695738', '总经理审批', '53', '611', 'gm_opinion', '总经理批示', 'Db5dfabed-a97f-424c-9815-3ba2888f0520:1:9', '32', '001');
INSERT INTO `wf_meta_flow_field` VALUES ('66', 'sid-E6190ECD-8C6E-4099-87EC-28058553A8E4', '行政主管审批', '55', '611', 'ad_opinion', '行政部意见', 'D4c09fec6-e315-4477-b727-90ddc6a95efd:1:41', '36', '003');
INSERT INTO `wf_meta_flow_field` VALUES ('67', 'sid-282C4F1E-8F78-4502-A82F-5E68F7506ED9', '总经理审批', '61', '611', 'gm_opinion', '总经理批示', 'Dd8223ccd-0c6e-4581-a313-80f1c1c26a76:1:34', '42', '007');
INSERT INTO `wf_meta_flow_field` VALUES ('68', 'sid-0FAB1E47-CB6F-428D-BDEA-511E7E642ACB', '人力资源部审批', '61', '611', 'hr_opinion', '人力资源部意见', 'Dd8223ccd-0c6e-4581-a313-80f1c1c26a76:1:34', '42', '007');
INSERT INTO `wf_meta_flow_field` VALUES ('69', 'sid-7635F876-0B96-49BC-9E15-F521DC1A6549', '直属副总审批', '61', '611', 'dgm_opinion', '直属副总意见', 'Dd8223ccd-0c6e-4581-a313-80f1c1c26a76:1:34', '42', '007');
INSERT INTO `wf_meta_flow_field` VALUES ('70', 'sid-57BF2190-6A0B-448E-AFB5-637B01674A31', '部门领导审批', '61', '611', 'dept_opinion', '部门领导意见', 'Dd8223ccd-0c6e-4581-a313-80f1c1c26a76:1:34', '42', '007');
INSERT INTO `wf_meta_flow_field` VALUES ('71', 'sid-3EA88BFD-8050-4867-A3FE-ECB33043DAE8', '部门领导审批', '60', '611', 'dept_opinion', '部门领导意见', 'D3571d21f-8961-4796-beab-84c6ec40c97a:1:48', '43', '009');
INSERT INTO `wf_meta_flow_field` VALUES ('72', 'sid-6994D8A8-CDFA-43B0-AD0E-BF39F765E66F', '直属副总审批', '60', '611', 'dgm_opinion', '直属副总意见', 'D3571d21f-8961-4796-beab-84c6ec40c97a:1:48', '43', '009');
INSERT INTO `wf_meta_flow_field` VALUES ('73', 'sid-10F0E5AD-ED70-4DF4-A4C9-794FB94B2DFE', '人力资源审批', '60', '611', 'hr_opinion', '人力资源部意见', 'D3571d21f-8961-4796-beab-84c6ec40c97a:1:48', '43', '009');
INSERT INTO `wf_meta_flow_field` VALUES ('74', 'sid-BEE1C649-66F2-48E7-9CD3-21BCBEF15BCB', '总经理审批', '60', '611', 'gm_opinion', '总经理意见', 'D3571d21f-8961-4796-beab-84c6ec40c97a:1:48', '43', '009');
INSERT INTO `wf_meta_flow_field` VALUES ('75', 'sid-457C9322-9849-4679-82AB-1A479568A729', '部门领导审批', '58', '611', 'dept_opinion', '部门领导意见', 'De5827c6e-d17b-46ff-8f20-a9196efd5431:1:59', '40', '008');
INSERT INTO `wf_meta_flow_field` VALUES ('76', 'sid-7BF6FB6F-162A-4BDA-92B6-C2587BABD6B7', '直属副总审批', '58', '611', 'dgm_opinion', '直属副总意见', 'De5827c6e-d17b-46ff-8f20-a9196efd5431:1:59', '40', '008');
INSERT INTO `wf_meta_flow_field` VALUES ('77', 'sid-C19006F3-F31E-490F-B979-E105A3629628', '总经理审批', '58', '611', 'gm_opinion', '总经理审批', 'De5827c6e-d17b-46ff-8f20-a9196efd5431:1:59', '40', '008');
INSERT INTO `wf_meta_flow_field` VALUES ('78', 'sid-2F52637E-C344-4127-AFC1-9023BAE0E9C2', '部门领导审批', '57', '611', 'dept_opinion', '部门领导意见', 'Dc9fb7ce8-ac53-4611-af2e-430ada42b98b:1:2504', '38', '005');
INSERT INTO `wf_meta_flow_field` VALUES ('79', 'sid-33AAB5FC-907C-4D2D-9C3D-DBCA2F72520F', '行政经理审批', '57', '611', 'ad_opinion', '行政部意见', 'Dc9fb7ce8-ac53-4611-af2e-430ada42b98b:1:2504', '38', '005');
INSERT INTO `wf_meta_flow_field` VALUES ('80', 'sid-60171667-4341-4227-B9CB-7DDFE63B7F5C', '总经理', '57', '611', 'gm_opinion', '总经理意见', 'Dc9fb7ce8-ac53-4611-af2e-430ada42b98b:1:2504', '38', '005');
INSERT INTO `wf_meta_flow_field` VALUES ('81', 'sid-98F25A3F-6A3F-49E3-833B-E3E5F720AADD', '盖章审批人', '54', '611', 'audit_person', '审批人', 'D3c3d4254-6f21-4851-ae14-aa2829979993:1:76', '33', '002');
INSERT INTO `wf_meta_flow_field` VALUES ('82', 'sid-B06B270E-8006-49E2-877C-E04352A551A8', '部门领导审批', '56', '611', 'dept_opinion', '部门领导意见', 'D21f05053-9fa1-447d-b7c9-2a99f0148821:1:66', '37', '004');
INSERT INTO `wf_meta_flow_field` VALUES ('83', 'sid-E1BB533F-31F8-4740-AB5E-5C111569B100', '直属副总审核', '56', '611', 'dgm_opinion', '直属副总意见', 'D21f05053-9fa1-447d-b7c9-2a99f0148821:1:66', '37', '004');
INSERT INTO `wf_meta_flow_field` VALUES ('84', 'sid-65AA5587-94A1-4FAF-B5EE-4DC4E5D3027C', '总经理审批', '59', '611', 'gm_opinion', '总经理审核意见', 'Dfa7f6448-8ff4-4461-84e6-69b43dcef177:1:5004', '41', '006');
INSERT INTO `wf_meta_flow_field` VALUES ('85', 'sid-E42DB6BB-58CD-44B9-A64E-0DEDC3E5DEEC', '人力资源审批', '59', '611', 'hr_opinion', '人力资源意见', 'Dfa7f6448-8ff4-4461-84e6-69b43dcef177:1:5004', '41', '006');
INSERT INTO `wf_meta_flow_field` VALUES ('86', 'sid-8E3A591F-896A-42E2-B464-E1462C68A43F', '现直属副总审批', '59', '611', 'des_dgm_opinion', '调整后直属副总意见', 'Dfa7f6448-8ff4-4461-84e6-69b43dcef177:1:5004', '41', '006');
INSERT INTO `wf_meta_flow_field` VALUES ('87', 'sid-CF090356-0912-440B-96EE-3D99FB23DD97', '现部门领导审批', '59', '611', 'des_dept_opinion', '调整后部门领导意见', 'Dfa7f6448-8ff4-4461-84e6-69b43dcef177:1:5004', '41', '006');
INSERT INTO `wf_meta_flow_field` VALUES ('88', 'sid-60F7D6EE-2717-4A83-A516-B59A8D6A6EB8', '原直属副总审批', '59', '611', 'src_dgm_opinion', '调整前直属副总意见', 'Dfa7f6448-8ff4-4461-84e6-69b43dcef177:1:5004', '41', '006');
INSERT INTO `wf_meta_flow_field` VALUES ('89', 'sid-B8C93B32-06F9-449F-BEF1-30621481B7CB', '原部门领导审批', '59', '611', 'src_dept_opinion', '调整前部门领导意见', 'Dfa7f6448-8ff4-4461-84e6-69b43dcef177:1:5004', '41', '006');
