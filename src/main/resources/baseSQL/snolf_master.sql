
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cc_category
-- ----------------------------
DROP TABLE IF EXISTS `cc_category`;
CREATE TABLE `cc_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '类别名称',
  `parent_id` int(11) DEFAULT '0' COMMENT '父id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态（0：禁用1：正常）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_user` varchar(32) DEFAULT NULL COMMENT '删除人',
  `del` tinyint(4) DEFAULT NULL COMMENT '删除标识（0：正常 1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
-- Records of cc_category
-- ----------------------------

-- ----------------------------
-- Table structure for cc_goods
-- ----------------------------
DROP TABLE IF EXISTS `cc_goods`;
CREATE TABLE `cc_goods` (
  `id` varchar(50) NOT NULL,
  `goods_num` varchar(50) NOT NULL COMMENT '商品编号',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `brand` varchar(100) NOT NULL COMMENT '商品品牌',
  `img_url` varchar(200) DEFAULT NULL COMMENT '商品图片地址',
  `market_price` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '市场价',
  `shop_price` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '门店价',
  `warn_stock` int(11) NOT NULL DEFAULT '0' COMMENT '预警库存',
  `goods_stock` int(11) NOT NULL DEFAULT '0' COMMENT '总库存',
  `goods_unit` char(10) DEFAULT NULL COMMENT '单位',
  `sale_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否上架（0：不上架 1：上架）',
  `category_ids` varchar(100) NOT NULL COMMENT '分类ID路径',
  `sale_num` int(11) DEFAULT '0' COMMENT '总销售量',
  `sale_time` datetime DEFAULT NULL COMMENT '上架时间',
  `description` text COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（0：禁用1：正常）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_user` varchar(32) DEFAULT NULL COMMENT '删除人',
  `del` tinyint(4) DEFAULT NULL COMMENT '删除标识（0：正常 1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of cc_goods
-- ----------------------------

-- ----------------------------
-- Table structure for cc_member
-- ----------------------------
DROP TABLE IF EXISTS `cc_member`;
CREATE TABLE `cc_member` (
  `id` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别（0：女 1：男）',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `member_level` int(11) DEFAULT NULL COMMENT '会员等级',
  `star_level` int(11) DEFAULT NULL COMMENT '星级',
  `invite_code` varchar(20) DEFAULT NULL COMMENT '邀请码',
  `recommend_code` varchar(20) DEFAULT NULL COMMENT '推荐码',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态（0：禁用1：正常）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `del_user` varchar(32) DEFAULT NULL COMMENT '删除人',
  `del` tinyint(4) DEFAULT NULL COMMENT '删除标识（0：正常 1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='999消费商俱乐部会员信息表';

-- ----------------------------
-- Records of cc_member
-- ----------------------------
INSERT INTO `cc_member` VALUES ('f107a191743545ffb21a71918beb8bad', '111', '111', null, null, null, '0', null, 'adDqHA', 'LDLqn0', '1', '2018-10-20 17:43:29', null, null, null, null, null, null);
INSERT INTO `cc_member` VALUES ('s323d', '123456', 'qweqwe', '李大傻', null, null, '1', '2', null, 'adDqHA', '1', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for cc_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `cc_order_goods`;
CREATE TABLE `cc_order_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` varchar(50) NOT NULL,
  `goods_id` varchar(50) NOT NULL,
  `goods_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `goods_price` decimal(11,2) DEFAULT '0.00' COMMENT '商品价格',
  `goods_name` varchar(100) NOT NULL COMMENT '商品名称',
  `goods_img` varchar(200) DEFAULT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';

-- ----------------------------
-- Records of cc_order_goods
-- ----------------------------

-- ----------------------------
-- Table structure for cc_orders
-- ----------------------------
DROP TABLE IF EXISTS `cc_orders`;
CREATE TABLE `cc_orders` (
  `id` varchar(50) NOT NULL,
  `order_num` varchar(50) NOT NULL COMMENT '订单号',
  `user_id` varchar(50) NOT NULL,
  `order_status` tinyint(4) NOT NULL COMMENT '订单状态（0：未付款的订单 1：待发货 2：配送中 3：用户确认收货 4：用户拒收 5：用户取消）',
  `goods_money` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '商品总金额',
  `deliver_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '收货方式（0：自提1：包邮 2：不包邮）',
  `deliver_money` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '运费',
  `total_money` decimal(11,2) NOT NULL COMMENT '订单总金额',
  `real_total_money` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '实际订单总金额',
  `pay_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付方式（0:货到付款 1:在线支付）',
  `pay_from` tinyint(4) NOT NULL DEFAULT '1' COMMENT '支付来源（1:支付宝，2：微信 3：银行卡）',
  `pay_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付状态（0:未支付 1:已支付）',
  `receiver_name` varchar(20) NOT NULL COMMENT '收货人名称',
  `receiver_address` varchar(255) NOT NULL COMMENT '收件人地址',
  `receiver_phone` char(11) NOT NULL COMMENT '收件人手机',
  `order_score` int(11) DEFAULT NULL COMMENT '所得积分',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `express_name` varchar(50) DEFAULT NULL COMMENT '快递公司',
  `express_no` varchar(20) DEFAULT NULL COMMENT '快递单号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del` tinyint(4) DEFAULT '0' COMMENT '删除标识（0：正常 1：删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of cc_orders
-- ----------------------------

-- ----------------------------
-- Table structure for cc_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `cc_shopping_cart`;
CREATE TABLE `cc_shopping_cart` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `goods_id` varchar(50) NOT NULL,
  `goods_num` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
-- Records of cc_shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `auth_name` varchar(255) NOT NULL COMMENT '权限名称',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `level` tinyint(8) DEFAULT NULL COMMENT '权限级别',
  `type` tinyint(8) DEFAULT NULL COMMENT '权限类型 1：目录 2：菜单 3：按钮',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '上级资源',
  `auth_key` varchar(255) NOT NULL COMMENT '资源key',
  `pro_id` int(11) DEFAULT NULL COMMENT '项目id',
  `is_show` tinyint(8) NOT NULL DEFAULT '0' COMMENT '是否在菜单中显示 0：展示 1：不展示',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序字段',
  `create_time` datetime NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `del_user` varchar(255) DEFAULT NULL,
  `is_del` tinyint(2) DEFAULT '0' COMMENT '删除标识 0：正常 1：删除',
  `remark` varchar(255) DEFAULT '0' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_key` (`auth_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('5', '用户中心', '&#xe62d;', '1', '1', '0000', 'UserManager', '7', '0', '0', '2017-09-26 15:02:17', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('6', '部门管理', '/system/dept/list.html', '2', '2', '5', 'dept', '7', '0', '0', '2017-09-26 15:03:49', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('7', '部门列表', '/system/rest/dept/list', '3', '3', '6', 'deptList', '7', '0', null, '2017-09-26 15:05:23', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('8', '添加部门页面', '/system/dept/add.html', '3', '3', '6', 'deptAddPage', '7', '0', null, '2017-09-26 15:19:45', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('9', '添加部门', '/system/rest/dept/add', '3', '3', '6', 'deptAdd', '7', '0', null, '2017-09-26 15:20:49', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('10', '部门树结构', '/system/rest/dept/tree', '3', '3', '6', 'deptTree', '7', '0', null, '2017-09-26 15:22:27', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('11', '修改部门页面', '/system/dept/edit.html', '3', '3', '6', 'deptEditPage', '7', '0', null, '2017-09-26 15:24:22', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('12', '修改部门', '/system/rest/dept/edit', '3', '3', '6', 'deptEdit', '7', '0', null, '2017-09-26 15:32:03', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('13', '删除部门', '/system/rest/dept/delete', '3', '3', '6', 'deptDelete', '7', '0', null, '2017-09-26 15:33:09', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('14', '批量删除部门', '/system/rest/dept/batchDelete', '3', '3', '6', 'deptBatchDelete', '7', '0', null, '2017-09-26 15:33:57', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('15', '角色管理', '/system/role/list.html', '2', '2', '5', 'role', '7', '0', null, '2017-09-26 15:36:30', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('16', '项目管理', '/system/project/list.html', '2', '2', '5', 'project', '7', '0', null, '2017-09-26 15:37:12', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('17', '权限管理', '/system/authority/list.html', '2', '2', '5', 'authority', '7', '0', null, '2017-09-26 15:37:40', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('18', '用户管理', '/system/user/list.html', '2', '2', '5', 'user', '7', '0', null, '2017-09-26 15:38:15', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('19', '角色列表', '/system/rest/role/list', '3', '3', '15', 'roleList', '7', '0', null, '2017-09-26 15:53:24', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('20', '添加角色页面', '/system/role/add.html', '3', '3', '15', 'roleAddPage', '7', '0', null, '2017-09-26 15:55:33', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('21', '添加角色', '/system/rest/role/add', '3', '3', '15', 'roleAdd', '7', '0', null, '2017-09-26 15:57:16', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('22', '修改角色页面', '/system/role/edit.html', '3', '3', '15', 'roleEditPage', '7', '0', null, '2017-09-26 15:58:06', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('23', '部门详情', '/system/rest/dept/get', '3', '3', '6', 'deptInfo', '7', '0', null, '2017-09-26 15:59:17', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('24', '角色详情', '/system/rest/role/get', '3', '3', '15', 'roleInfo', '7', '0', null, '2017-09-26 16:33:44', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('26', '修改角色', '/system/rest/role/edit', '3', '3', '18', 'roleEdit', '7', '0', null, '2017-09-26 16:35:35', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('27', '删除角色', '/system/rest/role/delete', '3', '3', '15', 'roleDelete', '7', '0', null, '2017-09-26 16:36:43', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('28', '批量删除角色', '/system/rest/role/batchDelete', '3', '3', '15', 'roleBatchDelete', '7', '0', null, '2017-09-26 16:37:36', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('29', '分配角色权限页面', '/system/role/assignAuth.html', '3', '3', '15', 'assignAuthPage', '7', '0', null, '2017-09-26 16:39:34', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('30', '项目列表', '/system/rest/project/list', '3', '3', '16', 'projectList', '7', '0', null, '2017-09-26 17:04:28', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('31', '添加项目页面', '/system/project/add.html', '3', '3', '16', 'projectAddPage', '7', '0', null, '2017-09-26 17:05:57', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('33', '添加项目', '/system/rest/project/add', '3', '3', '16', 'projectAdd', '7', '0', null, '2017-09-26 17:06:44', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('34', '修改项目页面', '/system/project/edit.html', '3', '3', '16', 'projectEditPage', '7', '0', null, '2017-09-26 17:07:48', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('35', '项目详情', '/system/rest/project/get', '3', '3', '16', 'projectInfo', '7', '0', null, '2017-09-26 17:08:45', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('36', '修改项目', '/system/rest/project/edit', '3', '3', '16', 'projectEdit', '7', '0', null, '2017-09-26 17:10:31', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('37', '权限列表', '/system/rest/authority/list', '3', '3', '17', 'authorityList', '7', '0', null, '2017-09-26 17:12:06', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('38', '添加权限页面', '/system/authority/add.html', '3', '3', '17', 'authorityAddPage', '7', '0', null, '2017-09-26 17:14:28', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('39', '添加权限', '/system/rest/authority/add', '3', '3', '17', 'authorityAdd', '7', '0', null, '2017-09-26 17:20:41', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('40', '全部项目列表', '/system/rest/project/listAll', '3', '3', '17', 'projectListAll', '7', '0', null, '2017-09-26 17:22:19', '未知', null, null, null, null, '0', '添加权限用');
INSERT INTO `sys_authority` VALUES ('41', '权限选择列表', '/system/rest/authority/listSelect', '3', '3', '17', 'authorityListSelect', '7', '0', null, '2017-09-26 17:23:19', '未知', null, null, null, null, '0', '添加权限选择上级权限用');
INSERT INTO `sys_authority` VALUES ('42', '编辑权限页面', '/system/authority/edit.html', '3', '3', '15', 'authorityEditPage', '7', '0', null, '2017-09-26 17:24:28', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('44', '编辑权限', '/system/rest/authority/edit', '3', '3', '17', 'authorityEdit', '7', '0', null, '2017-09-26 17:25:32', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('45', '用户列表', '/system/rest/user/list', '3', '3', '18', 'userList', '7', '0', null, '2017-09-26 17:26:34', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('46', '删除项目', '/system/rest/project/delete', '3', '3', '16', 'projectDelete', '7', '0', null, '2017-09-26 17:27:52', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('47', '批量删除项目', '/system/rest/project/batchDelete', '3', '3', '16', 'projectBatchDelete', '7', '0', null, '2017-09-26 17:28:34', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('48', '删除权限', '/system/rest/authority/delete', '3', '3', '17', 'authorityDelete', '7', '0', null, '2017-09-26 17:29:26', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('49', '批量删除权限', '/system/rest/authority/batchDelete', '3', '3', '17', 'authorityBatchDelete', '7', '0', null, '2017-09-26 17:29:56', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('50', '添加用户页面', '/system/user/add.html', '3', '3', '18', 'userAddPage', '7', '0', null, '2017-09-26 17:31:46', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('51', '添加用户', '/system/rest/user/add', '3', '3', '18', 'userAdd', '7', '0', null, '2017-09-26 17:32:15', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('52', '修改用户页面', '/system/user/edit.html', '3', '3', '18', 'userEditPage', '7', '0', null, '2017-09-26 17:32:52', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('53', '修改用户', '/system/rest/user/edit', '3', '3', '18', 'userEdit', '7', '0', null, '2017-09-26 17:33:24', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('54', '用户详情', '/system/rest/user/get', '3', '3', '18', 'userInfo', '7', '0', null, '2017-09-26 17:33:50', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('55', '修改用户状态', '/system/rest/user/updateStatus', '3', '3', '18', 'userUpdateStatus', '7', '0', null, '2017-09-26 17:34:54', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('56', '删除用户', '/system/rest/user/delete', '3', '3', '18', 'userDelete', '7', '0', null, '2017-09-26 17:35:38', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('57', '批量删除用户', '/system/rest/user/batchDelete', '3', '3', '18', 'userBatchDelete', '7', '0', null, '2017-09-26 17:36:22', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('58', '分配权限', '/system/rest/role/assignRoleAuth', '3', '3', '15', 'assignRoleAuth', '7', '0', null, '2017-10-19 16:48:15', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('59', '权限分配数据', '/system/rest/authority/tree', '3', '3', '17', 'authorityTree', '7', '0', null, '2017-10-25 18:34:22', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('60', '权限详情', '/system/rest/authority/get', '3', '3', '17', 'authorityInfo', '7', '0', null, '2017-10-25 18:35:05', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('61', '用户角色分配页面', '/system/user/assignRole.html', '3', '3', '18', 'userAssignRole', '7', '0', null, '2017-10-25 18:39:41', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('62', '获取所有角色', '/system/rest/role/getRoleAll', '3', '3', '15', 'roleGetRoleAll', '7', '0', null, '2017-10-25 18:41:21', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('63', '用户分配角色', '/system/rest/user/assignUserRole', '3', '3', '18', 'userAssignUserRole', '7', '0', null, '2017-10-25 18:43:56', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('64', '获取系统信息', '/system/rest/getSystemInfo', '3', '3', '16', 'getSystemInfo', '7', '0', null, '2017-11-02 17:47:20', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('65', '系统菜单', '/system/rest/getSystemMenu', '3', '3', '16', 'getSystemMenu', '7', '0', null, '2017-11-09 15:56:09', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('66', '用户信息展示页面', '/system/user/show.html', '3', '3', '18', 'show', '7', '0', null, '2017-11-16 16:30:59', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_authority` VALUES ('67', '修改登录用户密码', '/system/rest/user/updatePassword', '3', '3', '18', 'updatePassword', '7', '0', null, '2017-11-23 11:32:12', '未知', null, null, null, null, '0', '');

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
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('0', 'GM', 'admin', '0', '', null, '2', '2017-07-26 18:52:17', 'admin', '2017-07-26 18:52:17', null, '2017-07-26 18:52:17', null, null, null);
INSERT INTO `sys_dept` VALUES ('1f0460707ea045e380e8937f69a60639', 'sss', '未知', '', '', '18793939393', '2', '2017-08-18 16:58:53', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_dept` VALUES ('6b1b5f1645da4aecb39a53b8829c5b11', '啥地方', '入方法撒', 'fe199d390b75420397a4afc2dd77534b', '测试1', '', '4', '2017-08-18 17:09:49', '未知', null, null, null, null, '0', '道福到副食超市');
INSERT INTO `sys_dept` VALUES ('b549e98afe01460c81d7bf822644a6f3', '孙福生', 'sdfs', '', '', '', '2', '2017-08-18 17:00:54', '未知', null, null, null, null, '0', '');
INSERT INTO `sys_dept` VALUES ('fe199d390b75420397a4afc2dd77534b', '测试1', '小王', '', '', 'asss', '2', '2017-08-18 16:54:14', '未知', null, null, null, null, '0', '');

-- ----------------------------
-- Table structure for sys_project
-- ----------------------------
DROP TABLE IF EXISTS `sys_project`;
CREATE TABLE `sys_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目标识，唯一',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `pro_key` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '项目链接',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '项目状态 0：正常 1：停用 2：维护',
  `create_time` datetime NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `del_user` varchar(255) DEFAULT NULL,
  `is_del` tinyint(2) DEFAULT NULL COMMENT '删除标识 0：正常 1：删除',
  `remark` varchar(255) DEFAULT '0' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pro_key` (`pro_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_project
-- ----------------------------
INSERT INTO `sys_project` VALUES ('2', '百度', 'baidu', 'https://www.baidu.com/', '0', '2017-09-15 17:25:06', '未知', null, null, null, null, null, '才看见奥基督教佛教VR绕道');
INSERT INTO `sys_project` VALUES ('4', '测试2', 'ceshi2', 'https://www.ceshi2.com', '0', '2017-09-18 17:19:09', '未知', null, null, null, null, null, '');
INSERT INTO `sys_project` VALUES ('5', '测试3', 'ceshi3', 'https://www.ceshi3.com', '1', '2017-09-19 11:28:55', '未知', '2017-09-19 11:28:55', null, '2017-09-19 11:28:55', null, null, '');
INSERT INTO `sys_project` VALUES ('6', '测试4', 'ceshi4', 'https://www.ceshi4.com', '2', '2017-09-19 11:28:48', '未知', '2017-09-19 11:28:49', null, '2017-09-19 11:28:48', null, null, '');
INSERT INTO `sys_project` VALUES ('7', 'snolf-master', 'master', 'http://47.93.241.17', '0', '2017-09-26 15:00:11', '未知', '2017-11-16 11:20:46', null, null, null, null, '我的项目管理');

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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', '超级管理员', 'superManager', '', '2017-10-10 15:33:24', '未知', null, null, null, null, '0');
INSERT INTO `sys_role` VALUES ('bff93b6653a64c7bad2b6785588a145c', '测试', 'ceshi', '', '2017-10-10 16:16:41', '未知', null, null, null, null, '0');

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority` (
  `role_id` varchar(255) NOT NULL,
  `role_key` varchar(255) NOT NULL,
  `auth_id` int(11) NOT NULL,
  `auth_key` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '5', 'UserManager');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '18', 'user');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '54', 'userInfo');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '51', 'userAdd');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '50', 'userAddPage');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '45', 'userList');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '16', 'project');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '65', 'getSystemMenu');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '64', 'getSystemInfo');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '47', 'projectBatchDelete');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '46', 'projectDelete');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '36', 'projectEdit');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '35', 'projectInfo');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '34', 'projectEditPage');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '33', 'projectAdd');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '31', 'projectAddPage');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '30', 'projectList');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '15', 'role');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '62', 'roleGetRoleAll');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '58', 'assignRoleAuth');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '24', 'roleInfo');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '21', 'roleAdd');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '20', 'roleAddPage');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '19', 'roleList');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '6', 'dept');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '23', 'deptInfo');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '14', 'deptBatchDelete');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '13', 'deptDelete');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '12', 'deptEdit');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '11', 'deptEditPage');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '10', 'deptTree');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '9', 'deptAdd');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '8', 'deptAddPage');
INSERT INTO `sys_role_authority` VALUES ('bff93b6653a64c7bad2b6785588a145c', 'ceshi', '7', 'deptList');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '5', 'UserManager');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '18', 'user');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '66', 'show');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '63', 'userAssignUserRole');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '61', 'userAssignRole');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '57', 'userBatchDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '56', 'userDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '55', 'userUpdateStatus');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '54', 'userInfo');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '53', 'userEdit');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '52', 'userEditPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '51', 'userAdd');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '50', 'userAddPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '45', 'userList');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '26', 'roleEdit');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '17', 'authority');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '60', 'authorityInfo');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '59', 'authorityTree');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '49', 'authorityBatchDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '48', 'authorityDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '44', 'authorityEdit');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '41', 'authorityListSelect');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '40', 'projectListAll');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '39', 'authorityAdd');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '38', 'authorityAddPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '37', 'authorityList');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '16', 'project');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '65', 'getSystemMenu');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '64', 'getSystemInfo');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '47', 'projectBatchDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '46', 'projectDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '36', 'projectEdit');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '35', 'projectInfo');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '34', 'projectEditPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '33', 'projectAdd');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '31', 'projectAddPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '30', 'projectList');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '15', 'role');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '62', 'roleGetRoleAll');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '58', 'assignRoleAuth');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '42', 'authorityEditPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '29', 'assignAuthPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '28', 'roleBatchDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '27', 'roleDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '24', 'roleInfo');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '22', 'roleEditPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '21', 'roleAdd');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '20', 'roleAddPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '19', 'roleList');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '6', 'dept');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '23', 'deptInfo');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '14', 'deptBatchDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '13', 'deptDelete');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '12', 'deptEdit');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '11', 'deptEditPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '10', 'deptTree');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '9', 'deptAdd');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '8', 'deptAddPage');
INSERT INTO `sys_role_authority` VALUES ('21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '7', 'deptList');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(255) NOT NULL,
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(255) DEFAULT NULL COMMENT '上次登录IP',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录IP',
  `user_status` tinyint(4) DEFAULT '0' COMMENT '用户状态 0：正常 1冻结',
  `role_id` varchar(255) DEFAULT NULL COMMENT '上次使用的角色',
  `create_time` datetime NOT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `del_user` varchar(255) DEFAULT NULL,
  `is_del` tinyint(2) DEFAULT NULL COMMENT '删除标识 0：正常 1：删除',
  `remark` varchar(255) DEFAULT '0' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0', 'admin', '超级管理员', 'admin', '2019-03-20 10:24:39', '123.160.246.6', '2019-03-27 17:30:43', '123.160.246.6', '0', '21c8e2ee31e44d2d9d344e38863a5cdf', '2017-06-14 17:54:37', null, null, null, null, null, '0', null);
INSERT INTO `sys_user` VALUES ('23cd288fff9140a2ab7b74a3de6d1060', 'wangjunjie', '王俊杰', '123456', '2017-11-22 10:47:03', '192.168.32.54', '2017-11-22 16:17:04', '192.168.32.54', '0', null, '2017-10-10 16:21:29', null, null, null, null, null, '0', null);
INSERT INTO `sys_user` VALUES ('70744fb24e9e4036aca5a17868abf129', 'ceshi001', '测试一号', '123456', '2018-02-01 11:42:24', '202.142.28.42', '2019-03-27 17:28:32', '124.65.98.198', '0', null, '2017-10-10 16:19:17', null, null, null, null, null, '0', null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(255) NOT NULL COMMENT '用户id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名称',
  `role_id` varchar(255) NOT NULL,
  `role_key` varchar(255) NOT NULL COMMENT '角色标识',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('23cd288fff9140a2ab7b74a3de6d1060', '王俊杰', '21c8e2ee31e44d2d9d344e38863a5cdf', 'superManager', '超级管理员');
INSERT INTO `sys_user_role` VALUES ('70744fb24e9e4036aca5a17868abf129', '测试一号', 'bff93b6653a64c7bad2b6785588a145c', 'ceshi', '测试');
