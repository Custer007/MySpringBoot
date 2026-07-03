-- 创建学生表
CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    student_no VARCHAR(20) NOT NULL UNIQUE COMMENT '学生号',
    gender VARCHAR(10) NOT NULL COMMENT '性别',
    class_name VARCHAR(50) NOT NULL COMMENT '班级',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 插入测试数据
INSERT INTO student (name, student_no, gender, class_name) VALUES
('张三', '2024001', '男', '计算机1班'),
('李四', '2024002', '女', '计算机1班'),
('王五', '2024003', '男', '软件工程2班'),
('赵六', '2024004', '女', '软件工程2班');
