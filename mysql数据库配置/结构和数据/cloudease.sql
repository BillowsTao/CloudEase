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

 Date: 16/05/2018 10:28:43
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
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES (100002, 100001);
INSERT INTO `follow` VALUES (100001, 100002);
INSERT INTO `follow` VALUES (100003, 100002);
INSERT INTO `follow` VALUES (100002, 100003);
INSERT INTO `follow` VALUES (100001, 100004);
INSERT INTO `follow` VALUES (100002, 100004);
INSERT INTO `follow` VALUES (100001, 100005);
INSERT INTO `follow` VALUES (100002, 100005);
INSERT INTO `follow` VALUES (100004, 100005);
INSERT INTO `follow` VALUES (100001, 100006);

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
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES (100001, 100001);
INSERT INTO `likes` VALUES (100002, 100001);
INSERT INTO `likes` VALUES (100001, 100002);
INSERT INTO `likes` VALUES (100002, 100002);
INSERT INTO `likes` VALUES (100001, 100003);
INSERT INTO `likes` VALUES (100001, 100004);
INSERT INTO `likes` VALUES (100003, 100004);
INSERT INTO `likes` VALUES (100001, 100005);
INSERT INTO `likes` VALUES (100001, 100006);
INSERT INTO `likes` VALUES (100004, 100006);
INSERT INTO `likes` VALUES (100001, 100007);
INSERT INTO `likes` VALUES (100004, 100007);
INSERT INTO `likes` VALUES (100001, 100008);
INSERT INTO `likes` VALUES (100004, 100008);
INSERT INTO `likes` VALUES (100005, 100008);
INSERT INTO `likes` VALUES (100001, 100009);
INSERT INTO `likes` VALUES (100002, 100009);
INSERT INTO `likes` VALUES (100001, 100010);
INSERT INTO `likes` VALUES (100001, 100011);
INSERT INTO `likes` VALUES (100001, 100012);
INSERT INTO `likes` VALUES (100002, 100012);
INSERT INTO `likes` VALUES (100003, 100012);
INSERT INTO `likes` VALUES (100001, 100013);
INSERT INTO `likes` VALUES (100002, 100013);
INSERT INTO `likes` VALUES (100004, 100013);
INSERT INTO `likes` VALUES (100001, 100014);
INSERT INTO `likes` VALUES (100001, 100015);
INSERT INTO `likes` VALUES (100004, 100015);
INSERT INTO `likes` VALUES (100001, 100016);
INSERT INTO `likes` VALUES (100001, 100017);
INSERT INTO `likes` VALUES (100001, 100018);
INSERT INTO `likes` VALUES (100001, 100019);
INSERT INTO `likes` VALUES (100001, 100020);

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
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES (100001, 100017, 100001, '硬汉普京', '2018-01-10 18:17:38');
INSERT INTO `reply` VALUES (100001, 100012, 100002, '可以的，很强', '2018-01-10 18:24:01');
INSERT INTO `reply` VALUES (100001, 100008, 100003, 'Happy new year.', '2018-01-10 18:25:44');
INSERT INTO `reply` VALUES (100002, 100012, 100004, '老铁666啊', '2018-01-10 18:31:25');
INSERT INTO `reply` VALUES (100004, 100015, 100005, 'nice work', '2018-01-10 22:14:04');
INSERT INTO `reply` VALUES (100004, 100014, 100006, 'Make American gerat again', '2018-01-10 22:14:28');
INSERT INTO `reply` VALUES (100005, 100015, 100008, '必须的', '2018-01-10 22:14:59');
INSERT INTO `reply` VALUES (100001, 100015, 100009, '???', '2018-01-10 22:17:15');
INSERT INTO `reply` VALUES (100004, 100019, 100010, 'MAKING AMERICA GREAT AGAIN! ', '2018-01-11 07:01:59');
INSERT INTO `reply` VALUES (100001, 100008, 100011, 'merry Christmas', '2018-01-11 07:05:46');
INSERT INTO `reply` VALUES (100001, 100001, 100013, '', '2018-01-11 21:54:33');
INSERT INTO `reply` VALUES (100001, 100001, 100014, '', '2018-01-11 21:54:35');
INSERT INTO `reply` VALUES (100001, 100019, 100015, '啊', '2018-02-19 17:17:21');
INSERT INTO `reply` VALUES (100001, 100020, 100016, 'zzzzz', '2018-04-15 10:49:09');

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
-- Records of twitter
-- ----------------------------
INSERT INTO `twitter` VALUES (100001, 100002, 'I am a Hongkonger,so I think I can try to answer this question.\r\n\r\nTo begin with, Hong Kong is definitely a part of China. Hong Kong (part of it) was ruled by Britain started in 1842, with the whole of Hong Kong coming under British rule in 1898. Britain promised to give back Hong Kong to China after 99 years, which meant 1997. In 1997 Hong Kong was returned to the official government at that time, which means the current Chinese government.', '2018-01-03 10:09:02', '/imageLib/cloudEase/twitter/background2.jpg,/imageLib/cloudEase/twitter/5718416-moving-universe-wallpaper.jpg,');
INSERT INTO `twitter` VALUES (100002, 100002, 'In 2004, the Japanese found a Chinese sub sailing in what were clearly Japanese territorial waters. The Chinese government rather quickly apologized for the action.\r\n\r\nThere was a legal reason for that. If the Chinese government had refused to apologize, that would have given the Japanese and Americans permission to sail submarines in Chinese territorial waters. Since it was impossible to argue that this was a mistake, China apologized to Japan for the incident.\r\n\r\nAlso I can find a example of a Chinese apology for attacking a US ship in 1937 and some incidents in the Qing dynasty.', '2018-01-04 10:09:10', '/imageLib/cloudEase/twitter/664157_13354956718Gj4.jpg,');
INSERT INTO `twitter` VALUES (100003, 100001, 'If China truly “likes” to ask other countries for apologies, then Great Britain is the first China should ask. Great Britain forced China open and insisted that the Chinese buy opium from Great Britain grown from other parts of the British Empire. It’s entirely fair to say that for generations, the Brits enjoyed a high standard of living by depending on the patronage of tens of millions of Chinese addicts.', '2018-01-05 10:09:13', '/imageLib/cloudEase/twitter/RWiVJq_1920x1080.jpg,');
INSERT INTO `twitter` VALUES (100004, 100001, 'Why is Chinese nationalism so popular on Quora? While obviously nationalism exists in all countries, it seems disproportionately popular when it comes to questions about China.', '2018-01-08 21:38:54', '\\imageLib\\cloudEase\\twitter\\RWj8HC_1920x1080.jpg,');
INSERT INTO `twitter` VALUES (100005, 100004, 'make america great again', '2018-01-08 21:49:52', '\\imageLib\\cloudEase\\twitter\\trump.jpg');
INSERT INTO `twitter` VALUES (100006, 100001, 'I’ve lived here for 6 years, and yes, I do love China. Please recognize that ALL my comments reflect that of a foreigner in China - not natives. Here are my top reasons why I (and my wife) love living in China:\r\n\r\n#1 - The people are great and the friendliest I have known in my world travels and international residencies. And, people are HAPPY!\r\n\r\n#2 - The food is fantastic, fresh, and incredibly cheap! I especially enjoy buying fresh produce, at least 10 types of mushrooms at about 10% of USA prices. Our average precooked takeout 3 course dinner is around $3.00-$4.50 per person. Sometimes it is only about $2.00 per person (again, this is for fresh pre-cooked takeout, not for making our own dinner!).\r\n\r\n#3 - There is no violent crime in Beijing, a city of 22 million people. Our teenage daughter attending high school here could be out until 2AM with friends, doing karaoke or whatnot, and we had no worries for her safety!\r\n\r\n#4 - Incredible public transportation. Never need a car. My occasional city bus ride costs $.15! The high speed inter-city trains are amazing! This weekend I’m travelling by train from downtown Beijing to downtown Hefei - 1,000 KM by fast train - 4Hrs23Min. You could never make that time by plane!\r\n\r\n#5 - The career opportunities are excellent, with salaries comparable or higher than the USA while cost of living is about 35% the cost of the USA. That’s why Chinese are able to save - not because they are so frugal! And, no age discrimination in employment!!\r\n\r\n#6 - Vibrant, lively, and endlessly entertaining. Even after 6 years, walking down our street in Beijing is like attending “Cirque du Soleil.”\r\n\r\n#7 - Thousands years old and ultra modern Art, culture, history, architecture - FANTASTIC!!', '2018-01-08 22:01:34', '\\imageLib\\cloudEase\\twitter\\Motherboard_ZH-CN12819254349_1920x1080.jpg,');
INSERT INTO `twitter` VALUES (100007, 100004, 'Can’t wait to be back in the amazing state of Tennessee to address the 99th American @FarmBureau Federation’s Annual Convention in Nashville! #AFBF18\n\nOn my way now - join me LIVE at 4:00pmE: ', '2018-01-09 11:48:11', '\\imageLib\\cloudEase\\twitter\\DTCncm9UMAAQr1c.jpg,');
INSERT INTO `twitter` VALUES (100008, 100005, 'On behalf of the Obama family, Merry Christmas! We wish you joy and peace this holiday season.', '2018-01-09 12:17:27', '\\imageLib\\cloudEase\\twitter\\DR53o7RWAAAPPrU.jpg,');
INSERT INTO `twitter` VALUES (100009, 100001, '每个不曾起舞的日子，都是对生命的辜负', '2018-01-09 12:26:39', '/imageLib/cloudEase/twitter/1515471946218-1637465609.jpg,');
INSERT INTO `twitter` VALUES (100010, 100001, 'fighting!!!', '2018-01-09 12:28:23', '/imageLib/cloudEase/twitter/1515472042876-852861553.jpg,');
INSERT INTO `twitter` VALUES (100011, 100002, 'Today is a gift.', '2018-01-09 12:31:57', '/imageLib/cloudEase/twitter/20180108_083521.jpg,');
INSERT INTO `twitter` VALUES (100012, 100001, 'Today is beauty day', '2018-01-10 16:16:15', '/imageLib/cloudEase/twitter/IMG20180110161420.jpg,/imageLib/cloudEase/twitter/IMG20180110161342.jpg,');
INSERT INTO `twitter` VALUES (100013, 100004, 'Today, it was my great honor to sign a new Executive Order to ensure Veterans have the resources they need as they transition back to civilian life. We must ensure that our HEROES are given the care and support they so richly deserve!', '2018-01-10 16:28:29', '/imageLib/cloudEase/twitter/DTIn_PkU8AAKtLK.jpg,');
INSERT INTO `twitter` VALUES (100014, 100004, 'It was my great honor to sign H.R. 267, the “Martin Luther King, Jr. National Historical Park Act,” which redesignates the Martin Luther King, Junior, National Historic Site in the State of Georgia as the Martin Luther King, Jr. National Historical Park.', '2018-01-10 16:29:21', '/imageLib/cloudEase/twitter/DTIBLVGWsAAjmi1.jpg,');
INSERT INTO `twitter` VALUES (100015, 100005, 'There\'s no better time than the holiday season to reach out and give back to our communities. Great to hear from young people at the Boys & Girls Club in DC today.', '2018-01-10 16:30:42', '/imageLib/cloudEase/twitter/DRCoXNpW4AAukLk.jpg,');
INSERT INTO `twitter` VALUES (100016, 100006, 'Working meeting with Prime Minister Dmitry Medvedev ', '2018-01-10 16:38:18', '/imageLib/cloudEase/twitter/DRmATLmWkAAlqMy.jpg,');
INSERT INTO `twitter` VALUES (100017, 100006, 'Congratulations to Emergencies Ministry staff and veterans on their professional day', '2018-01-10 16:39:27', '/imageLib/cloudEase/twitter/DSDSFv6WAAAB509.jpg,');
INSERT INTO `twitter` VALUES (100018, 100002, '我走过山的时候山不说话，我路过海的时候海不说话；\n我坐着的毛驴一步一步滴滴答答，我带着的倚天喑哑。 \n大家说我因为爱着杨过大侠，找不到所以在峨嵋安家；\n其实我只是喜欢峨嵋的雾，像十六岁那年绽放的烟花。', '2018-01-11 01:55:47', '/imageLib/cloudEase/twitter/20161102_164524.jpg,');
INSERT INTO `twitter` VALUES (100019, 100004, 'I want to thank my Cabinet for working tirelessly on behalf of our country. 2017 was a year of monumental achievement and we look forward to the year ahead. Together, we are delivering results and MAKING AMERICA GREAT AGAIN! ', '2018-01-11 06:47:38', '/imageLib/cloudEase/twitter/DTNIzmdWsAcYTe7.jpg,');
INSERT INTO `twitter` VALUES (100020, 100001, 'HAHAHAH', '2018-02-19 17:16:16', '');

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

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('丁星', 'wildhunt', 'root', 'dx@qtu404.com', '17866625604', 100001, '每一个不曾起舞的日子,都是对生命的辜负', '/imageLib/cloudEase/avator/100001/user.jpg');
INSERT INTO `user` VALUES ('王海涛', 'BillowsTao', 'root', 'tao@qtu404.com', '17864293685', 100002, '我走过山的时候山不说话，我路过海的时候海不说话', '/imageLib/cloudEase/avator/100002/user.jpg');
INSERT INTO `user` VALUES ('葛化亚', 'Acary', 'root', 'ya@qtu404.com', '17806236033', 100003, '17806236033打不通就打13045003353', '\\imageLib\\cloudEase\\avator\\100003\\user.jpg');
INSERT INTO `user` VALUES ('Donald J. Trump', 'realDonaldTrump', 'root', 'trump@qtu404.com', '17888886666', 100004, '45th President of the United States of America', '\\imageLib\\cloudEase\\avator\\100004\\user.jpg');
INSERT INTO `user` VALUES ('Barack Obama', 'BarackObama', 'root', 'bama@qtu404.com', '17866668888', 100005, '44th President of the United States of America', '\\imageLib\\cloudEase\\avator\\100005\\user.jpg');
INSERT INTO `user` VALUES ('Vladimir Putin', 'PutinRF_Eng\r\n', 'root', 'pu@qtu404.com', '17866666666', 100006, 'The official twitter channel for President of the Russian Federation. Tweets from the President are signed', '/imageLib/cloudEase/avator/100006/user.jpg');

SET FOREIGN_KEY_CHECKS = 1;
