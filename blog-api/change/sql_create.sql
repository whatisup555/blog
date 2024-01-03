
/*
Navicat MySQL Data Transfer

Source Server : localhost
Source Server Version : 50721
Source Host : localhost
Source Database : blog

Target Server Version : 50721
File Encoding : utf-8

Date: 04/28/2018 09:14:32 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `user_type` enum('系统管理员','普通用户') NOT NULL,
  `real_name` varchar(50) NOT NULL,
  `id_type` varchar(50) NOT NULL,
  `id_number` varchar(50) NOT NULL,
  `phone_number` char(11) NOT NULL,
  `user_level` enum('一般','VIP') NOT NULL,
  `user_intro` text,
  `register_city` varchar(50) NOT NULL,
  `register_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `request_info`
-- ----------------------------
DROP TABLE IF EXISTS `request_info`;
CREATE TABLE `request_info` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `destination_type` varchar(50) NOT NULL,
  `request_title` varchar(50) NOT NULL,
  `request_description` text,
  `request_image` text,
  `max_price` decimal(10,2) NOT NULL,
  `request_end_date` date NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `status` enum('已完成','待响应','已取消','到期未达成') NOT NULL,
  PRIMARY KEY (`request_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `response_info`
-- ----------------------------
DROP TABLE IF EXISTS `response_info`;
CREATE TABLE `response_info` (
  `response_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `response_description` text,
  `response_image` text,
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  `status` enum('0','1','2','3') NOT NULL,
  PRIMARY KEY (`response_id`),
  FOREIGN KEY (`request_id`) REFERENCES `request_info` (`request_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `request_detail`
-- ----------------------------
DROP TABLE IF EXISTS `request_detail`;
CREATE TABLE `request_detail` (
  `request_id` int(11) NOT NULL,
  `publisher_id` int(11) NOT NULL,
  `responder_id` int(11) NOT NULL,
  `achieve_date` date NOT NULL,
  `publisher_fee` decimal(10,2) NOT NULL,
  `responder_fee` decimal(10,2) NOT NULL,
  PRIMARY KEY (`request_id`),
  FOREIGN KEY (`publisher_id`) REFERENCES `user` (`user_id`),
  FOREIGN KEY (`responder_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `income_summary`
-- ----------------------------
DROP TABLE IF EXISTS `income_summary`;
CREATE TABLE `income_summary` (
  `month` char(6) NOT NULL,
  `region` varchar(50) NOT NULL,
  `destination_type` varchar(50) NOT NULL,
  `achieve_count` int(11) NOT NULL,
  `income` decimal(10,2) NOT NULL,
  PRIMARY KEY (`month`, `region`, `destination_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Insert a admin user
-- ----------------------------
INSERT INTO `user` (`username`, `password`, `user_type`, `real_name`, `id_type`, `id_number`, `phone_number`, `user_level`, `register_city`, `register_time`, `modify_time`) 
VALUES ('admin', 'admin', '系统管理员', 'admin', '身份证', '000000000000000000', '00000000000', '一般', '北京', NOW(), NOW());