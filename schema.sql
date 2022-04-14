-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `name`       varchar(30)        NOT NULL COMMENT '用户姓名',
    `tel`        varchar(11) UNIQUE NOT NULL COMMENT '手机号',
    `age`        int(11) NOT NULL DEFAULT 20 COMMENT '年龄',
    `gender`     int(11) NOT NULL DEFAULT 0 COMMENT '性别',
    `IDCardNum`  varchar(18) UNIQUE NOT NULL COMMENT '身份证号',
    `profession` varchar(30)        NOT NULL DEFAULT '无业' COMMENT '职业s',
    `password`   varchar(30)        NOT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (null, '张三', '15971021573', 24, 1, "420621199910023333", "教师", "bnm");
INSERT INTO `user`
VALUES (null, '李四', '15971021574', 25, 0, "420621199910025555", "工程师", "bnm");
INSERT INTO `user`
VALUES (null, '李四', '15971021575', 26, 1, "420621199910026666", "工程师", "bnm");
INSERT INTO `user`
VALUES (null, '王五', '15971021576', 26, 0, "420621199910027777", "工程师", "bnm");

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name`       varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
    `count`      int(11) NOT NULL COMMENT '库存',
    `sale`       int(11) NOT NULL COMMENT '已售',
    `version`    int(11) COMMENT '乐观锁，版本号',
    `rate`       DOUBLE COMMENT '利率',
    `years`      int(11) COMMENT '年数',
    `amount`     int(11) COMMENT '金额',
    `detail`     varchar(200) COMMENT '详细',
    `createTime` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `beginTime`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    `endTime`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `stock`
VALUES (null, '存款活动1', 50, 0, 0, 0.05, 1, 10000, '每份限额1万元，年利率5.00%，定存1年，共计50份。\n每人限购一份，先购先得。', CURRENT_TIMESTAMP,
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 1 DAY),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 2 DAY));
INSERT INTO `stock`
VALUES (null, '存款活动2', 100, 0, 0, 0.04, 1, 10000, '每份限额1万元，年利率4.00%，定存1年，共计100份。\n每人限购一份，先购先得。', CURRENT_TIMESTAMP,
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 2 DAY),
        DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 3 DAY));
-- ----------------------------
-- Table structure for stock_order
-- ----------------------------
DROP TABLE IF EXISTS `stock_order`;
CREATE TABLE `stock_order`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT,
    `sid`         int(11) NOT NULL COMMENT '库存ID',
    `name`        varchar(30) NOT NULL DEFAULT '' COMMENT '商品名称',
    `user_id`     int(11) NOT NULL DEFAULT '0',
    `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for defaultRecord
-- ----------------------------
DROP TABLE IF EXISTS `defaultRecord`;
CREATE TABLE `defaultRecord`
(
    `IDCardNum`  varchar(18) UNIQUE NOT NULL COMMENT '身份证号',
    `updateTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`IDCardNum`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for loanAccessRule
-- 贷款准入规则
-- ----------------------------
DROP TABLE IF EXISTS `loanAccessRule`;
CREATE TABLE `loanAccessRule`
(
    `createTime`    timestamp NOT NULL default current_timestamp  COMMENT '提交时间',
    `yearLimit` int(11) unsigned NOT NULL  COMMENT '几年内',
    `times` int(11) unsigned NOT NULL  COMMENT '几次',
    `excludeAmount` int(11) unsigned NOT NULL  COMMENT '小于此金额',
    `excludeTimes` int(11) unsigned NOT NULL  COMMENT '小于此天数',
    PRIMARY KEY (`createTime`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for loan
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `IDCardNum`  varchar(18) NOT NULL COMMENT '身份证号',
    `amount`     int(11) COMMENT '金额',
    `returnTime` date COMMENT '归还时间',
    `beginTime`  date               NOT NULL  COMMENT '借款时间',
    `endTime`    date               NOT NULL COMMENT '到期时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert into loan values(null,'1',2000,null,'2022-04-01','2022-04-10');
insert into loan values(null,'1',2000,'2022-04-11','2022-04-01','2022-04-10');
insert into loan values(null,'1',500,'2022-04-11','2022-04-01','2022-04-10');
insert into loan values(null,'1',500,'2022-04-14','2022-04-01','2022-04-10');
insert into loan values(null,'2',2000,null,'2022-04-01','2022-04-10');
insert into loan values(null,'2',2000,'2022-04-11','2022-04-01','2022-04-10');
insert into loan values(null,'2',500,'2022-04-11','2022-04-01','2022-04-10');
insert into loan values(null,'2',500,'2022-04-14','2022-04-01','2022-04-10');
insert into loan values(null,'4',500,'2022-04-14','2022-04-01','2022-04-10');
insert into loan values(null,'4',500,'2022-04-11','2022-04-01','2022-04-10');
insert into loan values(null,'3',500,null,'2022-04-01','2022-04-10');
insert into loan values(null,'3',2000,'2022-04-11','2022-04-01','2022-04-10');
insert into loan values(null,'3',500,'2022-04-11','2022-04-01','2022-04-10');
insert into loan values(null,'3',500,'2022-04-14','2022-04-01','2022-04-10');
select datediff(now(), '2022-04-10');
select * from loan;
# 近 3 年逾期 2 次以上（金额小于 1000 元，3 天内还清的除外）
select count(*)>2
from loan
where IDCardNum = '3'
  and beginTime > date_sub(now(),interval 3 year)
  and ((returnTime is not null  and  returnTime > endTime ) or (returnTime is null and  now() > returnTime))
  and not (amount < 1000 and (
        (returnTime is not null and datediff(returnTime, endTime) <= 3)
        or
        (returnTime is null and datediff(now(), returnTime) <= 3)
    )
);

