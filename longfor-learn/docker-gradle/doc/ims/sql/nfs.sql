create database  if not exists URCS_FDFSDB CHARACTER SET utf8 COLLATE utf8_general_ci;

/*
Navicat MySQL Data Transfer

Source Server         : 10.10.220.120
Source Server Version : 50640
Source Host           : 10.10.220.120:3306
Source Database       : URCS_FDFSDB

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2020-07-03 09:59:32
*/

use URCS_FDFSDB;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fdfs_file_index_00
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_00`;
CREATE TABLE `fdfs_file_index_00` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_id` varchar(36) NOT NULL COMMENT '业务文件ID',
  `fdfs_file_id` varchar(256) NOT NULL COMMENT 'FastDFS文件ID',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_01
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_01`;
CREATE TABLE `fdfs_file_index_01` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_02
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_02`;
CREATE TABLE `fdfs_file_index_02` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_03
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_03`;
CREATE TABLE `fdfs_file_index_03` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_04
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_04`;
CREATE TABLE `fdfs_file_index_04` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_05
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_05`;
CREATE TABLE `fdfs_file_index_05` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_06
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_06`;
CREATE TABLE `fdfs_file_index_06` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_07
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_07`;
CREATE TABLE `fdfs_file_index_07` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_08
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_08`;
CREATE TABLE `fdfs_file_index_08` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_file_index_09
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_file_index_09`;
CREATE TABLE `fdfs_file_index_09` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_00
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_00`;
CREATE TABLE `fdfs_permanent_index_00` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_id` varchar(36) NOT NULL COMMENT '业务文件ID',
  `fdfs_file_id` varchar(256) NOT NULL COMMENT 'FastDFS文件ID',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_00_copy
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_00_copy`;
CREATE TABLE `fdfs_permanent_index_00_copy` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_id` varchar(36) NOT NULL COMMENT '业务文件ID',
  `fdfs_file_id` varchar(256) NOT NULL COMMENT 'FastDFS文件ID',
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_01
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_01`;
CREATE TABLE `fdfs_permanent_index_01` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_02
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_02`;
CREATE TABLE `fdfs_permanent_index_02` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_03
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_03`;
CREATE TABLE `fdfs_permanent_index_03` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_04
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_04`;
CREATE TABLE `fdfs_permanent_index_04` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_05
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_05`;
CREATE TABLE `fdfs_permanent_index_05` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_06
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_06`;
CREATE TABLE `fdfs_permanent_index_06` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_07
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_07`;
CREATE TABLE `fdfs_permanent_index_07` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_08
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_08`;
CREATE TABLE `fdfs_permanent_index_08` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_permanent_index_09
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_permanent_index_09`;
CREATE TABLE `fdfs_permanent_index_09` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `fdfs_file_id` varchar(256) NOT NULL,
  `record_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_unique_id` varchar(70) DEFAULT NULL,
  `thumb_file_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_record_create_time` (`record_create_time`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_temp
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_temp`;
CREATE TABLE `fdfs_temp` (
  `file_id` varchar(36) NOT NULL,
  `file_unique_id` varchar(256) NOT NULL,
  PRIMARY KEY (`file_id`),
  KEY `file_unique_id` (`file_unique_id`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fdfs_tmp
-- ----------------------------
DROP TABLE IF EXISTS `fdfs_tmp`;
CREATE TABLE `fdfs_tmp` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `file_id` varchar(36) NOT NULL,
  `file_unique_id` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_file_id` (`file_id`),
  KEY `idx_file_unique_id` (`file_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
