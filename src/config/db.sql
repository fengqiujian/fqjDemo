从2016年10月18日起  对分院的数据库进行的修改，sql要记录到当前文件中 

ALTER TABLE `his_fy_test`.`his_patient`   
  ADD COLUMN `type` VARCHAR(20) NULL  COMMENT '患者类型（1正畸患者，2种植患者，可多选逗号分隔）' AFTER `persid`;

ALTER TABLE `his_fy_test`.`his_register`   
  ADD COLUMN `affirm` INT(2) DEFAULT 0  NULL  COMMENT '预约是否确认，1为确认' AFTER `out_call`;

alter table `his_statement` 
   add column `old_statement_code` varchar(20) NULL after `act_id`;
   
ALTER TABLE `his_fy_test`.`his_statement_item_detail` 
  ADD COLUMN `if_formal` INT(11) NULL COMMENT '是否是正式处置项' AFTER `tooth`;