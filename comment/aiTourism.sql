/*******************  用户表  **********************/
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `user_password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `del_flag` varchar(2) DEFAULT NULL COMMENT '删除标志位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*******************  插入一条记录  *********************/
--  用户名：ZhouXY   密码：ZXY
INSERT into user_info (
	user_name,user_password,del_flag
)
VALUES(
	"ZhouXY" ,MD5('ZXY'),"0"
)