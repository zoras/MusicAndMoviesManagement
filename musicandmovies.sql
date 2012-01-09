/*
SQLyog Enterprise - MySQL GUI v5.15
Host - 5.1.30-community : Database - musicandmovies
*********************************************************************
Server version : 5.1.30-community
*/
SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `musicandmovies`;

USE `musicandmovies`;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `disc` */

DROP TABLE IF EXISTS `disc`;

CREATE TABLE `disc` (
  `DISC` varchar(255) NOT NULL,
  PRIMARY KEY (`DISC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `disc` */

insert into `disc` (`DISC`) values ('Blue'),('Gold'),('Green');

/*Table structure for table `language` */

DROP TABLE IF EXISTS `language`;

CREATE TABLE `language` (
  `LANGUAGE` varchar(255) NOT NULL,
  PRIMARY KEY (`LANGUAGE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `language` */

insert into `language` (`LANGUAGE`) values ('English'),('Hindi'),('Nepali'),('Urdu');

/*Table structure for table `members` */

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `members` */

insert into `members` (`ID`,`NAME`,`PHONE`,`ADDRESS`) values (1,'Saroj Maharjan','9841586376','Hyumat-12'),(4,'Asim Shakya','4536096','Hyumat'),(5,'Suren','5655023','Maitidevi');

/*Table structure for table `movies` */

DROP TABLE IF EXISTS `movies`;

CREATE TABLE `movies` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `ACTOR` varchar(255) DEFAULT NULL,
  `ACTRESS` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `DIRECTOR` varchar(255) DEFAULT NULL,
  `LANGUAGE` varchar(255) DEFAULT NULL,
  `YEAR` varchar(255) DEFAULT NULL,
  `DISC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MOVIES_DISCMOVIES` (`DISC`),
  KEY `MOVIES_TYPEMOVIES` (`TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `movies` */

insert into `movies` (`ID`,`TITLE`,`ACTOR`,`ACTRESS`,`TYPE`,`DIRECTOR`,`LANGUAGE`,`YEAR`,`DISC`) values (3,'Matrix','Kenue Reeves','Trinity','Action','Morpheus','English','2000','Gold');

/*Table structure for table `movies_issue` */

DROP TABLE IF EXISTS `movies_issue`;

CREATE TABLE `movies_issue` (
  `ISSUE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `MOVIES_ID` int(10) unsigned DEFAULT NULL,
  `MEMBER_ID` int(10) unsigned DEFAULT NULL,
  `ISSUE_DATE` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ISSUE_ID`),
  KEY `MEMBERSMOVIES ISSUE` (`MEMBER_ID`),
  KEY `MOVIES_ID` (`MOVIES_ID`),
  KEY `MOVIES_MEMBER_ID` (`MEMBER_ID`),
  KEY `MOVIESMOVIES ISSUE` (`MOVIES_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `movies_issue` */

insert into `movies_issue` (`ISSUE_ID`,`MOVIES_ID`,`MEMBER_ID`,`ISSUE_DATE`,`STATUS`) values (2,3,1,'November 27, 2011','OUT');

/*Table structure for table `movies_type` */

DROP TABLE IF EXISTS `movies_type`;

CREATE TABLE `movies_type` (
  `TYPE` varchar(255) NOT NULL,
  PRIMARY KEY (`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `movies_type` */

insert into `movies_type` (`TYPE`) values ('Action'),('Cartoon'),('Comedy'),('Documentry'),('Horror'),('Suspense'),('Thriller');

/*Table structure for table `music` */

DROP TABLE IF EXISTS `music`;

CREATE TABLE `music` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `ARTIST` varchar(255) DEFAULT NULL,
  `ALBUM` varchar(255) DEFAULT NULL,
  `YEAR` varchar(255) DEFAULT NULL,
  `GENRE` varchar(255) DEFAULT NULL,
  `LANGUAGE` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `DISC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MUSIC_DISCMUSIC` (`DISC`),
  KEY `MUSIC_GENEREMUSIC` (`GENRE`),
  KEY `MUSIC_TYPEMUSIC` (`TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `music` */

insert into `music` (`ID`,`TITLE`,`ARTIST`,`ALBUM`,`YEAR`,`GENRE`,`LANGUAGE`,`TYPE`,`DISC`) values (13,'Hello','saroj','sarojmaharjan','1978','Rock','ENGLISH','Audio Music','Gold'),(16,'Chalne lage hai','Shaan','Jab We Met','2008','Classical','HINDI','Audio Music','Gold'),(21,'Smoke on the water','Deep Purple','Deep','1969','Rock','ENGLISH','Audio Music','Gold');

/*Table structure for table `music_genre` */

DROP TABLE IF EXISTS `music_genre`;

CREATE TABLE `music_genre` (
  `GENRE` varchar(255) NOT NULL,
  PRIMARY KEY (`GENRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `music_genre` */

insert into `music_genre` (`GENRE`) values ('Alternative'),('Black Metal'),('Classical'),('Death Metal'),('Jazz'),('Metal'),('Rock'),('Romantic'),('Sad'),('Slow Rock');

/*Table structure for table `music_issue` */

DROP TABLE IF EXISTS `music_issue`;

CREATE TABLE `music_issue` (
  `ISSUE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `MUSIC_ID` int(10) DEFAULT NULL,
  `MEMBER_ID` int(10) DEFAULT NULL,
  `ISSUE_DATE` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ISSUE_ID`),
  KEY `MEMBERSMUSIC_ISSUE` (`MEMBER_ID`),
  KEY `MUSIC_ID` (`MUSIC_ID`),
  KEY `MUSIC_MEMBER_ID` (`MEMBER_ID`),
  KEY `MUSICMUSIC_ISSUE` (`MUSIC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `music_issue` */

insert into `music_issue` (`ISSUE_ID`,`MUSIC_ID`,`MEMBER_ID`,`ISSUE_DATE`,`STATUS`) values (1,13,1,'November 29, 2011','OUT'),(2,16,1,'November 25, 2011','OUT');

/*Table structure for table `music_type` */

DROP TABLE IF EXISTS `music_type`;

CREATE TABLE `music_type` (
  `TYPE` varchar(255) NOT NULL,
  PRIMARY KEY (`TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `music_type` */

insert into `music_type` (`TYPE`) values ('Audio Music'),('Music Videos');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `USER_PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert into `users` (`USER_ID`,`USER_NAME`,`USER_PASSWORD`) values (1,'saroj','saroj'),(2,'admin','admin');

SET SQL_MODE=@OLD_SQL_MODE;