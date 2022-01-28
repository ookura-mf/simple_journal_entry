use simple_journal_entry_db;

create table users
    # いったんすんごいシンプルにユーザを追加してみるよ
(
    id   bigint       not null primary key auto_increment,
    name varchar(255) not null
);