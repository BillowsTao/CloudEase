/*
 Navicat Premium Data Transfer

 Source Server         : CouldServer
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : 120.24.186.116:3306
 Source Schema         : cloudease

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 16/05/2018 10:28:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `user_id_follow` bigint(255) NOT NULL,
  `user_id_star` bigint(255) NOT NULL,
  PRIMARY KEY (`user_id_follow`, `user_id_star`) USING BTREE,
  INDEX `user_id`(`user_id_star`) USING BTREE,
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`user_id_follow`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`user_id_star`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `like_user` bigint(255) NOT NULL,
  `like_twitter` bigint(255) NOT NULL,
  PRIMARY KEY (`like_user`, `like_twitter`) USING BTREE,
  INDEX `like_twitter`(`like_twitter`) USING BTREE,
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`like_user`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`like_twitter`) REFERENCES `twitter` (`twitter_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `user_id` bigint(20) NOT NULL,
  `twitter_id` bigint(20) NOT NULL,
  `reply_id` bigint(20) NOT NULL,
  `reply_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `reply_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`reply_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `reply_ibfk_2`(`twitter_id`) USING BTREE,
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`twitter_id`) REFERENCES `twitter` (`twitter_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for twitter
-- ----------------------------
DROP TABLE IF EXISTS `twitter`;
CREATE TABLE `twitter`  (
  `twitter_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `twitter_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `twitter_date` datetime(0) NULL DEFAULT NULL,
  `twitter_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`twitter_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `twitter_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `user_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `user_head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_no`(`user_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
