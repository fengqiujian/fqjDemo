/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.6.16 : Database - enjoyhisjt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `admin_menu` */

DROP TABLE IF EXISTS `admin_menu`;

CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(32) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `orders` varchar(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Table structure for table `admin_role` */

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `admin_role_menu_mapping` */

DROP TABLE IF EXISTS `admin_role_menu_mapping`;

CREATE TABLE `admin_role_menu_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;

/*Table structure for table `his_account_charge` */

DROP TABLE IF EXISTS `his_account_charge`;

CREATE TABLE `his_account_charge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水id,无意义',
  `orderid` bigint(20) DEFAULT NULL COMMENT '单据id',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '记账科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '记账二级科目',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '实际金额',
  `tgpoundage` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `account_type` int(11) DEFAULT NULL COMMENT '账务类型：1挂号，2处置收费，3收欠款，4整单退费，6调减，9预收',
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `flow_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_statdate3` (`stat_date`),
  KEY `idx_flowid` (`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结算单收费明细';

/*Table structure for table `his_account_flow` */

DROP TABLE IF EXISTS `his_account_flow`;

CREATE TABLE `his_account_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` bigint(20) DEFAULT NULL COMMENT '院区+日期+5位流水',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `account_type` int(11) DEFAULT NULL COMMENT '账务类型：1挂号，2处置收费，3收欠款，4整单退费，6调减，9预收',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id，一般为空',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `isnew` int(11) DEFAULT NULL COMMENT '是否新患者，0不是，1是',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '总金额，含优惠',
  `pre_amount` decimal(10,2) DEFAULT NULL COMMENT '预收金额',
  `account_amount` decimal(10,2) DEFAULT NULL COMMENT '记账金额=实收+应收',
  `receivable_amount` decimal(10,2) DEFAULT NULL COMMENT '应收金额',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '实收金额',
  `deductions_amount` decimal(10,2) DEFAULT NULL,
  `service_desc` varchar(255) DEFAULT NULL COMMENT '业务描述',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `parent_orderid` bigint(20) DEFAULT NULL COMMENT '父级单据',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作员id',
  `order_status` int(11) DEFAULT NULL COMMENT '单据状态：正常流水，人工异常',
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `from_patient_id` bigint(20) DEFAULT NULL COMMENT '账务类型是预存转账时，该字段是从哪个用户转账',
  PRIMARY KEY (`id`),
  KEY `idx_statdate` (`stat_date`),
  KEY `idx_orderid` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='his账目流水';

/*Table structure for table `his_account_item` */

DROP TABLE IF EXISTS `his_account_item`;

CREATE TABLE `his_account_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` bigint(20) DEFAULT NULL COMMENT '处置单id',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `item_id` varchar(50) DEFAULT NULL COMMENT '处置项id',
  `item_sub_id` varchar(50) DEFAULT NULL COMMENT '处置项子id',
  `item_amount` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `actual_amount` decimal(10,2) DEFAULT NULL COMMENT '实收金额，暂无用',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `qty` int(11) DEFAULT NULL COMMENT '数量',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `isnew` int(11) DEFAULT NULL COMMENT '1新患者',
  `account_type` int(11) DEFAULT NULL COMMENT '账务类型：1挂号，2处置收费，3收欠款，4整单退费，6调减，9预收',
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `flow_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_statdate2` (`stat_date`),
  KEY `idx_flowid` (`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结算单项目明细';

/*Table structure for table `his_account_itemcharge` */

DROP TABLE IF EXISTS `his_account_itemcharge`;

CREATE TABLE `his_account_itemcharge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` bigint(20) DEFAULT NULL COMMENT '处置单id',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `item_id` varchar(50) DEFAULT NULL COMMENT '处置项id',
  `item_sub_id` varchar(50) DEFAULT NULL COMMENT '处置项子级id',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '计费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '计费二级科目',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '收入金额',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医师id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `isnew` int(11) DEFAULT NULL COMMENT '是否为新',
  `account_type` int(11) DEFAULT NULL COMMENT '账务类型：1挂号，2处置收费，3收欠款，4整单退费，6调减，9预收',
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `flow_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_statdate1` (`stat_date`),
  KEY `idx_flowid` (`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结算单项目收费类型明细';

/*Table structure for table `his_arrears_record` */

DROP TABLE IF EXISTS `his_arrears_record`;

CREATE TABLE `his_arrears_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者ID',
  `statement_id` bigint(20) DEFAULT NULL COMMENT '欠款结算单ID',
  `unit_code` int(11) DEFAULT NULL COMMENT '欠款院区码',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '欠款结算单医生',
  `debt_amount` decimal(10,2) DEFAULT '0.00' COMMENT '欠款金额',
  `payoff` decimal(10,2) DEFAULT '0.00' COMMENT '还款金额',
  `repay_code` int(11) DEFAULT NULL COMMENT '还款院区',
  `repay_statement_id` bigint(20) DEFAULT NULL COMMENT '还款结算单ID',
  `flag` int(11) DEFAULT NULL COMMENT '是否退款（1是0否）',
  `status` int(11) DEFAULT NULL COMMENT '还款状态（0未还款，1部分已结，3已结款）',
  `dis_amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
  `type_code` varchar(11) DEFAULT NULL COMMENT '付款代码',
  `replace_statement_id` bigint(20) DEFAULT NULL COMMENT '退单还款ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_br_ss` */

DROP TABLE IF EXISTS `his_br_ss`;

CREATE TABLE `his_br_ss` (
  `SS_ID` bigint(20) NOT NULL COMMENT '收费单号',
  `SS_TYPE` varchar(1) DEFAULT NULL COMMENT '收费类型,0-门诊,1-住院',
  `ss_no` varchar(20) DEFAULT NULL COMMENT '收费单号',
  `SS_DATE` datetime NOT NULL COMMENT '收费日期(可修改)',
  `PAT_ID` bigint(20) DEFAULT NULL COMMENT '病人编号',
  `HOSP_ID` int(11) DEFAULT NULL COMMENT '院所代号',
  `DISCOUNTTYPE` int(11) DEFAULT NULL COMMENT '折扣类型,0-会员卡,1-优惠券',
  `DISCOUNTNO` varchar(50) DEFAULT NULL COMMENT '会员卡号或优惠券号',
  `AMOUNT` decimal(10,2) DEFAULT NULL COMMENT '本次金额',
  `AMOUNTOWN` decimal(10,2) DEFAULT NULL COMMENT '累计欠款',
  `AMOUNTYS` decimal(10,2) DEFAULT NULL COMMENT '本次应收',
  `AMOUNTVIP` decimal(10,2) DEFAULT NULL COMMENT '会员卡余额缴纳',
  `ACTIONNO` varchar(50) DEFAULT NULL COMMENT '抵用券号',
  `AMOUNTACTION` decimal(10,2) DEFAULT NULL COMMENT '抵用券金额',
  `OTHERTYPE` varchar(50) DEFAULT NULL COMMENT '其他支付方式',
  `AMOUNTOTHER` decimal(10,2) DEFAULT NULL COMMENT '其他方式支付金额',
  `OTHERNO` varchar(30) DEFAULT NULL COMMENT '其他支付号码',
  `DISCOUNTREASON` varchar(50) DEFAULT NULL COMMENT '人工优惠原因',
  `AMOUNTDISCOUNT` decimal(10,2) DEFAULT NULL COMMENT '人工优惠金额',
  `AMOUNTYF` decimal(10,2) DEFAULT NULL COMMENT '应收金额',
  `AMOUNTCASH` decimal(10,2) DEFAULT NULL COMMENT '现金支付',
  `AMOUNTSS` decimal(10,2) DEFAULT NULL COMMENT '实收金额',
  `AMOUNTCHANGE` decimal(10,2) DEFAULT NULL COMMENT '找零',
  `AMOUNTRETURN` decimal(10,2) DEFAULT NULL COMMENT '退费金额',
  `RETURNREASON` varchar(50) DEFAULT NULL COMMENT '退费原因',
  `RETURNMAN` bigint(20) DEFAULT NULL COMMENT '退费人',
  `RETURNCONFIRM` bigint(20) DEFAULT NULL COMMENT '退费审核人',
  `RETURNDATE` datetime DEFAULT NULL COMMENT '退费日期',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态,0-收费,1-申请退费,2-审批通过,3-完成退费',
  `PRINTNUM` int(11) DEFAULT NULL COMMENT '打印次数',
  `REMARK` varchar(30) DEFAULT NULL COMMENT '备注',
  `OPERATOR` bigint(20) DEFAULT NULL COMMENT '收费人',
  `OPERTIME` datetime DEFAULT NULL COMMENT '收费时间',
  `CANCELMAN` bigint(20) DEFAULT NULL COMMENT '退费人员',
  `CANCELTIME` datetime DEFAULT NULL COMMENT '退费时间',
  `ROWFLAG` int(11) DEFAULT NULL COMMENT '收退费类型:1:挂号收费2.处置收费3.收欠费4.整单退费5.补加6.调减7.协商退费8.退计划',
  `MONEY_OTH1` decimal(10,2) DEFAULT NULL COMMENT '其他非现金',
  `MONEY_OTH2` decimal(10,2) DEFAULT NULL COMMENT '优惠券(团购)',
  `ARREARAGE` decimal(10,2) DEFAULT NULL COMMENT '欠款金额(不变)',
  `PAYOFF` decimal(10,2) DEFAULT NULL COMMENT '还收欠款金额累计(还欠款，补加调减)',
  `ISFUNDMENT` int(11) DEFAULT NULL COMMENT '是否退费',
  `GH_ID` bigint(20) DEFAULT NULL COMMENT '挂号编号',
  `SSS_ID` bigint(20) DEFAULT NULL COMMENT '退费关联ss_id',
  `MONEY_PRE` decimal(10,2) DEFAULT NULL COMMENT '预交款金额',
  `MONEY_GRA` decimal(10,2) DEFAULT NULL COMMENT '预交款赠金',
  `MONEY_WX` decimal(10,2) DEFAULT NULL COMMENT '微信支付金额',
  `rowguid` varchar(100) DEFAULT NULL COMMENT '小天使实收表唯一关键字',
  `money_ic` decimal(10,2) DEFAULT NULL COMMENT 'ic卡支付',
  `rowguid2` varchar(100) DEFAULT NULL,
  `out_trade_no` varchar(32) DEFAULT NULL COMMENT '商户系统内部的订单号,32个字符内、可包含字母',
  `out_refund_no` varchar(32) DEFAULT NULL COMMENT '商户退费单号',
  `outnos` varchar(1000) DEFAULT NULL COMMENT '退费单号列表(仅退费时用到)',
  `dmoney` decimal(10,2) DEFAULT NULL COMMENT '折扣金额',
  `dnote` varchar(50) DEFAULT NULL COMMENT '折扣原因',
  `MONEY_OTH2TYPE` varchar(50) DEFAULT NULL COMMENT '团购方式',
  `MONEY_OTH2NO` varchar(30) DEFAULT NULL COMMENT '团购券号码',
  `POSPoundage` decimal(18,5) DEFAULT NULL COMMENT 'POS手续费',
  `TGPoundage` decimal(10,2) DEFAULT NULL COMMENT '团购手续费',
  `MONEY_INSUR` decimal(10,2) DEFAULT NULL COMMENT '太平保险金额',
  `MONEY_DZYHQ` decimal(10,2) DEFAULT NULL COMMENT '电子优惠券',
  PRIMARY KEY (`SS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='老his实收数据表';

/*Table structure for table `his_br_ys` */

DROP TABLE IF EXISTS `his_br_ys`;

CREATE TABLE `his_br_ys` (
  `YS_ID` bigint(20) NOT NULL COMMENT '收费序号',
  `YS_DATE` datetime DEFAULT NULL COMMENT '应收日期',
  `YS_TYPE` varchar(1) DEFAULT NULL COMMENT '应收类型,0-门诊,1-住院',
  `HOSP_ID` int(11) DEFAULT NULL COMMENT '院所代号',
  `PAT_ID` bigint(20) DEFAULT NULL COMMENT '病人序号',
  `GH_ID` bigint(20) DEFAULT NULL COMMENT '挂号编号',
  `ZY_ID` bigint(20) DEFAULT NULL COMMENT '住院ID',
  `DR_ID` bigint(20) DEFAULT NULL COMMENT '医师代号',
  `NURSE_ID` bigint(20) DEFAULT NULL COMMENT '护士',
  `SFXM_ID` int(11) DEFAULT NULL COMMENT '收费项目',
  `PRICE` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `COST` decimal(10,2) DEFAULT NULL COMMENT '成本',
  `UNIT` varchar(10) DEFAULT NULL COMMENT '单位',
  `DISCOUNT` decimal(10,2) DEFAULT NULL COMMENT '折扣',
  `QTY` decimal(10,2) DEFAULT NULL COMMENT '数量',
  `QTY_T` decimal(10,2) DEFAULT NULL COMMENT '退费数量',
  `AMOUNT` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `AMOUNTINFACT` decimal(10,2) DEFAULT NULL COMMENT '实际金额',
  `STATUS` int(11) DEFAULT NULL COMMENT '状态,0-划价,1-送出,2-完成,3-结算',
  `SS_ID` bigint(20) DEFAULT NULL COMMENT '收据单号',
  `YJ_ID` bigint(20) DEFAULT NULL COMMENT '医技ID,检查/化验/取药/手术单ID',
  `YJ_NO` varchar(15) DEFAULT NULL COMMENT '医技NO',
  `PLAN_ID` bigint(20) DEFAULT NULL COMMENT '计划治疗编号',
  `FDI` varchar(80) DEFAULT NULL COMMENT '牙位',
  `SIDEPOS` varchar(3) DEFAULT NULL COMMENT '牙面',
  `PART` varchar(50) DEFAULT NULL COMMENT '部位',
  `RESULT` varchar(200) DEFAULT NULL COMMENT '结果',
  `SEHAO` varchar(50) DEFAULT NULL COMMENT '色号',
  `YPPINLV` varchar(20) DEFAULT NULL COMMENT '频次',
  `YPJILIANG` decimal(10,2) DEFAULT NULL COMMENT '剂量',
  `YPJILIANGUNIT` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `YPYONGFA` varchar(10) DEFAULT NULL COMMENT '用法',
  `YPSHIJI` varchar(10) DEFAULT NULL COMMENT '时机',
  `EXECUTOR` bigint(20) DEFAULT NULL COMMENT '执行人',
  `EXECUTETIME` datetime DEFAULT NULL COMMENT '执行时间',
  `OPERATOR` bigint(20) DEFAULT NULL COMMENT '操作人员',
  `OPERTIME` datetime DEFAULT NULL COMMENT '操作时间',
  `CANCELREASON` varchar(50) DEFAULT NULL COMMENT '取消原因',
  `CANCELMAN` bigint(20) DEFAULT NULL COMMENT '退费人员',
  `CANCELTIME` datetime DEFAULT NULL COMMENT '退费时间',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `ldele` int(11) DEFAULT NULL COMMENT '是否退费（1:退费，0未退费）',
  `nstate` int(11) DEFAULT NULL COMMENT '是否收费(0:未收，1已收)',
  `BftPayAble` decimal(10,2) DEFAULT NULL COMMENT '太平保险赔付金额',
  `BftCLSV_COIN_AMT` decimal(10,2) DEFAULT NULL COMMENT '太平保险客户自费金额',
  `BftHPHP_AMT` decimal(10,2) DEFAULT NULL COMMENT '太平保险个人优惠金额',
  PRIMARY KEY (`YS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='老his应收数据表';

/*Table structure for table `his_card_patient` */

DROP TABLE IF EXISTS `his_card_patient`;

CREATE TABLE `his_card_patient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '卡ID',
  `patid` bigint(20) DEFAULT NULL COMMENT '病人ID',
  `ismaster` int(11) DEFAULT NULL COMMENT '主卡人（0-否，1-是）',
  `duptdate` datetime DEFAULT NULL COMMENT '修改时间',
  `card_type` int(11) DEFAULT NULL COMMENT '0折扣卡，1IC卡',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_cards` */

DROP TABLE IF EXISTS `his_cards`;

CREATE TABLE `his_cards` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卡id',
  `disccardno` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '折扣卡号',
  `kindkey` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '卡种码',
  `expiration` date DEFAULT NULL COMMENT '有效期',
  `releasedt` date DEFAULT NULL COMMENT '发卡日期',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作人',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '工本费',
  `maxdmoney` decimal(10,2) DEFAULT NULL COMMENT '最大折扣金额',
  `needmaxdm` int(11) DEFAULT NULL COMMENT '是否需要最大折扣',
  `duptdate` datetime DEFAULT NULL COMMENT '操作日期',
  `operator2` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '卖卡人',
  `selldt` datetime DEFAULT NULL COMMENT '卖卡时间',
  `sellperson` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '买卡人',
  `pricecard` int(11) DEFAULT NULL,
  `card_type` int(11) DEFAULT NULL COMMENT '0：IC卡，1：折扣卡',
  `unit_code` int(11) DEFAULT NULL,
  `remark` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `payment_code` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_city` */

DROP TABLE IF EXISTS `his_city`;

CREATE TABLE `his_city` (
  `CityID` bigint(20) NOT NULL,
  `CityName` varchar(50) DEFAULT NULL,
  `ProvinceID` bigint(20) DEFAULT NULL,
  `DateCreated` datetime DEFAULT NULL,
  `DateUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`CityID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `his_counter_fee` */

DROP TABLE IF EXISTS `his_counter_fee`;

CREATE TABLE `his_counter_fee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(50) DEFAULT NULL,
  `counter_fee` decimal(10,5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手续费';

/*Table structure for table `his_depart` */

DROP TABLE IF EXISTS `his_depart`;

CREATE TABLE `his_depart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_name` varchar(50) DEFAULT NULL,
  `pym` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_dict` */

DROP TABLE IF EXISTS `his_dict`;

CREATE TABLE `his_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `dict_name` varchar(100) DEFAULT NULL,
  `dict_type` varchar(20) DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8 COMMENT='字典';

/*Table structure for table `his_discdetaillog` */

DROP TABLE IF EXISTS `his_discdetaillog`;

CREATE TABLE `his_discdetaillog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regguid` bigint(20) DEFAULT NULL,
  `patguid` bigint(20) DEFAULT NULL,
  `disccardid` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `itemcode` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `kindname` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `kindkey` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `chargeguid` bigint(20) DEFAULT NULL,
  `inprice` decimal(10,2) DEFAULT '0.00',
  `reckind` int(11) DEFAULT NULL,
  `limittime` int(11) DEFAULT NULL,
  `discmoney` decimal(10,2) DEFAULT '0.00',
  `duptdate` datetime DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_discitems` */

DROP TABLE IF EXISTS `his_discitems`;

CREATE TABLE `his_discitems` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kindkey` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `itemcode` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `limittime` int(11) DEFAULT NULL,
  `caclmax` int(11) DEFAULT NULL,
  `discnum` decimal(10,2) DEFAULT '0.00',
  `discmode` int(11) DEFAULT NULL,
  `inprice` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_disckind` */

DROP TABLE IF EXISTS `his_disckind`;

CREATE TABLE `his_disckind` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'kindkey',
  `kindname` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `needint` int(11) DEFAULT NULL,
  `checkpat` int(11) DEFAULT NULL COMMENT '适用所有病人-活动（0-是，1-否）',
  `expiration` date DEFAULT NULL,
  `hospnolist` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isdele` int(11) DEFAULT NULL,
  `startdate` date DEFAULT NULL,
  `limitpeop` int(11) DEFAULT NULL,
  `maxdmoney` decimal(10,2) DEFAULT '0.00',
  `hosp_no` int(11) DEFAULT NULL,
  `duptdate` datetime DEFAULT NULL,
  `sellmny` decimal(10,2) DEFAULT '0.00',
  `addmny` decimal(10,2) DEFAULT '0.00',
  `exdnum` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `payment_code` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_disckind_history` */

DROP TABLE IF EXISTS `his_disckind_history`;

CREATE TABLE `his_disckind_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `disckind_id` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '卡种ID',
  `exp_time` datetime DEFAULT NULL COMMENT '有效期',
  `card_num` int(11) DEFAULT NULL COMMENT '发卡数量',
  `employee` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领用人',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_disctimes` */

DROP TABLE IF EXISTS `his_disctimes`;

CREATE TABLE `his_disctimes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disccardid` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pcode` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `usetime` int(11) DEFAULT NULL,
  `duptdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_docroom` */

DROP TABLE IF EXISTS `his_docroom`;

CREATE TABLE `his_docroom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(255) DEFAULT NULL,
  `room_ip` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='诊室';

/*Table structure for table `his_employee` */

DROP TABLE IF EXISTS `his_employee`;

CREATE TABLE `his_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增+院区编号',
  `employee_name` varchar(50) DEFAULT NULL COMMENT '雇员名称',
  `create_time` date DEFAULT NULL COMMENT '注册时间',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `user_type` int(11) DEFAULT NULL COMMENT '岗位，2医生，1前台',
  `islogin` int(11) DEFAULT NULL COMMENT '允许登录',
  `mobile` varchar(15) NOT NULL COMMENT '手机号',
  `password` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `lastvist_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `role_id` int(11) DEFAULT NULL COMMENT '系统角色id，1财务，4优惠券',
  `status` int(11) DEFAULT NULL COMMENT '0无效，1有效',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `cookie` varchar(32) DEFAULT NULL COMMENT '登录cookie',
  `depart_id` int(11) DEFAULT NULL COMMENT '部门',
  `docroom_id` int(11) DEFAULT NULL COMMENT '诊室id',
  `disc_rate` decimal(10,2) DEFAULT NULL,
  `is_show` int(11) DEFAULT NULL COMMENT '1显示 0不显示',
  `title` varchar(45) DEFAULT NULL COMMENT '职称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织雇员表';

/*Table structure for table `his_group` */

DROP TABLE IF EXISTS `his_group`;

CREATE TABLE `his_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组的ID',
  `group_name` varchar(20) DEFAULT NULL COMMENT '组的名称',
  `status` int(11) DEFAULT NULL,
  `unit_id` bigint(20) DEFAULT NULL COMMENT '分院ID',
  `create_time` datetime DEFAULT NULL COMMENT '组创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_group_employee` */

DROP TABLE IF EXISTS `his_group_employee`;

CREATE TABLE `his_group_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '中间表的ID',
  `group_id` bigint(20) DEFAULT NULL COMMENT '组的ID',
  `employee_id` bigint(20) DEFAULT NULL COMMENT '医生ID',
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_iccard_moneyhistory` */

DROP TABLE IF EXISTS `his_iccard_moneyhistory`;

CREATE TABLE `his_iccard_moneyhistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rowflag` int(11) DEFAULT NULL COMMENT '消费方式1:充值2:退还3:消费4:退卡5:转出6:转入8:卖卡9:发卡',
  `moneyadd` decimal(10,2) DEFAULT '0.00' COMMENT '操作金额',
  `restmoney` decimal(10,2) DEFAULT '0.00' COMMENT '剩余金额',
  `addcash` decimal(10,2) DEFAULT '0.00' COMMENT '现金',
  `addcard` decimal(10,2) DEFAULT '0.00' COMMENT '银行卡',
  `addtime` datetime DEFAULT NULL COMMENT '消费日期',
  `duptdate` datetime DEFAULT NULL COMMENT '最后更新日期',
  `remark` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `iccard_id` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IC卡ID',
  `cardkind` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付方式',
  `cardno` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付号码',
  `patid` bigint(20) DEFAULT NULL,
  `operator` bigint(20) DEFAULT NULL COMMENT '操作人',
  `unit_code` int(11) DEFAULT NULL,
  `given_amount` decimal(10,2) DEFAULT '0.00',
  `orderid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_iccard_rest` */

DROP TABLE IF EXISTS `his_iccard_rest`;

CREATE TABLE `his_iccard_rest` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'ID',
  `restmoney` decimal(10,2) DEFAULT '0.00' COMMENT '余额',
  `arrearage` decimal(10,2) DEFAULT '0.00' COMMENT '未减去的钱数',
  `duptdate` datetime DEFAULT NULL COMMENT '最后更新日期',
  `given_amount` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `his_organiz` */

DROP TABLE IF EXISTS `his_organiz`;

CREATE TABLE `his_organiz` (
  `id` int(11) NOT NULL COMMENT '机构代码',
  `unit_name` varchar(50) DEFAULT NULL COMMENT '机构名字',
  `parent_id` int(11) DEFAULT NULL COMMENT '上级机构代码',
  `level` int(11) DEFAULT NULL COMMENT '机构层级',
  `province` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `access_token` varchar(32) DEFAULT NULL COMMENT '访问token',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `address` varchar(45) DEFAULT NULL COMMENT '地址',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `x_id` varchar(45) DEFAULT NULL COMMENT 'x光id',
  `is_effect` varchar(45) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unit_name_UNIQUE` (`unit_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构  数据初始化完成';

/*Table structure for table `his_patient` */

DROP TABLE IF EXISTS `his_patient`;

CREATE TABLE `his_patient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增+院区编号',
  `pat_name` varchar(50) DEFAULT NULL COMMENT '患者名字',
  `pym` varchar(50) DEFAULT NULL COMMENT '拼音',
  `unit_code` int(11) DEFAULT NULL COMMENT '院区代码',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `mobile` varchar(15) DEFAULT NULL COMMENT '手机号',
  `pat_no` varchar(20) DEFAULT NULL COMMENT '病历编号',
  `user_sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄，生日字段参考',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `maindoc_id` bigint(20) DEFAULT NULL COMMENT '主治医生id',
  `maindoc_name` varchar(45) DEFAULT NULL COMMENT '主治医生名字',
  `account_amount` decimal(10,2) DEFAULT '0.00' COMMENT '账户余额',
  `original_amount` decimal(10,2) DEFAULT '0.00' COMMENT '充值金额',
  `given_amount` decimal(10,2) DEFAULT '0.00' COMMENT '赠送金额',
  `debt_amount` decimal(10,2) DEFAULT '0.00' COMMENT '欠款',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `source` varchar(50) DEFAULT NULL COMMENT '来源',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `newly_ask` varchar(255) DEFAULT NULL COMMENT '初诊询问',
  `allergic_his` varchar(255) DEFAULT NULL COMMENT '过敏史',
  `newly_date` date DEFAULT NULL COMMENT '初诊日期',
  `visit_times` int(11) DEFAULT NULL COMMENT '就诊次数',
  `lastappoint_date` date DEFAULT NULL COMMENT '最近预约',
  `lasthosp_date` date DEFAULT NULL COMMENT '最近就诊',
  `total_spand` decimal(10,2) DEFAULT '0.00' COMMENT '累计消费',
  `introducer` varchar(20) DEFAULT NULL COMMENT '介绍人',
  `given_coeff` decimal(10,2) DEFAULT '0.00' COMMENT '赠金比率',
  `tel` varchar(45) DEFAULT NULL COMMENT '电话',
  `email` varchar(45) DEFAULT NULL,
  `persid` varchar(45) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT '患者类型（1正畸患者，2种植患者，可多选逗号分隔）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='患者表';

/*Table structure for table `his_patient_case` */

DROP TABLE IF EXISTS `his_patient_case`;

CREATE TABLE `his_patient_case` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pat_id` bigint(20) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `tooth` varchar(1000) DEFAULT NULL,
  `zs` varchar(1000) DEFAULT NULL COMMENT '主诉',
  `xbs` varchar(1000) DEFAULT NULL COMMENT '现病史',
  `jwbs` varchar(1000) DEFAULT NULL COMMENT '既往病史',
  `jc` varchar(1000) DEFAULT NULL COMMENT '检查',
  `zd` varchar(1000) DEFAULT NULL COMMENT '诊断 ',
  `cl` varchar(1000) DEFAULT NULL COMMENT '处理',
  `yz` varchar(1000) DEFAULT NULL COMMENT '医嘱 ',
  `bl_date` datetime DEFAULT NULL,
  `did` bigint(20) DEFAULT NULL,
  `jzsx` varchar(1000) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `jzs` varchar(1000) DEFAULT NULL COMMENT '接种史',
  `gms` varchar(1000) DEFAULT NULL COMMENT '过敏史',
  `wss` varchar(1000) DEFAULT NULL COMMENT '外伤史',
  `sss` varchar(1000) DEFAULT NULL COMMENT '手术史',
  `jzs1` varchar(1000) DEFAULT NULL COMMENT '家族史',
  `hys` varchar(1000) DEFAULT NULL COMMENT '婚育史',
  `zlfa` varchar(1000) DEFAULT NULL COMMENT '治疗方案',
  `zlgc` varchar(1000) DEFAULT NULL COMMENT '治疗过程',
  `crbs` varchar(1000) DEFAULT NULL COMMENT '传染病史',
  `sf` varchar(1000) DEFAULT NULL COMMENT '随访',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_patient_ext` */

DROP TABLE IF EXISTS `his_patient_ext`;

CREATE TABLE `his_patient_ext` (
  `pat_id` bigint(20) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_patient_insurance` */

DROP TABLE IF EXISTS `his_patient_insurance`;

CREATE TABLE `his_patient_insurance` (
  `id` bigint(20) NOT NULL,
  `policy_id` varchar(50) DEFAULT NULL,
  `member_key` bigint(20) DEFAULT NULL,
  `pat_id` bigint(20) DEFAULT NULL,
  `bxtype` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `certificates` varchar(10) DEFAULT NULL,
  `certificatescode` varchar(30) DEFAULT NULL,
  `moile` varchar(30) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `remark` varchar(5000) DEFAULT NULL,
  `premiums` decimal(10,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_payment` */

DROP TABLE IF EXISTS `his_payment`;

CREATE TABLE `his_payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(11) NOT NULL DEFAULT '' COMMENT '付款代码',
  `type_name` varchar(25) DEFAULT NULL COMMENT '支付名称',
  `pay_type` varchar(10) DEFAULT NULL COMMENT '类型',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '二级付款方式',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_paytype` (`type_code`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COMMENT='付款方式';

/*Table structure for table `his_prepayact` */

DROP TABLE IF EXISTS `his_prepayact`;

CREATE TABLE `his_prepayact` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `actname` varchar(45) DEFAULT NULL COMMENT '活动名称',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '实充金额',
  `given_amount` decimal(10,2) DEFAULT NULL COMMENT '赠送金额',
  `status` int(11) DEFAULT NULL COMMENT '状态：1正常，0失效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_provice` */

DROP TABLE IF EXISTS `his_provice`;

CREATE TABLE `his_provice` (
  `ProvinceID` bigint(20) NOT NULL,
  `ProvinceName` varchar(50) DEFAULT NULL,
  `DateCreated` datetime DEFAULT NULL,
  `DateUpdated` datetime DEFAULT NULL,
  PRIMARY KEY (`ProvinceID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `his_register` */

DROP TABLE IF EXISTS `his_register`;

CREATE TABLE `his_register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `gh_time` datetime DEFAULT NULL COMMENT '挂号时间',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `appo_len` int(11) DEFAULT NULL COMMENT '预约时长，分钟',
  `remark` varchar(255) DEFAULT NULL COMMENT '预约备注',
  `dentist_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `unit_code` int(11) DEFAULT NULL COMMENT '院区码',
  `isnew` int(11) DEFAULT NULL COMMENT '0旧，1新',
  `source` varchar(50) DEFAULT NULL COMMENT '来源',
  `msg_id` bigint(20) DEFAULT NULL COMMENT '短信id',
  `dept_code` int(11) DEFAULT NULL COMMENT '科室id',
  `service_items` varchar(255) DEFAULT NULL COMMENT '事项',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `is_appoint` int(11) DEFAULT NULL COMMENT '是否预约1是，0不是',
  `status` int(11) DEFAULT NULL COMMENT '状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号',
  `lock_user` bigint(20) DEFAULT NULL COMMENT '锁表用户，只有锁表用户可以修改，解锁',
  `statement_itemid` bigint(20) DEFAULT NULL COMMENT '结算ID',
  `out_call` varchar(20) DEFAULT NULL COMMENT '外呼电话',
  `affirm` int(2) DEFAULT '0' COMMENT '预约是否确认，1为确认',
  `introducer` varchar(20) DEFAULT NULL COMMENT '预约人',
  PRIMARY KEY (`id`),
  KEY `idx_ghdate` (`gh_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约挂号表';

/*Table structure for table `his_register_log` */

DROP TABLE IF EXISTS `his_register_log`;

CREATE TABLE `his_register_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reg_id` bigint(20) DEFAULT NULL COMMENT '挂号表id',
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `gh_time` datetime DEFAULT NULL COMMENT '挂号时间',
  `begin_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `appo_len` int(11) DEFAULT NULL COMMENT '预约时长，分钟',
  `remark` varchar(255) DEFAULT NULL COMMENT '预约备注',
  `dentist_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `unit_code` int(11) DEFAULT NULL COMMENT '院区码',
  `isnew` int(11) DEFAULT NULL COMMENT '0旧，1新',
  `source` varchar(50) DEFAULT NULL COMMENT '来源',
  `msg_id` bigint(20) DEFAULT NULL COMMENT '短信id',
  `dept_code` int(11) DEFAULT NULL COMMENT '科室id',
  `service_items` varchar(255) DEFAULT NULL COMMENT '事项',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作者',
  `is_appoint` int(11) DEFAULT NULL COMMENT '是否预约1是，0不是',
  `status` int(11) DEFAULT NULL COMMENT '状态，1预约，2挂号，3待收费，4已诊结，5预约未到，6取消预约，7退号',
  `lock_user` bigint(20) DEFAULT NULL COMMENT '锁表用户，只有锁表用户可以修改，解锁',
  `statement_id` bigint(20) DEFAULT NULL COMMENT '结算ID',
  `out_call` varchar(20) DEFAULT NULL COMMENT '外呼电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约挂号表';

/*Table structure for table `his_service_item` */

DROP TABLE IF EXISTS `his_service_item`;

CREATE TABLE `his_service_item` (
  `item_code` varchar(20) NOT NULL DEFAULT '' COMMENT '收费科目编码',
  `item_name` varchar(100) DEFAULT NULL COMMENT '收费科目名称',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '收费科目上级id',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='处置项';

/*Table structure for table `his_service_item_fl` */

DROP TABLE IF EXISTS `his_service_item_fl`;

CREATE TABLE `his_service_item_fl` (
  `item_code` varchar(20) NOT NULL COMMENT '收费科目编码',
  `item_name` varchar(100) DEFAULT NULL COMMENT '收费科目名称',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '收费科目上级id',
  `hiscode` int(11) DEFAULT NULL COMMENT 'his的编码',
  `pym` varchar(100) DEFAULT NULL COMMENT '拼音首字母',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='处置项二级分类';

/*Table structure for table `his_service_item_xm` */

DROP TABLE IF EXISTS `his_service_item_xm`;

CREATE TABLE `his_service_item_xm` (
  `item_code` varchar(20) NOT NULL COMMENT '收费科目编码',
  `item_name` varchar(100) DEFAULT NULL COMMENT '收费科目名称',
  `pym` varchar(100) DEFAULT NULL COMMENT '拼音',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '收费科目上级id',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `jt_price` decimal(10,2) DEFAULT NULL COMMENT '建议价格',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_show` int(11) DEFAULT NULL COMMENT '0不可见，1可见',
  `hosp_code` varchar(1000) DEFAULT NULL,
  `item_jtname` varchar(100) DEFAULT NULL,
  `sf_code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='处置项三级项目';

/*Table structure for table `his_sice_item_fl` */

DROP TABLE IF EXISTS `his_sice_item_fl`;

CREATE TABLE `his_sice_item_fl` (
  `item_code` varchar(20) NOT NULL COMMENT '收费科目编码',
  `item_name` varchar(100) DEFAULT NULL COMMENT '收费科目名称',
  `parent_id` varchar(20) DEFAULT NULL COMMENT '收费科目上级id',
  `hiscode` int(11) DEFAULT NULL COMMENT 'his的编码',
  `pym` varchar(100) DEFAULT NULL COMMENT '拼音首字母',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='处置项二级分类';

/*Table structure for table `his_statement` */

DROP TABLE IF EXISTS `his_statement`;

CREATE TABLE `his_statement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水id',
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `account_type` int(11) DEFAULT NULL COMMENT '1挂号，2处置收费，3收欠费，4结算单调整，9预存，10退预存，11预存转账，12处置单调整，13会计退费',
  `reg_id` bigint(20) DEFAULT NULL COMMENT '挂号id',
  `statement_itemid` bigint(20) DEFAULT NULL COMMENT '处置项id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父结算单id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `unit_code` int(11) DEFAULT NULL COMMENT '院区码',
  `total_amount` decimal(10,2) DEFAULT '0.00' COMMENT '总额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作员',
  `pay_amount` decimal(10,2) DEFAULT '0.00' COMMENT '实收金额',
  `debt_amount` decimal(10,2) DEFAULT '0.00' COMMENT '欠款金额',
  `payoff` decimal(10,2) DEFAULT '0.00' COMMENT '还款金额',
  `print_num` int(11) DEFAULT '0' COMMENT '打印次数',
  `status` int(11) DEFAULT NULL COMMENT '0未结，1已结',
  `flag` int(11) DEFAULT NULL COMMENT '0正常，1退单',
  `act_id` int(11) DEFAULT NULL,
  `old_statement_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_cdtime` (`create_time`),
  KEY `idx_regid` (`reg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='患者结算表';

/*Table structure for table `his_statement_charge` */

DROP TABLE IF EXISTS `his_statement_charge`;

CREATE TABLE `his_statement_charge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水id',
  `statement_id` bigint(20) DEFAULT NULL COMMENT '单据id',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '记账科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '记账二级科目',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '实际金额',
  `serial_number` varchar(255) DEFAULT NULL COMMENT '卡劵类序列号',
  `tgpoundage` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `account_type` int(11) DEFAULT NULL COMMENT '账务类型：1挂号，2处置收费，3收欠费，4整单退费，9预存，10退预存，11预存转账',
  `operator` bigint(20) DEFAULT NULL COMMENT '医生，前台',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_statm` (`statement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结算单收费明细';

/*Table structure for table `his_statement_charge_del` */

DROP TABLE IF EXISTS `his_statement_charge_del`;

CREATE TABLE `his_statement_charge_del` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水id',
  `sc_id` bigint(20) DEFAULT NULL COMMENT '单据id',
  `statement_id` bigint(20) DEFAULT NULL COMMENT '单据id',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '记账科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '记账二级科目',
  `real_amount` decimal(10,2) DEFAULT NULL COMMENT '实际金额',
  `serial_number` varchar(255) DEFAULT NULL COMMENT '卡劵类序列号',
  `tgpoundage` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `account_type` int(11) DEFAULT NULL COMMENT '账务类型：1挂号，2处置收费，3收欠费，4整单退费，9预存，10退预存，11预存转账',
  `operator` bigint(20) DEFAULT NULL COMMENT '医生，前台',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结算单收费明细';

/*Table structure for table `his_statement_del` */

DROP TABLE IF EXISTS `his_statement_del`;

CREATE TABLE `his_statement_del` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水id',
  `s_id` bigint(20) DEFAULT NULL COMMENT '表id',
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `account_type` int(11) DEFAULT NULL COMMENT '1挂号，2处置收费，3收欠费，4整单退费，9预存，10退预存，11预存转账',
  `reg_id` bigint(20) DEFAULT NULL COMMENT '挂号id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父结算单id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `unit_code` int(11) DEFAULT NULL COMMENT '院区码',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '总额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作员',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '实收金额',
  `debt_amount` decimal(10,2) DEFAULT NULL COMMENT '欠款金额',
  `payoff` decimal(10,2) DEFAULT NULL COMMENT '还款金额',
  `print_num` int(11) DEFAULT '0' COMMENT '打印次数',
  `status` int(11) DEFAULT NULL COMMENT '0未结，1已结',
  `flag` int(11) DEFAULT NULL COMMENT '0正常，1退单',
  `act_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='患者结算表';

/*Table structure for table `his_statement_item` */

DROP TABLE IF EXISTS `his_statement_item`;

CREATE TABLE `his_statement_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '流水id',
  `pat_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `account_type` int(11) DEFAULT NULL COMMENT '1挂号，2处置收费，3收欠费，4整单退费，9预存，10退预存，11预存转账',
  `reg_id` bigint(20) DEFAULT NULL COMMENT '挂号id',
  `statement_id` bigint(20) DEFAULT NULL COMMENT '结算单id',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父结算单id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `unit_code` int(11) DEFAULT NULL COMMENT '院区码',
  `total_amount` decimal(10,2) DEFAULT '0.00' COMMENT '总额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `item_amount` decimal(10,2) DEFAULT '0.00' COMMENT '处置总金额',
  `pay_amount` decimal(10,2) DEFAULT '0.00' COMMENT '应付金额',
  `discount_type` varchar(20) DEFAULT NULL COMMENT '优惠方式',
  `discount_amount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额',
  `print_num` int(11) DEFAULT '0' COMMENT '打印次数',
  `status` int(11) DEFAULT NULL COMMENT '0未结，1已结',
  `flag` int(11) DEFAULT NULL COMMENT '0正常，1退单',
  `act_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_cdtime` (`create_time`),
  KEY `idx_regid` (`reg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='患者结算表';

/*Table structure for table `his_statement_item_del` */

DROP TABLE IF EXISTS `his_statement_item_del`;

CREATE TABLE `his_statement_item_del` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `si_id` bigint(20) DEFAULT NULL COMMENT '处置单id',
  `statement_id` bigint(20) DEFAULT NULL COMMENT '处置单id',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `item_id` varchar(50) DEFAULT NULL COMMENT '处置项id',
  `item_sub_id` varchar(50) DEFAULT NULL COMMENT '处置项子id',
  `item_amount` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `qty` int(11) DEFAULT NULL COMMENT '数量',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `isnew` int(11) DEFAULT NULL COMMENT '1新患者',
  `account_type` int(11) DEFAULT NULL COMMENT '账务类型：1挂号，2处置收费，3收欠费，4整单退费，9预存，10退预存，11预存转账',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `tooth` varchar(255) DEFAULT NULL COMMENT '牙位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结算单项目明细';

/*Table structure for table `his_statement_item_detail` */

DROP TABLE IF EXISTS `his_statement_item_detail`;

CREATE TABLE `his_statement_item_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `statement_itemid` bigint(20) DEFAULT NULL COMMENT '处置单id',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `item_id` varchar(50) DEFAULT NULL COMMENT '处置项id',
  `item_sub_id` varchar(50) DEFAULT NULL COMMENT '处置项子id',
  `item_amount` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `qty` int(11) DEFAULT NULL COMMENT '数量',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `patient_id` bigint(20) DEFAULT NULL COMMENT '患者id',
  `isnew` int(11) DEFAULT NULL COMMENT '1新患者',
  `account_type` int(11) DEFAULT NULL COMMENT '账务类型：1挂号，2处置收费，3收欠费，4整单退费，9预存，10退预存，11预存转账',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `tooth` varchar(255) DEFAULT NULL COMMENT '牙位',
  PRIMARY KEY (`id`),
  KEY `idx_statme` (`statement_itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='结算单项目明细';

/*Table structure for table `his_station` */

DROP TABLE IF EXISTS `his_station`;

CREATE TABLE `his_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gw_name` varchar(50) DEFAULT NULL,
  `pym` varchar(50) DEFAULT NULL,
  `classes` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_kind` */

DROP TABLE IF EXISTS `his_tmp_kind`;

CREATE TABLE `his_tmp_kind` (
  `kindname` varchar(100) DEFAULT NULL,
  `oldtype` varchar(100) DEFAULT NULL,
  `oldid` varchar(100) DEFAULT NULL,
  `newtype` varchar(100) DEFAULT NULL,
  `newid` varchar(100) DEFAULT NULL,
  `fee` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `docfee` int(11) DEFAULT NULL,
  `people` varchar(20) DEFAULT NULL,
  `isnull` varchar(10) DEFAULT NULL,
  `kindid` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_kind2` */

DROP TABLE IF EXISTS `his_tmp_kind2`;

CREATE TABLE `his_tmp_kind2` (
  `kindname` varchar(100) DEFAULT NULL,
  `oldtype` varchar(100) DEFAULT NULL,
  `oldid` varchar(100) DEFAULT NULL,
  `newtype` varchar(100) DEFAULT NULL,
  `newid` varchar(100) DEFAULT NULL,
  `fee` decimal(11,2) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `docfee` bigint(11) DEFAULT NULL,
  `people` varchar(20) DEFAULT NULL,
  `isnull` varchar(10) DEFAULT NULL,
  `kindid` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_mobile` */

DROP TABLE IF EXISTS `his_tmp_mobile`;

CREATE TABLE `his_tmp_mobile` (
  `id` bigint(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_pat_id` */

DROP TABLE IF EXISTS `his_tmp_pat_id`;

CREATE TABLE `his_tmp_pat_id` (
  `pat_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_patient` */

DROP TABLE IF EXISTS `his_tmp_patient`;

CREATE TABLE `his_tmp_patient` (
  `id` bigint(20) DEFAULT NULL,
  `pat_name` varchar(100) DEFAULT NULL,
  `pat_no` varchar(20) DEFAULT NULL,
  `source` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_register_isnew` */

DROP TABLE IF EXISTS `his_tmp_register_isnew`;

CREATE TABLE `his_tmp_register_isnew` (
  `pat_id` bigint(20) DEFAULT NULL,
  `gh_time` date DEFAULT NULL,
  KEY `index_tmp_re_isnew` (`pat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_type` */

DROP TABLE IF EXISTS `his_tmp_type`;

CREATE TABLE `his_tmp_type` (
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `new1` varchar(100) DEFAULT NULL,
  `new2` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_user` */

DROP TABLE IF EXISTS `his_tmp_user`;

CREATE TABLE `his_tmp_user` (
  `username` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_code` varchar(100) DEFAULT NULL,
  `user_gw` varchar(20) DEFAULT NULL,
  `gw_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_user_doc` */

DROP TABLE IF EXISTS `his_tmp_user_doc`;

CREATE TABLE `his_tmp_user_doc` (
  `pat_id` bigint(20) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL,
  `doc_name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_tmp_user_doc2` */

DROP TABLE IF EXISTS `his_tmp_user_doc2`;

CREATE TABLE `his_tmp_user_doc2` (
  `pat_id` bigint(20) DEFAULT NULL,
  `pat_name` varchar(100) DEFAULT NULL,
  `doc_name` varchar(100) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `his_xt_xray` */

DROP TABLE IF EXISTS `his_xt_xray`;

CREATE TABLE `his_xt_xray` (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(30) DEFAULT NULL COMMENT 'X光名称',
  `exefile` varchar(200) DEFAULT NULL COMMENT '可执行文件路径',
  `inifile` varchar(200) DEFAULT NULL COMMENT '配置文件路径',
  `dbfile` varchar(200) DEFAULT NULL COMMENT '数据库路径',
  `isuse` int(11) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `report_daily_doc_income` */

DROP TABLE IF EXISTS `report_daily_doc_income`;

CREATE TABLE `report_daily_doc_income` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `xj_amount` decimal(10,2) DEFAULT '0.00' COMMENT '现金',
  `pos_amount` decimal(10,2) DEFAULT '0.00' COMMENT 'POS卡金额',
  `possx_amount` decimal(10,2) DEFAULT '0.00' COMMENT 'pos手续费',
  `ic_amount` decimal(10,2) DEFAULT '0.00' COMMENT 'IC卡金额',
  `tg_amount` decimal(10,2) DEFAULT '0.00' COMMENT '团购金额',
  `wx_amount` decimal(10,2) DEFAULT '0.00' COMMENT '微信支付',
  `yjk_amount` decimal(10,2) DEFAULT '0.00' COMMENT '预交款转入',
  `fxj_amount` decimal(10,2) DEFAULT '0.00' COMMENT '非现金',
  `mk_amount` decimal(10,2) DEFAULT '0.00' COMMENT '市场活动',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生收入分类日报表';

/*Table structure for table `report_daily_doc_item` */

DROP TABLE IF EXISTS `report_daily_doc_item`;

CREATE TABLE `report_daily_doc_item` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生流水日报表';

/*Table structure for table `report_daily_first_diagnosis` */

DROP TABLE IF EXISTS `report_daily_first_diagnosis`;

CREATE TABLE `report_daily_first_diagnosis` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目初诊人数日报表';

/*Table structure for table `report_daily_first_income` */

DROP TABLE IF EXISTS `report_daily_first_income`;

CREATE TABLE `report_daily_first_income` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目初诊收入日报表';

/*Table structure for table `report_daily_first_referral` */

DROP TABLE IF EXISTS `report_daily_first_referral`;

CREATE TABLE `report_daily_first_referral` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `first_num` int(11) DEFAULT NULL COMMENT '初诊数量',
  `referral_num` int(11) DEFAULT NULL COMMENT '复诊数量',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='初复诊人数统计日报表';

/*Table structure for table `report_daily_item_income` */

DROP TABLE IF EXISTS `report_daily_item_income`;

CREATE TABLE `report_daily_item_income` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目收入日报表';

/*Table structure for table `report_daily_item_member` */

DROP TABLE IF EXISTS `report_daily_item_member`;

CREATE TABLE `report_daily_item_member` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目就诊人数日报表';

/*Table structure for table `report_daily_prepay` */

DROP TABLE IF EXISTS `report_daily_prepay`;

CREATE TABLE `report_daily_prepay` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `handling_charge` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  `payment_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预付款收款方式日报表';

/*Table structure for table `report_daily_receipt` */

DROP TABLE IF EXISTS `report_daily_receipt`;

CREATE TABLE `report_daily_receipt` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `handling_charge` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  `payment_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收款方式日报表';

/*Table structure for table `report_daily_receipt_item` */

DROP TABLE IF EXISTS `report_daily_receipt_item`;

CREATE TABLE `report_daily_receipt_item` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  `payment_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收款方式日报表';

/*Table structure for table `report_daily_referral_diagnosis` */

DROP TABLE IF EXISTS `report_daily_referral_diagnosis`;

CREATE TABLE `report_daily_referral_diagnosis` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目复诊人数日报表';

/*Table structure for table `report_daily_referral_income` */

DROP TABLE IF EXISTS `report_daily_referral_income`;

CREATE TABLE `report_daily_referral_income` (
  `id` bigint(20) NOT NULL,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目复诊收入日报表';

/*Table structure for table `report_monthly_doc_income` */

DROP TABLE IF EXISTS `report_monthly_doc_income`;

CREATE TABLE `report_monthly_doc_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `xj_amount` decimal(10,2) DEFAULT NULL COMMENT '现金',
  `pos_amount` decimal(10,2) DEFAULT NULL COMMENT 'POS卡金额',
  `possx_amount` decimal(10,2) DEFAULT NULL COMMENT 'pos手续费',
  `ic_amount` decimal(10,2) DEFAULT NULL COMMENT 'IC卡金额',
  `tg_amount` decimal(10,2) DEFAULT NULL COMMENT '团购金额',
  `wx_amount` decimal(10,2) DEFAULT NULL COMMENT '微信支付',
  `yjk_amount` decimal(10,2) DEFAULT NULL COMMENT '预交款转入',
  `fxj_amount` decimal(10,2) DEFAULT NULL COMMENT '非现金',
  `mk_amount` decimal(10,2) DEFAULT NULL COMMENT '市场活动',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生收入分类月报表';

/*Table structure for table `report_monthly_doc_item` */

DROP TABLE IF EXISTS `report_monthly_doc_item`;

CREATE TABLE `report_monthly_doc_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生流水月报表';

/*Table structure for table `report_monthly_first_diagnosis` */

DROP TABLE IF EXISTS `report_monthly_first_diagnosis`;

CREATE TABLE `report_monthly_first_diagnosis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目初诊人数月报表';

/*Table structure for table `report_monthly_first_income` */

DROP TABLE IF EXISTS `report_monthly_first_income`;

CREATE TABLE `report_monthly_first_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目初诊收入月报表';

/*Table structure for table `report_monthly_first_referral` */

DROP TABLE IF EXISTS `report_monthly_first_referral`;

CREATE TABLE `report_monthly_first_referral` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `first_num` int(11) DEFAULT NULL COMMENT '初诊数量',
  `referral_num` int(11) DEFAULT NULL COMMENT '复诊数量',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='初复诊人数统计月报表';

/*Table structure for table `report_monthly_item_income` */

DROP TABLE IF EXISTS `report_monthly_item_income`;

CREATE TABLE `report_monthly_item_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目收入月报表';

/*Table structure for table `report_monthly_item_member` */

DROP TABLE IF EXISTS `report_monthly_item_member`;

CREATE TABLE `report_monthly_item_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目就诊人数月报表';

/*Table structure for table `report_monthly_prepay` */

DROP TABLE IF EXISTS `report_monthly_prepay`;

CREATE TABLE `report_monthly_prepay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `handling_charge` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  `payment_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预付款收款方式月报表';

/*Table structure for table `report_monthly_receipt` */

DROP TABLE IF EXISTS `report_monthly_receipt`;

CREATE TABLE `report_monthly_receipt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `handling_charge` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收款方式月报表';

/*Table structure for table `report_monthly_receipt_item` */

DROP TABLE IF EXISTS `report_monthly_receipt_item`;

CREATE TABLE `report_monthly_receipt_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收款方式月报表';

/*Table structure for table `report_monthly_referral_diagnosis` */

DROP TABLE IF EXISTS `report_monthly_referral_diagnosis`;

CREATE TABLE `report_monthly_referral_diagnosis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目复诊人数月报表';

/*Table structure for table `report_monthly_referral_income` */

DROP TABLE IF EXISTS `report_monthly_referral_income`;

CREATE TABLE `report_monthly_referral_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目复诊收入月报表';

/*Table structure for table `report_process` */

DROP TABLE IF EXISTS `report_process`;

CREATE TABLE `report_process` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL COMMENT '统计时间',
  `status` int(11) DEFAULT NULL COMMENT '状态，0未扎帐，1锁HIS，2预生成报表，3审计通过，4扎帐结束',
  `unit_code` varchar(50) DEFAULT NULL COMMENT '单位代码',
  `operator` varchar(50) DEFAULT NULL COMMENT '操作人员',
  `isrunning` int(11) DEFAULT NULL COMMENT '是否正在执行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表处理表';

/*Table structure for table `report_weekly_doc_income` */

DROP TABLE IF EXISTS `report_weekly_doc_income`;

CREATE TABLE `report_weekly_doc_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `xj_amount` decimal(10,2) DEFAULT NULL COMMENT '现金',
  `pos_amount` decimal(10,2) DEFAULT NULL COMMENT 'POS卡金额',
  `possx_amount` decimal(10,2) DEFAULT NULL COMMENT 'pos手续费',
  `ic_amount` decimal(10,2) DEFAULT NULL COMMENT 'IC卡金额',
  `tg_amount` decimal(10,2) DEFAULT NULL COMMENT '团购金额',
  `wx_amount` decimal(10,2) DEFAULT NULL COMMENT '微信支付',
  `yjk_amount` decimal(10,2) DEFAULT NULL COMMENT '预交款转入',
  `fxj_amount` decimal(10,2) DEFAULT NULL COMMENT '非现金',
  `mk_amount` decimal(10,2) DEFAULT NULL COMMENT '市场活动',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生收入分类周报表';

/*Table structure for table `report_weekly_doc_item` */

DROP TABLE IF EXISTS `report_weekly_doc_item`;

CREATE TABLE `report_weekly_doc_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生流水周报表';

/*Table structure for table `report_weekly_first_diagnosis` */

DROP TABLE IF EXISTS `report_weekly_first_diagnosis`;

CREATE TABLE `report_weekly_first_diagnosis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目初诊人数周报表';

/*Table structure for table `report_weekly_first_income` */

DROP TABLE IF EXISTS `report_weekly_first_income`;

CREATE TABLE `report_weekly_first_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目初诊收入周报表';

/*Table structure for table `report_weekly_first_referral` */

DROP TABLE IF EXISTS `report_weekly_first_referral`;

CREATE TABLE `report_weekly_first_referral` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `doc_id` bigint(20) DEFAULT NULL COMMENT '医生id',
  `first_num` int(11) DEFAULT NULL COMMENT '初诊数量',
  `referral_num` int(11) DEFAULT NULL COMMENT '复诊数量',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='初复诊人数统计周报表';

/*Table structure for table `report_weekly_item_income` */

DROP TABLE IF EXISTS `report_weekly_item_income`;

CREATE TABLE `report_weekly_item_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目收入周报表';

/*Table structure for table `report_weekly_item_member` */

DROP TABLE IF EXISTS `report_weekly_item_member`;

CREATE TABLE `report_weekly_item_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目就诊人数周报表';

/*Table structure for table `report_weekly_prepay` */

DROP TABLE IF EXISTS `report_weekly_prepay`;

CREATE TABLE `report_weekly_prepay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `handling_charge` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  `payment_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预付款收款方式周报表';

/*Table structure for table `report_weekly_receipt` */

DROP TABLE IF EXISTS `report_weekly_receipt`;

CREATE TABLE `report_weekly_receipt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `handling_charge` decimal(10,5) DEFAULT NULL COMMENT '手续费',
  `net_value` decimal(10,2) DEFAULT NULL COMMENT '净值',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收款方式周报表';

/*Table structure for table `report_weekly_receipt_item` */

DROP TABLE IF EXISTS `report_weekly_receipt_item`;

CREATE TABLE `report_weekly_receipt_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL COMMENT '统计日期',
  `unit_code` int(11) DEFAULT NULL COMMENT '机构代码',
  `payment_type` varchar(50) DEFAULT NULL COMMENT '收费科目',
  `payment_subtype` varchar(50) DEFAULT NULL COMMENT '收费子科目',
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收款方式周报表';

/*Table structure for table `report_weekly_referral_diagnosis` */

DROP TABLE IF EXISTS `report_weekly_referral_diagnosis`;

CREATE TABLE `report_weekly_referral_diagnosis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_member` int(11) DEFAULT NULL COMMENT '检查类',
  `item02_member` int(11) DEFAULT NULL COMMENT '美白类',
  `item03_member` int(11) DEFAULT NULL COMMENT '牙周类',
  `item04_member` int(11) DEFAULT NULL COMMENT '牙体类',
  `item05_member` int(11) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_member` int(11) DEFAULT NULL COMMENT '药品',
  `item07_member` int(11) DEFAULT NULL COMMENT '护理产品',
  `item08_member` int(11) DEFAULT NULL COMMENT '检验项目',
  `item09_member` int(11) DEFAULT NULL COMMENT '套餐类',
  `item10_member` int(11) DEFAULT NULL COMMENT '儿牙类',
  `item11_member` int(11) DEFAULT NULL COMMENT '修复类',
  `item12_member` int(11) DEFAULT NULL COMMENT '种植类',
  `item13_member` int(11) DEFAULT NULL COMMENT '正畸类',
  `item14_member` int(11) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目复诊人数周报表';

/*Table structure for table `report_weekly_referral_income` */

DROP TABLE IF EXISTS `report_weekly_referral_income`;

CREATE TABLE `report_weekly_referral_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stat_date` date DEFAULT NULL,
  `unit_code` int(11) DEFAULT NULL,
  `item01_amount` decimal(10,2) DEFAULT NULL COMMENT '检查类',
  `item02_amount` decimal(10,2) DEFAULT NULL COMMENT '美白类',
  `item03_amount` decimal(10,2) DEFAULT NULL COMMENT '牙周类',
  `item04_amount` decimal(10,2) DEFAULT NULL COMMENT '牙体类',
  `item05_amount` decimal(10,2) DEFAULT NULL COMMENT '牙槽颌面外科',
  `item06_amount` decimal(10,2) DEFAULT NULL COMMENT '药品',
  `item07_amount` decimal(10,2) DEFAULT NULL COMMENT '护理产品',
  `item08_amount` decimal(10,2) DEFAULT NULL COMMENT '检验项目',
  `item09_amount` decimal(10,2) DEFAULT NULL COMMENT '套餐类',
  `item10_amount` decimal(10,2) DEFAULT NULL COMMENT '儿牙类',
  `item11_amount` decimal(10,2) DEFAULT NULL COMMENT '修复类',
  `item12_amount` decimal(10,2) DEFAULT NULL COMMENT '种植类',
  `item13_amount` decimal(10,2) DEFAULT NULL COMMENT '正畸类',
  `item14_amount` decimal(10,2) DEFAULT NULL COMMENT '其他类',
  `stat_time_range` varchar(100) DEFAULT NULL COMMENT '统计周期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目复诊收入周报表';

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keystr` varchar(50) DEFAULT NULL,
  `valuestr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_sysconfig` (`keystr`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_seq` */

DROP TABLE IF EXISTS `sys_seq`;

CREATE TABLE `sys_seq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `currid` bigint(20) DEFAULT NULL,
  `increment` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
