根据93上的测试环境查询一下sql语句 把byte字段值拷贝到 158 正式环境中
SELECT * FROM ACT_GE_BYTEARRAY a WHERE a.`ID_` IN( 28,32,50,5002);

表 meta_custom_field 增加 字段
isMust 是否必填项 orderByNum 新增排序字段 针对App使用
sql脚本
ALTER TABLE `meta_custom_field`
ADD COLUMN `isMust`  varchar(1) NULL DEFAULT 1 COMMENT '是否必填项' AFTER `ifVariableValue`;

ALTER TABLE `meta_custom_field`
  ADD COLUMN `orderByNum` VARCHAR(10) NULL   COMMENT '排序字段' AFTER `isMust`;

修改App端是否必填项字段信息
update meta_custom_field set isMust = 0 where id in (67,90,104,133,142,143,144,148,156,191,192,193,194,195);


   144  专业                     professionals   input          41  (NULL)     1                        2015-12-02 19:31:07               611  (NULL)  (NULL)  (NULL)    1       1                                                                                                                              0       0           0                           0
   195  专业                     professionals   input          42  (NULL)     1                        2015-12-03 20:18:06               611  (NULL)  (NULL)  (NULL)    1       1                                                                                                                              0       0           0                           0
   104  其他原因                   otherreason     input          32  (NULL)     1                        2015-11-24 14:34:19               611  (NULL)  (NULL)  (NULL)    1       1                                                                                                                              0       0           0                           0
   142  出生日期                   date_of_birth   input          41  (NULL)     1                        2015-12-02 19:30:48               611  (NULL)  (NULL)  (NULL)    0       1                                                                                                                              1       0           0                           0
    67  出行说明                   other_remark    textarea       40  (NULL)     1                        2015-10-27 16:06:38               611  (NULL)  (NULL)  (NULL)    1       1                                                                                                                              0       0           0                           0
    90  备注                     remark          input          33  (NULL)     1                        2015-11-24 09:19:25               611  (NULL)  (NULL)  (NULL)    1       1                                                                                                                              0       0           0                           0
   133  备注                     remark          textarea       38  (NULL)     1                        2015-12-01 13:26:12               611  (NULL)  (NULL)  (NULL)    1       1                                                                                                                              0       0           0                           0
   143  学历                     edu             input          41  (NULL)     1                        2015-12-02 19:30:55               611  (NULL)  (NULL)  (NULL)    0       1                                                                                                                              1       0           0                           0
   194  学历                     educational     input          42  (NULL)     1                        2015-12-03 20:18:00               611  (NULL)  (NULL)  (NULL)    1       1          不限,博士,硕士,研究生,本科,专科                                                                                                  0       0           0                           0
   192  年龄                     age             input          42  (NULL)     1                        2015-12-03 20:17:49               611  (NULL)  (NULL)  (NULL)    1       1          不限,20岁以下,20-25,25-30,30-35,35-40,40-45,45-50,50-55,55-60,60-65                                                      0       0           0                           0
   191  性别                     sex             input          42  (NULL)     1                        2015-12-03 20:17:42               611  (NULL)  (NULL)  (NULL)    1       1          不限,男,女                                                                                                              0       0           0                           0
   193  薪酬                     salary          input          42  (NULL)     1                        2015-12-03 20:17:55               611  (NULL)  (NULL)  (NULL)    1       1          不限,1000元以下,1000-2000,2001-4000,4001-6000,6001-8000,8001-10000,10000-15000,15000-25000,25000以上,面议                    0       0           0                           0
   148  调整前任职地点                src_offce_site  input          41  (NULL)     1                        2015-12-02 19:32:24               611  (NULL)  (NULL)  (NULL)    1       1                                                                                                                              1       0           0                           0
   156  调整后任职地点

   
 测试环境92 上模版调整
 员工异动单据修改
 
 请假申请单据，和出差调整单据 时间的选择
 修改员工异动流程
 

针对员工异动工单的特殊性，对接口保存做以下变动：
增加接口参数
createUserName 制单人姓名
createUserId        制单人id
old_dept_user     原部门负责人id
old_bgm_user     原直属副总id
now_dept_user   现部门负责人id
now_bgm_user   现直属副总id

原接口参数
userId 和 user_info 依旧存放申请人信息

★其中 userType参数，仅在新增工单保存，提交使用；其他情况目前均不需要传递，否则将影响数据的正确性

 
 
 
 
 
 
 
 
 
 