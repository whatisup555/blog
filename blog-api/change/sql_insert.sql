
SET FOREIGN_KEY_CHECKS=0;
-- Insert test data into `user`
INSERT INTO `user` (`username`, `password`, `user_type`, `real_name`, `id_type`, `id_number`, `phone_number`, `user_level`, `user_intro`, `register_city`, `register_time`, `modify_time`) 
VALUES 
('admin', 'admin', '系统管理员', 'Admin', '身份证', '123456789012345678', '13800000000', '一般', '系统管理员', '北京', NOW(), NOW()),
('user1', 'password1', '普通用户', 'User1', '身份证', '123456789012345679', '13800000001', 'VIP', '普通用户1', '上海', NOW(), NOW());

-- Insert test data into `request_info`
INSERT INTO `request_info` (`user_id`, `destination_type`, `request_title`, `request_description`, `max_price`, `request_end_date`, `create_time`, `modify_time`, `status`) 
VALUES 
(1, '钓鱼', '钓鱼之旅', '寻找一个好的钓鱼地点', 500.00, '2022-12-31', NOW(), NOW(), '待响应'),
(2, '农家院', '农家乐', '寻找一个好的农家乐地点', 1000.00, '2022-12-31', NOW(), NOW(), '待响应');

-- Insert test data into `response_info`
INSERT INTO `response_info` (`request_id`, `user_id`, `response_description`, `create_time`, `modify_time`, `status`) 
VALUES 
(1, 2, '我知道一个好的钓鱼地点', NOW(), NOW(), '0'),
(2, 1, '我知道一个好的农家乐地点', NOW(), NOW(), '0');

-- Insert test data into `request_detail`
INSERT INTO `request_detail` (`request_id`, `publisher_id`, `responder_id`, `achieve_date`, `publisher_fee`, `responder_fee`) 
VALUES 
(1, 1, 2, '2022-01-01', 2.00, 2.00),
(2, 2, 1, '2022-01-02', 2.00, 2.00);

-- Insert test data into `income_summary`
INSERT INTO `income_summary` (`month`, `region`, `destination_type`, `achieve_count`, `income`) 
VALUES 
('202201', '北京', '钓鱼', 1, 4.00),
('202201', '上海', '农家院', 1, 4.00);