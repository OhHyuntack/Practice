CREATE TABLE `fileinfo` (
  `file_seq` int(11) NOT NULL AUTO_INCREMENT,
  `original_file_name` varchar(200) DEFAULT NULL,
  `stored_file_name` varchar(200) DEFAULT NULL,
  `board_seq` int(11) DEFAULT NULL,
  `file_size` varchar(100) DEFAULT NULL,
  `file_ext` varchar(100) DEFAULT NULL,
  `use_yn` varchar(3) DEFAULT NULL,
  `reg_id` varchar(50) DEFAULT NULL,
  `file_path` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`file_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4

CREATE TABLE `imagefile` (
  `image_file_seq` int(11) NOT NULL AUTO_INCREMENT,
  `original_file_name` varchar(200) DEFAULT NULL,
  `stored_file_name` varchar(200) DEFAULT NULL,
  `file_size` varchar(100) DEFAULT NULL,
  `file_ext` varchar(100) DEFAULT NULL,
  `use_yn` varchar(3) DEFAULT NULL,
  `reg_id` varchar(50) DEFAULT NULL,
  `file_path` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`file_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

CREATE TABLE `board` (
  `board_seq` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `read_cnt` int(11) DEFAULT NULL,
  `writer` varchar(50) DEFAULT NULL,
  `boardPW` varchar(50) DEFAULT NULL,
  `regdate` timestamp NULL DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `contact` varchar(30) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  `board_type` varchar(50) DEFAULT NULL,
  `is_del` varchar(2) DEFAULT 'N',
  `del_date` date DEFAULT NULL,
  PRIMARY KEY (`board_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4

CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  `mobile_no` varchar(50) DEFAULT NULL,
  `userPW` varchar(200) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modified_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4


CREATE TABLE `schedule` (
  `schedule_seq` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `description` varchar(900) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `text_color` varchar(10) DEFAULT NULL,
  `regdate` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_id` varchar(50) DEFAULT NULL,
  `is_del` varchar(2) DEFAULT 'N',
  `del_date` datetime DEFAULT NULL,
  `background_color` varchar(10) DEFAULT NULL,
  `all_day` varchar(10) DEFAULT 'false',
  PRIMARY KEY (`schedule_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4