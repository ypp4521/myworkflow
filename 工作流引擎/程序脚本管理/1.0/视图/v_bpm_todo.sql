ALTER 
ALGORITHM=UNDEFINED 
DEFINER=`root`@`%` 
SQL SECURITY DEFINER 
VIEW `v_bpm_todo` AS 
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
	`busi`.`businame` AS `businame`,
	`busi`.`busicode` AS `busi_code`,
	`busi`.`flowid` AS `flowid`,
	`meta`.`metaname` AS `metaname`,
	`meta`.`metacode` AS `metacode`
FROM
	(
		(
			`bpm_todo` `todo`
			JOIN `meta_busi` `busi`
		)
		JOIN `meta_custom` `meta`
	)
WHERE
	(
		(
			`todo`.`busiid` = `busi`.`id`
		)
		AND (
			`todo`.`metaid` = `meta`.`id`
		)
	) ;

