-- 数据结构创建 sys_user sys_user_role sys_role sys_role_menu sys_menu
-- 创建数据库，如果有则进入
CREATE DATABASE IF NOT EXISTS my_xyz_blog;
USE my_xyz_blog;

-- 创建用户表
CREATE TABLE IF NOT EXISTS `sys_user`
(
    id          bigint auto_increment primary key comment '主键',
    user_name   varchar(64) default 'null' not null comment '用户名',
    nick_name   varchar(64) default 'null' not null comment '昵称',
    password    varchar(64) default 'null' not null comment '密码',
    type        tinyint     default 0    null comment '用户类型：0代表普通用户，1代表管理员',
    status      tinyint     default 0    null comment '账号状态（0正常 1停用）',
    email       varchar(64)                null comment '邮箱',
    phone_number varchar(32)               null comment '手机号',
    gender      tinyint                    null comment '用户性别（0男，1女，2未知）',
    avatar      varchar(128)               null comment '头像',
    create_by   bigint                     null comment '创建人的用户id',
    create_time datetime                   null comment '创建时间',
    update_by   bigint                     null comment '更新人',
    update_time datetime                   null comment '更新时间',
    del_flag    tinyint     default 0      null comment '删除标志（0代表未删除，1代表已删除）'
) ENGINE=INNODB DEFAULT CHARSET=utf8       comment '用户表';
-- 用户数据 密码由BCryptPasswordEncoder加密后存储
INSERT INTO sys_user (id,user_name, nick_name, password, type, status, email, phone_number, gender, avatar, create_by, create_time, update_by, update_time, del_flag)
VALUES
    (1,'root', 'admin', '$2a$10$s.Ftv92SfIsRBMhFKCKLKuW4HYSMzo6eIUZWCNnqMJer2vFZAYd86', 0, 0, 'root@example.com', '1234567890', 0, 'root.jpg', 1, '2023-05-01 09:00:00', 1, '2023-05-01 09:00:00', 0),
    (2,'test', 'ordinary', '$2a$10$s.Ftv92SfIsRBMhFKCKLKuW4HYSMzo6eIUZWCNnqMJer2vFZAYd86', 0, 0, 'test@example.com', '0987654321', 1, 'test.jpg', 2, '2023-05-02 10:00:00', 2, '2023-05-02 10:00:00', 0);

-- 创建 用户-角色关联表
CREATE TABLE IF NOT EXISTS `sys_user_role`
(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    primary key (user_id, role_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '用户和角色关联表';
-- 用户-角色关联数据
INSERT INTO sys_user_role (user_id,role_id)
VALUES (1,1),(2,2);

-- 创建角色表
CREATE TABLE IF NOT EXISTS `sys_role`
(
    id          bigint auto_increment primary key comment '角色id',
    role_name   varchar(30)      not null comment '角色名称',
    role_key    varchar(100)     not null comment '角色权限字符串',
    role_sort   int              not null comment '显示顺序',
    status      tinyint          not null comment '角色状态（0正常 1停用）',
    remark      varchar(500)     null comment '备注',
    create_by   bigint           null comment '创建者',
    create_time datetime         null comment '创建时间',
    update_by   bigint           null comment '更新者',
    update_time datetime         null comment '更新时间',
    del_flag    tinyint default 0 null comment '删除标志（0代表存在 1代表删除）'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '角色信息表';
-- 角色数据
INSERT INTO sys_role (id,role_name, role_key, role_sort, status, create_by, create_time, update_by, update_time, remark, del_flag)
VALUES (1,'管理员', 'admin', 1, 0, 1,'2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', '超级管理员', 0),
       (2,'普通用户', 'ordinary ', 1, 0, 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', '普通角色', 0);


-- 创建 角色-菜单/权限关联表
CREATE TABLE IF NOT EXISTS `sys_role_menu`
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID',
    primary key (role_id, menu_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '角色和菜单关联表';
-- 角色-菜单关联数据 root用户拥有所有权限，test用户仅查询权限
INSERT INTO sys_role_menu (role_id,menu_id)
VALUES (1,1),(1,101),(1,102),(1,103),
       (1,1001),(1,1002),(1,1003),(1,1004),(1,1005),(1,1006),(1,1007),(1,1008),
       (1,1009),(1,1010),(1,1011),(1,1012),(1,1013),(1,1014),(1,1015),(1,1016),
       (2,1),(2,101),(2,102),(2,103),
       (2,1001),(2,1008),(2,1013);

-- 创建菜单/权限表
CREATE TABLE IF NOT EXISTS `sys_menu`
(
    id          bigint auto_increment comment '菜单ID'
        primary key,
    menu_name   varchar(50)              not null comment '菜单名称',
    parent_id   bigint       default 0   null comment '父菜单ID',
    order_num   int          default 0   null comment '显示顺序',
    path        varchar(200) default ''  null comment '路由地址',
    component   varchar(255)             null comment '组件路径',
    menu_type   char         default ''  null comment '菜单类型（M目录 C菜单 F按钮）',
    visible     tinyint      default 0   null comment '菜单状态（0显示 1隐藏）',
    status      tinyint      default 0   null comment '菜单状态（0正常 1停用）',
    perms       varchar(100)             null comment '权限标识',
    icon        varchar(100) default '#' null comment '菜单图标',
    remark      varchar(500) default ''  null comment '备注',
    create_by   bigint                   null comment '创建者',
    create_time datetime                 null comment '创建时间',
    update_by   bigint                   null comment '更新者',
    update_time datetime                 null comment '更新时间',
    del_flag    tinyint default 0 null comment '删除标志（0代表存在 1代表删除）'
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '菜单权限表';
-- 权限数据
INSERT INTO sys_menu (id,menu_name, parent_id, order_num, path, component, menu_type, visible, status, perms, icon, remark, create_by, create_time, update_by, update_time, del_flag) VALUES
    (1,'系统管理', 0, 1, 'system', '', 'M', 0, 0, 'system:system', 'Setting', '系统管理目录', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (101,'用户管理', 1, 1, 'user', 'system/user/index', 'C', 0, 0, 'system:user:list', 'User', '用户管理目录', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (102,'角色管理', 1, 2, 'role', 'system/role/index', 'C', 0, 0, 'system:role:list', 'Avatar', '角色管理目录', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (103,'菜单管理', 1, 3, 'menu', 'system/menu/index', 'C', 0, 0, 'system:menu:list', 'Menu', '菜单管理目录', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1001,'用户查询', 101, 1, '', '', 'F', 0, 0, 'system:user:query', '', '用户查询权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1002,'用户新增', 101, 2, '', '', 'F', 0, 0, 'system:user:add', '', '用户新增权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1003,'用户修改', 101, 3, '', '', 'F', 0, 0, 'system:user:edit', '', '用户修改权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1004,'用户删除', 101, 4, '', '', 'F', 0, 0, 'system:user:remove', '', '用户删除权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1005,'用户导出', 101, 5, '', '', 'F', 0, 0, 'system:user:export', '', '用户导出权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1006,'用户导入', 101, 6, '', '', 'F', 0, 0, 'system:user:import', '', '用户导入权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1007,'重置密码', 101, 7, '', '', 'F', 0, 0, 'system:user:resetPwd', '', '用户重置密码权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1008,'角色查询', 102, 1, '', '', 'F', 0, 0, 'system:role:query', '', '角色查询权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1009,'角色新增', 102, 2, '', '', 'F', 0, 0, 'system:role:add', '', '角色新增权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1010,'角色修改', 102, 3, '', '', 'F', 0, 0, 'system:role:edit', '', '角色修改权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1011,'角色删除', 102, 4, '', '', 'F', 0, 0, 'system:role:remove', '', '角色删除权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1012,'角色导出', 102, 5, '', '', 'F', 0, 0, 'system:role:export', '', '角色导出权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1013,'菜单查询', 103, 1, '', '', 'F', 0, 0, 'system:menu:query', '', '菜单查询权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1014,'菜单新增', 103, 2, '', '', 'F', 0, 0, 'system:menu:add', '', '菜单新增权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1015,'菜单修改', 103, 3, '', '', 'F', 0, 0, 'system:menu:edit', '', '菜单修改权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0),
    (1016,'菜单删除', 103, 4, '', '', 'F', 0, 0, 'system:menu:remove', '', '菜单删除权限', 1, '2023-05-23 10:00:00', 1, '2023-05-23 10:00:00', 0);
