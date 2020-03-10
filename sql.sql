#create database parameters;
DROP TABLE IF EXISTS `tb_jvm_parameters`;
CREATE TABLE `tb_jvm_parameters` (
  `id`            int(11)       NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name`          varchar(128)  NOT NULL                COMMENT '参数命令或者完整的模式',
  `versions`      varchar(256)  DEFAULT NULL            COMMENT '参数版本: jdk7,jdk8',
  `examples`      varchar(1024) DEFAULT NULL            COMMENT '参数的使用例子',
  `type`          varchar(128)  DEFAULT NULL            COMMENT '参数类型',
  `os`            varchar(16)   DEFAULT NULL            COMMENT '适用的操作系统',
  `meaning`       text          DEFAULT NULL            COMMENT '英文含义',
  `hanyi`         text          DEFAULT NULL            COMMENT '中文解释',
  `use`           text          DEFAULT NULL            COMMENT '使用',
  `extend`        text          DEFAULT NULL            COMMENT '备注',
  `url`           varchar(1024)  DEFAULT NULL           COMMENT '链接',
  `default_value` varchar(128)  DEFAULT NULL            COMMENT '默认值',
  `create_time`   datetime      DEFAULT NULL            COMMENT '创建时间/注册时间',
  `modify_time`   datetime      DEFAULT NULL            COMMENT '最后更新时间',
   PRIMARY KEY (`id`),
   index(`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='参数信息集合';

DROP TABLE IF EXISTS `tb_jvm_parameters_rank`;
CREATE TABLE `tb_jvm_parameters_rank` (
  `id`            int(11)       NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name`          varchar(128)  NOT NULL                COMMENT '参数命令或者完整的模式',
  `use_off_line`    int(11)       DEFAULT 0               COMMENT '线下',
  `use_on_line`     int(11)       DEFAULT 0               COMMENT '线上',
  `create_time`   datetime      DEFAULT NULL            COMMENT '创建时间/注册时间',
  `modify_time`   datetime      DEFAULT NULL            COMMENT '最后更新时间',
   PRIMARY KEY (`id`),
   index(`name`),
   index(`use_off_line`),
   UNIQUE (`name`),
   index(`use_on_line`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='参数使用排名';


DROP TABLE IF EXISTS `tb_java_info`;
CREATE TABLE `tb_java_info` (
  `id`            int(11)       NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `hostname`      varchar(256)  NOT NULL                COMMENT '主机名称',
  `java_info`     text          DEFAULT NULL            COMMENT 'javaInfo 信息',
  `env`           varchar(12)   DEFAULT NULL            COMMENT '环境信息',
  `create_time`   datetime      DEFAULT NULL            COMMENT '创建时间/注册时间',
  `modify_time`   datetime      DEFAULT NULL            COMMENT '最后更新时间',
   PRIMARY KEY (`id`),
   UNIQUE (`hostname`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机的JavaInfo信息';

DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id`            int(11)       NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `ip`            varchar(256)  NOT NULL                COMMENT 'ip地址',
  `datetime`      datetime     DEFAULT NULL            COMMENT '访问时间',
  `location`      varchar(256)  NOT NULL                COMMENT 'ip归属地',
  `create_time`   datetime      DEFAULT NULL            COMMENT '创建时间/注册时间',
  `modify_time`   datetime      DEFAULT NULL            COMMENT '最后更新时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='主机的JavaInfo信息';