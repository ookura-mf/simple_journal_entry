use simple_journal_entry_db;

create table journals
(
    id          bigint not null primary key auto_increment,
    incurred_on DATE   not null
);