/*
Navicat MySQL Data Transfer

Source Server         : 42.96.198.92
Source Server Version : 50173
Source Host           : 42.96.198.92:3306
Source Database       : workFlow

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2015-12-05 15:49:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for meta_custom_field
-- ----------------------------
DROP TABLE IF EXISTS `meta_custom_field`;
CREATE TABLE `meta_custom_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fieldname` varchar(50) DEFAULT NULL,
  `fieldcode` varchar(50) DEFAULT NULL,
  `fieldtype` varchar(10) DEFAULT NULL,
  `metaid` int(11) DEFAULT NULL,
  `checktype` varchar(20) DEFAULT NULL,
  `fieldtodo` varchar(10) DEFAULT NULL,
  `todotemplate` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `tenantId` int(11) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL COMMENT '年度',
  `userId` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `userName` varchar(100) DEFAULT NULL COMMENT '创建人名称',
  `ifEdit` char(1) DEFAULT '0' COMMENT '是否可编辑',
  `ifVisible` char(1) DEFAULT '1' COMMENT '是否显示',
  `defaultValue` varchar(255) DEFAULT NULL COMMENT '默认值',
  `ifAuto` char(1) DEFAULT '0' COMMENT '是否自动输入',
  `ifVariable` char(1) DEFAULT '0' COMMENT '是否变量',
  `isOpinion` char(1) DEFAULT '0' COMMENT '是否意见',
  `ifVariableValue` varchar(500) DEFAULT NULL COMMENT '变量值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meta_custom_field
-- ----------------------------
INSERT INTO `meta_custom_field` VALUES ('30', '填表日期', 'input_date', 'text', '32', null, '1', '', '2015-10-27 14:31:37', '', '611', null, null, null, '0', '1', null, '1', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('31', '部门', 'dept', 'text', '32', null, '1', '', '2015-10-27 14:16:42', '', '611', null, null, null, '0', '1', null, '1', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('32', '申请人', 'user_info', 'text', '32', null, '2', '${user_info}提交了请假申请！', '2015-10-27 14:33:18', '', '611', null, null, null, '0', '1', null, '1', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('33', '请假开始日期', 'begin_date', 'date', '32', null, '1', '', '2015-10-27 14:31:13', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('34', '请假类型', 'oper_type', 'select', '32', null, '1', '', '2015-10-27 14:30:58', '', '611', null, null, null, '1', '1', '事假,病假,婚假,丧假,年假,产假,其他', '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('36', '部门领导意见', 'dept_opinion', 'text', '32', null, '1', '', '2015-11-24 14:34:53', '', '611', null, null, null, '0', '0', '', '0', '0', '1', null);
INSERT INTO `meta_custom_field` VALUES ('37', '人力资源部意见', 'hr_opinion', 'text', '32', null, '1', '', '2015-11-24 14:35:03', '', '611', null, null, null, '0', '0', '', '0', '0', '1', null);
INSERT INTO `meta_custom_field` VALUES ('38', '直属副总意见', 'dgm_opinion', 'text', '32', null, '1', '', '2015-11-24 14:35:07', '', '611', null, null, null, '0', '0', '', '0', '0', '1', null);
INSERT INTO `meta_custom_field` VALUES ('39', '总经理批示', 'gm_opinion', 'input', '32', null, '1', '', '2015-11-26 16:12:11', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('40', '请假结束日期', 'end_date', 'date', '32', null, '1', '', '2015-10-27 14:31:07', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('41', '请假事由', 'reason_info', 'input', '32', null, '1', '', '2015-11-24 14:34:16', '', '611', null, null, null, '1', '1', '', '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('42', '申请人', 'user_info', 'text', '40', null, '2', '${user_info}提交了出差申请单！', '2015-10-27 15:19:28', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('43', '申请日期', 'input_date', 'text', '40', null, '1', '', '2015-10-27 15:19:19', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('44', '部门', 'dept', 'input', '40', null, '1', '', '2015-10-27 15:19:09', '', '611', null, null, null, '0', '0', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('45', '职位', 'post_info', 'text', '40', '', '1', '', '2015-10-27 15:20:40', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('46', '出差地点', 'trip_addr', 'text', '40', '', '1', '', '2015-10-27 15:21:16', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('47', '拜访公司', 'trip_company', 'text', '40', '', '1', '', '2015-10-27 15:21:48', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('48', '出差开始时间', 'begin_date', 'text', '40', '', '1', '', '2015-10-27 15:22:23', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('49', '出差结束时间', 'end_date', 'text', '40', null, '1', '', '2015-10-27 15:22:47', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('50', '出差事由', 'reason_info', 'text', '40', '', '1', '', '2015-10-27 15:51:28', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('51', '出发地', 'departure', 'text', '40', '', '1', '', '2015-10-27 15:52:13', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('52', '目的地', 'destination', 'text', '40', '', '1', '', '2015-10-27 15:52:30', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('53', '出行时间', 'src_date', 'text', '40', '', '1', '', '2015-10-27 15:53:27', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('54', '车次', 'src_train', 'text', '40', '', '1', '', '2015-10-27 15:54:17', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('55', '席别', 'src_seat', 'text', '40', '', '1', '', '2015-10-27 15:57:17', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('56', '金额', 'src_cost', 'text', '40', null, '1', '', '2015-10-27 15:58:20', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('57', '出行时间', 'src_date_plane', 'text', '40', '', '1', '', '2015-10-27 15:59:07', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('58', '航班', 'src_flightno', 'text', '40', null, '1', '', '2015-10-27 16:01:13', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('59', '金额（折扣）', 'src_cost_plane', 'text', '40', '', '1', '', '2015-10-27 16:01:53', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('60', '回程日期', 'des_date', 'text', '40', null, '1', '', '2015-10-27 16:02:41', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('61', '回程车次', 'des_train', 'input', '40', null, '1', '', '2015-10-27 16:03:16', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('62', '回程席别', 'des_seat', 'input', '40', null, '1', '', '2015-10-27 16:03:38', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('63', '回程金额', 'des_cost', 'input', '40', null, '1', '', '2015-10-27 16:03:57', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('64', '出行时间', 'des_date_plane', 'date', '40', null, '1', '', '2015-10-27 16:04:47', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('65', '航班', 'des_flightno', 'input', '40', null, '1', '', '2015-10-27 16:05:12', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('66', '金额（折扣）', 'des_cost_plane', 'input', '40', null, '1', '', '2015-10-27 16:05:56', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('67', '其他', 'other_remark', 'textarea', '40', null, '1', '', '2015-10-27 16:06:38', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('68', '部门领导意见', 'dept_opinion', 'input', '40', null, '1', '', '2015-10-27 16:07:37', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('69', '直属副总意见', 'dgm_opinion', 'input', '40', null, '1', '', '2015-10-27 16:08:17', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('70', '总经理审批', 'gm_opinion', 'input', '40', null, '1', '', '2015-10-27 16:08:49', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('71', '填表日期', 'input_date', 'input', '37', null, '1', '', '2015-10-27 16:10:32', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('72', '部门', 'dept', 'input', '37', null, '1', '', '2015-10-27 16:10:47', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('73', '申请人', 'user_info', 'input', '37', null, '2', '${user_info}提交了外出申请单！', '2015-10-27 16:11:02', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('74', '外出开始时间', 'begin_date', 'date', '37', null, '1', '', '2015-10-27 16:11:32', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('75', '外出结束时间', 'end_date', 'date', '37', null, '1', '', '2015-10-27 16:11:50', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('76', '拜访公司名称', 'visit_company', 'input', '37', null, '1', '', '2015-10-27 16:12:23', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('77', '拜访人', 'visit_person', 'input', '37', null, '1', '', '2015-10-27 16:12:45', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('79', '拜访事由', 'reason_info', 'textarea', '37', null, '1', '', '2015-10-27 16:13:37', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('80', '部门领导意见', 'dept_opinion', 'input', '37', null, '1', '', '2015-10-27 16:14:07', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('81', '直属副总意见', 'dgm_opinion', 'input', '37', null, '1', '', '2015-10-27 16:14:38', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('82', '申请日期', 'input_date', 'input', '33', null, '1', '', '2015-10-27 16:40:08', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('83', '部门', 'dept', 'input', '33', null, '1', '', '2015-10-27 16:40:22', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('84', '申请人', 'user_info', 'input', '33', null, '2', '${user_info}提交了盖章申请单！', '2015-10-27 16:40:42', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('85', '所盖文件名称', 'file_name', 'input', '33', null, '1', '', '2015-10-27 16:41:11', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('86', '事由', 'reason_info', 'textarea', '33', null, '1', '', '2015-10-27 16:41:32', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('87', '份数', 'copies', 'input', '33', null, '1', '', '2015-10-27 16:42:33', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('88', '用章类别', 'oper_type', 'select', '33', null, '1', '', '2015-10-27 16:42:54', '', '611', null, null, null, '1', '1', '公章,合同章', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('89', '审批人', 'audit_person', 'input', '33', null, '1', '', '2015-10-27 16:43:20', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('90', '备注', 'remark', 'input', '33', null, '1', '', '2015-11-24 09:19:25', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('91', '申请日期', 'input_date', 'text', '36', '', '1', '', '2015-10-27 17:03:10', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('92', '印刷类型', 'oper_type', 'select', '36', null, '1', '', '2015-10-27 17:03:29', '', '611', null, null, null, '1', '1', '普通印刷,加急印刷', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('93', '部门', 'dept', 'input', '36', null, '1', '', '2015-10-27 17:03:47', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('94', '申请人', 'user_info', 'input', '36', null, '2', '${user_info}提交了名片印刷申请单！', '2015-10-27 17:04:08', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('95', '对外职位', 'out_post', 'input', '36', null, '1', '', '2015-10-27 17:04:32', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('96', '直线', 'telephone', 'input', '36', null, '1', '', '2015-10-27 17:06:07', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('97', '手机', 'mobile', 'input', '36', null, '1', '', '2015-10-27 17:06:21', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('98', '数量', 'numbers', 'input', '36', null, '1', '', '2015-10-27 17:06:45', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('99', '电子邮件', 'email', 'input', '36', null, '1', '', '2015-10-27 17:07:09', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('100', '部门领导意见', 'dept_opinion', 'input', '36', null, '1', '', '2015-10-27 17:07:28', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('101', '行政部意见', 'ad_opinion', 'input', '36', null, '1', '', '2015-10-27 17:08:11', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('102', '天', 'countday', 'input', '32', null, '1', '', '2015-11-04 17:09:55', '', '611', null, null, null, '1', '1', '', '0', '1', '0', '');
INSERT INTO `meta_custom_field` VALUES ('103', '时', 'counthour', 'input', '32', '', '1', '', '2015-11-04 17:10:12', '', '611', null, null, null, '1', '1', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('104', '其他原因', 'otherreason', 'input', '32', null, '1', '', '2015-11-24 14:34:19', '', '611', null, null, null, '1', '1', '', '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('107', '天', 'countday', 'text', '37', null, '1', null, '2015-12-01 12:39:50', null, null, null, null, null, '0', '0', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('108', '时', 'counthour', 'text', '37', null, '1', null, '2015-12-01 12:39:52', null, null, null, null, null, '0', '0', null, '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('123', '申请人', 'user_info', 'input', '38', null, '2', '${user_info}提交了印刷品申请单！', '2015-12-01 13:19:56', '', '611', null, null, null, '0', '1', '', '1', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('124', '部门', 'dept', 'input', '38', null, '1', '', '2015-12-01 13:20:25', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('125', '申请日期', 'input_date', null, '38', null, '1', '', '2015-12-01 13:20:57', '', '611', null, null, null, null, null, '', null, null, null, null);
INSERT INTO `meta_custom_field` VALUES ('126', '申请理由', 'reason_info', null, '38', null, '1', '', '2015-12-01 13:21:22', '', '611', null, null, null, null, null, '', null, null, null, null);
INSERT INTO `meta_custom_field` VALUES ('127', '产品名称', 'product_name', 'input', '38', null, '1', '', '2015-12-01 13:22:10', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('128', '材质', 'material', 'input', '38', null, '1', '', '2015-12-01 13:23:26', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('129', '规格型号', 'specification_model', 'input', '38', null, '1', '', '2015-12-01 13:24:01', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('130', '数量', 'amount', 'input', '38', null, '1', '', '2015-12-01 13:24:24', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('131', '预计单价（元）', 'forecast_price', 'input', '38', null, '1', '', '2015-12-01 13:25:07', '', '611', null, null, null, '1', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('132', '合计金额', 'total_amount', 'input', '38', null, '1', '', '2015-12-01 13:25:49', '金额*数量', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('133', '备注', 'remark', 'textarea', '38', null, '1', '', '2015-12-01 13:26:12', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('134', '部门领导意见', 'dept_opinion', 'input', '38', null, '1', '', '2015-12-01 14:14:41', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('135', '行政部意见', 'ad_opinion', 'input', '38', null, '1', '', '2015-12-01 14:15:07', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('136', '总经理意见', 'gm_opinion', 'input', '38', null, '1', '', '2015-12-01 14:15:17', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('138', '申请人', 'user_info', 'input', '41', null, '2', '${user_info}提交了员工异动申请单！', '2015-12-02 19:28:33', '', '611', null, null, null, '0', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('139', '部门', 'dept', 'input', '41', null, '1', '', '2015-12-02 19:29:43', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('140', '职位', 'position', 'input', '41', null, '1', '', '2015-12-02 19:30:11', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('141', '员工编号', 'user_code', 'input', '41', null, '1', '', '2015-12-02 19:30:26', '', '611', null, null, null, '1', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('142', '出生日期', 'date_of_birth', 'input', '41', null, '1', '', '2015-12-02 19:30:48', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('143', '学历', 'edu', 'input', '41', null, '1', '', '2015-12-02 19:30:55', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('144', '专业', 'professionals', 'input', '41', null, '1', '', '2015-12-02 19:31:07', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('145', '入职日期', 'dntry_date', 'input', '41', null, '1', '', '2015-12-02 19:31:13', '', '611', null, null, null, '0', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('146', '调整类型', 'oper_type', 'input', '41', null, '1', '', '2015-12-02 19:31:42', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('147', '申请原因', 'reason_info', 'input', '41', null, '1', '', '2015-12-02 19:31:52', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('148', '调整前任职地点', 'src_offce_site', 'input', '41', null, '1', '', '2015-12-02 19:32:24', '', '611', null, null, null, '1', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('149', '调整前任职部门', 'src_dept', 'input', '41', null, '1', '', '2015-12-02 19:32:46', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('150', '调整前任职岗位', 'src_office_type', 'input', '41', null, '1', '', '2015-12-02 19:32:56', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('151', '调整前薪资总额', 'src_salary_total', 'input', '41', null, '1', '', '2015-12-02 19:33:09', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('152', '调整前基本工资', 'src_basic_salary', 'input', '41', null, '1', '', '2015-12-02 19:33:24', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('153', '调整前绩效工资', 'src_achievement', 'input', '41', null, '1', '', '2015-12-02 19:33:34', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('154', '调整前部门领导意见', 'src_dept_opinion', 'input', '41', null, '1', '', '2015-12-02 19:34:01', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('155', '调整前直属副总意见', 'src_dgm_opinion', 'input', '41', null, '1', '', '2015-12-02 19:34:13', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('156', '调整后任职地点', 'des_offce_site', 'input', '41', null, '1', '', '2015-12-02 19:34:46', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('157', '调整后任职部门', 'des_dept', 'input', '41', null, '1', '', '2015-12-02 19:34:54', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('158', '调整后任职岗位', 'des_office_type', 'input', '41', null, '1', '', '2015-12-02 19:35:01', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('159', '调整后薪资总额', 'des_salary_total', 'input', '41', null, '1', '', '2015-12-02 19:35:16', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('160', '调整后基本薪资', 'des_basic_salary', 'input', '41', null, '1', '', '2015-12-02 19:35:38', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('161', '调整后绩效工资', 'des_achievement', 'input', '41', null, '1', '', '2015-12-02 19:35:45', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('162', '调整后部门领导意见', 'des_dept_opinion', 'input', '41', null, '1', '', '2015-12-02 19:35:59', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('163', '调整后直属副总意见', 'des_dgm_opinion', 'input', '41', null, '1', '', '2015-12-02 19:36:10', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('164', '人力资源意见', 'hr_opinion', 'input', '41', null, '1', '', '2015-12-02 19:36:21', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('165', '总经理审核意见', 'gm_opinion', 'input', '41', null, '1', '', '2015-12-02 19:36:31', '', '611', null, null, null, '0', '0', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('166', '生效日期', 'effective_date', 'date', '41', null, '1', '', '2015-12-02 19:36:39', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('167', '员工签字确认', 'staff', 'input', '41', null, '1', '', '2015-12-02 19:36:53', '', '611', null, null, null, '0', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('168', '离职员姓名', 'user_info', 'input', '43', null, '2', '${user_info}提交了离职申请单！', '2015-12-03 15:42:57', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('169', '任职部门', 'post_dept', 'input', '43', null, '1', '', '2015-12-03 19:17:59', '', '611', null, null, null, '0', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('170', '任职岗位', 'post_office', 'input', '43', null, '1', '', '2015-12-03 19:18:05', '', '611', null, null, null, '0', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('171', '直接主管', 'supervisor', 'input', '43', null, '1', '', '2015-12-03 19:18:11', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('172', '入职日期', 'entry_date', 'date', '43', null, '1', '', '2015-12-03 19:18:25', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('173', '预计离职日期', 'departure_date', 'date', '43', null, '1', '', '2015-12-03 19:18:32', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('174', '合同期期限开始时间', 'contract_end_time', 'input', '43', null, '1', '', '2015-12-03 19:18:42', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('175', '合同期期限结束时间', 'contract_start_time', 'date', '43', null, '1', '', '2015-12-03 19:18:48', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('176', '关系解除选项', 'of_options', 'input', '43', null, '1', '', '2015-12-03 19:18:54', '', '611', null, null, null, '1', '1', '试用关系解除,自动离职,其他', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('177', '离职原因', 'reason_info', 'input', '43', null, '1', '', '2015-12-03 19:18:59', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('178', '部门领导意见', 'dept_opinion', 'input', '43', null, '1', '', '2015-12-03 19:19:05', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('179', '直属副总意见', 'dgm_opinion', 'input', '43', null, '1', '', '2015-12-03 19:19:09', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('180', '人力资源部意见', 'hr_opinion', 'input', '43', null, '1', '', '2015-12-03 19:19:15', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('181', '总经理意见', 'gm_opinion', 'input', '43', null, '1', '', '2015-12-03 19:19:28', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('182', '申请日期', 'input_date', 'date', '42', null, '1', '', '2015-12-03 20:16:00', '', '611', null, null, null, '1', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('183', '招聘职位', 'careers', 'input', '42', null, '1', '', '2015-12-03 20:16:06', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('184', '申请原因', 'reason_info', 'input', '42', null, '1', '', '2015-12-03 20:16:12', '', '611', null, null, null, '1', '1', '新增职位,空缺、调离职位补充,已有职位人员增加,其他', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('185', '申请部门', 'detp', 'input', '42', null, '1', '', '2015-12-03 20:16:18', '', '611', null, null, null, '1', '1', '', '1', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('186', '申请人', 'user_info', 'input', '42', null, '2', '${user_info}提交了招聘申请单！', '2015-12-03 20:16:25', '', '611', null, null, null, '0', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('187', '招聘人数', 'hiring', 'input', '42', null, '1', '', '2015-12-03 20:16:31', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('188', '期望到岗日期', 'expected_date', 'input', '42', null, '1', '', '2015-12-03 20:16:36', '', '611', null, null, null, '1', '1', '一个月,不限,一周,半个月,两个月,三个月', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('189', '招聘形式', 'recruitment', 'input', '42', null, '1', '', '2015-12-03 20:16:43', '', '611', null, null, null, '1', '1', '内部招聘,网站招聘,培训机构推荐', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('190', '简历筛选', 'resume_screening', 'checkbox', '42', null, '1', '', '2015-12-03 20:17:24', '', '611', null, null, null, '1', '1', '部门筛选,人力资源部筛选,简历下载', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('191', '性别', 'sex', 'input', '42', null, '1', '', '2015-12-03 20:17:42', '', '611', null, null, null, '1', '1', '不限,男,女', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('192', '年龄', 'age', 'input', '42', null, '1', '', '2015-12-03 20:17:49', '', '611', null, null, null, '1', '1', '不限,20岁以下,20-25,25-30,30-35,35-40,40-45,45-50,50-55,55-60,60-65', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('193', '薪酬', 'salary', 'input', '42', null, '1', '', '2015-12-03 20:17:55', '', '611', null, null, null, '1', '1', '不限,1000元以下,1000-2000,2001-4000,4001-6000,6001-8000,8001-10000,10000-15000,15000-25000,25000以上,面议', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('194', '学历', 'educational', 'input', '42', null, '1', '', '2015-12-03 20:18:00', '', '611', null, null, null, '1', '1', '不限,博士,硕士,研究生,本科,专科', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('195', '专业', 'professionals', 'input', '42', null, '1', '', '2015-12-03 20:18:06', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('196', '优先条件', 'priority', 'input', '42', null, '1', '', '2015-12-03 20:18:12', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('197', '补充说明', 'addendum', 'input', '42', null, '1', '', '2015-12-03 20:18:17', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('198', '工作职责', 'responsibilities', 'input', '42', null, '1', '', '2015-12-03 20:18:25', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
INSERT INTO `meta_custom_field` VALUES ('199', '部门领导意见', 'dept_opinion', 'input', '42', null, '1', '', '2015-12-03 20:18:32', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('200', '人力资源部意见', 'hr_opinion', 'input', '42', null, '1', '', '2015-12-03 20:18:37', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('201', '直属副总意见', 'dgm_opinion', 'input', '42', null, '1', '', '2015-12-03 20:18:41', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('202', '总经理批示', 'gm_opinion', 'input', '42', null, '1', '', '2015-12-03 20:18:46', '', '611', null, null, null, '0', '1', '', '0', '0', '1', '');
INSERT INTO `meta_custom_field` VALUES ('203', '名片使用人', 'use_user_info', 'input', '36', null, '1', '', '2015-12-04 12:03:17', '', '611', null, null, null, '1', '1', '', '0', '0', '0', null);
INSERT INTO `meta_custom_field` VALUES ('204', '其他', 'other_reason', 'input', '42', null, '1', '', '2015-12-04 13:04:53', '', '611', null, null, null, '1', '1', '', '0', '0', '0', '');
