/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : amireux

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 04/07/2021 19:37:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `create_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `update_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_enable` tinyint NOT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `keyword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort_num` int NULL DEFAULT NULL,
  `template` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `permission_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ebva8raq7nmfeqit7xqujtahl`(`url`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_permission
-- ----------------------------
BEGIN;
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('032fe8d1-e556-448e-9587-b39fdd274cdd', '2021-07-04 19:36:26.868382', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.868382', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'ca5e8acc-1228-418c-b06f-bc9ac239d7dd', NULL, 1, NULL, '权限详情', 'api', '/api/v1/permission/detail');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('06310347-74f0-4bea-ad72-9a85a77d243f', '2021-07-04 19:36:26.870324', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.870324', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'b62a07eb-c734-48df-907f-aebb1a8199f7', NULL, 1, NULL, '添加角色', 'api', '/api/v1/role/save');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('0761c358-5b16-42a5-9b08-256d9f3a7cbe', '2021-07-04 19:36:26.871179', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.871179', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'b62a07eb-c734-48df-907f-aebb1a8199f7', NULL, 1, NULL, '角色赋权', 'api', '/api/v1/role/auth');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('09fe51ea-9796-4dbe-8eed-551833b8df36', '2021-07-04 19:36:26.871847', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.871847', 'fc04d55e-a07f-4b80-aa58-56a948409981', 1, NULL, NULL, '6d494c20-511f-45fe-bbb2-42320233e381', NULL, 3, '/user/SystemUserIndex', '用户管理', 'page', '/system/user');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('0a4416c7-ff34-4748-9976-d5c9cdcf58aa', '2021-07-04 19:36:26.872452', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.872452', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'f6804c08-fad2-4e23-9328-a78c73b7474b', '', 1, NULL, '获取当前用户信息', 'api', '/api/v1/auth/current');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('0cca0cbc-b8a4-4b8c-84b0-223476d49af3', '2021-07-04 19:36:26.873116', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.873116', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'ca5e8acc-1228-418c-b06f-bc9ac239d7dd', NULL, 1, NULL, '查询权限', 'api', '/api/v1/permission/page');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('1b24118e-9a20-4752-8f61-02355d9c6107', '2021-07-04 19:36:26.873644', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.873644', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'b62a07eb-c734-48df-907f-aebb1a8199f7', NULL, 1, NULL, '查询用户拥有的角色', 'api', '/api/v1/role/queryByUser');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('32bccada-9196-4e8f-804d-4e5de276ea73', '2021-07-04 19:36:26.874267', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.874267', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, '09fe51ea-9796-4dbe-8eed-551833b8df36', NULL, 1, NULL, '删除用户', 'api', '/api/v1/user/delete');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('3c0a8ce2-87fd-4851-aa7b-43dd0581ed0b', '2021-07-04 19:36:26.875120', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.875120', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, '09fe51ea-9796-4dbe-8eed-551833b8df36', NULL, 1, NULL, '用户详情', 'api', '/api/v1/user/detail');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('3ef33379-2ad1-4c7d-b87c-f794bf573112', '2021-07-04 19:36:26.876001', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.876001', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'f6804c08-fad2-4e23-9328-a78c73b7474b', '', 1, NULL, '获取当前用户菜单', 'api', '/api/v1/auth/menu');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('4f80c87c-ff7a-4db4-99c6-634fb923e34b', '2021-07-04 19:36:26.876526', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.876526', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'b62a07eb-c734-48df-907f-aebb1a8199f7', NULL, 1, NULL, '删除角色', 'api', '/api/v1/role/delete');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('50d02cfd-ae32-4cb0-8e2b-55f793f64e7f', '2021-07-04 19:36:26.877095', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.877095', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'ca5e8acc-1228-418c-b06f-bc9ac239d7dd', '', 1, NULL, '保存权限', 'api', '/api/v1/permission/save');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('69877892-d1b6-4e5b-b471-d20f1235c266', '2021-07-04 19:36:26.877637', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.877637', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, '09fe51ea-9796-4dbe-8eed-551833b8df36', NULL, 1, NULL, '查询用户', 'api', '/api/v1/user/page');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('6d494c20-511f-45fe-bbb2-42320233e381', '2021-07-04 19:36:26.878157', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.878157', 'fc04d55e-a07f-4b80-aa58-56a948409981', 1, 'tool', NULL, '0', NULL, 2, 'RouteView', '系统管理', 'menu', '/system');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('6f62d4bc-450d-4eac-bbb5-509dbf068377', '2021-07-04 19:36:26.878718', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.878718', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'b62a07eb-c734-48df-907f-aebb1a8199f7', NULL, 1, NULL, '角色详情', 'api', '/api/v1/role/detail');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('83e8c192-3fe6-4e95-a25c-28bd0a565da4', '2021-07-04 19:36:26.879728', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.879728', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, '09fe51ea-9796-4dbe-8eed-551833b8df36', NULL, 1, NULL, '添加用户', 'api', '/api/v1/user/save');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('99f9e3a5-0cfb-4346-8e74-1207c3c43e70', '2021-07-04 19:36:26.880648', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.880648', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, '09fe51ea-9796-4dbe-8eed-551833b8df36', NULL, 1, NULL, '用户授权', 'api', '/api/v1/user/auth');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('9c77e909-8a28-4ff9-8cf7-0ac4d4d43de6', '2021-07-04 19:36:26.881534', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.881534', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'b62a07eb-c734-48df-907f-aebb1a8199f7', NULL, 1, NULL, '查询角色', 'api', '/api/v1/role/page');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('a3851190-8424-48a6-9cee-688ec7dc1103', '2021-07-04 19:36:26.882133', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.882133', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'ca5e8acc-1228-418c-b06f-bc9ac239d7dd', NULL, 1, NULL, '删除权限', 'api', '/api/v1/permission/delete');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('a4ee248e-7645-4cf1-a36b-ff357382e3f3', '2021-07-04 19:36:26.882625', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.882625', 'fc04d55e-a07f-4b80-aa58-56a948409981', 0, NULL, NULL, 'ca5e8acc-1228-418c-b06f-bc9ac239d7dd', NULL, 1, NULL, '查询角色拥有的权限', 'api', '/api/v1/permission/queryByRole');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('b62a07eb-c734-48df-907f-aebb1a8199f7', '2021-07-04 19:36:26.883450', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.883450', 'fc04d55e-a07f-4b80-aa58-56a948409981', 1, NULL, NULL, '6d494c20-511f-45fe-bbb2-42320233e381', NULL, 2, '/role/SystemRoleIndex', '角色管理', 'page', '/system/role');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('ca5e8acc-1228-418c-b06f-bc9ac239d7dd', '2021-07-04 19:36:26.884182', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.884182', 'fc04d55e-a07f-4b80-aa58-56a948409981', 1, NULL, NULL, '6d494c20-511f-45fe-bbb2-42320233e381', NULL, 1, '/permission/SystemPermissionIndex', '权限管理', 'page', '/system/permission');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('f6804c08-fad2-4e23-9328-a78c73b7474b', '2021-07-04 19:36:26.884948', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.884948', 'fc04d55e-a07f-4b80-aa58-56a948409981', 1, 'dashboard', NULL, '0', '', 1, 'RouteView', '仪表盘', 'menu', '/dashboard');
INSERT INTO `system_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `icon`, `keyword`, `parent_id`, `remark`, `sort_num`, `template`, `title`, `permission_type`, `url`) VALUES ('ffb76e31-61e6-468e-ba86-23bb5f2c9063', '2021-07-04 19:36:26.885918', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.885918', 'fc04d55e-a07f-4b80-aa58-56a948409981', 1, 'icon', NULL, 'f6804c08-fad2-4e23-9328-a78c73b7474b', '', 1, '/dashboard/Workplace', '工作台', 'page', '/dashboard/workplace');
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `create_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `update_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_enable` tinyint NOT NULL,
  `keyword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `is_enable`, `keyword`, `remark`, `title`) VALUES ('afee1ce5-b12e-4110-b174-a74dc809db2a', '2021-07-04 19:36:26.851988', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.851988', 'fc04d55e-a07f-4b80-aa58-56a948409981', 1, 'admin', '拥有所有权限', '超级管理员');
COMMIT;

-- ----------------------------
-- Table structure for system_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_role_permission`;
CREATE TABLE `system_role_permission`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `create_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `update_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `permission_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('00d2c8f8-3374-4a91-9fc2-7ca2f13cf1ed', '2021-07-04 19:36:26.837187', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.837187', 'fc04d55e-a07f-4b80-aa58-56a948409981', 'a3851190-8424-48a6-9cee-688ec7dc1103', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('04d0cc25-1a1d-4189-9432-4e65caa5083a', '2021-07-04 19:36:26.830026', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.830026', 'fc04d55e-a07f-4b80-aa58-56a948409981', '0cca0cbc-b8a4-4b8c-84b0-223476d49af3', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('0b3b986b-fde2-4b61-b791-3a61bd8eecfa', '2021-07-04 19:36:26.836152', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.836152', 'fc04d55e-a07f-4b80-aa58-56a948409981', '99f9e3a5-0cfb-4346-8e74-1207c3c43e70', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('128ae4cc-70a7-4e7d-b1ec-5ca7103c699e', '2021-07-04 19:36:26.835564', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.835564', 'fc04d55e-a07f-4b80-aa58-56a948409981', '83e8c192-3fe6-4e95-a25c-28bd0a565da4', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('15068c1e-5f7d-41f7-be0e-2926e135944d', '2021-07-04 19:36:26.837671', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.837671', 'fc04d55e-a07f-4b80-aa58-56a948409981', 'a4ee248e-7645-4cf1-a36b-ff357382e3f3', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('218b5f47-d2f7-4fa3-a517-94112c0cf13b', '2021-07-04 19:36:26.839005', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.839005', 'fc04d55e-a07f-4b80-aa58-56a948409981', 'f6804c08-fad2-4e23-9328-a78c73b7474b', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('3854bc2e-e1e0-444e-a6e2-a772723c34e2', '2021-07-04 19:36:26.834180', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.834180', 'fc04d55e-a07f-4b80-aa58-56a948409981', '69877892-d1b6-4e5b-b471-d20f1235c266', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('3efd6940-864c-46cc-8390-9db9de333847', '2021-07-04 19:36:26.838167', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.838167', 'fc04d55e-a07f-4b80-aa58-56a948409981', 'b62a07eb-c734-48df-907f-aebb1a8199f7', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('423d6203-c19e-4611-8b17-a6d4f90782c3', '2021-07-04 19:36:26.828235', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.828235', 'fc04d55e-a07f-4b80-aa58-56a948409981', '0761c358-5b16-42a5-9b08-256d9f3a7cbe', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('451a13e8-1204-41d0-a0cb-29ad7871e81f', '2021-07-04 19:36:26.836669', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.836669', 'fc04d55e-a07f-4b80-aa58-56a948409981', '9c77e909-8a28-4ff9-8cf7-0ac4d4d43de6', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('7f925109-7114-457b-9207-195c90b6d749', '2021-07-04 19:36:26.823357', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.823357', 'fc04d55e-a07f-4b80-aa58-56a948409981', '032fe8d1-e556-448e-9587-b39fdd274cdd', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('80e945fb-845e-451d-b401-0a6d009bc7b1', '2021-07-04 19:36:26.828847', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.828847', 'fc04d55e-a07f-4b80-aa58-56a948409981', '09fe51ea-9796-4dbe-8eed-551833b8df36', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('8cf5921e-5540-4ac1-97ed-e55ac4c9bedf', '2021-07-04 19:36:26.833351', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.833351', 'fc04d55e-a07f-4b80-aa58-56a948409981', '4f80c87c-ff7a-4db4-99c6-634fb923e34b', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('962bbf0c-05eb-4656-8b12-94dbbb11eb6c', '2021-07-04 19:36:26.835002', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.835002', 'fc04d55e-a07f-4b80-aa58-56a948409981', '6f62d4bc-450d-4eac-bbb5-509dbf068377', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('9aeafe79-f5f1-4fcb-bcb5-efe22534eba0', '2021-07-04 19:36:26.838554', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.838554', 'fc04d55e-a07f-4b80-aa58-56a948409981', 'ca5e8acc-1228-418c-b06f-bc9ac239d7dd', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('9fcdee8a-a03c-448b-ae60-dda1607312aa', '2021-07-04 19:36:26.829446', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.829446', 'fc04d55e-a07f-4b80-aa58-56a948409981', '0a4416c7-ff34-4748-9976-d5c9cdcf58aa', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('b1863735-c045-4606-9caf-b5d79c2b15ed', '2021-07-04 19:36:26.833777', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.833777', 'fc04d55e-a07f-4b80-aa58-56a948409981', '50d02cfd-ae32-4cb0-8e2b-55f793f64e7f', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('b7f68491-6e36-4f01-ab06-f92ce0f35868', '2021-07-04 19:36:26.832348', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.832348', 'fc04d55e-a07f-4b80-aa58-56a948409981', '3c0a8ce2-87fd-4851-aa7b-43dd0581ed0b', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('cf553d81-75aa-4d1a-8964-22b0a1326876', '2021-07-04 19:36:26.831718', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.831718', 'fc04d55e-a07f-4b80-aa58-56a948409981', '32bccada-9196-4e8f-804d-4e5de276ea73', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('dac95ed8-3741-4637-9cce-759f64eaf339', '2021-07-04 19:36:26.827678', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.827678', 'fc04d55e-a07f-4b80-aa58-56a948409981', '06310347-74f0-4bea-ad72-9a85a77d243f', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('e3260241-472a-41d7-96af-23fd166a8578', '2021-07-04 19:36:26.839430', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.839430', 'fc04d55e-a07f-4b80-aa58-56a948409981', 'ffb76e31-61e6-468e-ba86-23bb5f2c9063', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('ef0df22a-0329-45c4-9fd2-c163c5264b9e', '2021-07-04 19:36:26.834566', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.834566', 'fc04d55e-a07f-4b80-aa58-56a948409981', '6d494c20-511f-45fe-bbb2-42320233e381', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('f20450f8-214c-4b73-9787-f0ac5b7ccb67', '2021-07-04 19:36:26.830814', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.830814', 'fc04d55e-a07f-4b80-aa58-56a948409981', '1b24118e-9a20-4752-8f61-02355d9c6107', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
INSERT INTO `system_role_permission` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `permission_id`, `role_id`) VALUES ('f859ef87-3d43-4156-81d3-76ae8f8a95b8', '2021-07-04 19:36:26.832904', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.832904', 'fc04d55e-a07f-4b80-aa58-56a948409981', '3ef33379-2ad1-4c7d-b87c-f794bf573112', 'afee1ce5-b12e-4110-b174-a74dc809db2a');
COMMIT;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `create_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `update_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_enable` tinyint NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_74y7xiqrvp39wycn0ron4xq4h`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_user
-- ----------------------------
BEGIN;
INSERT INTO `system_user` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `avatar`, `email`, `is_enable`, `password`, `phone`, `username`) VALUES ('fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.810545', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.810545', 'fc04d55e-a07f-4b80-aa58-56a948409981', 'https://q1.qlogo.cn/g?b=qq&nk=2964556627&s=640', '2964556627@qq.com', 1, '$2a$10$3oX9DZa/r1l2t.qb2DpEV./PXwUATez4BORhPJM84kG3oIBPnXHpi', '2964556627', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `create_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime(6) NOT NULL,
  `update_user` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
BEGIN;
INSERT INTO `system_user_role` (`id`, `create_time`, `create_user`, `update_time`, `update_user`, `role_id`, `user_id`) VALUES ('84f01929-035e-475e-a8ab-21e18937ed2b', '2021-07-04 19:36:26.796940', 'fc04d55e-a07f-4b80-aa58-56a948409981', '2021-07-04 19:36:26.796940', 'fc04d55e-a07f-4b80-aa58-56a948409981', 'afee1ce5-b12e-4110-b174-a74dc809db2a', 'fc04d55e-a07f-4b80-aa58-56a948409981');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
