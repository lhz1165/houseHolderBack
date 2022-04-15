/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : householder

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 15/04/2022 16:40:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (7, 'string1', 'string', '2022-04-13 13:16:23', 'string');
INSERT INTO `comment` VALUES (8, 'string2', 'string', '2022-04-13 13:16:23', 'string');
INSERT INTO `comment` VALUES (9, 'string3', 'string', '2022-04-13 13:16:23', 'string');
INSERT INTO `comment` VALUES (10, 'string', 'string', '2022-04-13 13:16:23', 'string');
INSERT INTO `comment` VALUES (11, 'string', 'string', '2022-04-13 13:16:24', 'string');
INSERT INTO `comment` VALUES (12, 'string', 'string', '2022-04-13 13:16:24', 'string');
INSERT INTO `comment` VALUES (13, 'string', 'string', '2022-04-13 13:16:24', 'string');
INSERT INTO `comment` VALUES (14, 'string', 'string', '2022-04-13 13:16:24', 'string');
INSERT INTO `comment` VALUES (15, 'string', 'string', '2022-04-13 13:16:24', 'string');
INSERT INTO `comment` VALUES (16, 'string', 'string', '2022-04-13 13:16:24', 'string');
INSERT INTO `comment` VALUES (17, 'string23', 'string', '2022-04-13 13:16:25', 'string');
INSERT INTO `comment` VALUES (18, 'string1', 'string', '2022-04-13 13:16:25', 'string');
INSERT INTO `comment` VALUES (19, 'string', 'string', '2022-04-13 13:16:25', 'string');
INSERT INTO `comment` VALUES (20, 'string', 'string', '2022-04-13 13:16:25', 'string');
INSERT INTO `comment` VALUES (21, 'string', 'string', '2022-04-13 13:16:25', 'string');
INSERT INTO `comment` VALUES (22, 'string', 'string', '2022-04-13 13:16:26', 'string');
INSERT INTO `comment` VALUES (23, 'string', 'string', '2022-04-13 13:16:26', 'string');
INSERT INTO `comment` VALUES (24, 'string213', 'string', '2022-04-13 13:16:26', 'string');
INSERT INTO `comment` VALUES (25, 'string23', 'string', '2022-04-13 13:16:26', 'string');
INSERT INTO `comment` VALUES (26, 'string4', 'string', '2022-04-13 13:16:26', 'string');

-- ----------------------------
-- Table structure for house_hold
-- ----------------------------
DROP TABLE IF EXISTS `house_hold`;
CREATE TABLE `house_hold`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `householder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户主名',
  `group_no` int NULL DEFAULT NULL COMMENT '组号\r\n',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍地址',
  `house_no` int NULL DEFAULT NULL COMMENT '户号',
  `people_count` int NULL DEFAULT NULL COMMENT '总人数',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of house_hold
-- ----------------------------

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `reply_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES (5, 'nimsdada', '2022-04-13 17:16:03', 8);
INSERT INTO `reply` VALUES (6, 'sdadasd ', '2022-04-13 17:16:08', 9);
INSERT INTO `reply` VALUES (7, '哈哈哈哈', '2022-04-13 17:16:17', 7);

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` float(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (2, '语文', '教育出版社', 2.00);
INSERT INTO `t_book` VALUES (4, '英语', '教育出版社', 4.00);
INSERT INTO `t_book` VALUES (6, '时间简史', '历史出版社', 12.00);
INSERT INTO `t_book` VALUES (7, '钱穆谈中国文化历史', '出版社111', 100.00);
INSERT INTO `t_book` VALUES (8, '历史', '出版社2222', 12.00);
INSERT INTO `t_book` VALUES (9, '化学', '化学出版社', 122.00);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1管理员 2普通用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123', '/headImage/default2.jpg', NULL, '1');
INSERT INTO `user` VALUES (2, 'hello', '123', '/headImage/default.jpg', NULL, '2');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `race` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户口类型 常住/外地',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `identity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `healhty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康状况',
  `company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `contacts` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位地址',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍状态 迁出/迁出/注销',
  `householder_id` int NULL DEFAULT NULL COMMENT '户籍号',
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
