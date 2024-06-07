-- タスク管理システムDB
-- DB名：task_management
-- ユーザー名：root
-- パスワード：tkznemou19
-- テーブル：USER, TASK


-- DB新規作成（DB構築時のみ）
-- CREATE DATABASE task_management DEFAULT CHARACTER SET utf8;


-- 旧テーブル削除
DROP TABLE USER;
DROP TABLE TASK;

-- USERテーブル
CREATE TABLE USER(
    USERID int         AUTO_INCREMENT PRIMARY KEY COMMENT 'ユーザーID',
    USERNM varchar(32) NOT NULL UNIQUE COMMENT 'ユーザー名',
    PASSWD varchar(32) COMMENT 'パスワード',
    RGDTTM timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時'
);

-- TASKテーブル
CREATE TABLE TASK(
    TASKID int           AUTO_INCREMENT PRIMARY KEY COMMENT 'タスクID',
    USERID int           NOT NULL COMMENT 'ユーザーID',
    TASKNM varchar(100)  NOT NULL COMMENT 'タスク名',
    TASKDT varchar(8000) NOT NULL COMMENT 'タスク詳細',
    TSKSTT varchar(100)  NOT NULL COMMENT 'タスク状況',
    RGDTTM timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登録日時',
    UPDTTM timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時'
);
