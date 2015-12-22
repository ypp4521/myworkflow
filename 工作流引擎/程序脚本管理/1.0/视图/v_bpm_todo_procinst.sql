ALTER 
ALGORITHM=UNDEFINED 
DEFINER=`root`@`%` 
SQL SECURITY DEFINER 
VIEW `v_bpm_todo_procinst` AS 
SELECT
	`todo`.`id` AS `id`,
	`todo`.`title` AS `title`,
	`todo`.`code` AS `code`,
	`todo`.`instanceid` AS `instanceid`,
	`todo`.`name` AS `name`,
	`todo`.`metaid` AS `metaid`,
	`todo`.`createtime` AS `createtime`,
	`todo`.`remark` AS `remark`,
	`todo`.`tenantId` AS `tenantId`,
	`todo`.`busiid` AS `busiid`,
	`todo`.`businessKey` AS `businessKey`,
	`todo`.`year` AS `year`,
	`todo`.`userId` AS `userId`,
	`todo`.`userName` AS `userName`,
	`todo`.`isback` AS `isback`,
	`todo`.`isrevoke` AS `isrevoke`,
	`todo`.`busicode` AS `busicode`,
	`todo`.`businame` AS `businame`,
	`todo`.`busi_code` AS `busi_code`,
	`todo`.`flowid` AS `flowid`,
	`todo`.`metaname` AS `metaname`,
	`todo`.`metacode` AS `metacode`,
	`proc`.`ID_` AS `ID_`,
	`proc`.`PROC_INST_ID_` AS `PROC_INST_ID_`,
	`proc`.`BUSINESS_KEY_` AS `BUSINESS_KEY_`,
	`proc`.`PROC_DEF_ID_` AS `PROC_DEF_ID_`,
	`proc`.`START_TIME_` AS `START_TIME_`,
	`proc`.`END_TIME_` AS `END_TIME_`,
	`proc`.`DURATION_` AS `DURATION_`,
	`proc`.`START_USER_ID_` AS `START_USER_ID_`,
	`proc`.`START_ACT_ID_` AS `START_ACT_ID_`,
	`proc`.`END_ACT_ID_` AS `END_ACT_ID_`,
	`proc`.`SUPER_PROCESS_INSTANCE_ID_` AS `SUPER_PROCESS_INSTANCE_ID_`,
	`proc`.`DELETE_REASON_` AS `DELETE_REASON_`,
	`proc`.`TENANT_ID_` AS `TENANT_ID_`,
	`proc`.`NAME_` AS `NAME_`
FROM
	(
		`v_bpm_todo` `todo`
		LEFT JOIN `ACT_HI_PROCINST` `proc` ON (
			(
				(
					`todo`.`instanceid` = `proc`.`ID_`
				)
				AND (
					`todo`.`tenantId` = `proc`.`TENANT_ID_`
				)
			)
		)
	) ;

