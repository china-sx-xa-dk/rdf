/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : rdf

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 25/07/2019 14:27:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
                          `id` varchar(20) NOT NULL,
                          `head_photo` varchar(255) DEFAULT NULL COMMENT '头像',
                          `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
                          `login_password` varchar(255) DEFAULT NULL COMMENT '登录密码',
                          `user_state` int(11) DEFAULT NULL COMMENT '用户状态1启动2停用',
                          `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
                          `user_card_no` varchar(255) DEFAULT NULL COMMENT '身份证',
                          `user_mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
                          `user_sex` int(11) DEFAULT NULL COMMENT '用户性别1男2女',
                          `user_email` varchar(255) DEFAULT NULL COMMENT '邮箱',
                          `user_login_num` int(11) DEFAULT NULL COMMENT '用户登录次数',
                          `user_desc` varchar(255) DEFAULT NULL COMMENT '描述',
                          `user_last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
                          `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                          `del_flag` int(11) DEFAULT '0' COMMENT '删除标识1删除0未删除',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='APP登录用户信息表';

-- ----------------------------
-- Table structure for app_user_org
-- ----------------------------
DROP TABLE IF EXISTS `app_user_org`;
CREATE TABLE `app_user_org` (
                              `user_id` varchar(20) NOT NULL COMMENT '用户编号',
                              `org_id` int(11) NOT NULL COMMENT '机构编号',
                              PRIMARY KEY (`user_id`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app用户机构';

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
                           `file_id` int(11) NOT NULL AUTO_INCREMENT,
                           `resource_id` varchar(15) DEFAULT NULL COMMENT '资源id',
                           `file_name` varchar(100) NOT NULL COMMENT '上传后的文件名',
                           `file_origin_name` varchar(100) NOT NULL COMMENT '原始文件名',
                           `file_path` varchar(100) NOT NULL COMMENT '上传后的路径',
                           `file_type` varchar(100) NOT NULL COMMENT '文件类型',
                           `md5` varchar(255) DEFAULT NULL,
                           `valid` tinyint(2) NOT NULL COMMENT '文件是否有效(true/1: 有效 ;false/0: 无效)',
                           `is_delete` tinyint(2) NOT NULL COMMENT '是否删除(true/1 : 已删除 ; false/0: 未删除)',
                           `size` bigint(50) NOT NULL COMMENT '文件大小',
                           `upload_time` varchar(50) NOT NULL COMMENT '上传时间',
                           `delete_time` varchar(50) DEFAULT NULL COMMENT '删除时间',
                           `created_by` varchar(20) DEFAULT NULL,
                           `created_date` date DEFAULT NULL,
                           `updated_by` varchar(20) DEFAULT NULL,
                           `updated_date` date DEFAULT NULL,
                           PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件信息表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
                            `config_id` varchar(20) NOT NULL,
                            `config_key` varchar(255) DEFAULT NULL,
                            `config_value` varchar(255) DEFAULT NULL,
                            `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                            `sort_num` int(10) DEFAULT NULL,
                            PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
                          `value` varchar(100) NOT NULL COMMENT '数据值',
                          `label` varchar(100) NOT NULL COMMENT '标签名',
                          `type` varchar(100) NOT NULL COMMENT '类型',
                          `description` varchar(100) NOT NULL COMMENT '描述',
                          `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
                          `parent_id` varchar(64) DEFAULT '0' COMMENT '父级编号',
                          `create_by` int(11) NOT NULL COMMENT '创建者',
                          `create_date` datetime NOT NULL COMMENT '创建时间',
                          `update_by` int(11) NOT NULL COMMENT '更新者',
                          `update_date` datetime NOT NULL COMMENT '更新时间',
                          `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
                          `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标记',
                          PRIMARY KEY (`id`),
                          KEY `sys_dict_value` (`value`) USING BTREE,
                          KEY `sys_dict_label` (`label`) USING BTREE,
                          KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (19, '1', '启用', 'adminState', '启用禁用状态', 1, '0', 1, '2019-06-10 09:24:38', 1, '2019-06-10 09:24:38', '', 0);
INSERT INTO `sys_dict` VALUES (20, '2', '禁用', 'adminState', '启用禁用状态', 2, '0', 1, '2019-06-10 09:24:47', 1, '2019-06-10 09:24:47', '', 0);
INSERT INTO `sys_dict` VALUES (21, '1', '男', 'sex', '性别', 1, '0', 1, '2019-06-10 11:46:36', 1, '2019-06-10 11:46:36', '', 0);
INSERT INTO `sys_dict` VALUES (22, '2', '女', 'sex', '性别', 2, '0', 1, '2019-06-10 11:46:45', 1, '2019-06-10 11:46:45', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_log_operate
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_operate`;
CREATE TABLE `sys_log_operate` (
                                 `log_id` varchar(20) NOT NULL COMMENT '日志ID',
                                 `user_id` varchar(255) DEFAULT NULL COMMENT '访问用户ID',
                                 `community_id` int(11) DEFAULT NULL COMMENT '社区编码',
                                 `func_id` varchar(120) DEFAULT NULL COMMENT '功能ID',
                                 `func_sys_name` varchar(120) DEFAULT NULL COMMENT '模块名称',
                                 `func_name` varchar(120) DEFAULT NULL COMMENT '功能名称',
                                 `page_url` varchar(300) DEFAULT NULL COMMENT '操作路径',
                                 `operate_ip` varchar(120) DEFAULT NULL COMMENT '操作IP',
                                 `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
                                 `operate_desc` varchar(500) DEFAULT NULL COMMENT '访问描述',
                                 `product_id` varchar(110) DEFAULT NULL COMMENT '产品ID',
                                 `product_name` varchar(120) DEFAULT NULL COMMENT '产品名称',
                                 `visit_type` varchar(110) DEFAULT NULL COMMENT '访问类型 登录、登出、功能',
                                 `shop_id` varchar(90) DEFAULT NULL COMMENT '店铺ID',
                                 `shop_name` varchar(120) DEFAULT NULL COMMENT '店铺名称',
                                 `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
                                 `remark` varchar(300) DEFAULT NULL COMMENT '备注',
                                 PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
                                  `org_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '组织机构Id',
                                  `org_name` varchar(100) NOT NULL COMMENT '组织机构名称',
                                  `parent_id` int(11) NOT NULL COMMENT '父节点为0的是根机构',
                                  `parentIds` varchar(100) NOT NULL COMMENT '父级id串',
                                  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
BEGIN;
INSERT INTO `sys_organization` VALUES (1, '西安', 0, '0,');
INSERT INTO `sys_organization` VALUES (2, '新城区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (3, '碑林区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (4, '莲湖区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (5, '灞桥区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (6, '未央区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (7, '雁塔区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (8, '阎良区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (9, '临潼区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (10, '长安区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (11, '蓝田县', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (12, '周至县', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (13, '鄠邑区', 1, '0,1,');
INSERT INTO `sys_organization` VALUES (14, '高陵县', 1, '0,1,');
COMMIT;

-- ----------------------------
-- Table structure for system_admin
-- ----------------------------
DROP TABLE IF EXISTS `system_admin`;
CREATE TABLE `system_admin` (
                              `ADMIN_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
                              `LOGIN_NAME` varchar(50) NOT NULL COMMENT '登录名',
                              `LOGIN_PASS` varchar(32) NOT NULL COMMENT '密码',
                              `ADMIN_STATE` int(11) NOT NULL COMMENT '管理员状态1：启动；2：停用',
                              `ADMIN_NAME` varchar(20) DEFAULT NULL COMMENT '真实姓名',
                              `ADMIN_CARDNO` varchar(20) DEFAULT NULL COMMENT '身份证号',
                              `ADMIN_MOBILE` varchar(20) DEFAULT NULL COMMENT '手机',
                              `ADMIN_SEX` int(11) DEFAULT NULL COMMENT '性别',
                              `ADMIN_BIRTHDAY` datetime DEFAULT NULL COMMENT '生日',
                              `ADMIN_EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱',
                              `ADMIN_LOGIN_NUM` int(11) DEFAULT NULL COMMENT '登陆次数',
                              `ADMIN_CREATE_DATE` datetime DEFAULT NULL COMMENT '注册时间',
                              `ADMIN_LOGIN_DATE` datetime DEFAULT NULL COMMENT '当前登陆时间',
                              `ADMIN_LOGIN_OLDDATE` datetime DEFAULT NULL COMMENT '上次登陆时间',
                              `ADMIN_LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '当前ip',
                              `ADMIN_LOGIN_OLDIP` varchar(20) DEFAULT NULL COMMENT '上次ip',
                              `ADMIN_PROVINCE` varchar(32) DEFAULT NULL COMMENT '省',
                              `ADMIN_COUNTY` varchar(32) DEFAULT NULL COMMENT '县',
                              `ADMIN_CITY` varchar(32) DEFAULT NULL COMMENT '市',
                              `ADMIN_ADDRESS` varchar(512) DEFAULT NULL COMMENT '详细地址',
                              `COMPANY` varchar(255) DEFAULT NULL,
                              `COMPANY_NAME` varchar(255) DEFAULT NULL,
                              `ORG_ID` varchar(255) DEFAULT '86029' COMMENT '组织机构ID',
                              PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_admin
-- ----------------------------
BEGIN;
INSERT INTO `system_admin` VALUES (1, 'admin', '49ba59abbe56e057', 1, '管理员', '612501198911225338', '15129002900', 0, '2019-07-25 14:23:49', '123456789@qq.com', 992, NULL, '2019-07-25 14:23:49', '2019-07-11 11:33:58', '192.168.2.121', '0:0:0:0:0:0:0:1', '', '', '', '', NULL, NULL, '1');
INSERT INTO `system_admin` VALUES (2, 'superadmin', '49ba59abbe56e057', 1, '开发', '610629199405294730', '18192709429', 0, '2019-07-25 14:21:05', '1332866956@qq.com', 814, '2018-08-09 15:37:33', '2019-07-25 14:21:05', '2019-07-15 17:36:25', '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', NULL, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `system_admin` VALUES (6, '12', '49ba59abbe56e057', 1, '23', '612601199309271817', '13239237895', 2, '1993-09-27 00:00:00', '742976405@qq.com', NULL, '2019-07-10 15:54:08', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3');
COMMIT;

-- ----------------------------
-- Table structure for system_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `system_admin_role`;
CREATE TABLE `system_admin_role` (
                                   `ADMIN_ID` decimal(22,0) NOT NULL COMMENT '管理员编号',
                                   `ROLE_ID` decimal(22,0) NOT NULL COMMENT '角色编号',
                                   PRIMARY KEY (`ADMIN_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_admin_role
-- ----------------------------
BEGIN;
INSERT INTO `system_admin_role` VALUES (1, 1);
INSERT INTO `system_admin_role` VALUES (2, 2);
INSERT INTO `system_admin_role` VALUES (6, 2);
COMMIT;

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission` (
                                   `PERMISSION_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限编号',
                                   `PERMISSION_NAME` varchar(100) NOT NULL COMMENT '权限名称',
                                   `PERMISSION_PARENT_ID` int(11) NOT NULL COMMENT '父权限编号',
                                   `PERMISSION_TYPE` int(11) NOT NULL COMMENT '权限类型(1：链接；2：按钮)',
                                   `PERMISSION_VALUE` varchar(200) NOT NULL COMMENT '权限值',
                                   `PERMISSION_SORT` int(11) NOT NULL COMMENT '排序',
                                   `is_show` char(1) NOT NULL DEFAULT '1' COMMENT '是否在菜单中显示',
                                   `parentIds` varchar(100) DEFAULT NULL COMMENT '父级id串',
                                   PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_permission
-- ----------------------------
BEGIN;
INSERT INTO `SYSTEM_PERMISSION` VALUES (1, '系统菜单树', 0, 0, '0', 1, '1', '0,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (2, '系统管理', 1, 1, '../../', 1, '1', '0,1,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (3, '后台用户管理', 2, 1, 'admin/findAdminList', 1, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (4, '角色管理', 2, 1, 'role/findRoleList', 10, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (5, '菜单管理', 2, 1, 'permission/list', 20, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (6, '系统配置', 2, 1, 'sysConfig/get_list', 80, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (7, '系统日志', 2, 1, 'sysLogOperate/get_list', 90, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (22, '组织机构管理', 2, 1, 'org/list', 30, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (23, '文件管理', 2, 1, 'static/ckfinder/ckfinder.html', 40, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (24, '代码生成', 2, 1, 'database/findTableList', 50, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (25, '数据字典', 2, 1, 'dict/findDictList', 60, '1', '0,1,2,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (29, '数据资源管理', 1, 1, '../../', 10, '1', '0,1,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (30, 'APP用户', 29, 1, 'web/appuser/get_list', 1, '1', '0,1,29,');
INSERT INTO `SYSTEM_PERMISSION` VALUES (31, 'APP用户组织机构', 29, 1, 'web/appuser/index', 2, '1', '0,1,29,');
INSERT INTO `SYSTEM_PERMISSION` values (32, '敏感字设置', 2, 1, 'system/sensitiveInfo/get_list', 100, 1, '0,1,2,' );
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
                             `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
                             `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
                             `ROLE_STATE` int(11) NOT NULL COMMENT '角色状态',
                             `ROLE_DESC` text COMMENT '描述',
                             `ROLE_CREATE_USER` int(11) NOT NULL COMMENT '创建人',
                             `ROLE_CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
                             `ROLE_UPDATE_USER` int(11) DEFAULT NULL COMMENT '修改人',
                             `ROLE_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
                             PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES (1, '开发者', 0, '开发者', 1, '2015-07-16 15:48:50', 1, '2017-09-24 15:19:44');
INSERT INTO `system_role` VALUES (2, '平台管理员', 0, '平台管理员，拥有平台最高权限，可完成平台所有操作', 1, '2018-08-09 15:31:05', 1, '2019-04-21 20:35:41');
COMMIT;

-- ----------------------------
-- Table structure for system_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_role_permission`;
CREATE TABLE `system_role_permission` (
                                        `ROLE_ID` decimal(22,0) NOT NULL COMMENT '角色编号',
                                        `PERMISSION_ID` decimal(22,0) NOT NULL COMMENT '权限编号',
                                        `CHILD_IDS` varchar(255) DEFAULT NULL COMMENT '子权限（备用）',
                                        `ROLE_PER_CREATE_USER` decimal(22,0) DEFAULT NULL COMMENT '创建人',
                                        `ROLE_PER_CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
                                        PRIMARY KEY (`ROLE_ID`,`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `system_role_permission` VALUES (1, 2, NULL, NULL, '2018-03-21 14:33:32');
INSERT INTO `system_role_permission` VALUES (1, 3, NULL, NULL, '2018-03-21 14:33:32');
INSERT INTO `system_role_permission` VALUES (1, 4, NULL, NULL, '2018-03-21 14:33:32');
INSERT INTO `system_role_permission` VALUES (1, 5, NULL, NULL, '2018-03-21 14:33:32');
INSERT INTO `system_role_permission` VALUES (1, 6, NULL, NULL, '2019-06-10 10:52:30');
INSERT INTO `system_role_permission` VALUES (1, 7, NULL, NULL, '2019-06-10 10:52:30');
INSERT INTO `system_role_permission` VALUES (1, 22, NULL, NULL, '2019-03-13 14:19:33');
INSERT INTO `system_role_permission` VALUES (1, 23, NULL, NULL, '2019-05-27 15:08:32');
INSERT INTO `system_role_permission` VALUES (2, 2, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 3, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 4, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 5, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 6, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 7, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 22, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 23, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 24, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 25, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 29, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 30, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 31, NULL, NULL, '2019-06-18 16:00:16');
INSERT INTO `system_role_permission` VALUES (2, 32, NULL, NULL, '2019-06-18 16:00:16');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-- wgl 20190729 创建敏感词信息表
CREATE TABLE `sensitive_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sensitive_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '敏感词内容',
  `replace_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '替换内容',
  `state` tinyint(2) DEFAULT '1' COMMENT '启用状态（1启用；2禁用）',
  `sortnum` int(11) DEFAULT NULL COMMENT '排序号',
  `del_flag` tinyint(2) DEFAULT '0' COMMENT '删除状态（0未删除；1已删除）',
  `create_user` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` int(11) DEFAULT NULL COMMENT '更改人',
  `update_time` datetime DEFAULT NULL COMMENT '更改时间',
  `remark_int1` int(11) DEFAULT NULL COMMENT '备注Int1',
  `remark_int2` int(11) DEFAULT NULL COMMENT '备注Int2',
  `remark_int3` int(11) DEFAULT NULL COMMENT '备注Int3',
  `remark_str1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注Str1',
  `remark_str2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注Str2',
  `remark_str3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注Str3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='敏感词信息表';
