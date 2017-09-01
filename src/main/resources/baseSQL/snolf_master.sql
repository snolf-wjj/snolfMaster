
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(255) NOT NULL,
  `dept_name` varchar(255) NOT NULL COMMENT '部门名称',
  `contact_person` varchar(255) DEFAULT NULL COMMENT '联系人',
  `parent_id` varchar(255) NOT NULL COMMENT '上级部门',
  `parent_name` varchar(255) DEFAULT NULL COMMENT '父名称',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `dept_level` int(4) DEFAULT NULL COMMENT '部门级别',
  `create_time` datetime NOT NULL,
  `create_user` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `del_user` varchar(255) DEFAULT NULL,
  `is_del` tinyint(2) DEFAULT '0' COMMENT '删除标识 0：正常 1：删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_key` varchar(255) DEFAULT NULL COMMENT '角色键',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `del_user` varchar(255) DEFAULT NULL,
  `is_del` tinyint(2) DEFAULT '0' COMMENT '删除标识 0：正常 1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(255) NOT NULL,
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `create_date` datetime DEFAULT NULL COMMENT '注册日期',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(255) DEFAULT NULL COMMENT '上次登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录IP',
  `role_id` varchar(255) DEFAULT '' COMMENT '角色ID',
  `user_status` tinyint(4) DEFAULT '0' COMMENT '用户状态 0：正常 1冻结',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '删除标识 0：正常 1：删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
