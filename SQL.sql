DROP DATABASE HOME;

CREATE DATABASE HOME;

CREATE TABLE `admin` (
  `Name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `Id` char(11) NOT NULL COMMENT '账号',
  `Gender` char(1) DEFAULT NULL COMMENT '性别',
  `Pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `Tel` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `Email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员';

INSERT INTO admin values('admin', 'admin', '男', 'admin', '12345', '12345@xidian.edu.cn');

CREATE TABLE `reservation` (
  `resID` int NOT NULL AUTO_INCREMENT COMMENT '预约编号',
  `userID` int DEFAULT NULL COMMENT '预约人账号',
  `RNum` int DEFAULT NULL COMMENT '会议室编号',
  `subTime` datetime DEFAULT NULL COMMENT '提交申请时间',
  `checkTime` datetime DEFAULT NULL COMMENT '审核时间',
  `checkStatus` int DEFAULT NULL COMMENT '预约状态：0预约成功、1审核中、2预约驳回、3取消预约、4已签退',
  `note` varchar(50) DEFAULT NULL COMMENT '备注，预约用途或驳回理由',
  PRIMARY KEY (`resID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `room` (
  `RName` varchar(30) DEFAULT NULL COMMENT '名称',
  `RNum` int NOT NULL COMMENT '房号',
  `RMemberCount` int DEFAULT NULL COMMENT '人数',
  `RArea` int DEFAULT NULL COMMENT '面积',
  `RPicDir` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `RUsage` varchar(100) DEFAULT NULL COMMENT '用途',
  `RUserName` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `RUserId` varchar(11) DEFAULT NULL COMMENT '用户账号',
  `RUserTel` varchar(20) DEFAULT NULL COMMENT '用户联系方式',
  `RUserEmail` varchar(30) DEFAULT NULL COMMENT '用户邮箱',
  `UseDate` date DEFAULT NULL COMMENT '会议日期',
  `StartTime` time DEFAULT NULL COMMENT '会议开始时间',
  `EndTime` time DEFAULT NULL COMMENT '会议结束时间',
  `RUserUsage` varchar(200) DEFAULT NULL COMMENT '用户用途',
  `Status` char(1) DEFAULT NULL COMMENT '是否占用（占用为1）',
  PRIMARY KEY (`RNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会议厅信息';

CREATE TABLE `user` (
  `Name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `Id` varchar(11) NOT NULL COMMENT '账号',
  `Gender` char(1) DEFAULT NULL COMMENT '性别',
  `Pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `Tel` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `Email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='普通用户'