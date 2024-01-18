use simple_journal_entry_db;

create table users (
    id bigint not null primary key auto_increment,
    name varchar(50) not null
);
