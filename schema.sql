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
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
    `count`       int(11) NOT NULL COMMENT '库存',
    `sale`        int(11) NOT NULL COMMENT '已售',
    `version`     int(11) COMMENT '乐观锁，版本号',
    `create_time` timestamp  NOT  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `begin_time`  timestamp  NOT  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    `end_time`    timestamp  NOT  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `stock`
VALUES (null, '3年每年5%存款利率', 50, 0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `stock`
VALUES (null, '2年每年4%存款利率', 100, 0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `stock`
VALUES (null, '1年每年2.5%存款利率', 200, 0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
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
