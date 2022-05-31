create database  if not exists URCS_MAAPDB CHARACTER SET utf8 COLLATE utf8_general_ci;

/*
Navicat MySQL Data Transfer

Source Server         : 10.10.220.120
Source Server Version : 50640
Source Host           : 10.10.220.120:3306
Source Database       : URCS_MAAPDB

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2020-07-03 09:44:31
*/

use URCS_MAAPDB;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for maap_chatbot
-- ----------------------------
DROP TABLE IF EXISTS `maap_chatbot`;
CREATE TABLE `maap_chatbot` (
  `id` varchar(18) NOT NULL COMMENT 'Chatbot标识，同Chatbot码号',
  `service_id` varchar(20) DEFAULT NULL COMMENT '商户id',
  `service_number` varchar(50) DEFAULT NULL COMMENT '服务账号',
  `name` varchar(20) DEFAULT NULL COMMENT 'Chatbot 名称',
  `icon` varchar(50) DEFAULT NULL COMMENT '指向Chatbot图标的链接（可选）',
  `verified` smallint(1) DEFAULT '0' COMMENT 'Chatbot是否被认证 0未认证 1已认证',
  `description` varchar(20) DEFAULT NULL COMMENT '机器人描述信息',
  `date_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for maap_chatbot_user
-- ----------------------------
DROP TABLE IF EXISTS `maap_chatbot_user`;
CREATE TABLE `maap_chatbot_user` (
  `c_id` varchar(20) DEFAULT '',
  `u_id` varchar(20) DEFAULT NULL,
  `date_time` varchar(20) DEFAULT NULL COMMENT '插入时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for maap_customer
-- ----------------------------
DROP TABLE IF EXISTS `maap_customer`;
CREATE TABLE `maap_customer` (
  `id` varchar(20) DEFAULT NULL COMMENT 'id',
  `customer_number` varchar(20) DEFAULT NULL COMMENT '商户申请id',
  `customer_name` varchar(20) DEFAULT NULL COMMENT '商户名称',
  `customer_tax_code` varchar(50) DEFAULT NULL COMMENT '公司税号',
  `date_time` varchar(20) DEFAULT NULL COMMENT '插入时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for maap_user
-- ----------------------------
DROP TABLE IF EXISTS `maap_user`;
CREATE TABLE `maap_user` (
  `id` varchar(20) DEFAULT NULL COMMENT '用户id',
  `user_number` varchar(20) DEFAULT NULL COMMENT '用户号',
  `date_time` varchar(20) DEFAULT NULL COMMENT '插入时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
