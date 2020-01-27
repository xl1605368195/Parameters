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
  `default_value` varchar(128)  DEFAULT NULL            COMMENT '默认值',
  `create_time`   datetime      DEFAULT NULL            COMMENT '创建时间/注册时间',
  `modify_time`   datetime      DEFAULT NULL            COMMENT '最后更新时间',
   PRIMARY KEY (`id`),
   index(`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='参数集合';