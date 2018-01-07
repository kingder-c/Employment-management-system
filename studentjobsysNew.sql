/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : studentjobsys

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-07 14:01:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_info
-- ----------------------------
DROP TABLE IF EXISTS `auth_info`;
CREATE TABLE `auth_info` (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_name` varchar(30) NOT NULL,
  `auth_path` varchar(20) DEFAULT NULL,
  `auth_parentid` varchar(11) DEFAULT NULL,
  `auth_description` varchar(100) DEFAULT NULL,
  `auth_state` int(1) DEFAULT NULL,
  `auth_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_info
-- ----------------------------
INSERT INTO `auth_info` VALUES ('1', '1', '1', '学员管理', '1', '1', '0');
INSERT INTO `auth_info` VALUES ('2', '2', '2', '学员管理', '啊', '0', null);
INSERT INTO `auth_info` VALUES ('3', '3', '3', '1', 'fa ', '0', '0');
INSERT INTO `auth_info` VALUES ('4', '的', '的', '学员管理', '的', '0', null);

-- ----------------------------
-- Table structure for classes_info
-- ----------------------------
DROP TABLE IF EXISTS `classes_info`;
CREATE TABLE `classes_info` (
  `cla_id` int(11) NOT NULL AUTO_INCREMENT,
  `cou_id` int(11) DEFAULT NULL,
  `tea_id` int(11) DEFAULT NULL,
  `cla_name` varchar(30) NOT NULL,
  `cla_nature` varchar(30) DEFAULT NULL,
  `cla_num` int(3) DEFAULT NULL,
  `cla_starttime` date DEFAULT NULL,
  `cla_adder` varchar(30) DEFAULT NULL,
  `cla_note` varchar(100) DEFAULT NULL,
  `cla_addtime` date DEFAULT NULL,
  `cla_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`cla_id`),
  KEY `cou_id` (`cou_id`),
  KEY `tea_id` (`tea_id`),
  CONSTRAINT `classes_info_ibfk_1` FOREIGN KEY (`cou_id`) REFERENCES `course_info` (`cou_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `classes_info_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teacher_info` (`tea_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes_info
-- ----------------------------
INSERT INTO `classes_info` VALUES ('2', '111', '2', '13140F01', '混合班', '15', '2017-05-17', '闫煜瑶', '', '2017-06-02', '0');
INSERT INTO `classes_info` VALUES ('111', '111', '1', '13140B01', '散招', '33', '2017-05-10', '闫煜瑶', '', '2017-06-01', '0');
INSERT INTO `classes_info` VALUES ('113', '2', '1', '13140A01', '散招', '25', '2017-05-18', '闫煜瑶', '', '2017-06-01', '0');
INSERT INTO `classes_info` VALUES ('114', '111', '2', '13140D01', '3+1合作', '20', '2017-05-23', '闫煜瑶', '', '2017-06-01', '0');
INSERT INTO `classes_info` VALUES ('115', '2', '3', '13140C01', '混合班', '26', '2017-04-05', '闫煜瑶', '', '2017-06-04', '0');

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_name` varchar(30) NOT NULL,
  `com_city` varchar(20) DEFAULT NULL,
  `com_person` varchar(50) DEFAULT NULL,
  `com_phone` varchar(11) DEFAULT NULL,
  `com_email` varchar(20) DEFAULT NULL,
  `com_direction` varchar(50) DEFAULT NULL,
  `com_status` int(1) NOT NULL,
  `com_level` varchar(20) DEFAULT NULL,
  `com_adder` varchar(30) DEFAULT NULL,
  `com_note` varchar(50) DEFAULT NULL,
  `com_checkstatus` varchar(20) DEFAULT NULL,
  `com_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`com_id`),
  KEY `com_name` (`com_name`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES ('1', '山西汾酒', '汾阳', '张先生', '18435185635', '860157625@163.com', '酒酿', '0', '11', '哈哈', ' 五险一金,出差补助', '已审核', '1');
INSERT INTO `company_info` VALUES ('3', '分', '分', '分', '14', '807820764@qq.com', '分', '0', '3', null, ' 五险一金,还有旅游福利待遇。 ', '已审核', '0');
INSERT INTO `company_info` VALUES ('5', '腾讯', '汾阳', '哈哈', '18435185', '807820764@qq.com', '迪庆', '0', '3', null, '', '已审核', '1');
INSERT INTO `company_info` VALUES ('6', '万达', '青岛', '王先生', '18859888888', '18859888888@163.com', '万达', '0', '4', null, '', '已审核', '0');
INSERT INTO `company_info` VALUES ('8', '少年宫', '太原', '赵女士', '18456982365', '18456982365@163.com', '幼师', '0', '3', null, ' 五险一金,还有旅游福利待遇。 ', '已审核', '0');
INSERT INTO `company_info` VALUES ('9', '大疆', '新疆', '李', '762543', '807820764@qq.com', '大疆', '0', '3', null, ' 五险一金,还有旅游福利待遇。 ', '已审核', '0');
INSERT INTO `company_info` VALUES ('51', '中央电视台', '北京', '魏女士', '16854265973', '16854265973@163.com', '数字媒体', '0', '2', null, ' 五险一金,还有旅游福利待遇。 ', '已审核', '0');
INSERT INTO `company_info` VALUES ('52', '百度（北京）', '北京', '王女士', '15864789536', '15864789536@163.com', 'Java', '0', '4', null, ' 五险一金,还有旅游福利待遇。 ', '已审核', '0');
INSERT INTO `company_info` VALUES ('53', '网易', '深圳', '刘女士', '13356479852', '13356479852@163.com', 'UI', '0', '2', null, ' 五险一金,还有旅游福利待遇。 ', '已审核', '0');
INSERT INTO `company_info` VALUES ('54', '美团（上海分部）', '上海', '张先生', '16598654723', '16598654723@163.com', 'java', '0', '3', null, ' 五险一金,免费美团订餐。', '已审核', '0');
INSERT INTO `company_info` VALUES ('55', '华为（北京分公司）', '北京', '李先生', '15865986742', '15865986742@163.com', '华为（北京分公司）', '1', '3', null, ' 五险一金,还有旅游福利待遇。 ', '已审核', '0');
INSERT INTO `company_info` VALUES ('84', '大疆33', '重庆', '', '', '', '大疆33', '0', '', null, '', '已审核', null);

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `cou_id` int(11) NOT NULL AUTO_INCREMENT,
  `cou_name` varchar(30) NOT NULL,
  `cou_note` varchar(500) DEFAULT NULL,
  `cou_logo` varchar(1000) DEFAULT NULL,
  `cou_adder` varchar(30) DEFAULT NULL,
  `cou_date` date DEFAULT NULL,
  `cou_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`cou_id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_info
-- ----------------------------
INSERT INTO `course_info` VALUES ('2', 'java', '<p>后台+框架</p>\r\n', '', '闫煜瑶', '2017-06-02', '1');
INSERT INTO `course_info` VALUES ('111', 'php', '<p>PHP开发</p>\r\n', '', '闫煜瑶', '2017-06-04', '1');
INSERT INTO `course_info` VALUES ('114', 'ios', '<p>苹果系统</p>\r\n', '', '乔布斯2', '2017-05-23', '0');
INSERT INTO `course_info` VALUES ('116', 'UI', '<p>前端开发</p>\r\n', '', '闫煜瑶', '2017-06-02', '0');
INSERT INTO `course_info` VALUES ('117', 'C/C++', '<p>基础课程</p>\r\n', '', '闫煜瑶', '2017-06-04', '0');
INSERT INTO `course_info` VALUES ('118', 'Struts2', '<p>深度好课，不容错过</p>\r\n', '', '闫煜瑶', '2017-06-04', '0');
INSERT INTO `course_info` VALUES ('119', '计算机组成原理', '<p>计算机基础课程</p>\r\n', '', '管理员', '2017-06-04', '0');

-- ----------------------------
-- Table structure for data_info
-- ----------------------------
DROP TABLE IF EXISTS `data_info`;
CREATE TABLE `data_info` (
  `data_key` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据字典',
  `data_name` varchar(100) DEFAULT NULL,
  `data_num` varchar(100) DEFAULT NULL,
  `data_type` varchar(100) DEFAULT NULL,
  `data_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`data_key`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_info
-- ----------------------------
INSERT INTO `data_info` VALUES ('1', '女', '1', '性别', '0');
INSERT INTO `data_info` VALUES ('2', '男', '0', '性别', '0');
INSERT INTO `data_info` VALUES ('3', '审核中', '1', '审核状态', '0');
INSERT INTO `data_info` VALUES ('4', '已审核', '2', '审核状态', '0');
INSERT INTO `data_info` VALUES ('5', '未通过', '3', '审核状态', '0');
INSERT INTO `data_info` VALUES ('6', '招聘中', '0', '合作企业状态', '0');
INSERT INTO `data_info` VALUES ('7', '不招聘', '1', '合作企业状态', '0');
INSERT INTO `data_info` VALUES ('8', 'test', '1', 'test', '0');
INSERT INTO `data_info` VALUES ('9', '删除', '1', '是否删除', '0');
INSERT INTO `data_info` VALUES ('10', '不删除', '0', '是否删除', '0');

-- ----------------------------
-- Table structure for job_info
-- ----------------------------
DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_company` varchar(100) DEFAULT NULL,
  `job_city` varchar(100) DEFAULT NULL,
  `job_job` varchar(100) DEFAULT NULL COMMENT '就业信息表\r\n',
  `job_startdate` date DEFAULT NULL,
  `job_basesalary` varchar(100) DEFAULT NULL,
  `job_three` int(4) DEFAULT NULL,
  `job_comment` varchar(100) DEFAULT NULL,
  `job_addtime` date DEFAULT NULL,
  `job_adder` varchar(100) DEFAULT NULL,
  `com_id` int(11) DEFAULT NULL,
  `stu_id` int(11) DEFAULT NULL,
  `job_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`job_id`),
  KEY `stu_id` (`stu_id`),
  KEY `job_info_ibfk_3` (`job_company`),
  KEY `job_info_ibfk_2` (`com_id`),
  CONSTRAINT `job_info_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student_info` (`stu_id`),
  CONSTRAINT `job_info_ibfk_2` FOREIGN KEY (`com_id`) REFERENCES `company_info` (`com_id`),
  CONSTRAINT `job_info_ibfk_3` FOREIGN KEY (`job_company`) REFERENCES `company_info` (`com_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_info
-- ----------------------------
INSERT INTO `job_info` VALUES ('1', '山西汾酒', '太原', '销售', '2017-06-08', '6000', '1', '吊死扶伤', '2017-06-07', '1', '1', '1', '0');
INSERT INTO `job_info` VALUES ('3', '中央电视台', '重庆', ' 销售员', '2017-05-31', '6000', '1', '1', '2017-06-11', '1', '51', '2', null);
INSERT INTO `job_info` VALUES ('4', '大疆33', '重庆', ' 销售员', '2017-06-06', '6000', '1', '1', '2017-06-11', '1', '84', '1', null);
INSERT INTO `job_info` VALUES ('5', '大疆33', '重庆', ' 销售员 ', '2017-05-31', '8000', '1', '', '2017-06-11', '1', '84', '2', null);
INSERT INTO `job_info` VALUES ('6', '大疆', '杭州', ' 前台', '2017-06-08', '5000', '1', '嗯嗯', '2017-06-11', '蛋蛋', '9', '17', null);

-- ----------------------------
-- Table structure for relation_info
-- ----------------------------
DROP TABLE IF EXISTS `relation_info`;
CREATE TABLE `relation_info` (
  `user_role_id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `relation_id` int(11) DEFAULT NULL,
  `relation_type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `relation_info_ibfk_1` (`role_id`),
  KEY `relation_info_ibfk_2` (`user_id`),
  CONSTRAINT `relation_info_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`role_id`) ON DELETE CASCADE,
  CONSTRAINT `relation_info_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relation_info
-- ----------------------------
INSERT INTO `relation_info` VALUES ('1', '20', '1', '1', '转化');

-- ----------------------------
-- Table structure for returninfo_info
-- ----------------------------
DROP TABLE IF EXISTS `returninfo_info`;
CREATE TABLE `returninfo_info` (
  `return_id` int(11) NOT NULL AUTO_INCREMENT,
  `return_city` varchar(50) NOT NULL,
  `return_company` varchar(100) DEFAULT NULL,
  `return_post` varchar(30) NOT NULL,
  `return_salary` varchar(10) NOT NULL,
  `return_satisfy` varchar(30) DEFAULT NULL,
  `return_phone` int(11) NOT NULL,
  `return_content` varchar(900) DEFAULT NULL,
  `return_note` varchar(1000) DEFAULT NULL,
  `stu_id` int(11) NOT NULL,
  `return_time` date DEFAULT NULL,
  `return_adder` varchar(30) DEFAULT NULL,
  `return_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`return_id`),
  KEY `stu_id` (`stu_id`),
  CONSTRAINT `returninfo_info_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student_info` (`stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of returninfo_info
-- ----------------------------
INSERT INTO `returninfo_info` VALUES ('4', '上海', '上海柯艾公司', '后台开发', '4500', '', '5821319', '', '', '1', '2017-06-04', '1', null);
INSERT INTO `returninfo_info` VALUES ('5', '啊啊', '啊', '', '', '', '0', '', '', '1', '2017-06-10', '', null);
INSERT INTO `returninfo_info` VALUES ('6', '太原', '恭送i', '', '', '', '0', '', '', '1', '2017-06-11', '', null);

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色表\r\n',
  `auth_ids` varchar(500) DEFAULT NULL,
  `role_delete` int(1) DEFAULT NULL,
  `role_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES ('20', '学生', '', '0', '<p>完善个人信息 + 选择工作</p>\r\n');
INSERT INTO `role_info` VALUES ('22', '企业', '', '0', '<p>完善企业信息 + 查看学生信息</p>\r\n');
INSERT INTO `role_info` VALUES ('28', '管理员', '', '0', '<p>后台操作</p>\r\n');

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `stu_id` int(11) NOT NULL AUTO_INCREMENT,
  `cla_id` int(11) NOT NULL,
  `stu_name` varchar(100) NOT NULL,
  `stu_sex` varchar(10) NOT NULL,
  `stu_native` varchar(50) DEFAULT NULL,
  `stu_address` varchar(50) DEFAULT NULL,
  `stu_email` varchar(50) NOT NULL,
  `stu_phone` varchar(11) NOT NULL,
  `stu_graduation` varchar(50) DEFAULT NULL,
  `stu_major` varchar(50) NOT NULL,
  `stu_into_time` date DEFAULT NULL,
  `stu_state` varchar(50) DEFAULT NULL,
  `stu_check` varchar(50) DEFAULT NULL,
  `stu_note` varchar(100) DEFAULT NULL,
  `stu_adder` varchar(50) DEFAULT NULL,
  `stu_addtime` date DEFAULT NULL,
  `stu_delete` int(4) DEFAULT NULL,
  PRIMARY KEY (`stu_id`),
  KEY `cla_id` (`cla_id`),
  CONSTRAINT `student_info_ibfk_1` FOREIGN KEY (`cla_id`) REFERENCES `classes_info` (`cla_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES ('1', '111', '闫煜瑶', '1', '山西', '山西长治', 'yannyuyao@163.com', '18435186359', '北京交通大学', '软件工程', '2013-09-04', '已审核', '2', '<p>学习刻苦认真，乐于助人，政治思想端正。</p>\r\n', '1', '2017-06-11', '0');
INSERT INTO `student_info` VALUES ('2', '111', '张健', '1', '湖南', '山东潍坊', '807820764@qq.com', '18435184567', '北京交通大学', '软件工程', '2017-05-17', '已审核', '2', '<p>1</p>\r\n工作努力', '1', '2017-06-05', '0');
INSERT INTO `student_info` VALUES ('17', '113', '盐葱葱', '1', '山西', '河北保定', '807820764@qq.com', '18435185635', '北京交通大学', '软件工程', '2017-05-28', null, null, '<p>蛋蛋</p>\r\n', '蛋蛋', '2017-06-11', '0');
INSERT INTO `student_info` VALUES ('20', '111', '祝渊', '0', '山西', '山西太原', '516871704@qq.com', '18465235784', '北京交通大学', '软件工程', '2013-09-11', null, null, '', '', '2017-06-11', '0');
INSERT INTO `student_info` VALUES ('21', '115', '朱峣', '0', '山东', '山西太原', '516871704@qq.com', '18465235784', '北京交通大学', '表演系', '1998-06-11', null, null, '', '', '2017-06-11', '0');
INSERT INTO `student_info` VALUES ('22', '2', '张博', '0', '北京', '河南', '18465924586@163.com', '18465924586', '北京交通大学', '音乐', '2013-09-11', null, null, '', '', '2017-06-11', '0');
INSERT INTO `student_info` VALUES ('23', '114', '闫聪聪', '0', '山西', '河北保定', '18465924586@163.com', '18465235784', '北京交通大学', '软件工程', '2017-06-05', null, null, '', '', '2017-06-12', '0');
INSERT INTO `student_info` VALUES ('24', '2', '耿雪饼', '0', '山西', '江苏徐州', '18465924586@163.com', '18465924586', '北京交通大学', '软件工程', '1998-06-11', null, null, '', '', '2017-06-12', '0');
INSERT INTO `student_info` VALUES ('27', '2', '岳润润', '0', '山西', '河北邢台', '18459486235@163.com', '18459862358', '北京交通大学', '软件工程', '2017-06-06', null, null, '<p>1</p>\r\n', '1', '2017-06-12', '0');

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info` (
  `tea_id` int(11) NOT NULL AUTO_INCREMENT,
  `tea_name` varchar(30) NOT NULL,
  `tea_sex` varchar(30) DEFAULT NULL,
  `tea_school` varchar(30) DEFAULT NULL,
  `tea_tel` varchar(11) DEFAULT NULL,
  `tea_state` varchar(30) DEFAULT NULL,
  `tea_note` varchar(50) DEFAULT NULL,
  `tea_major` varchar(30) DEFAULT NULL,
  `tea_adder` varchar(30) DEFAULT NULL,
  `tea_addtime` date DEFAULT NULL,
  `tea_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------
INSERT INTO `teacher_info` VALUES ('1', '李老师', '1', '中国政法大学', '505421', '在职', '博士', '法律', '闫煜瑶', '2017-04-01', '1');
INSERT INTO `teacher_info` VALUES ('2', '张老师', '0', '山西大学', '18435186666', '在职', '研究生', '电商', '小小', '2017-05-17', '1');
INSERT INTO `teacher_info` VALUES ('3', '王老师', '0', '中科院', '5684259', '在职', '博士', '软件', '闫煜瑶', '2017-05-31', '1');
INSERT INTO `teacher_info` VALUES ('5', '杜老师', '0', null, '13358468794', '在职', null, null, null, null, null);
INSERT INTO `teacher_info` VALUES ('6', '张健老师', null, null, '17744405239', '休假', null, null, null, null, null);
INSERT INTO `teacher_info` VALUES ('7', '闫煜瑶', null, null, '13277777777', '在职', null, null, null, null, null);
INSERT INTO `teacher_info` VALUES ('8', '盐葱葱', null, null, '17744444444', '离职', null, null, null, null, null);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `user_password` varchar(20) DEFAULT NULL,
  `user_type` varchar(10) DEFAULT NULL,
  `user_desc` varchar(200) DEFAULT NULL,
  `user_checkstatus` int(1) DEFAULT NULL,
  `user_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', 'admin', '28', '1', '1', '0');
INSERT INTO `user_info` VALUES ('2', 'jay', 'jay', '20', '1', '1', '1');
INSERT INTO `user_info` VALUES ('3', 'tea', 'tea', '28', '1', '1', '0');
INSERT INTO `user_info` VALUES ('4', '腾讯', 'company', '22', '1', '1', '0');
INSERT INTO `user_info` VALUES ('5', 'dandan', 'dandan', '20', '1', '3', '1');
INSERT INTO `user_info` VALUES ('6', '闫煜瑶', 'yyy12345', '20', '1', '3', '0');
INSERT INTO `user_info` VALUES ('7', 'root', 'root', '20', '1', '3', '1');
INSERT INTO `user_info` VALUES ('8', '丽丽', 'yyy12345', '20', '1', '0', '0');
INSERT INTO `user_info` VALUES ('9', '1314011304', 'yyy12345', '20', '1', '3', '0');
INSERT INTO `user_info` VALUES ('10', '万达', 'company', '22', '1', '1', '0');
INSERT INTO `user_info` VALUES ('11', '林更新', 'yyy12345', '20', '1', '3', '0');
INSERT INTO `user_info` VALUES ('12', 'dong', 'yyy12345', '20', '1', '3', '1');
INSERT INTO `user_info` VALUES ('13', '乔布斯', 'yyy12345', '20', '1', '3', '0');
INSERT INTO `user_info` VALUES ('14', 'C罗', 'yyy12345', '20', '1', '3', '0');
INSERT INTO `user_info` VALUES ('15', 'yyy', 'yyy12345', '20', '1', '3', '0');
