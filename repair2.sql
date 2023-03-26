/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.28 : Database - repair2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`repair2` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `repair2`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`) values (1,'admin','admin');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uprepairid` bigint NOT NULL,
  `repairmanid` bigint NOT NULL,
  `starttime` datetime NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

/*Data for the table `order` */

insert  into `order`(`id`,`uprepairid`,`repairmanid`,`starttime`,`status`) values (1,1,1,'2022-04-12 16:12:28',1),(5,2,6,'2022-04-12 22:36:16',1),(6,3,4,'2022-04-12 22:36:25',1),(8,8,2,'2022-04-13 00:49:10',1);

/*Table structure for table `repairman` */

DROP TABLE IF EXISTS `repairman`;

CREATE TABLE `repairman` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) NOT NULL,
  `age` int NOT NULL,
  `sex` varchar(5) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `section` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

/*Data for the table `repairman` */

insert  into `repairman`(`id`,`name`,`password`,`age`,`sex`,`phone`,`section`) values (1,'张三','123456',32,'女','19825991035','电力中心'),(2,'李四','935893',22,'女','18925991037','后勤部门'),(4,'王飞','12345',23,'男','18851775981','后勤部门'),(6,'张钱','123456',30,'女','12345342994','电力部门');

/*Table structure for table `uprepair` */

DROP TABLE IF EXISTS `uprepair`;

CREATE TABLE `uprepair` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `subtime` datetime NOT NULL,
  `address` varchar(20) NOT NULL,
  `number` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `info` varchar(50) NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

/*Data for the table `uprepair` */

insert  into `uprepair`(`id`,`subtime`,`address`,`number`,`name`,`phone`,`info`,`status`) values (1,'2022-04-12 10:37:47','明德楼N201','201883290610','小明','19825991037','电脑坏了',1),(2,'2022-04-08 10:39:56','沁园40 203','201883290510','小王','19825991032','灯坏了',1),(3,'2022-04-14 10:40:51','尚贤楼','201883298721','小军','19825992397','水龙头坏了',1),(5,'2022-04-12 12:42:48','文德楼 S203','201883290620','李强','12345679945','水龙头坏了',0),(6,'2022-04-12 20:49:38','东苑食堂 ','201883290677','王明','19825991034','灯坏了',0),(8,'2022-04-13 00:27:07','文德楼 S203','201883293456','张三','12345679945','椅子坏了',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
