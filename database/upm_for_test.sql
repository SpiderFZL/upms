/*
Navicat MySQL Data Transfer

Source Server         : UPM_LOCAL
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : upm_for_test

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-06-05 17:21:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zhyu_chat
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_chat`;
CREATE TABLE `zhyu_chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认序列',
  `chatmsg` varchar(1000) DEFAULT NULL COMMENT 'chat内容',
  `createtime` datetime DEFAULT NULL COMMENT 'chat时间',
  `userid` int(11) DEFAULT NULL COMMENT '用户ID',
  `uppackageid` int(11) DEFAULT NULL COMMENT '升级包ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhyu_dictype
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_dictype`;
CREATE TABLE `zhyu_dictype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `typename` varchar(255) DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhyu_download
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_download`;
CREATE TABLE `zhyu_download` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列',
  `downtime` datetime DEFAULT NULL COMMENT '下载时间',
  `packageid` int(11) DEFAULT NULL COMMENT '升级包',
  `username` varchar(50) DEFAULT NULL COMMENT '用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=440 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhyu_log
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_log`;
CREATE TABLE `zhyu_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `username` varchar(50) DEFAULT NULL COMMENT '用户',
  `content` varchar(1000) DEFAULT NULL COMMENT '操作日志',
  `createtime` datetime DEFAULT NULL COMMENT '日志时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1976 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhyu_package
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_package`;
CREATE TABLE `zhyu_package` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列编号',
  `packagename` varchar(100) DEFAULT NULL COMMENT '升级包名称',
  `tag` varchar(15) DEFAULT NULL COMMENT '版本类型alpha,beta,rc,release',
  `version` varchar(100) DEFAULT NULL COMMENT '版本号',
  `packagesize` varchar(50) DEFAULT NULL COMMENT '升级包大小',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` longtext COMMENT '提交备注信息',
  `downloadlink` varchar(100) DEFAULT NULL COMMENT '下载链接',
  `visable` int(2) DEFAULT NULL COMMENT '逻辑删除标识0删除1存在',
  `downloadstatus` int(2) DEFAULT NULL COMMENT '下载状态',
  `status` int(2) DEFAULT NULL COMMENT '升级包状态0close 1open',
  `packagetype` int(2) DEFAULT NULL COMMENT '所属类型例如服务端,控件,安卓客户端,苹果客户端',
  `projectid` int(11) DEFAULT NULL COMMENT '升级包所属项目',
  `userid` int(11) DEFAULT NULL COMMENT '操作用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=465 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhyu_project
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_project`;
CREATE TABLE `zhyu_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认序列',
  `projectname` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `type` varchar(20) DEFAULT NULL COMMENT '升级包类型字典ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhyu_tarlog
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_tarlog`;
CREATE TABLE `zhyu_tarlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `packageid` int(11) NOT NULL COMMENT '升级包管理id',
  `tarlog` longtext COMMENT '升级包日志',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhyu_tartask
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_tartask`;
CREATE TABLE `zhyu_tartask` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `projectid` int(11) DEFAULT NULL COMMENT '项目ID',
  `packagetype` int(11) DEFAULT NULL COMMENT '类型',
  `status` int(2) DEFAULT NULL COMMENT '打包状态',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zhyu_user
-- ----------------------------
DROP TABLE IF EXISTS `zhyu_user`;
CREATE TABLE `zhyu_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认序列',
  `username` varchar(50) DEFAULT NULL COMMENT '登陆后端用用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '登陆后端用密码',
  `realname` varchar(30) DEFAULT NULL COMMENT '用户姓名',
  `mail` varchar(100) DEFAULT NULL COMMENT '用户邮件',
  `auth` varchar(50) DEFAULT NULL COMMENT '用户权限',
  `status` int(2) DEFAULT NULL COMMENT '账户状态0禁用1启用',
  `role` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
