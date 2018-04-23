/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.6.26 : Database - log_monitor
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`log_monitor` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `log_monitor`;

/*Table structure for table `log_monitor_app` */

DROP TABLE IF EXISTS `log_monitor_app`;

CREATE TABLE `log_monitor_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '应用编号',
  `name` varchar(100) DEFAULT NULL COMMENT '应用名称',
  `desc` varchar(250) DEFAULT NULL COMMENT '应用简要描述',
  `isOnline` int(1) DEFAULT NULL COMMENT '应用是否在线',
  `typeId` int(1) DEFAULT NULL COMMENT '应用类型对应的ID',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '应用录入时间',
  `updateaDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '应用信息修改时间',
  `createUser` varchar(100) DEFAULT NULL COMMENT '创建用户',
  `updateUser` varchar(100) DEFAULT NULL COMMENT '修改用户',
  `userId` varchar(100) DEFAULT NULL COMMENT '应用所属用户，多个用户用逗号分开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `log_monitor_app` */

insert  into `log_monitor_app`(`id`,`name`,`desc`,`isOnline`,`typeId`,`createDate`,`updateaDate`,`createUser`,`updateUser`,`userId`) values (1,'storm集群','storm集群',1,1,'2015-11-12 18:15:23','2015-11-11 16:58:21','maoxiangyi','maoxiangyi','1'),(2,'java应用','java引用',1,1,'2015-11-12 18:15:28','2015-11-12 09:55:45','maoxiangyi','maoxiangyi','1');

/*Table structure for table `log_monitor_app_type` */

DROP TABLE IF EXISTS `log_monitor_app_type`;

CREATE TABLE `log_monitor_app_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '应用类型编号',
  `name` varchar(100) DEFAULT NULL COMMENT '应用类型名称，如linux，web，java，storm，kafka等',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '应用类型录入时间',
  `updataDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '应用类型修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `log_monitor_app_type` */

insert  into `log_monitor_app_type`(`id`,`name`,`createDate`,`updataDate`) values (1,'storm','2015-11-11 16:58:40','2015-11-11 16:58:42');

/*Table structure for table `log_monitor_rule` */

DROP TABLE IF EXISTS `log_monitor_rule`;

CREATE TABLE `log_monitor_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规则编号，自增长',
  `name` varchar(100) DEFAULT NULL COMMENT '规则名称',
  `desc` varchar(250) DEFAULT NULL COMMENT '规则描述',
  `keyword` varchar(100) DEFAULT NULL COMMENT '规则关键词',
  `isValid` int(1) DEFAULT NULL COMMENT '规则是否可用',
  `appId` int(11) DEFAULT NULL COMMENT '规则所属应用',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '规则创建时间',
  `updateDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '规则修改时间',
  `createUser` varchar(100) DEFAULT NULL COMMENT '创建用户',
  `updateUser` varchar(100) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `log_monitor_rule` */

insert  into `log_monitor_rule`(`id`,`name`,`desc`,`keyword`,`isValid`,`appId`,`createDate`,`updateDate`,`createUser`,`updateUser`) values (1,'exe','Exception','Exception',1,1,'2015-11-11 17:02:05','2015-11-11 16:57:25','maoxiangyi','maoxiangyi'),(2,'sys','测试数据','sys',1,2,'2015-11-12 10:02:13','0000-00-00 00:00:00','maoxiangyi','maoxiangyi'),(3,'error','错误信息','error',1,3,'2015-11-12 16:00:56','2015-11-12 16:00:58','maoxiangyi','maoxiangyi');

/*Table structure for table `log_monitor_rule_record` */

DROP TABLE IF EXISTS `log_monitor_rule_record`;

CREATE TABLE `log_monitor_rule_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '告警信息编号',
  `appId` int(11) DEFAULT NULL COMMENT '告警信息所属应用编号',
  `ruleId` int(11) DEFAULT NULL COMMENT '告警信息所属规则编号',
  `isEmail` int(11) DEFAULT '0' COMMENT '是否邮件告知，0：未告知  1：告知',
  `isPhone` int(11) DEFAULT '0' COMMENT '是否短信告知，0：未告知  1：告知',
  `isColse` int(11) DEFAULT '0' COMMENT '是否处理完毕，0：未处理  1：已处理',
  `noticeInfo` varchar(500) DEFAULT NULL COMMENT '告警信息明细',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '告警信息入库时间',
  `updataDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '告警信息修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;

/*Data for the table `log_monitor_rule_record` */

insert  into `log_monitor_rule_record`(`id`,`appId`,`ruleId`,`isEmail`,`isPhone`,`isColse`,`noticeInfo`,`createDate`,`updataDate`) values (173,1,1,1,1,0,'error:Java.lang.IllegalStateException','2015-11-12 15:20:47','2015-11-12 15:20:47'),(174,1,1,1,1,0,'error java.lang.ClassNotFoundException','2015-11-12 15:20:57','2015-11-12 15:20:58'),(175,1,1,1,1,0,'error javax.servlet.ServletException: Cannot find bean org.apache.struts.taglib.html.BEAN in any scope','2015-11-12 15:20:58','2015-11-12 15:20:59'),(176,1,1,1,1,0,'error java.lang.ArithmeticException','2015-11-12 15:21:17','2015-11-12 15:21:17'),(177,1,1,1,1,0,'error java.lang.IllegalMonitorStateException ','2015-11-12 15:26:41','2015-11-12 15:26:42'),(178,1,1,1,1,0,'error java.lang.ArrayIndexOutOfBoundsException','2015-11-12 18:15:59','2015-11-12 18:15:59'),(179,1,1,1,1,0,'error java.lang.ArrayIndexOutOfBoundsException','2015-11-12 18:16:46','2015-11-12 18:16:47'),(180,1,1,1,1,0,'error org.apache.jasper.JasperException: Exception in JSP','2015-11-12 18:17:06','2015-11-12 18:17:07'),(181,1,1,1,1,0,'error Java.lang.IndexOutOfBoundsException','2015-11-12 18:17:16','2015-11-12 18:17:17'),(182,1,1,1,1,0,'error java.lang.IllegalMonitorStateException ','2015-11-12 18:17:26','2015-11-12 18:17:27'),(183,1,1,1,1,0,'error java.lang.RuntimeException ','2015-11-12 18:17:36','2015-11-12 18:17:37'),(184,1,1,1,1,0,'error:Java.lang.IllegalMonitorStateException','2015-11-12 18:18:06','2015-11-12 18:18:07'),(185,1,1,1,1,0,'error org.apache.jasper.JasperException: Exception in JSP','2015-11-12 18:18:39','2015-11-12 18:18:40'),(186,1,1,1,1,0,'error java.lang.ClassCastException','2015-11-12 18:18:46','2015-11-12 18:18:47'),(187,1,1,1,1,0,'error:Java.lang.NegativeArraySizeException','2015-11-12 18:19:26','2015-11-12 18:19:27'),(188,1,1,1,1,0,'error java.lang.ClassNotFoundException','2015-11-12 18:19:36','2015-11-12 18:19:37'),(189,1,1,1,1,0,'error java.lang.ClassNotFoundException','2015-11-12 18:19:56','2015-11-12 18:19:57'),(190,1,1,1,1,0,'error java.lang.ArithmeticException:  by zero','2015-11-12 18:20:06','2015-11-12 18:20:07'),(191,1,1,1,1,0,'error:Servlet.service() for servlet action threw exception java.lang.NullPointerException','2015-11-12 18:20:16','2015-11-12 18:20:17'),(192,1,1,1,1,0,'error:Java.lang.TypeNotPresentException ','2015-11-12 18:20:26','2015-11-12 18:20:27'),(193,1,1,1,1,0,'error:Java.lang.IllegalMonitorStateException','2015-11-12 18:20:26','2015-11-12 18:20:27'),(194,1,1,1,1,0,'error java.lang.ArrayIndexOutOfBoundsException','2015-11-12 18:20:36','2015-11-12 18:20:37'),(195,1,1,1,1,0,'error java.lang.IndexOutOfBoundsException ','2015-11-12 18:20:56','2015-11-12 18:20:57'),(196,1,1,1,1,0,'error java.lang.ClassCastException','2015-11-13 09:18:32','2015-11-13 09:18:32'),(197,1,1,1,1,0,'error java.lang.TypeNotPresentException ','2015-11-13 09:18:50','2015-11-13 09:18:50'),(198,1,1,1,1,0,'error Stacktrace:  org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467)','2015-11-13 09:19:31','2015-11-13 09:19:31'),(199,1,1,1,1,0,'error:Servlet.service() for servlet action threw exception java.lang.NullPointerException','2015-11-13 09:19:32','2015-11-13 09:19:32'),(200,1,1,1,1,0,'error:Java.lang.IllegalStateException','2015-11-13 09:20:10','2015-11-13 09:20:11'),(201,1,1,1,1,0,'error java.lang.ArrayIndexOutOfBoundsException','2015-11-13 09:20:40','2015-11-13 09:20:41'),(202,1,1,1,1,0,'error:Java.lang.TypeNotPresentException ','2015-11-13 09:21:00','2015-11-13 09:21:01'),(203,1,1,1,1,0,'error:Java.lang.TypeNotPresentException ','2015-11-13 09:21:10','2015-11-13 09:21:11'),(204,1,1,1,1,0,'error:Java.lang.IllegalStateException','2015-11-13 09:21:20','2015-11-13 09:21:21'),(205,1,1,1,1,0,'error java.lang.IllegalMonitorStateException ','2015-11-13 09:21:40','2015-11-13 09:21:40'),(206,1,1,1,1,0,'error:Servlet.service() for servlet action threw exception java.lang.NullPointerException','2015-11-13 09:21:50','2015-11-13 09:21:51'),(207,1,1,1,1,0,'error org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467','2015-11-13 09:22:00','2015-11-13 09:22:01'),(208,1,1,1,1,0,'error Stacktrace:  org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467)','2015-11-13 09:22:20','2015-11-13 09:22:21'),(209,1,1,1,1,0,'error Stacktrace:  org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467)','2015-11-13 09:22:20','2015-11-13 09:22:21'),(210,1,1,1,1,0,'error java.lang.IndexOutOfBoundsException ','2015-11-13 09:23:00','2015-11-13 09:23:01'),(211,1,1,1,1,0,'error java.lang.RuntimeException ','2015-11-13 09:23:10','2015-11-13 09:23:11'),(212,1,1,1,1,0,'error: java.lang.EnumConstantNotPresentException ','2015-11-13 09:23:30','2015-11-13 09:23:31'),(213,1,1,1,1,0,'error Java.lang.IndexOutOfBoundsException','2015-11-13 09:23:40','2015-11-13 09:23:41'),(214,1,1,1,1,0,'error java.lang.ArithmeticException:  by zero','2015-11-13 09:24:10','2015-11-13 09:24:11'),(215,1,1,1,1,0,'error:Exception in thread main java.lang.ArrayIndexOutOfBoundsException: 2','2015-11-13 09:24:14','2015-11-13 09:24:14'),(216,1,1,1,1,0,'error:Java.lang.NegativeArraySizeException','2015-11-13 09:24:51','2015-11-13 09:24:51'),(217,1,1,1,1,0,'error: java.lang.CloneNotSupportedException','2015-11-13 09:24:59','2015-11-13 09:25:00'),(218,1,1,1,1,0,'error Java.lang.IndexOutOfBoundsException','2015-11-13 09:25:09','2015-11-13 09:25:10'),(219,1,1,1,1,0,'error java.lang.ClassNotFoundException','2015-11-13 09:25:19','2015-11-13 09:25:20'),(220,1,1,1,1,0,'error:Java.lang.UnsupprotedOperationException ','2015-11-13 09:25:59','2015-11-13 09:26:00'),(221,1,1,0,1,0,'error Java.lang.ClassNotFoundException','2015-11-13 09:27:10','2015-11-13 09:27:11'),(222,1,1,1,1,0,'error java.lang.NumberFormatException ','2015-11-13 09:34:16','2015-11-13 09:34:17'),(223,1,1,1,1,0,'error java.lang.TypeNotPresentException ','2015-11-13 09:34:25','2015-11-13 09:34:25'),(224,1,1,1,1,0,'error org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467','2015-11-13 09:34:45','2015-11-13 09:34:45'),(225,1,1,1,1,0,'error java.lang.ClassNotFoundException','2015-11-13 09:35:45','2015-11-13 09:35:45'),(226,1,1,1,1,0,'error javax.servlet.jsp.JspException: Cannot find message resources under key org.apache.struts.action.MESSAGE','2015-11-13 09:35:56','2015-11-13 09:35:56'),(227,1,1,1,1,0,'error java.lang.NumberFormatException ','2015-11-13 09:36:15','2015-11-13 09:36:15'),(228,1,1,1,1,0,'error java.lang.IllegalMonitorStateException ','2015-11-13 09:37:26','2015-11-13 09:37:26'),(229,1,1,1,1,0,'error:Servlet.service() for servlet action threw exception java.lang.NullPointerException','2015-11-13 09:37:35','2015-11-13 09:37:35'),(230,1,1,1,1,0,'error java.lang.IndexOutOfBoundsException ','2015-11-13 09:37:55','2015-11-13 09:37:55'),(231,1,1,1,1,0,'error java.lang.RuntimeException ','2015-11-13 09:38:05','2015-11-13 09:38:05'),(232,1,1,1,1,0,'error:Java.lang.NegativeArraySizeException','2015-11-13 09:38:15','2015-11-13 09:38:15'),(233,1,1,1,1,0,'error:Java.lang.IllegalStateException','2015-11-13 09:38:35','2015-11-13 09:38:35'),(234,1,1,1,1,0,'error:Java.lang.NegativeArraySizeException','2015-11-13 09:39:25','2015-11-13 09:39:25'),(235,1,1,1,1,0,'error:Java.lang.NegativeArraySizeException','2015-11-13 09:39:55','2015-11-13 09:39:55'),(236,1,1,1,1,0,'error java.lang.ArrayIndexOutOfBoundsException','2015-11-13 09:40:15','2015-11-13 09:40:15'),(237,1,1,1,1,0,'error:Java.lang.TypeNotPresentException ','2015-11-13 09:40:25','2015-11-13 09:40:25'),(238,1,1,1,1,0,'error:Java.lang.NegativeArraySizeException','2015-11-13 09:40:35','2015-11-13 09:40:35'),(239,1,1,1,1,0,'error: java.lang.EnumConstantNotPresentException ','2015-11-13 09:40:55','2015-11-13 09:40:55'),(240,1,1,1,1,0,'error org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467','2015-11-13 09:41:05','2015-11-13 09:41:05'),(241,1,1,1,1,0,'error java.lang.ClassNotFoundException','2015-11-13 09:41:05','2015-11-13 09:41:05'),(242,1,1,1,1,0,'error: java.lang.CloneNotSupportedException','2015-11-13 09:41:15','2015-11-13 09:41:15'),(243,1,1,1,1,0,'error:Java.lang.UnsupprotedOperationException ','2015-11-13 09:42:35','2015-11-13 09:42:35'),(244,1,1,1,1,0,'error Java.lang.IndexOutOfBoundsException','2015-11-13 09:42:45','2015-11-13 09:42:45'),(245,1,1,1,1,0,'error org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467','2015-11-13 09:42:45','2015-11-13 09:42:46'),(246,1,1,1,1,0,'error:Java.lang.NegativeArraySizeException','2015-11-13 09:43:05','2015-11-13 09:43:05'),(247,1,1,1,1,0,'error org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467','2015-11-13 09:43:15','2015-11-13 09:43:15'),(248,1,1,1,1,0,'error Stacktrace:  org.apache.jasper.servlet.JspServletWrapper.handleJspException(JspServletWrapper.java:467)','2015-11-13 09:43:25','2015-11-13 09:43:25'),(249,1,1,1,1,0,'error java.lang.TypeNotPresentException ','2015-11-13 09:43:35','2015-11-13 09:43:35'),(250,1,1,1,1,0,'error java.lang.ClassNotFoundException','2015-11-13 09:43:45','2015-11-13 09:43:45'),(251,1,1,1,1,0,'error java.lang.IllegalMonitorStateException ','2015-11-13 09:43:45','2015-11-13 09:43:45'),(252,1,1,1,1,0,'error org.apache.jasper.JasperException: Exception in JSP','2015-11-13 09:44:46','2015-11-13 09:44:46'),(253,1,1,1,1,0,'error java.lang.NumberFormatException ','2015-11-13 09:45:05','2015-11-13 09:45:05'),(254,1,1,1,1,0,'error javax.servlet.ServletException: Cannot find bean org.apache.struts.taglib.html.BEAN in any scope','2015-11-13 09:45:05','2015-11-13 09:45:06'),(255,1,1,1,1,0,'error:Java.lang.IllegalStateException','2015-11-13 09:45:15','2015-11-13 09:45:15'),(256,1,1,1,1,0,'error java.lang.NumberFormatException ','2015-11-13 09:45:15','2015-11-13 09:45:16'),(257,1,1,1,1,0,'error: java.lang.EnumConstantNotPresentException ','2015-11-13 09:45:25','2015-11-13 09:45:25'),(258,1,1,1,1,0,'error java.lang.ArrayIndexOutOfBoundsException','2015-11-13 09:45:25','2015-11-13 09:45:26'),(259,1,1,1,1,0,'error:Exception in thread main java.lang.ArrayIndexOutOfBoundsException: 2','2015-11-13 09:45:35','2015-11-13 09:45:35'),(260,1,1,1,1,0,'error Java.lang.IndexOutOfBoundsException','2015-11-13 09:46:05','2015-11-13 09:46:05'),(261,1,1,1,1,0,'error javax.servlet.ServletException: Cannot find bean org.apache.struts.taglib.html.BEAN in any scope','2015-11-13 09:46:15','2015-11-13 09:46:15'),(262,1,1,1,1,0,'error:Exception in thread main java.lang.ArrayIndexOutOfBoundsException: 2','2015-11-13 09:46:35','2015-11-13 09:46:35'),(263,1,1,1,1,0,'error:Java.lang.TypeNotPresentException ','2015-11-13 09:46:35','2015-11-13 09:46:35'),(264,1,1,1,1,0,'error java.lang.TypeNotPresentException ','2015-11-13 09:46:45','2015-11-13 09:46:45'),(265,1,1,1,1,0,'error java.lang.ArithmeticException','2015-11-13 09:47:05','2015-11-13 09:47:05'),(266,1,1,1,1,0,'error:Exception in thread main java.lang.ArrayIndexOutOfBoundsException: 2','2015-11-13 09:47:35','2015-11-13 09:47:35');

/*Table structure for table `log_monitor_user` */

DROP TABLE IF EXISTS `log_monitor_user`;

CREATE TABLE `log_monitor_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号，自增长',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '用户手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '用户的邮箱地址，默认为公司邮箱',
  `isValid` int(1) DEFAULT NULL COMMENT '用户是否有效',
  `appId` int(11) NOT NULL COMMENT '管理的应用',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户录入时间',
  `updateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户信息修改时间',
  `createUser` varchar(100) DEFAULT NULL COMMENT '创建用户',
  `updateUser` varchar(100) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `log_monitor_user` */

insert  into `log_monitor_user`(`id`,`name`,`mobile`,`email`,`isValid`,`appId`,`createDate`,`updateDate`,`createUser`,`updateUser`) values (1,'maoxiangyi','15652306418','maoxiangyi@itcast.cn',1,1,'2015-11-12 14:59:37','2015-11-11 16:59:13','maoxiangyi','maoxiangyi'),(2,'于洋','18610905408','yuyang@itcast.cn',1,1,'2015-11-12 15:00:14','2015-11-12 15:00:16','maoxiangyi','maoxiangyi'),(3,'赵星','18611132889','zhaoxing@itcast.cn',1,1,'2015-11-12 15:01:11','2015-11-12 15:01:13','maoxiangyi','maoxiangyi');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
