ALTER 
ALGORITHM=UNDEFINED 
DEFINER=`root`@`%` 
SQL SECURITY DEFINER 
VIEW `v_bpm_todo_ru_task` AS 
SELECT
	`task`.`ID_` AS `ID_`,
	`task`.`REV_` AS `REV_`,
	`task`.`EXECUTION_ID_` AS `EXECUTION_ID_`,
	`task`.`PROC_INST_ID_` AS `PROC_INST_ID_`,
	`task`.`PROC_DEF_ID_` AS `PROC_DEF_ID_`,
	`task`.`NAME_` AS `NAME_`,
	`task`.`PARENT_TASK_ID_` AS `PARENT_TASK_ID_`,
	`task`.`DESCRIPTION_` AS `DESCRIPTION_`,
	`task`.`TASK_DEF_KEY_` AS `TASK_DEF_KEY_`,
	`task`.`ASSIGNEE_` AS `ASSIGNEE_`,
	`task`.`DELEGATION_` AS `DELEGATION_`,
	`task`.`PRIORITY_` AS `PRIORITY_`,
	`task`.`CREATE_TIME_` AS `CREATE_TIME_`,
	`task`.`DUE_DATE_` AS `DUE_DATE_`,
	`task`.`CATEGORY_` AS `CATEGORY_`,
	`task`.`SUSPENSION_STATE_` AS `SUSPENSION_STATE_`,
	`task`.`FORM_KEY_` AS `FORM_KEY_`,
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
	`hitask`.`CLAIM_TIME_` AS `CLAIM_TIME_`,
	`hitask`.`END_TIME_` AS `END_TIME_`
FROM
	(
		(
			`ACT_RU_TASK` `task`
			JOIN `v_bpm_todo` `todo` ON (
				(
					(
						`task`.`PROC_INST_ID_` = `todo`.`instanceid`
					)
					AND (
						`task`.`TENANT_ID_` = `todo`.`tenantId`
					)
				)
			)
		)
		JOIN `ACT_HI_TASKINST` `hitask` ON (
			(
				`task`.`ID_` = `hitask`.`ID_`
			)
		)
	) ;

