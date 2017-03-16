# cardcase
## 此程序是后台
```html
代码地址：
	https://github.com/viakiba/cardcase
```
#### 技术要点
```html
1.myeclipse/maven(开发工具)
2.git(项目版本控制)
3.springmvc/spring/mybatis(主要框架)
4.mysql(数据库)
5.ehcache(缓存支持)
```
#### 交互使用get/post请求，数据格式为json
```html
查看如下地址：
	https://github.com/viakiba/cardcase/wiki
```
#### 数据库相关
##### 初始用户（测试使用）
```sql
INSERT INTO `cardcase`.`userinfo` 
(`userid`, `wxid`, `accountname`, `gender`, `avatarurl`) 
VALUES ('1', '111', 'test', '1', 'http://dwz.cn/5xYP38');
```
##### 建表语句
```sql
/*
Navicat MySQL Data Transfer

Source Server         : 182
Source Server Version : 50717
Source Host           : 182.254.139.74:3306
Source Database       : cardcase

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-16 11:10:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cardclassify
-- ----------------------------
DROP TABLE IF EXISTS `cardclassify`;
CREATE TABLE `cardclassify` (
  `classifyid` varchar(100) NOT NULL,
  `userid` varchar(100) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classifyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cardinfo
-- ----------------------------
DROP TABLE IF EXISTS `cardinfo`;
CREATE TABLE `cardinfo` (
  `cardid` varchar(100) NOT NULL,
  `userid` varchar(100) DEFAULT NULL,
  `classifyid` varchar(100) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `isConcern` varchar(255) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `qqNumber` varchar(255) DEFAULT NULL,
  `wechatNumber` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `telephoneNumber` varchar(255) DEFAULT NULL,
  `faxNumber` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `companyLogo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cardid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userid` varchar(100) NOT NULL,
  `wxid` varchar(100) DEFAULT NULL,
  `accountname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `avatarUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userlogin
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin` (
  `userid` varchar(100) NOT NULL,
  `trdsession` varchar(255) DEFAULT NULL,
  `sessionkey` varchar(255) DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
##### 触发器
```sql
DROP TRIGGER IF EXISTS `createuser`;
DELIMITER ;;
CREATE TRIGGER `createuser` AFTER INSERT ON `userinfo` FOR EACH ROW begin 
			insert into cardclassify values(new.userid,new.userid,"默认分类");
END
;;
DELIMITER ;
```