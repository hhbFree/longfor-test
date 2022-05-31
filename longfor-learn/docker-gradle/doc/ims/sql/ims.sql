create database  if not exists URCS_BLDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create database  if not exists URCS_DCDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create database  if not exists URCS_SECDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create database  if not exists URCS_UPDB CHARACTER SET utf8 COLLATE utf8_general_ci;

create database  if not exists URCS_TASDB CHARACTER SET utf8 COLLATE utf8_general_ci;

/*
Navicat MySQL Data Transfer

Source Server         : 10.10.220.120
Source Server Version : 50640
Source Host           : 10.10.220.120:3306
Source Database       : URCS_SECDB

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2020-07-03 10:23:14
*/

use URCS_SECDB;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Auth_AppInfo
-- ----------------------------
DROP TABLE IF EXISTS `Auth_AppInfo`;
CREATE TABLE `Auth_AppInfo` (
  `Id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `appId` varchar(50) NOT NULL,
  `appKey` varchar(50) NOT NULL,
  `comment` varchar(255) DEFAULT NULL COMMENT 'appId分配描述',
  PRIMARY KEY (`appId`),
  KEY `index_name` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for emergency
-- ----------------------------
DROP TABLE IF EXISTS `emergency`;
CREATE TABLE `emergency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emg_id` varchar(36) NOT NULL COMMENT '管控指令ID',
  `emg_transaction_id` varchar(64) NOT NULL COMMENT '事务标识',
  `emg_cmd_id` varchar(64) NOT NULL COMMENT '指令的唯一标识号',
  `emg_msg_type` varchar(64) NOT NULL COMMENT '管控类型',
  `emg_msisdn_seg_file_url` varchar(256) NOT NULL COMMENT '应急号段文件URL',
  `emg_ip_seg_file_url` varchar(256) NOT NULL COMMENT '应急IP地址段文件URL',
  `emg_ftp_user` varchar(64) NOT NULL COMMENT 'ftp用户名',
  `emg_ftp_passwd` varchar(64) NOT NULL COMMENT 'ftp密码',
  `emg_ftp_port` varchar(64) NOT NULL COMMENT 'ftp端口号',
  `emg_datetime` datetime NOT NULL COMMENT '指令下发时间',
  `emg_reason` varchar(512) NOT NULL COMMENT '应急管控原因描述',
  `emg_cmd_type` int(11) NOT NULL COMMENT '1-新增 2-变更 3-解除',
  `result_code` int(11) DEFAULT NULL COMMENT '指令执行结果：200成功|400失败',
  `result_desc` varchar(512) DEFAULT NULL COMMENT '指令执行结果描述',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，方便后续清理数据',
  PRIMARY KEY (`id`),
  KEY `idx_emg_transaction_id` (`emg_transaction_id`),
  KEY `idx_emg_cmd_type` (`emg_cmd_type`),
  KEY `idx_result_code` (`result_code`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_emg_cmd_id` (`emg_cmd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emergency_ip
-- ----------------------------
DROP TABLE IF EXISTS `emergency_ip`;
CREATE TABLE `emergency_ip` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `emg_id` varchar(36) NOT NULL COMMENT '管控指令ID',
  `emg_transaction_id` varchar(64) NOT NULL COMMENT '事务标识',
  `emg_cmd_id` varchar(64) NOT NULL COMMENT '指令的唯一标识号',
  `msg_type` varchar(64) NOT NULL COMMENT '管控类型',
  `begin_ip` varchar(64) NOT NULL COMMENT '开始IP',
  `end_ip` varchar(64) NOT NULL COMMENT '结束IP',
  `status` int(11) NOT NULL COMMENT '状态：0-有效 1-无效',
  `status_change_emg_id` varchar(36) DEFAULT NULL COMMENT '导致状态变更的管控指令ID',
  `status_change_time` datetime DEFAULT NULL COMMENT '状态变更时间',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，方便后续清理数据',
  PRIMARY KEY (`id`),
  KEY `idx_emg_id` (`emg_id`),
  KEY `idx_status` (`status`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_emg_transaction_id` (`emg_transaction_id`),
  KEY `idx_emg_cmd_id` (`emg_cmd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for emergency_msisdn
-- ----------------------------
DROP TABLE IF EXISTS `emergency_msisdn`;
CREATE TABLE `emergency_msisdn` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `emg_id` varchar(36) NOT NULL COMMENT '管控指令ID',
  `emg_transaction_id` varchar(64) NOT NULL COMMENT '事务标识',
  `emg_cmd_id` varchar(64) NOT NULL COMMENT '指令的唯一标识号',
  `msg_type` varchar(64) NOT NULL COMMENT '管控类型',
  `begin_no` varchar(64) NOT NULL COMMENT '开始号码',
  `end_no` varchar(64) NOT NULL COMMENT '结束号码',
  `status` int(11) NOT NULL COMMENT '状态：0-有效 1-无效',
  `status_change_emg_id` varchar(36) DEFAULT NULL COMMENT '导致状态变更的管控指令ID',
  `status_change_time` datetime DEFAULT NULL COMMENT '状态变更时间',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，方便后续清理数据',
  PRIMARY KEY (`id`),
  KEY `idx_emg_id` (`emg_id`),
  KEY `idx_status` (`status`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_emg_transaction_id` (`emg_transaction_id`),
  KEY `idx_emg_cmd_id` (`emg_cmd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hi_emergency
-- ----------------------------
DROP TABLE IF EXISTS `hi_emergency`;
CREATE TABLE `hi_emergency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `emg_id` varchar(36) NOT NULL COMMENT '管控指令ID',
  `type` varchar(64) NOT NULL COMMENT '管控类型',
  `target` varchar(64) NOT NULL COMMENT '管控标识',
  `begin_time` datetime DEFAULT NULL COMMENT '管控开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '管控结束时间',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，方便后续清理数据',
  `report_related_id` varchar(64) DEFAULT NULL COMMENT '信息报告关联标识',
  `tri_begin_status` varchar(2) DEFAULT NULL COMMENT '管控开始发送TRI',
  `tri_end_status` varchar(2) DEFAULT NULL COMMENT '管控结束发送TRI',
  `service_type` varchar(6) DEFAULT NULL COMMENT '管控业务类型',
  `sub_service_type` varchar(6) DEFAULT NULL COMMENT '业务子类型',
  `t_location` datetime DEFAULT NULL COMMENT '定时器TLocation',
  `control_num` bigint(32) DEFAULT '0' COMMENT '管制用户数',
  `control_traffic` bigint(32) DEFAULT '0' COMMENT '管制业务量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for hy_auto_requeset_msg_record
-- ----------------------------
DROP TABLE IF EXISTS `hy_auto_requeset_msg_record`;
CREATE TABLE `hy_auto_requeset_msg_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `system_name` varchar(20) DEFAULT NULL,
  `request_body` text,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `response_body` text,
  `response_status` int(4) DEFAULT NULL,
  `remark` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for msg_auth_record
-- ----------------------------
DROP TABLE IF EXISTS `msg_auth_record`;
CREATE TABLE `msg_auth_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(900) DEFAULT NULL,
  `msg_content` text,
  `receiver_address` varchar(300) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '1:接收报文；2：杭研接收返回数据；3：数据已经返回调用客户端；4：杭研异步返回结果；5：将结果返回给接入系统',
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for security_log_command_detail
-- ----------------------------
DROP TABLE IF EXISTS `security_log_command_detail`;
CREATE TABLE `security_log_command_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `inteface_name` varchar(20) NOT NULL COMMENT '接口类型',
  `auth_ip` varchar(100) DEFAULT NULL COMMENT '上传的配置ip',
  `auth_username` varchar(20) DEFAULT NULL COMMENT '上传的配置用户名',
  `auth_password` varchar(100) DEFAULT NULL COMMENT '上传的配置密码',
  `transactionid` varchar(300) DEFAULT NULL COMMENT '事务标识',
  `cmdId` varchar(100) DEFAULT NULL COMMENT '代表指令的唯一标示HYSPAM+时间戳+操作员ID',
  `msisdn_segfile_url` varchar(500) DEFAULT NULL COMMENT '应急号段文件URL',
  `ip_segfile_url` varchar(500) DEFAULT NULL COMMENT '应急IP地址段文件URL',
  `ftpuser` varchar(20) DEFAULT NULL COMMENT 'ftp用户名',
  `ftppasswd` varchar(100) DEFAULT NULL COMMENT 'FTP密码',
  `ftpport` varchar(20) DEFAULT NULL COMMENT 'FTP端口号',
  `datetime` varchar(20) DEFAULT NULL COMMENT '指令下发时间(格式：YYYYMMDDHHMMSS)',
  `reason` varchar(500) DEFAULT NULL COMMENT '应急管控原因描述',
  `cmd_type` tinyint(4) DEFAULT NULL COMMENT '1-新增、2-变更 3-解除',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for security_log_securitysoap
-- ----------------------------
DROP TABLE IF EXISTS `security_log_securitysoap`;
CREATE TABLE `security_log_securitysoap` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `inteface_name` varchar(20) NOT NULL COMMENT '接口类型',
  `receive_xml_content` text NOT NULL COMMENT '接收到的xml报文',
  `return_xml_content` text COMMENT '返回的xml报文',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `system_name` varchar(50) DEFAULT NULL COMMENT '此条消息下发的系统',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for security_rich_message_record
-- ----------------------------
DROP TABLE IF EXISTS `security_rich_message_record`;
CREATE TABLE `security_rich_message_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `plat_from` varchar(100) DEFAULT NULL,
  `msg_id` varchar(100) DEFAULT NULL,
  `from` varchar(100) DEFAULT NULL,
  `send_type` varchar(100) DEFAULT NULL,
  `to_count` int(11) DEFAULT NULL,
  `to` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `chat_id` varchar(100) DEFAULT NULL,
  `md5` varchar(100) DEFAULT NULL,
  `content_type` varchar(100) DEFAULT NULL,
  `content_length` int(11) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `content` blob,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for security_sms_message_record
-- ----------------------------
DROP TABLE IF EXISTS `security_sms_message_record`;
CREATE TABLE `security_sms_message_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `plat_from` varchar(300) DEFAULT NULL,
  `msg_id` varchar(300) DEFAULT NULL,
  `from` varchar(300) DEFAULT NULL,
  `send_type` varchar(300) DEFAULT NULL,
  `to_count` int(11) DEFAULT NULL,
  `to` varchar(300) DEFAULT NULL,
  `date` varchar(300) DEFAULT NULL,
  `Shortmsg_Port` varchar(300) DEFAULT NULL,
  `Chat_id` varchar(300) DEFAULT NULL,
  `Hash` varchar(300) DEFAULT NULL,
  `Content_Type` varchar(300) DEFAULT NULL,
  `Content_length` int(11) DEFAULT NULL,
  `content` varchar(4000) DEFAULT NULL,
  `create_Date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for security_text_message_record
-- ----------------------------
DROP TABLE IF EXISTS `security_text_message_record`;
CREATE TABLE `security_text_message_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(100) DEFAULT NULL,
  `from` varchar(100) DEFAULT NULL,
  `send_type` varchar(100) DEFAULT NULL,
  `to_count` int(11) DEFAULT NULL,
  `to` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `chat_id` varchar(100) DEFAULT NULL,
  `md5` varchar(1000) DEFAULT NULL,
  `content_type` varchar(100) DEFAULT NULL,
  `content_length` int(11) DEFAULT NULL,
  `content` varchar(3000) DEFAULT NULL,
  `create_Date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tel_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `tel_blacklist`;
CREATE TABLE `tel_blacklist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `sync_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tel_blacklist_unique` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tel_emergency
-- ----------------------------
DROP TABLE IF EXISTS `tel_emergency`;
CREATE TABLE `tel_emergency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `emg_id` varchar(36) NOT NULL COMMENT '管控指令ID',
  `emg_transaction_id` varchar(64) NOT NULL COMMENT '事务标识',
  `emg_cmd_id` varchar(64) NOT NULL COMMENT '指令的唯一标识号',
  `emg_msg_type` varchar(64) NOT NULL COMMENT '管控类型',
  `emg_msisdn_seg_file_url` varchar(256) NOT NULL COMMENT '应急号段文件URL',
  `emg_ip_seg_file_url` varchar(256) NOT NULL COMMENT '应急IP地址段文件URL',
  `emg_ftp_user` varchar(64) NOT NULL COMMENT 'ftp用户名',
  `emg_ftp_passwd` varchar(64) NOT NULL COMMENT 'ftp密码',
  `emg_ftp_port` varchar(64) NOT NULL COMMENT 'ftp端口号',
  `emg_datetime` datetime NOT NULL COMMENT '指令下发时间',
  `emg_reason` varchar(512) NOT NULL COMMENT '应急管控原因描述',
  `emg_cmd_type` int(11) NOT NULL COMMENT '1-新增 2-变更 3-解除',
  `result_code` int(11) DEFAULT NULL COMMENT '指令执行结果：200成功|400失败',
  `result_desc` varchar(512) DEFAULT NULL COMMENT '指令执行结果描述',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，方便后续清理数据',
  PRIMARY KEY (`id`),
  KEY `idx_emg_transaction_id` (`emg_transaction_id`),
  KEY `idx_emg_cmd_type` (`emg_cmd_type`),
  KEY `idx_result_code` (`result_code`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_emg_cmd_id` (`emg_cmd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tel_emergency_ip
-- ----------------------------
DROP TABLE IF EXISTS `tel_emergency_ip`;
CREATE TABLE `tel_emergency_ip` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `emg_id` varchar(36) NOT NULL COMMENT '管控指令ID',
  `emg_transaction_id` varchar(64) NOT NULL COMMENT '事务标识',
  `emg_cmd_id` varchar(64) NOT NULL COMMENT '指令的唯一标识号',
  `msg_type` varchar(64) NOT NULL COMMENT '管控类型',
  `begin_ip` varchar(64) NOT NULL COMMENT '开始IP',
  `end_ip` varchar(64) NOT NULL COMMENT '结束IP',
  `status` int(11) NOT NULL COMMENT '状态：0-有效 1-无效',
  `status_change_emg_id` varchar(36) DEFAULT NULL COMMENT '导致状态变更的管控指令ID',
  `status_change_time` datetime DEFAULT NULL COMMENT '状态变更时间',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，方便后续清理数据',
  PRIMARY KEY (`id`),
  KEY `idx_emg_id` (`emg_id`),
  KEY `idx_status` (`status`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_emg_transaction_id` (`emg_transaction_id`),
  KEY `idx_emg_cmd_id` (`emg_cmd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tel_emergency_msisdn
-- ----------------------------
DROP TABLE IF EXISTS `tel_emergency_msisdn`;
CREATE TABLE `tel_emergency_msisdn` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `emg_id` varchar(36) NOT NULL COMMENT '管控指令ID',
  `emg_transaction_id` varchar(64) NOT NULL COMMENT '事务标识',
  `emg_cmd_id` varchar(64) NOT NULL COMMENT '指令的唯一标识号',
  `msg_type` varchar(64) NOT NULL COMMENT '管控类型',
  `begin_no` varchar(64) NOT NULL COMMENT '开始号码',
  `end_no` varchar(64) NOT NULL COMMENT '结束号码',
  `status` int(11) NOT NULL COMMENT '状态：0-有效 1-无效',
  `status_change_emg_id` varchar(36) DEFAULT NULL COMMENT '导致状态变更的管控指令ID',
  `status_change_time` datetime DEFAULT NULL COMMENT '状态变更时间',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，方便后续清理数据',
  PRIMARY KEY (`id`),
  KEY `idx_emg_id` (`emg_id`),
  KEY `idx_status` (`status`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_emg_transaction_id` (`emg_transaction_id`),
  KEY `idx_emg_cmd_id` (`emg_cmd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_credit
-- ----------------------------
DROP TABLE IF EXISTS `user_credit`;
CREATE TABLE `user_credit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` varchar(64) NOT NULL COMMENT '用户ID，手机号',
  `creditLevel` int(11) NOT NULL COMMENT '用户信用等级',
  `fromType` int(11) NOT NULL DEFAULT '0' COMMENT '记录来源 0-批量 1-增量',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_from_type` (`fromType`),
  KEY `idx_record_create_time` (`record_create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_report_info
-- ----------------------------
DROP TABLE IF EXISTS `user_report_info`;
CREATE TABLE `user_report_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_no` varchar(500) DEFAULT NULL,
  `spam_From_No` varchar(500) DEFAULT NULL,
  `content_Type` varchar(500) DEFAULT NULL,
  `date_Time` varchar(500) DEFAULT NULL,
  `txt_Content` varchar(4000) DEFAULT NULL,
  `file_Url` varchar(1000) DEFAULT NULL,
  `msg_Id` varchar(500) DEFAULT NULL,
  `svc_Type` varchar(500) DEFAULT NULL,
  `report_Type` varchar(500) DEFAULT NULL,
  `comment` varchar(4000) DEFAULT NULL,
  `Status` varchar(500) DEFAULT NULL,
  `err_msg` varchar(500) DEFAULT NULL,
  `result_code` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `receiver_address` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*
Navicat MySQL Data Transfer

Source Server         : 10.10.220.120
Source Server Version : 50640
Source Host           : 10.10.220.120:3306
Source Database       : URCS_TASDB

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2020-07-03 10:26:43
*/

use URCS_TASDB;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for TAS_ConferenceManage
-- ----------------------------
DROP TABLE IF EXISTS `TAS_ConferenceManage`;
CREATE TABLE `TAS_ConferenceManage` (
  `ConferenceId` varchar(64) NOT NULL,
  `MrfAddress` varchar(30) DEFAULT NULL,
  `Creater` varchar(30) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `ComputerName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ConferenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_ConferenceManageLog
-- ----------------------------
DROP TABLE IF EXISTS `TAS_ConferenceManageLog`;
CREATE TABLE `TAS_ConferenceManageLog` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ConferenceId` varchar(64) DEFAULT NULL,
  `MrfAddress` varchar(30) DEFAULT NULL,
  `Creater` varchar(30) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `DestoryTime` datetime DEFAULT NULL,
  `ResultCode` int(4) DEFAULT NULL,
  `IsActiveDestory` tinyint(1) DEFAULT NULL,
  `ComputerName` varchar(30) DEFAULT NULL,
  `Reason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_EcSubscription
-- ----------------------------
DROP TABLE IF EXISTS `TAS_EcSubscription`;
CREATE TABLE `TAS_EcSubscription` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `SubscriptionId` varchar(64) DEFAULT NULL COMMENT '订阅ID',
  `MobileNo` varchar(64) DEFAULT NULL COMMENT '手机号',
  `Direction` varchar(10) DEFAULT NULL COMMENT '主叫流程或者被叫流程, MO, MT, Both',
  `Events` varchar(500) DEFAULT NULL COMMENT '订阅的事件列表',
  `Notificationmode` varchar(10) DEFAULT NULL COMMENT '通知模式, Notify：通知方式, Block：控制方式',
  `CallbackURL` varchar(200) DEFAULT NULL COMMENT '呼叫状态通知地址',
  `Sepid` varchar(64) DEFAULT NULL COMMENT '第三方应用标识，由平台侧分配',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建或更新时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_EcSubscriptionConfig
-- ----------------------------
DROP TABLE IF EXISTS `TAS_EcSubscriptionConfig`;
CREATE TABLE `TAS_EcSubscriptionConfig` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Sepid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_MultiCallHangupSmsLog
-- ----------------------------
DROP TABLE IF EXISTS `TAS_MultiCallHangupSmsLog`;
CREATE TABLE `TAS_MultiCallHangupSmsLog` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `MobileNo` varchar(20) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_PcSubscribeLog
-- ----------------------------
DROP TABLE IF EXISTS `TAS_PcSubscribeLog`;
CREATE TABLE `TAS_PcSubscribeLog` (
  `LogId` bigint(20) NOT NULL AUTO_INCREMENT,
  `MobileNo` varchar(50) DEFAULT NULL,
  `OperateType` tinyint(4) DEFAULT NULL,
  `PcNotifyUrl` varchar(1000) DEFAULT NULL,
  `ResultCode` int(4) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`LogId`),
  KEY `idx_mobileno` (`MobileNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_SingleCallDuration
-- ----------------------------
DROP TABLE IF EXISTS `TAS_SingleCallDuration`;
CREATE TABLE `TAS_SingleCallDuration` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Call_No` varchar(20) DEFAULT NULL COMMENT '主叫号码',
  `Duration` bigint(20) DEFAULT NULL COMMENT '时长，单位：秒',
  `RemainDuration` bigint(20) DEFAULT NULL,
  `Package_Type` int(11) DEFAULT NULL COMMENT '套餐类型，1.免费时长，2.充值时长，3.赠送时长',
  `Remark` varchar(100) DEFAULT NULL COMMENT '备注说明',
  `Create_Time` datetime DEFAULT NULL COMMENT '创建时间',
  `Expire_Time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_SingleCallDurationLog
-- ----------------------------
DROP TABLE IF EXISTS `TAS_SingleCallDurationLog`;
CREATE TABLE `TAS_SingleCallDurationLog` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Caller_No` varchar(20) DEFAULT NULL COMMENT '主叫号码',
  `Callee_No` varchar(20) DEFAULT NULL COMMENT '被叫号码',
  `Duration` bigint(20) DEFAULT NULL COMMENT '通话时长，单位：秒',
  `Package_Type` int(11) DEFAULT NULL COMMENT '套餐类型，1.免费时长，2.充值时长，3.赠送时长',
  `Remark` varchar(100) DEFAULT NULL COMMENT '备注说明',
  `Begin_Time` datetime DEFAULT NULL COMMENT '套餐开始时间',
  `End_Time` datetime DEFAULT NULL COMMENT '套餐结束时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_Stat_Call_Log
-- ----------------------------
DROP TABLE IF EXISTS `TAS_Stat_Call_Log`;
CREATE TABLE `TAS_Stat_Call_Log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sessionId` varchar(100) DEFAULT NULL COMMENT '会话ID',
  `callerNo` varchar(30) DEFAULT NULL COMMENT '主叫号',
  `calleeNo` varchar(30) DEFAULT NULL COMMENT '被叫号',
  `callerSite` varchar(4) DEFAULT NULL COMMENT '主叫号地域(NF,BF)',
  `calleeSite` varchar(4) DEFAULT NULL COMMENT '被叫号地域(NF,BF)',
  `callerCallPoint` varchar(4) DEFAULT NULL COMMENT '主叫发起类型(UE或PC)',
  `calleeCallPoint` varchar(4) DEFAULT NULL COMMENT '被叫接听类型(PC/UE)',
  `chairMan` int(11) DEFAULT NULL COMMENT '主席1，成员0',
  `callType` int(11) DEFAULT NULL COMMENT '类型(0: 一对一音频,1: 一对一视频, 2: 多方通话)',
  `duration` bigint(20) DEFAULT NULL COMMENT '通话时长(秒)',
  `chargingIdentifier` varchar(100) DEFAULT NULL,
  `conferenceId` varchar(100) DEFAULT NULL COMMENT '会场id',
  `hangupType` int(11) DEFAULT NULL COMMENT '挂断方(0: 未知, 1: 主叫, 2: 被叫, 3: 平台挂断)',
  `responseCode` int(11) DEFAULT NULL COMMENT '最终应答回码(200, 408, 480, 500, 504, 603等)',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `inviteTime` datetime DEFAULT NULL COMMENT '发送invite时间',
  `ringTime` datetime DEFAULT NULL COMMENT '振铃时间',
  `responseTime` datetime DEFAULT NULL COMMENT '最终应答时间(接听时间或拒接时间)',
  `hangupTime` datetime DEFAULT NULL COMMENT '挂断时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_Stat_Disburbance_Log
-- ----------------------------
DROP TABLE IF EXISTS `TAS_Stat_Disburbance_Log`;
CREATE TABLE `TAS_Stat_Disburbance_Log` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Number` varchar(32) DEFAULT NULL,
  `DisturbanceType` tinyint(4) DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `ResultCode` int(11) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_Stat_Notify_Log
-- ----------------------------
DROP TABLE IF EXISTS `TAS_Stat_Notify_Log`;
CREATE TABLE `TAS_Stat_Notify_Log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `conferenceId` varchar(100) DEFAULT NULL COMMENT '会场id',
  `referBy` varchar(50) DEFAULT NULL COMMENT '发起通知方',
  `referTo` varchar(50) DEFAULT NULL COMMENT '接收通知方',
  `notifyState` int(11) DEFAULT NULL COMMENT '通知类型',
  `notifyContent` blob COMMENT '通知内容',
  `responseCode` int(11) DEFAULT NULL COMMENT '返回码',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `notifyTime` datetime DEFAULT NULL COMMENT '通知时间',
  `responseTime` datetime DEFAULT NULL COMMENT '应答时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for TAS_Stat_ThirdNE_Log
-- ----------------------------
DROP TABLE IF EXISTS `TAS_Stat_ThirdNE_Log`;
CREATE TABLE `TAS_Stat_ThirdNE_Log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sessionId` varchar(100) DEFAULT NULL,
  `callerNo` varchar(30) DEFAULT NULL COMMENT '主叫号',
  `calleeNo` varchar(30) DEFAULT NULL COMMENT '被叫号',
  `calleeSite` varchar(10) DEFAULT NULL COMMENT '被叫地域',
  `direction` int(11) DEFAULT NULL COMMENT '方向(上行或下行)',
  `sessionMode` varchar(10) DEFAULT NULL COMMENT '(MO/MT端)',
  `netEleType` varchar(10) DEFAULT NULL COMMENT '网元类型',
  `requestContent` blob COMMENT '请求内容',
  `conferenceId` varchar(100) DEFAULT NULL COMMENT '会场',
  `responseCode` int(11) DEFAULT NULL COMMENT '返回码',
  `reason` varchar(500) DEFAULT NULL COMMENT '原因',
  `inviteTime` datetime DEFAULT NULL COMMENT 'invite时间',
  `responseTime` datetime DEFAULT NULL COMMENT '应答时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for VowifiMultiRemainDuration
-- ----------------------------
DROP TABLE IF EXISTS `VowifiMultiRemainDuration`;
CREATE TABLE `VowifiMultiRemainDuration` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增列',
  `Number` varchar(21) DEFAULT NULL COMMENT '电话号码',
  `Type` int(3) DEFAULT NULL COMMENT '套餐类型（1免费时长，2现金充值时长，3系统赠送）',
  `Description` varchar(50) DEFAULT NULL COMMENT '套餐描述',
  `Duration` int(11) DEFAULT NULL COMMENT '套餐时长',
  `RemainDuration` int(11) DEFAULT NULL COMMENT '套餐剩余时长',
  `ExpiryDate` datetime DEFAULT NULL COMMENT '套餐过期时间',
  `CreateDate` datetime DEFAULT NULL COMMENT '套餐创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for VowifiMultiTotalDuration
-- ----------------------------
DROP TABLE IF EXISTS `VowifiMultiTotalDuration`;
CREATE TABLE `VowifiMultiTotalDuration` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `Number` varchar(21) DEFAULT NULL COMMENT '主叫号码',
  `Type` int(2) DEFAULT NULL COMMENT '套餐类型（1免费时长，2现金充值时长，3系统赠送）',
  `Description` varchar(50) DEFAULT NULL COMMENT '描述',
  `TotalDuration` int(11) DEFAULT NULL COMMENT '多方通话用时',
  `RemainTime` int(11) DEFAULT NULL COMMENT '剩余时间',
  `StartDate` datetime DEFAULT NULL COMMENT '开始时间',
  `EndDate` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*
Navicat MySQL Data Transfer

Source Server         : 10.10.220.120
Source Server Version : 50640
Source Host           : 10.10.220.120:3306
Source Database       : URCS_UPDB

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2020-07-03 10:20:57
*/

use URCS_UPDB;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `imsi_phone`;
CREATE TABLE `imsi_phone` (
  `imsi` varchar(50) DEFAULT NULL COMMENT 'imsi',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for ap_iplist_limit
-- ----------------------------
DROP TABLE IF EXISTS `ap_iplist_limit`;
CREATE TABLE `ap_iplist_limit` (
  `beginIp` varchar(50) DEFAULT NULL,
  `endIp` varchar(50) DEFAULT NULL,
  `ipDesc` varchar(200) DEFAULT NULL,
  `type` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for c3p0Test
-- ----------------------------
DROP TABLE IF EXISTS `c3p0Test`;
CREATE TABLE `c3p0Test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for dm_config
-- ----------------------------
DROP TABLE IF EXISTS `dm_config`;
CREATE TABLE `dm_config` (
  `config_name` varchar(64) NOT NULL,
  `config_value` varchar(512) NOT NULL,
  `type` varchar(64) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`config_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gray_mobile
-- ----------------------------
DROP TABLE IF EXISTS `gray_mobile`;
CREATE TABLE `gray_mobile` (
  `phone` bigint(64) DEFAULT NULL,
  `identity` varchar(384) DEFAULT NULL,
  `domain_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for iplist_black
-- ----------------------------
DROP TABLE IF EXISTS `iplist_black`;
CREATE TABLE `iplist_black` (
  `beginIp` varchar(50) DEFAULT NULL,
  `endIp` varchar(50) DEFAULT NULL,
  `ipDesc` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for stg_city
-- ----------------------------
DROP TABLE IF EXISTS `stg_city`;
CREATE TABLE `stg_city` (
  `line` int(11) DEFAULT NULL,
  `city_id` varchar(15) DEFAULT NULL,
  `p_id` varchar(9) DEFAULT NULL,
  `city_name` varchar(60) DEFAULT NULL,
  `eff_time` char(42) DEFAULT NULL,
  `exp_time` char(42) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stg_domain
-- ----------------------------
DROP TABLE IF EXISTS `stg_domain`;
CREATE TABLE `stg_domain` (
  `domain_id` int(11) DEFAULT NULL,
  `visited_network` varchar(192) DEFAULT NULL,
  `local` tinyint(1) DEFAULT NULL,
  `visited_group` varchar(192) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stg_mobile_seg
-- ----------------------------
DROP TABLE IF EXISTS `stg_mobile_seg`;
CREATE TABLE `stg_mobile_seg` (
  `line` int(11) NOT NULL,
  `city_id` varchar(5) DEFAULT NULL,
  `city_name` varchar(20) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `user_type` char(1) DEFAULT NULL,
  `eff_time` char(14) DEFAULT NULL,
  `exp_time` char(14) DEFAULT NULL,
  PRIMARY KEY (`line`),
  KEY `PHONE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stg_province
-- ----------------------------
DROP TABLE IF EXISTS `stg_province`;
CREATE TABLE `stg_province` (
  `p_id` varchar(9) DEFAULT NULL,
  `p_ab` varchar(12) DEFAULT NULL,
  `p_name` varchar(60) DEFAULT NULL,
  `identity` varchar(192) DEFAULT NULL,
  `domain_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_id_val
-- ----------------------------
DROP TABLE IF EXISTS `user_id_val`;
CREATE TABLE `user_id_val` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flag` char(3) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_impi_00
-- ----------------------------
DROP TABLE IF EXISTS `user_impi_00`;
CREATE TABLE `user_impi_00` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `impi` varchar(64) NOT NULL DEFAULT '',
  `realm` varchar(64) NOT NULL DEFAULT 'test.ims.mnc000.mcc460.3gppnetwork.org',
  `password` varchar(32) NOT NULL DEFAULT '',
  `msisdn` varchar(16) DEFAULT NULL,
  `imsi` varchar(32) DEFAULT NULL,
  `open_rcs` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开通"两新"',
  `open_volte` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开通“VoLTE”',
  `active_close_rcs` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否主动关闭"VoLTE"',
  `active_close_volte` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否使用native客户端开通RCS服务',
  `channel` char(2) NOT NULL DEFAULT '05',
  `status` char(2) NOT NULL DEFAULT '01',
  `status_desc` varchar(128) NOT NULL DEFAULT '',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `4g` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `ix_impi` (`impi`),
  UNIQUE KEY `ix_msisdn` (`msisdn`)
) ENGINE=InnoDB AUTO_INCREMENT=1205150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_impi_01
-- ----------------------------
DROP TABLE IF EXISTS `user_impi_01`;
CREATE TABLE `user_impi_01` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `impi` varchar(64) NOT NULL DEFAULT '',
  `realm` varchar(64) NOT NULL DEFAULT 'test.ims.mnc000.mcc460.3gppnetwork.org',
  `password` varchar(32) NOT NULL DEFAULT '',
  `msisdn` varchar(16) DEFAULT NULL,
  `imsi` varchar(32) DEFAULT NULL,
  `open_rcs` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否开通"两新"',
  `open_volte` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开通“VoLTE”',
  `active_close_rcs` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否主动关闭"VoLTE"',
  `active_close_volte` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否使用native客户端开通RCS服务',
  `channel` char(2) NOT NULL DEFAULT '05',
  `status` char(2) NOT NULL DEFAULT '01',
  `status_desc` varchar(128) NOT NULL DEFAULT '',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `ix_impi` (`impi`),
  UNIQUE KEY `ix_msisdn` (`msisdn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_impu_00
-- ----------------------------
DROP TABLE IF EXISTS `user_impu_00`;
CREATE TABLE `user_impu_00` (
  `user_id` bigint(20) NOT NULL,
  `impu` varchar(64) NOT NULL DEFAULT '',
  `display_name` varchar(32) DEFAULT NULL,
  `barflag` tinyint(1) NOT NULL COMMENT '0：该PUI允许IMS通讯；1：该PUI限制IMS通讯',
  `regflag` tinyint(1) NOT NULL COMMENT '0：该PUI禁止IMS注册，1：该PUI允许IMS注册',
  `dftpui` tinyint(1) NOT NULL COMMENT '是否缺省隐式注册.带省份的sip impu为1',
  PRIMARY KEY (`impu`),
  KEY `ix_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_impu_01
-- ----------------------------
DROP TABLE IF EXISTS `user_impu_01`;
CREATE TABLE `user_impu_01` (
  `user_id` bigint(20) NOT NULL,
  `impu` varchar(64) NOT NULL DEFAULT '',
  `display_name` varchar(32) DEFAULT NULL,
  `barflag` tinyint(1) NOT NULL COMMENT '0：该PUI允许IMS通讯；1：该PUI限制IMS通讯',
  `regflag` tinyint(1) NOT NULL COMMENT '0：该PUI禁止IMS注册，1：该PUI允许IMS注册',
  `dftpui` tinyint(1) NOT NULL COMMENT '是否缺省隐式注册.带省份的sip impu为1',
  PRIMARY KEY (`impu`),
  KEY `ix_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_index_00
-- ----------------------------
DROP TABLE IF EXISTS `user_index_00`;
CREATE TABLE `user_index_00` (
  `identity` varchar(192) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `db_name` varchar(48) DEFAULT NULL,
  `table_id` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_index_01
-- ----------------------------
DROP TABLE IF EXISTS `user_index_01`;
CREATE TABLE `user_index_01` (
  `identity` varchar(192) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `db_name` varchar(48) DEFAULT NULL,
  `table_id` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 10.10.220.120
Source Server Version : 50640
Source Host           : 10.10.220.120:3306
Source Database       : URCS_BLDB

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2020-07-03 10:20:11
*/

use URCS_BLDB;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Ad_Config
-- ----------------------------
DROP TABLE IF EXISTS `Ad_Config`;
CREATE TABLE `Ad_Config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apiVersion` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `adConfigVersionNo` varchar(10) DEFAULT NULL,
  `adPictureUrl` varchar(200) DEFAULT NULL,
  `adShowUrl` varchar(200) DEFAULT NULL,
  `adEffectBeginTime` datetime DEFAULT NULL,
  `adEffectEndTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for gd_mobile
-- ----------------------------
DROP TABLE IF EXISTS `gd_mobile`;
CREATE TABLE `gd_mobile` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mobile_begin` varchar(11) DEFAULT NULL COMMENT '号码前缀',
  `region` varchar(20) DEFAULT NULL COMMENT '地市名称',
  PRIMARY KEY (`Id`),
  KEY `idx_mobile_begin` (`mobile_begin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for group_Log
-- ----------------------------
DROP TABLE IF EXISTS `group_Log`;
CREATE TABLE `group_Log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `groupId` int(11) DEFAULT NULL COMMENT '群ID',
  `operateMobile` varchar(20) DEFAULT '' COMMENT '操作者',
  `sendMobile` varchar(32) DEFAULT '' COMMENT '被操作者',
  `operationType` varchar(32) DEFAULT '' COMMENT '操作类型',
  `describing` varchar(255) DEFAULT '' COMMENT '描述',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `remark` varchar(32) DEFAULT '' COMMENT '备注',
  `statusing` tinyint(4) DEFAULT '1' COMMENT '状态 1:正常 2:已撤回 3:已过期,待删除',
  `groupName` varchar(32) DEFAULT '' COMMENT '群名称',
  PRIMARY KEY (`id`),
  KEY `idx_goc` (`groupId`,`operateMobile`,`createTime`),
  KEY `idx_phone` (`operateMobile`,`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for group_Log_bak0909
-- ----------------------------
DROP TABLE IF EXISTS `group_Log_bak0909`;
CREATE TABLE `group_Log_bak0909` (
  `int` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `groupId` int(11) DEFAULT NULL COMMENT '群ID',
  `operateMobile` varchar(20) DEFAULT '' COMMENT '操作者',
  `sendMobile` varchar(32) DEFAULT NULL COMMENT '被操作者',
  `operationType` varchar(32) DEFAULT '' COMMENT '操作类型',
  `describing` varchar(255) DEFAULT NULL COMMENT '描述',
  `createTime` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(32) DEFAULT '' COMMENT '备注',
  `statusing` tinyint(1) DEFAULT NULL COMMENT '状态 1:正常 2:已撤回 3:已过期,待删除',
  `groupName` varchar(32) DEFAULT '' COMMENT '群名称',
  PRIMARY KEY (`int`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for group_Logtable
-- ----------------------------
DROP TABLE IF EXISTS `group_Logtable`;
CREATE TABLE `group_Logtable` (
  `int` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `groupId` int(11) DEFAULT NULL COMMENT '群ID',
  `operateMobile` varchar(16) DEFAULT NULL COMMENT '操作者',
  `sendMobile` varchar(16) DEFAULT NULL COMMENT '被操作者',
  `operationType` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `describing` varchar(32) DEFAULT NULL COMMENT '描述',
  `createTime` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(32) DEFAULT NULL COMMENT '备注',
  `statusing` tinyint(1) DEFAULT NULL COMMENT '状态 1:正常 2:已撤回 3:已过期,待删除',
  PRIMARY KEY (`int`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for groupLogtable
-- ----------------------------
DROP TABLE IF EXISTS `groupLogtable`;
CREATE TABLE `groupLogtable` (
  `int` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `groupId` int(11) DEFAULT NULL COMMENT '群ID',
  `operateMobile` varchar(16) DEFAULT NULL COMMENT '操作者',
  `sendMobile` varchar(16) DEFAULT NULL COMMENT '被操作者',
  `operationType` varchar(32) DEFAULT NULL COMMENT '操作类型',
  `describe` varchar(32) DEFAULT NULL COMMENT '描述',
  `createTime` datetime DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(32) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1:正常 2:已撤回 3:已过期,待删除',
  PRIMARY KEY (`int`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_conference
-- ----------------------------
DROP TABLE IF EXISTS `log_conference`;
CREATE TABLE `log_conference` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(16) DEFAULT '' COMMENT '发起请求者手机号',
  `conferenceroom` varchar(50) DEFAULT '' COMMENT '会场id',
  `conferenceevent` varchar(30) DEFAULT '' COMMENT '会场事件',
  `requesturi` varchar(200) DEFAULT '' COMMENT '请求uri',
  `requestparams` blob COMMENT '请求参数',
  `response` blob COMMENT '响应结果',
  `time` varchar(20) DEFAULT '' COMMENT '请求时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_conferencenotify
-- ----------------------------
DROP TABLE IF EXISTS `log_conferencenotify`;
CREATE TABLE `log_conferencenotify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(16) DEFAULT '' COMMENT '发起请求者手机号',
  `conferenceroom` varchar(50) DEFAULT '' COMMENT '会场id',
  `conferenceevent` varchar(30) DEFAULT '' COMMENT '会场事件',
  `notifyreason` varchar(100) DEFAULT '' COMMENT '通知原因',
  `notifytype` varchar(30) DEFAULT '' COMMENT '通知类型，PC、APP、VOLTE',
  `notifyArgs` blob COMMENT '通知参数',
  `notifyresult` blob COMMENT '通知结果',
  `epid` varchar(300) DEFAULT '' COMMENT 'epid',
  `time` varchar(30) DEFAULT '' COMMENT '请求时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_conferneceoperate
-- ----------------------------
DROP TABLE IF EXISTS `log_conferneceoperate`;
CREATE TABLE `log_conferneceoperate` (
  `LogTime` varchar(25) DEFAULT NULL,
  `Mobile` varchar(16) DEFAULT NULL,
  `ConferenceRoom` varchar(50) DEFAULT NULL,
  `Event` varchar(30) DEFAULT NULL,
  `ResultCode` int(11) DEFAULT NULL,
  `RequestArgs` blob,
  `Result` blob,
  KEY `IX_LogTime` (`LogTime`),
  KEY `IX_Mobile` (`Mobile`),
  KEY `IX_ConferenceRoom` (`ConferenceRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_dmaccount
-- ----------------------------
DROP TABLE IF EXISTS `log_dmaccount`;
CREATE TABLE `log_dmaccount` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `msisdn` varchar(16) DEFAULT '' COMMENT '手机号',
  `imsi` varchar(32) DEFAULT '' COMMENT 'IMSI',
  `rangetype` char(2) DEFAULT '' COMMENT '开通范围',
  `channel` char(2) DEFAULT '' COMMENT '终端渠道标识',
  `optime` varchar(16) DEFAULT '',
  `statuscode` varchar(16) DEFAULT '' COMMENT '结果描述',
  `statusdesc` varchar(128) DEFAULT '' COMMENT '结果描述',
  `clientvendor` varchar(32) DEFAULT '' COMMENT 'RCS client 厂家',
  `clientversion` varchar(32) DEFAULT '' COMMENT 'RCS client 版本',
  `terminalvendor` varchar(32) DEFAULT '' COMMENT '终端厂家',
  `terminalmode` varchar(32) DEFAULT '' COMMENT '终端型号',
  `terminalswversion` varchar(32) DEFAULT '' COMMENT '终端软件版本',
  `terminaltype` varchar(32) DEFAULT '' COMMENT '终端形态标识',
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `province` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_dmboss
-- ----------------------------
DROP TABLE IF EXISTS `log_dmboss`;
CREATE TABLE `log_dmboss` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `useridentity` varchar(16) NOT NULL DEFAULT '',
  `origdomain` varchar(16) NOT NULL DEFAULT '',
  `homedomain` varchar(16) NOT NULL DEFAULT '',
  `sessionid` varchar(32) NOT NULL DEFAULT '',
  `transido` varchar(32) NOT NULL DEFAULT '',
  `transidotime` varchar(16) NOT NULL DEFAULT '',
  `transidh` varchar(32) NOT NULL DEFAULT '',
  `transidhtime` varchar(16) NOT NULL DEFAULT '',
  `servicename` varchar(16) NOT NULL DEFAULT '',
  `oprtype` varchar(16) NOT NULL DEFAULT '',
  `oprnumb` varchar(32) NOT NULL DEFAULT '',
  `statuscode` varchar(16) NOT NULL DEFAULT '',
  `statusdesc` varchar(128) NOT NULL DEFAULT '',
  `requesthead` longblob NOT NULL,
  `requestbody` longblob NOT NULL,
  `response` longblob NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_dmhss
-- ----------------------------
DROP TABLE IF EXISTS `log_dmhss`;
CREATE TABLE `log_dmhss` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `useridentity` varchar(64) NOT NULL DEFAULT '',
  `hssclass` varchar(32) NOT NULL DEFAULT '',
  `hssmethod` varchar(32) NOT NULL DEFAULT '',
  `statuscode` varchar(16) NOT NULL DEFAULT '',
  `statusdesc` varchar(128) NOT NULL DEFAULT '',
  `request` longblob NOT NULL,
  `response` longblob NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_dmsippass
-- ----------------------------
DROP TABLE IF EXISTS `log_dmsippass`;
CREATE TABLE `log_dmsippass` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `useridentity` varchar(16) NOT NULL DEFAULT '',
  `opertype` varchar(16) NOT NULL DEFAULT '',
  `statuscode` varchar(16) NOT NULL DEFAULT '',
  `statusdesc` varchar(128) NOT NULL DEFAULT '',
  `request` longblob NOT NULL,
  `response` longblob NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_groupmanager
-- ----------------------------
DROP TABLE IF EXISTS `log_groupmanager`;
CREATE TABLE `log_groupmanager` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `OwnerUri` varchar(128) DEFAULT NULL,
  `OwnerSite` varchar(30) DEFAULT NULL,
  `GroupUri` varchar(128) DEFAULT NULL,
  `GroupSite` varchar(16) DEFAULT NULL,
  `Content` blob,
  `ReferBy` varchar(128) DEFAULT NULL,
  `UserClientType` int(11) DEFAULT NULL,
  `OperateType` int(11) DEFAULT NULL,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `RecordTime` datetime DEFAULT NULL,
  `Ex1` blob,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `managerbefore` varchar(64) DEFAULT NULL COMMENT '操作前',
  `managerafter` varchar(64) DEFAULT NULL COMMENT '操作后',
  `SUBJECT` varchar(50) DEFAULT NULL COMMENT '群名称',
  `HOST` varchar(20) DEFAULT NULL COMMENT '机器ip',
  PRIMARY KEY (`Id`),
  KEY `IX_OWNERURI` (`OwnerUri`),
  KEY `IX_GROUPURI` (`OwnerSite`),
  KEY `IX_REFERBY` (`GroupUri`),
  KEY `IX_OPERATETYPE` (`GroupSite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupmessage
-- ----------------------------
DROP TABLE IF EXISTS `log_groupmessage`;
CREATE TABLE `log_groupmessage` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `messageid` varchar(384) DEFAULT NULL,
  `owneruri` varchar(384) DEFAULT NULL,
  `ownersite` varchar(30) DEFAULT NULL,
  `peeruri` varchar(100) DEFAULT NULL,
  `peersite` varchar(30) DEFAULT NULL,
  `peerurilist` blob,
  `peerurilistcount` int(11) DEFAULT NULL,
  `referby` varchar(384) DEFAULT NULL,
  `useragent` varchar(384) DEFAULT NULL,
  `clientip` varchar(384) DEFAULT NULL,
  `accessaddress` varchar(384) DEFAULT NULL,
  `messagedirection` int(11) DEFAULT NULL,
  `messagetype` int(11) DEFAULT NULL,
  `messageflags` int(11) DEFAULT NULL,
  `messagekindtype` int(11) DEFAULT NULL,
  `contenttype` int(11) DEFAULT NULL,
  `content` blob,
  `filesize` int(11) DEFAULT NULL,
  `filename` varchar(256) DEFAULT NULL,
  `statuscode` int(11) DEFAULT NULL,
  `reason` longblob,
  `messagetime` datetime DEFAULT NULL,
  `messageflux` int(11) DEFAULT NULL,
  `messageextend` text,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `senderdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `receiverdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `smscenteraddr` varchar(64) DEFAULT NULL COMMENT '??????',
  `terminalaccesstype` int(11) DEFAULT NULL COMMENT '??????',
  `conversationid` varchar(64) DEFAULT NULL COMMENT '????',
  PRIMARY KEY (`Id`),
  KEY `referby` (`referby`(191)),
  KEY `messageid` (`messageid`(191)),
  KEY `owneruri` (`owneruri`(191),`messagedirection`),
  KEY `idx_rc_time` (`record_create_time`),
  KEY `idx_ownersite` (`ownersite`),
  KEY `idx_statuscode` (`statuscode`),
  KEY `idx_contenttype` (`contenttype`),
  KEY `idx_messagekindtype` (`messagekindtype`),
  KEY `idx_peeruri` (`peeruri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupmessage_20191210
-- ----------------------------
DROP TABLE IF EXISTS `log_groupmessage_20191210`;
CREATE TABLE `log_groupmessage_20191210` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `messageid` varchar(384) DEFAULT NULL,
  `owneruri` varchar(384) DEFAULT NULL,
  `ownersite` varchar(30) DEFAULT NULL,
  `peeruri` varchar(100) DEFAULT NULL,
  `peersite` varchar(30) DEFAULT NULL,
  `peerurilist` blob,
  `peerurilistcount` int(11) DEFAULT NULL,
  `referby` varchar(384) DEFAULT NULL,
  `useragent` varchar(384) DEFAULT NULL,
  `clientip` varchar(384) DEFAULT NULL,
  `accessaddress` varchar(384) DEFAULT NULL,
  `messagedirection` int(11) DEFAULT NULL,
  `messagetype` int(11) DEFAULT NULL,
  `messageflags` int(11) DEFAULT NULL,
  `messagekindtype` int(11) DEFAULT NULL,
  `contenttype` int(11) DEFAULT NULL,
  `content` blob,
  `filesize` int(11) DEFAULT NULL,
  `filename` varchar(256) DEFAULT NULL,
  `statuscode` int(11) DEFAULT NULL,
  `reason` longblob,
  `messagetime` datetime DEFAULT NULL,
  `messageflux` int(11) DEFAULT NULL,
  `messageextend` text,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `senderdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `receiverdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `smscenteraddr` varchar(64) DEFAULT NULL COMMENT '??????',
  `terminalaccesstype` int(11) DEFAULT NULL COMMENT '??????',
  `conversationid` varchar(64) DEFAULT NULL COMMENT '????',
  PRIMARY KEY (`Id`),
  KEY `referby` (`referby`(191)),
  KEY `messageid` (`messageid`(191)),
  KEY `owneruri` (`owneruri`(191),`messagedirection`),
  KEY `idx_rc_time` (`record_create_time`),
  KEY `idx_ownersite` (`ownersite`),
  KEY `idx_statuscode` (`statuscode`),
  KEY `idx_contenttype` (`contenttype`),
  KEY `idx_messagekindtype` (`messagekindtype`),
  KEY `idx_peeruri` (`peeruri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupmessage_20191211
-- ----------------------------
DROP TABLE IF EXISTS `log_groupmessage_20191211`;
CREATE TABLE `log_groupmessage_20191211` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `messageid` varchar(384) DEFAULT NULL,
  `owneruri` varchar(384) DEFAULT NULL,
  `ownersite` varchar(30) DEFAULT NULL,
  `peeruri` varchar(100) DEFAULT NULL,
  `peersite` varchar(30) DEFAULT NULL,
  `peerurilist` blob,
  `peerurilistcount` int(11) DEFAULT NULL,
  `referby` varchar(384) DEFAULT NULL,
  `useragent` varchar(384) DEFAULT NULL,
  `clientip` varchar(384) DEFAULT NULL,
  `accessaddress` varchar(384) DEFAULT NULL,
  `messagedirection` int(11) DEFAULT NULL,
  `messagetype` int(11) DEFAULT NULL,
  `messageflags` int(11) DEFAULT NULL,
  `messagekindtype` int(11) DEFAULT NULL,
  `contenttype` int(11) DEFAULT NULL,
  `content` blob,
  `filesize` int(11) DEFAULT NULL,
  `filename` varchar(256) DEFAULT NULL,
  `statuscode` int(11) DEFAULT NULL,
  `reason` longblob,
  `messagetime` datetime DEFAULT NULL,
  `messageflux` int(11) DEFAULT NULL,
  `messageextend` text,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `senderdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `receiverdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `smscenteraddr` varchar(64) DEFAULT NULL COMMENT '??????',
  `terminalaccesstype` int(11) DEFAULT NULL COMMENT '??????',
  `conversationid` varchar(64) DEFAULT NULL COMMENT '????',
  PRIMARY KEY (`Id`),
  KEY `referby` (`referby`(191)),
  KEY `messageid` (`messageid`(191)),
  KEY `owneruri` (`owneruri`(191),`messagedirection`),
  KEY `idx_rc_time` (`record_create_time`),
  KEY `idx_ownersite` (`ownersite`),
  KEY `idx_statuscode` (`statuscode`),
  KEY `idx_contenttype` (`contenttype`),
  KEY `idx_messagekindtype` (`messagekindtype`),
  KEY `idx_peeruri` (`peeruri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupmessage_20191212
-- ----------------------------
DROP TABLE IF EXISTS `log_groupmessage_20191212`;
CREATE TABLE `log_groupmessage_20191212` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `messageid` varchar(384) DEFAULT NULL,
  `owneruri` varchar(384) DEFAULT NULL,
  `ownersite` varchar(30) DEFAULT NULL,
  `peeruri` varchar(100) DEFAULT NULL,
  `peersite` varchar(30) DEFAULT NULL,
  `peerurilist` blob,
  `peerurilistcount` int(11) DEFAULT NULL,
  `referby` varchar(384) DEFAULT NULL,
  `useragent` varchar(384) DEFAULT NULL,
  `clientip` varchar(384) DEFAULT NULL,
  `accessaddress` varchar(384) DEFAULT NULL,
  `messagedirection` int(11) DEFAULT NULL,
  `messagetype` int(11) DEFAULT NULL,
  `messageflags` int(11) DEFAULT NULL,
  `messagekindtype` int(11) DEFAULT NULL,
  `contenttype` int(11) DEFAULT NULL,
  `content` blob,
  `filesize` int(11) DEFAULT NULL,
  `filename` varchar(256) DEFAULT NULL,
  `statuscode` int(11) DEFAULT NULL,
  `reason` longblob,
  `messagetime` datetime DEFAULT NULL,
  `messageflux` int(11) DEFAULT NULL,
  `messageextend` text,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `senderdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `receiverdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `smscenteraddr` varchar(64) DEFAULT NULL COMMENT '??????',
  `terminalaccesstype` int(11) DEFAULT NULL COMMENT '??????',
  `conversationid` varchar(64) DEFAULT NULL COMMENT '????',
  PRIMARY KEY (`Id`),
  KEY `referby` (`referby`(191)),
  KEY `messageid` (`messageid`(191)),
  KEY `owneruri` (`owneruri`(191),`messagedirection`),
  KEY `idx_rc_time` (`record_create_time`),
  KEY `idx_ownersite` (`ownersite`),
  KEY `idx_statuscode` (`statuscode`),
  KEY `idx_contenttype` (`contenttype`),
  KEY `idx_messagekindtype` (`messagekindtype`),
  KEY `idx_peeruri` (`peeruri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupmessage_20191213
-- ----------------------------
DROP TABLE IF EXISTS `log_groupmessage_20191213`;
CREATE TABLE `log_groupmessage_20191213` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `messageid` varchar(384) DEFAULT NULL,
  `owneruri` varchar(384) DEFAULT NULL,
  `ownersite` varchar(30) DEFAULT NULL,
  `peeruri` varchar(100) DEFAULT NULL,
  `peersite` varchar(30) DEFAULT NULL,
  `peerurilist` blob,
  `peerurilistcount` int(11) DEFAULT NULL,
  `referby` varchar(384) DEFAULT NULL,
  `useragent` varchar(384) DEFAULT NULL,
  `clientip` varchar(384) DEFAULT NULL,
  `accessaddress` varchar(384) DEFAULT NULL,
  `messagedirection` int(11) DEFAULT NULL,
  `messagetype` int(11) DEFAULT NULL,
  `messageflags` int(11) DEFAULT NULL,
  `messagekindtype` int(11) DEFAULT NULL,
  `contenttype` int(11) DEFAULT NULL,
  `content` blob,
  `filesize` int(11) DEFAULT NULL,
  `filename` varchar(256) DEFAULT NULL,
  `statuscode` int(11) DEFAULT NULL,
  `reason` longblob,
  `messagetime` datetime DEFAULT NULL,
  `messageflux` int(11) DEFAULT NULL,
  `messageextend` text,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `senderdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `receiverdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `smscenteraddr` varchar(64) DEFAULT NULL COMMENT '??????',
  `terminalaccesstype` int(11) DEFAULT NULL COMMENT '??????',
  `conversationid` varchar(64) DEFAULT NULL COMMENT '????',
  PRIMARY KEY (`Id`),
  KEY `referby` (`referby`(191)),
  KEY `messageid` (`messageid`(191)),
  KEY `owneruri` (`owneruri`(191),`messagedirection`),
  KEY `idx_rc_time` (`record_create_time`),
  KEY `idx_ownersite` (`ownersite`),
  KEY `idx_statuscode` (`statuscode`),
  KEY `idx_contenttype` (`contenttype`),
  KEY `idx_messagekindtype` (`messagekindtype`),
  KEY `idx_peeruri` (`peeruri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupmessage_20191216
-- ----------------------------
DROP TABLE IF EXISTS `log_groupmessage_20191216`;
CREATE TABLE `log_groupmessage_20191216` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `messageid` varchar(384) DEFAULT NULL,
  `owneruri` varchar(384) DEFAULT NULL,
  `ownersite` varchar(30) DEFAULT NULL,
  `peeruri` varchar(100) DEFAULT NULL,
  `peersite` varchar(30) DEFAULT NULL,
  `peerurilist` blob,
  `peerurilistcount` int(11) DEFAULT NULL,
  `referby` varchar(384) DEFAULT NULL,
  `useragent` varchar(384) DEFAULT NULL,
  `clientip` varchar(384) DEFAULT NULL,
  `accessaddress` varchar(384) DEFAULT NULL,
  `messagedirection` int(11) DEFAULT NULL,
  `messagetype` int(11) DEFAULT NULL,
  `messageflags` int(11) DEFAULT NULL,
  `messagekindtype` int(11) DEFAULT NULL,
  `contenttype` int(11) DEFAULT NULL,
  `content` blob,
  `filesize` int(11) DEFAULT NULL,
  `filename` varchar(256) DEFAULT NULL,
  `statuscode` int(11) DEFAULT NULL,
  `reason` longblob,
  `messagetime` datetime DEFAULT NULL,
  `messageflux` int(11) DEFAULT NULL,
  `messageextend` text,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `senderdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `receiverdeviceid` varchar(64) DEFAULT NULL COMMENT '???????',
  `smscenteraddr` varchar(64) DEFAULT NULL COMMENT '??????',
  `terminalaccesstype` int(11) DEFAULT NULL COMMENT '??????',
  `conversationid` varchar(64) DEFAULT NULL COMMENT '????',
  PRIMARY KEY (`Id`),
  KEY `referby` (`referby`(191)),
  KEY `messageid` (`messageid`(191)),
  KEY `owneruri` (`owneruri`(191),`messagedirection`),
  KEY `idx_rc_time` (`record_create_time`),
  KEY `idx_ownersite` (`ownersite`),
  KEY `idx_statuscode` (`statuscode`),
  KEY `idx_contenttype` (`contenttype`),
  KEY `idx_messagekindtype` (`messagekindtype`),
  KEY `idx_peeruri` (`peeruri`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupnotify
-- ----------------------------
DROP TABLE IF EXISTS `log_groupnotify`;
CREATE TABLE `log_groupnotify` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ReferByUri` varchar(128) DEFAULT NULL,
  `GroupUri` varchar(128) DEFAULT NULL,
  `ReferToUris` blob,
  `UserClientType` int(11) DEFAULT NULL,
  `OperateType` int(11) DEFAULT NULL,
  `Direction` int(11) DEFAULT NULL,
  `event` varchar(128) DEFAULT NULL,
  `Content` blob,
  `Reason` blob,
  `RecordTime` datetime DEFAULT NULL,
  `Ex1` blob,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IX_REFERBYURI` (`ReferByUri`),
  KEY `IX_GROUPURI` (`GroupUri`),
  KEY `IX_OPERATETYPE` (`OperateType`),
  KEY `IX_DATETIME` (`RecordTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupprofile
-- ----------------------------
DROP TABLE IF EXISTS `log_groupprofile`;
CREATE TABLE `log_groupprofile` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userMobile` varchar(128) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `curProfileName` varchar(128) DEFAULT NULL,
  `oldProfileName` varchar(128) DEFAULT NULL,
  `host` varchar(128) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_groupsubandnotify
-- ----------------------------
DROP TABLE IF EXISTS `log_groupsubandnotify`;
CREATE TABLE `log_groupsubandnotify` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `GroupUri` varchar(128) DEFAULT NULL,
  `ReferBy` varchar(128) DEFAULT NULL,
  `ReferTo` varchar(128) DEFAULT NULL,
  `UserClientType` int(11) DEFAULT NULL,
  `Event` varchar(30) DEFAULT NULL,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `ExpiresTime` int(11) DEFAULT NULL COMMENT '订阅过期时间',
  `NotifyReason` varchar(128) DEFAULT NULL COMMENT '通知原因',
  `NotifyContent` blob COMMENT '通知内容',
  `CallId` varchar(128) DEFAULT NULL,
  `Host` varchar(20) DEFAULT NULL COMMENT '机器ip',
  `RecordTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_GROUPURI` (`GroupUri`),
  KEY `IX_REFERBY` (`ReferBy`),
  KEY `IX_REFERTO` (`ReferTo`),
  KEY `IX_CALLID` (`CallId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupsubnotify
-- ----------------------------
DROP TABLE IF EXISTS `log_groupsubnotify`;
CREATE TABLE `log_groupsubnotify` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `GroupUri` varchar(128) DEFAULT NULL,
  `ReferBy` varchar(128) DEFAULT NULL,
  `ReferTo` varchar(128) DEFAULT NULL,
  `UserClientType` int(11) DEFAULT NULL,
  `Event` varchar(30) DEFAULT NULL,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `ExpiresTime` int(11) DEFAULT NULL COMMENT '订阅过期时间',
  `NotifyReason` varchar(128) DEFAULT NULL COMMENT '通知原因',
  `NotifyContent` blob COMMENT '通知内容',
  `CallId` varchar(128) DEFAULT NULL,
  `Host` varchar(20) DEFAULT NULL COMMENT '机器ip',
  `RecordTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_GROUPURI` (`GroupUri`),
  KEY `IX_REFERBY` (`ReferBy`),
  KEY `IX_REFERTO` (`ReferTo`),
  KEY `IX_CALLID` (`CallId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupsubnotify_20191211
-- ----------------------------
DROP TABLE IF EXISTS `log_groupsubnotify_20191211`;
CREATE TABLE `log_groupsubnotify_20191211` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `GroupUri` varchar(128) DEFAULT NULL,
  `ReferBy` varchar(128) DEFAULT NULL,
  `ReferTo` varchar(128) DEFAULT NULL,
  `UserClientType` int(11) DEFAULT NULL,
  `Event` varchar(30) DEFAULT NULL,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `ExpiresTime` int(11) DEFAULT NULL COMMENT '订阅过期时间',
  `NotifyReason` varchar(128) DEFAULT NULL COMMENT '通知原因',
  `NotifyContent` blob COMMENT '通知内容',
  `CallId` varchar(128) DEFAULT NULL,
  `Host` varchar(20) DEFAULT NULL COMMENT '机器ip',
  `RecordTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_GROUPURI` (`GroupUri`),
  KEY `IX_REFERBY` (`ReferBy`),
  KEY `IX_REFERTO` (`ReferTo`),
  KEY `IX_CALLID` (`CallId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupsubnotify_20191212
-- ----------------------------
DROP TABLE IF EXISTS `log_groupsubnotify_20191212`;
CREATE TABLE `log_groupsubnotify_20191212` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `GroupUri` varchar(128) DEFAULT NULL,
  `ReferBy` varchar(128) DEFAULT NULL,
  `ReferTo` varchar(128) DEFAULT NULL,
  `UserClientType` int(11) DEFAULT NULL,
  `Event` varchar(30) DEFAULT NULL,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `ExpiresTime` int(11) DEFAULT NULL COMMENT '订阅过期时间',
  `NotifyReason` varchar(128) DEFAULT NULL COMMENT '通知原因',
  `NotifyContent` blob COMMENT '通知内容',
  `CallId` varchar(128) DEFAULT NULL,
  `Host` varchar(20) DEFAULT NULL COMMENT '机器ip',
  `RecordTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_GROUPURI` (`GroupUri`),
  KEY `IX_REFERBY` (`ReferBy`),
  KEY `IX_REFERTO` (`ReferTo`),
  KEY `IX_CALLID` (`CallId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupsubnotify_20191213
-- ----------------------------
DROP TABLE IF EXISTS `log_groupsubnotify_20191213`;
CREATE TABLE `log_groupsubnotify_20191213` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `GroupUri` varchar(128) DEFAULT NULL,
  `ReferBy` varchar(128) DEFAULT NULL,
  `ReferTo` varchar(128) DEFAULT NULL,
  `UserClientType` int(11) DEFAULT NULL,
  `Event` varchar(30) DEFAULT NULL,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `ExpiresTime` int(11) DEFAULT NULL COMMENT '订阅过期时间',
  `NotifyReason` varchar(128) DEFAULT NULL COMMENT '通知原因',
  `NotifyContent` blob COMMENT '通知内容',
  `CallId` varchar(128) DEFAULT NULL,
  `Host` varchar(20) DEFAULT NULL COMMENT '机器ip',
  `RecordTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_GROUPURI` (`GroupUri`),
  KEY `IX_REFERBY` (`ReferBy`),
  KEY `IX_REFERTO` (`ReferTo`),
  KEY `IX_CALLID` (`CallId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_groupsubnotify_20191216
-- ----------------------------
DROP TABLE IF EXISTS `log_groupsubnotify_20191216`;
CREATE TABLE `log_groupsubnotify_20191216` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `GroupUri` varchar(128) DEFAULT NULL,
  `ReferBy` varchar(128) DEFAULT NULL,
  `ReferTo` varchar(128) DEFAULT NULL,
  `UserClientType` int(11) DEFAULT NULL,
  `Event` varchar(30) DEFAULT NULL,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `ExpiresTime` int(11) DEFAULT NULL COMMENT '订阅过期时间',
  `NotifyReason` varchar(128) DEFAULT NULL COMMENT '通知原因',
  `NotifyContent` blob COMMENT '通知内容',
  `CallId` varchar(128) DEFAULT NULL,
  `Host` varchar(20) DEFAULT NULL COMMENT '机器ip',
  `RecordTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_GROUPURI` (`GroupUri`),
  KEY `IX_REFERBY` (`ReferBy`),
  KEY `IX_REFERTO` (`ReferTo`),
  KEY `IX_CALLID` (`CallId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_message
-- ----------------------------
DROP TABLE IF EXISTS `log_message`;
CREATE TABLE `log_message` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `messageid` varchar(128) DEFAULT NULL COMMENT '消息ID',
  `owneruri` varchar(128) DEFAULT NULL COMMENT '所有者Uri',
  `ownersite` varchar(10) DEFAULT NULL COMMENT 'BF-北方 NF-南方',
  `peeruri` varchar(128) DEFAULT NULL COMMENT '对端Url(群组/公众号/群发管理员等)',
  `peersite` varchar(10) DEFAULT NULL COMMENT 'BF-北方 NF-南方',
  `peerurilist` longblob COMMENT '接收方列表',
  `referby` varchar(128) DEFAULT NULL COMMENT '群组消息发送方uri',
  `useragent` varchar(128) DEFAULT NULL COMMENT '所有者的客户端的类型',
  `clientip` varchar(128) DEFAULT NULL COMMENT '终端IP地址 P-NetWork-Info',
  `accessaddress` varchar(128) DEFAULT NULL COMMENT '接入地址:SBC/P-CSCF',
  `messagedirection` int(11) DEFAULT NULL COMMENT '发送类型',
  `messagetype` int(11) DEFAULT NULL COMMENT '消息类型',
  `messageflags` int(11) DEFAULT NULL COMMENT '静默标记',
  `messagekindtype` int(11) DEFAULT NULL COMMENT 'SIP消息种类',
  `contenttype` int(11) DEFAULT NULL COMMENT '媒体类型',
  `content` longblob COMMENT '消息内容',
  `filesize` int(11) DEFAULT NULL COMMENT '文件大小',
  `filename` varchar(256) DEFAULT NULL,
  `statuscode` int(11) DEFAULT NULL COMMENT '结果',
  `reason` longblob COMMENT '原因',
  `messagetime` datetime DEFAULT NULL COMMENT '消息时间',
  `messageflux` int(11) DEFAULT NULL COMMENT '消息流量',
  `messageextend` longblob,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `senderdeviceid` varchar(64) DEFAULT NULL COMMENT '发送方设备标识',
  `receiverdeviceid` varchar(64) DEFAULT NULL COMMENT '接收方设备标识',
  `smscenteraddr` varchar(64) DEFAULT NULL COMMENT '短信中心地址',
  `terminalaccesstype` int(11) DEFAULT NULL COMMENT '终端接入类型',
  `conversationid` varchar(64) DEFAULT NULL COMMENT '会话标识',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息';

-- ----------------------------
-- Table structure for log_messagestore
-- ----------------------------
DROP TABLE IF EXISTS `log_messagestore`;
CREATE TABLE `log_messagestore` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `messageid` varchar(128) DEFAULT NULL COMMENT '消息ID',
  `user` varchar(128) DEFAULT NULL COMMENT '用户手机号',
  `useragent` varchar(128) DEFAULT NULL COMMENT '客户端类型',
  `clientip` varchar(128) DEFAULT NULL COMMENT '客户端ip',
  `optype` int(11) DEFAULT NULL COMMENT '操作类型',
  `optime` datetime DEFAULT NULL COMMENT '操作时间',
  `opcontent` longblob,
  `statuscode` int(11) DEFAULT NULL COMMENT '操作结果',
  `reason` longblob COMMENT '原因描述',
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网络消息存储';

-- ----------------------------
-- Table structure for log_messagetosms
-- ----------------------------
DROP TABLE IF EXISTS `log_messagetosms`;
CREATE TABLE `log_messagetosms` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `messageId` varchar(128) DEFAULT NULL COMMENT '消息ID',
  `smscenter` varchar(128) DEFAULT NULL COMMENT '短消息中心地址',
  `sender` varchar(128) DEFAULT NULL COMMENT '真实发送地址',
  `receiver` varchar(128) DEFAULT NULL COMMENT '真实接受地址',
  `submittime` datetime DEFAULT NULL COMMENT '提交时间',
  `completetime` datetime DEFAULT NULL COMMENT '完成时间',
  `messagesize` int(11) DEFAULT NULL COMMENT '短消息长度',
  `messageresult` int(11) DEFAULT NULL COMMENT '消息类型，如文本、图片、音频、其他文件等',
  `tosmscount` int(11) DEFAULT NULL COMMENT '短信分片总数',
  `tosmsindex` int(11) DEFAULT NULL COMMENT '当前分片索引',
  `deliverresult` int(11) DEFAULT NULL COMMENT '发送给SMSC的结果：0-失败；1-成功',
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息转短信';

-- ----------------------------
-- Table structure for log_ott_platformlog
-- ----------------------------
DROP TABLE IF EXISTS `log_ott_platformlog`;
CREATE TABLE `log_ott_platformlog` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `platform` varchar(10) DEFAULT NULL COMMENT 'ott平台',
  `bustype` varchar(4) DEFAULT NULL COMMENT '业务类型',
  `channel` varchar(4) DEFAULT NULL COMMENT '渠道',
  `oprtype` varchar(4) DEFAULT NULL COMMENT '操作类型',
  `msisdn` varchar(16) DEFAULT NULL,
  `imsi` varchar(32) DEFAULT NULL,
  `clientip` varchar(32) DEFAULT NULL COMMENT '客户端ip',
  `clientversion` varchar(16) DEFAULT NULL COMMENT '客户端版本',
  `request` longblob COMMENT '请求报文',
  `response` longblob COMMENT '返回报文',
  `statuscode` varchar(16) DEFAULT NULL COMMENT '返回状态码',
  `statusdesc` varchar(128) DEFAULT NULL COMMENT '返回说明',
  `memo` varchar(128) DEFAULT NULL COMMENT '备注',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `idx_time` (`createtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_ottplatformlog
-- ----------------------------
DROP TABLE IF EXISTS `log_ottplatformlog`;
CREATE TABLE `log_ottplatformlog` (
  `id` int(11) NOT NULL,
  `log_platform` varchar(10) DEFAULT NULL,
  `log_bustype` varchar(2) DEFAULT NULL,
  `log_channel` varchar(2) DEFAULT NULL,
  `log_oprtype` varchar(2) DEFAULT NULL,
  `msisdn` varchar(16) DEFAULT NULL,
  `imsi` varchar(32) DEFAULT NULL,
  `log_clientip` varchar(32) DEFAULT NULL,
  `log_clientversion` varchar(16) DEFAULT NULL,
  `log_request` varchar(200) DEFAULT NULL,
  `log_response` varchar(200) DEFAULT NULL,
  `log_statuscode` varchar(16) DEFAULT NULL,
  `log_statusdesc` varchar(128) DEFAULT NULL,
  `memo` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_platformlog
-- ----------------------------
DROP TABLE IF EXISTS `log_platformlog`;
CREATE TABLE `log_platformlog` (
  `id` int(11) NOT NULL,
  `platform` varchar(10) DEFAULT NULL COMMENT 'ott平台',
  `bustype` varchar(2) DEFAULT NULL COMMENT '业务类型',
  `channel` varchar(2) DEFAULT NULL COMMENT '渠道',
  `oprtype` varchar(2) DEFAULT NULL COMMENT '操作类型',
  `msisdn` varchar(16) DEFAULT NULL,
  `imsi` varchar(32) DEFAULT NULL,
  `clientip` varchar(32) DEFAULT NULL COMMENT '客户端ip',
  `clientversion` varchar(16) DEFAULT NULL COMMENT '客户端版本',
  `request` varchar(200) DEFAULT NULL COMMENT '请求报文',
  `response` varchar(200) DEFAULT NULL COMMENT '返回报文',
  `statuscode` varchar(16) DEFAULT NULL COMMENT '返回状态码',
  `statusdesc` varchar(128) DEFAULT NULL COMMENT '返回说明',
  `memo` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_platformlog_ott
-- ----------------------------
DROP TABLE IF EXISTS `log_platformlog_ott`;
CREATE TABLE `log_platformlog_ott` (
  `id` int(11) NOT NULL,
  `platform` varchar(10) DEFAULT NULL COMMENT 'ott平台',
  `bustype` varchar(4) DEFAULT NULL COMMENT '业务类型',
  `channel` varchar(4) DEFAULT NULL COMMENT '渠道',
  `oprtype` varchar(4) DEFAULT NULL COMMENT '操作类型',
  `msisdn` varchar(16) DEFAULT NULL,
  `imsi` varchar(32) DEFAULT NULL,
  `clientip` varchar(32) DEFAULT NULL COMMENT '客户端ip',
  `clientversion` varchar(16) DEFAULT NULL COMMENT '客户端版本',
  `request` longblob COMMENT '请求报文',
  `response` longblob COMMENT '返回报文',
  `statuscode` varchar(16) DEFAULT NULL COMMENT '返回状态码',
  `statusdesc` varchar(128) DEFAULT NULL COMMENT '返回说明',
  `memo` varchar(128) DEFAULT NULL COMMENT '备注',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_rcs2v6sync
-- ----------------------------
DROP TABLE IF EXISTS `log_rcs2v6sync`;
CREATE TABLE `log_rcs2v6sync` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Sender` varchar(128) DEFAULT NULL,
  `GroupId` varchar(128) DEFAULT NULL,
  `Tid` varchar(128) DEFAULT NULL,
  `Method` varchar(32) DEFAULT NULL,
  `Content` blob,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IX_FROM` (`Sender`),
  KEY `IX_GROUP` (`GroupId`),
  KEY `IX_TID` (`Tid`),
  KEY `IX_OPERATETYPE` (`Method`),
  KEY `IX_record_time` (`record_create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_rcs2v6syncMsg
-- ----------------------------
DROP TABLE IF EXISTS `log_rcs2v6syncMsg`;
CREATE TABLE `log_rcs2v6syncMsg` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Sender` varchar(128) DEFAULT NULL,
  `GroupId` varchar(128) DEFAULT NULL,
  `Tid` varchar(128) DEFAULT NULL,
  `MessageId` varchar(128) DEFAULT NULL,
  `Content` blob,
  `Direction` int(11) DEFAULT NULL,
  `StatusCode` int(11) DEFAULT NULL,
  `Reason` blob,
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  KEY `IX_FROM` (`Sender`),
  KEY `IX_GROUP` (`GroupId`),
  KEY `IX_TID` (`Tid`),
  KEY `IX_OPERATETYPE` (`MessageId`),
  KEY `IX_record_time` (`record_create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for log_sectcp
-- ----------------------------
DROP TABLE IF EXISTS `log_sectcp`;
CREATE TABLE `log_sectcp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'autoid',
  `msg_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'secid',
  `msg_bid` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'imdnid',
  `msg_bustype` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'business type',
  `msg_from` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'msg from',
  `msg_to` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'msg to',
  `msg_date` datetime DEFAULT NULL COMMENT 'msg date',
  `msg_uqid` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'msg id',
  `msg_content` longblob COMMENT 'msg content',
  `msg_content_type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'msg type',
  `msg_return_code` int(10) DEFAULT NULL COMMENT 'msg status',
  `msg_reason` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'msg_reason',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'record_create_time',
  `msg_orghash` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'orgfile hash',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for log_userlogin
-- ----------------------------
DROP TABLE IF EXISTS `log_userlogin`;
CREATE TABLE `log_userlogin` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `loginid` varchar(32) DEFAULT NULL COMMENT '记录每次登录登出',
  `impi` varchar(128) DEFAULT NULL COMMENT '唯一标识',
  `user` varchar(128) DEFAULT NULL COMMENT '用户手机号码',
  `time` datetime DEFAULT NULL COMMENT '操作时间',
  `type` int(11) DEFAULT NULL COMMENT '注册/注销',
  `unregistercause` int(11) DEFAULT NULL COMMENT '客户端注销原因',
  `clientip` varchar(128) DEFAULT NULL,
  `useragent` varchar(128) DEFAULT NULL COMMENT '客户端类型',
  `registeraddress` varchar(128) DEFAULT NULL COMMENT '注册所在的S-CSCF节点信息',
  `accessaddress` varchar(128) DEFAULT NULL COMMENT '接入地址',
  `clientcapability` longblob COMMENT '客户端能力',
  `statuscode` int(11) DEFAULT NULL COMMENT '注册状态码',
  `reason` longblob COMMENT '原因描述',
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `province` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户端登录';

-- ----------------------------
-- Table structure for log_vowificall
-- ----------------------------
DROP TABLE IF EXISTS `log_vowificall`;
CREATE TABLE `log_vowificall` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `sessionId` varchar(128) DEFAULT NULL COMMENT '会话标识',
  `owneruri` varchar(128) DEFAULT NULL COMMENT '所有者Uri',
  `ownersite` varchar(10) DEFAULT NULL COMMENT '南北方',
  `peeruri` varchar(128) DEFAULT NULL COMMENT '对端Uri',
  `peersite` varchar(10) DEFAULT NULL COMMENT '南北方',
  `chairmanuri` varchar(128) DEFAULT NULL COMMENT '主席',
  `chairmansite` varchar(10) DEFAULT NULL COMMENT '主席归属地',
  `peerurilist` longblob COMMENT '接收方列表',
  `referby` varchar(128) DEFAULT NULL COMMENT '多方通话邀请方Uri',
  `useragent` varchar(128) DEFAULT NULL COMMENT '所有者的客户端类型',
  `clientip` varchar(128) DEFAULT NULL COMMENT '终端IP地址',
  `accessaddress` varchar(128) DEFAULT NULL COMMENT '接入地址:SBC/P-CSCF',
  `calldirection` int(11) DEFAULT NULL COMMENT '上下行',
  `calltype` int(11) DEFAULT NULL COMMENT '音视频业务',
  `callmethod` int(11) DEFAULT NULL COMMENT 'INVITE/REINVITE/....',
  `callflags` int(6) DEFAULT NULL COMMENT '扩展',
  `recordtype` int(11) DEFAULT NULL COMMENT 'SCSCF话单/AS话单',
  `chargingidentifier` varchar(64) DEFAULT NULL COMMENT '计费标识',
  `businessidentity` varchar(64) DEFAULT NULL COMMENT '补充业务标识',
  `conferenceid` varchar(128) DEFAULT NULL COMMENT '会议ID',
  `phoneflux` int(11) DEFAULT NULL COMMENT '话单流量',
  `statuscode` int(11) DEFAULT NULL COMMENT '状态码',
  `reason` longblob COMMENT '原因',
  `sequencenumber` int(11) DEFAULT NULL COMMENT '话单序列号',
  `requesttime` datetime DEFAULT NULL COMMENT '服务请求时间',
  `ringtime` datetime DEFAULT NULL COMMENT '振铃时间',
  `responsetime` datetime DEFAULT NULL COMMENT '终结应答时间',
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通话';

-- ----------------------------
-- Table structure for Open_Test
-- ----------------------------
DROP TABLE IF EXISTS `Open_Test`;
CREATE TABLE `Open_Test` (
  `vers` varchar(10) DEFAULT NULL,
  `msisdn` varchar(20) DEFAULT NULL,
  `token` varchar(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for oss_ad_config
-- ----------------------------
DROP TABLE IF EXISTS `oss_ad_config`;
CREATE TABLE `oss_ad_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apiVersion` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `adConfigVersionNo` int(11) DEFAULT NULL,
  `adPictureUrl` varchar(200) DEFAULT NULL,
  `adShowUrl` varchar(200) DEFAULT NULL,
  `clientType` varchar(10) DEFAULT NULL,
  `pxType` varchar(20) DEFAULT NULL,
  `webType` varchar(10) DEFAULT NULL,
  `wtType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for oss_ad_config_copy
-- ----------------------------
DROP TABLE IF EXISTS `oss_ad_config_copy`;
CREATE TABLE `oss_ad_config_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apiVersion` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `adConfigVersionNo` int(11) DEFAULT NULL,
  `adPictureUrl` varchar(200) DEFAULT NULL,
  `adShowUrl` varchar(200) DEFAULT NULL,
  `clientType` varchar(10) DEFAULT NULL,
  `pxType` varchar(20) DEFAULT NULL,
  `webType` varchar(10) DEFAULT NULL,
  `wtType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for oss_service_config
-- ----------------------------
DROP TABLE IF EXISTS `oss_service_config`;
CREATE TABLE `oss_service_config` (
  `ConfigId` int(11) NOT NULL AUTO_INCREMENT,
  `ConfigName` varchar(50) DEFAULT NULL,
  `ConfigValue` varchar(20) DEFAULT NULL,
  `ConfigType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ConfigId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sync_fail_party_groups
-- ----------------------------
DROP TABLE IF EXISTS `sync_fail_party_groups`;
CREATE TABLE `sync_fail_party_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) DEFAULT NULL COMMENT '群id',
  `int_time` datetime DEFAULT NULL COMMENT '失败日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tas_monitor
-- ----------------------------
DROP TABLE IF EXISTS `tas_monitor`;
CREATE TABLE `tas_monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sessionId` varchar(128) DEFAULT NULL COMMENT '会话标识',
  `count` int(11) DEFAULT NULL COMMENT '一分钟内的数量',
  `businesstype` int(6) DEFAULT NULL COMMENT '一对一通话/一对一落地/多方通话',
  `useragent` varchar(50) DEFAULT NULL COMMENT 'PC,Android,IOS',
  `callmethod` int(2) DEFAULT NULL COMMENT 'INVITE/REINVITE/....',
  `statuscode` int(11) DEFAULT NULL COMMENT '状态码',
  `calltype` int(2) DEFAULT NULL COMMENT '音视频业务',
  `calldirection` int(11) DEFAULT NULL COMMENT '上下行',
  `conferenceid` varchar(100) DEFAULT NULL COMMENT '会场Id',
  `callflags` int(6) DEFAULT NULL COMMENT '扩展',
  `OwnerSite` varchar(10) DEFAULT NULL COMMENT '发送方南北方',
  `peersite` varchar(10) DEFAULT NULL COMMENT '接受方南北方',
  `ringdelay` int(6) DEFAULT NULL COMMENT '一分钟之内总接通延时',
  `callduration` int(6) DEFAULT NULL COMMENT '一分钟之内总接通延时',
  `connecttime` datetime DEFAULT NULL COMMENT '接通时间',
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tas_monitor_erl
-- ----------------------------
DROP TABLE IF EXISTS `tas_monitor_erl`;
CREATE TABLE `tas_monitor_erl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `count` int(11) DEFAULT NULL COMMENT '此前一小时内的会话数',
  `useragent` varchar(50) DEFAULT NULL COMMENT 'PC,Android,IOS',
  `calltype` int(2) DEFAULT NULL COMMENT '音视频业务',
  `calltotalduration` int(6) DEFAULT NULL COMMENT '此前一小时之内的通话总时长',
  `record_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for Test
-- ----------------------------
DROP TABLE IF EXISTS `Test`;
CREATE TABLE `Test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vowifi_bmp_show
-- ----------------------------
DROP TABLE IF EXISTS `vowifi_bmp_show`;
CREATE TABLE `vowifi_bmp_show` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `count` int(11) DEFAULT NULL COMMENT '一分钟内的数量',
  `useragent` varchar(50) DEFAULT NULL COMMENT 'PC,Android,IOS',
  `callmethod` int(2) DEFAULT NULL COMMENT 'INVITE/REINVITE/....',
  `calltype` int(2) DEFAULT NULL COMMENT '音视频业务',
  `calldirection` int(11) DEFAULT NULL COMMENT '上下行',
  `conferenceid` varchar(100) DEFAULT NULL COMMENT '会场Id',
  `OwnerSite` varchar(10) DEFAULT NULL COMMENT '发送方南北方',
  `peersite` varchar(10) DEFAULT NULL COMMENT '接受方南北方',
  `time` datetime DEFAULT NULL COMMENT '信令时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for vowifi_upstream_1
-- ----------------------------
DROP TABLE IF EXISTS `vowifi_upstream_1`;
CREATE TABLE `vowifi_upstream_1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `useragent` varchar(50) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for vowifi_upstream_log
-- ----------------------------
DROP TABLE IF EXISTS `vowifi_upstream_log`;
CREATE TABLE `vowifi_upstream_log` (
  `id` int(11) DEFAULT NULL,
  `useragent` varchar(20) DEFAULT NULL COMMENT 'useragent',
  `count` int(11) DEFAULT NULL COMMENT '统计数',
  `datetime` datetime DEFAULT NULL COMMENT '日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*
Navicat MySQL Data Transfer

Source Server         : 10.10.220.120
Source Server Version : 50640
Source Host           : 10.10.220.120:3306
Source Database       : URCS_DCDB

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2020-07-03 10:25:55
*/

use URCS_DCDB;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for VowifiBlacklist
-- ----------------------------
DROP TABLE IF EXISTS `VowifiBlacklist`;
CREATE TABLE `VowifiBlacklist` (
  `BlkId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BlkNumber` varchar(21) CHARACTER SET utf8mb4 DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `CallInterceptOrigin` tinyint(4) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`BlkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for VowifiBlacklistLog
-- ----------------------------
DROP TABLE IF EXISTS `VowifiBlacklistLog`;
CREATE TABLE `VowifiBlacklistLog` (
  `BlkLogId` bigint(20) NOT NULL AUTO_INCREMENT,
  `BlkNumber` varchar(21) CHARACTER SET utf8mb4 DEFAULT NULL,
  `InstructionType` tinyint(4) DEFAULT NULL,
  `BlkType` tinyint(4) DEFAULT NULL,
  `ProvinceCode` varchar(4) CHARACTER SET utf8mb4 DEFAULT NULL,
  `CityCode` varchar(4) CHARACTER SET utf8mb4 DEFAULT NULL,
  `StartTime` datetime DEFAULT NULL,
  `EndTime` datetime DEFAULT NULL,
  `RetryTimes` tinyint(4) DEFAULT NULL,
  `CallInterceptOrigin` tinyint(4) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`BlkLogId`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;

