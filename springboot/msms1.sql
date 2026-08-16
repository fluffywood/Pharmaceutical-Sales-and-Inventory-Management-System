DROP DATABASE IF EXISTS `msms1`;
CREATE DATABASE `msms1`;
use `msms1`;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`username` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '账号',
`password` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '密码',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '名称',
`avatar` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '头像',
`role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'admin' COMMENT '角色标识',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='管理员表';

INSERT INTO `admin` (username, password, name, avatar, role) VALUES ('admin', '123456', '管理员', 'http://localhost:9090/files/qy-default.png', 'admin');

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`employee_code` int(10) DEFAULT 0 COMMENT '员工编号',
`username` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '账号',
`password` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '密码',
`name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '名称',
`avatar` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '头像',
`phone` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '手机',
`email` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '邮箱',
`role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'employee' COMMENT '角色标识',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='员工表';


DROP TABLE IF EXISTS `employeeinfo`;
CREATE TABLE `employeeinfo` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`employee_id` int(10) DEFAULT NULL COMMENT '员工ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='员工信息表';


DROP TABLE IF EXISTS `customerinfo`;
CREATE TABLE `customerinfo` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`customer_name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '客户姓名',
`customer_sex` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '客户性别',
`customer_birth` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '客户生日',
`customer_phone` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '联系方式',
`customer_idcard` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '身份证号',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='客户信息表';


DROP TABLE IF EXISTS `supplierinfo`;
CREATE TABLE `supplierinfo` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`supplier_name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '供应商名称',
`supplier_address` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '地址',
`supplier_legal` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '法人代表',
`supplier_email` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '电子邮件',
`supplier_remark` text COMMENT '备注',
`customerinfo_id` int(10) DEFAULT NULL COMMENT '客户信息ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='供应商信息表';


DROP TABLE IF EXISTS `druginfo`;
CREATE TABLE `druginfo` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`drug_code` int(10) DEFAULT 0 COMMENT '药品编号',
`drug_name` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '药品名称',
`drug_script` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '药品性质',
`saleinfo_id` int(10) DEFAULT NULL COMMENT '销售信息ID',
`supplierinfo_id` int(10) DEFAULT NULL COMMENT '供应商信息ID',
`purchaseinfo_id` int(10) DEFAULT NULL COMMENT '进货信息ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='药品信息表';


DROP TABLE IF EXISTS `purchaseinfo`;
CREATE TABLE `purchaseinfo` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`supply_price` double(10,2) DEFAULT 0 COMMENT '供应价格',
`supply_num` int(10) DEFAULT 0 COMMENT '进货数量',
`drug_date` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '保质期',
`purchase_time` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '进货时间',
`purchase_transport` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '进货物流',
`supplierinfo_id` int(10) DEFAULT NULL COMMENT '供应商信息ID',
`employeeinfo_id` int(10) DEFAULT NULL COMMENT '员工信息ID',
`druginfo_id` int(10) DEFAULT NULL COMMENT '药品信息ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='进货信息表';


DROP TABLE IF EXISTS `purchase_reporter`;
CREATE TABLE `purchase_reporter` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`purchase_report` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '进货报表提交',
`purchase_pic` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '进货报表截图',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='进货报表表';


DROP TABLE IF EXISTS `saleinfo`;
CREATE TABLE `saleinfo` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`sale_price` double(10,2) DEFAULT 0 COMMENT '售价',
`sale_num` int(10) DEFAULT 0 COMMENT '销售数量',
`return_apply` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '退货申请',
`return_pos` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '操作状态',
`return_transport` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '退货物流',
`druginfo_id` int(10) DEFAULT NULL COMMENT '药品信息ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='销售信息表';


DROP TABLE IF EXISTS `sale_reporter`;
CREATE TABLE `sale_reporter` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`sale_report` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '销售报表提交',
`sale_pic` varchar(255) COLLATE utf8mb4_unicode_ci COMMENT '销售报表截图',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='销售报表表';


DROP TABLE IF EXISTS `inventoryinfo`;
CREATE TABLE `inventoryinfo` (
`id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`drug_num` int(10) DEFAULT 0 COMMENT '库存信息',
`saleinfo_id` int(10) DEFAULT NULL COMMENT '销售信息ID',
`purchaseinfo_id` int(10) DEFAULT NULL COMMENT '进货信息ID',
`druginfo_id` int(10) DEFAULT NULL COMMENT '药品信息ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='库房管理表';


