use simple_journal_entry_db;

create table accounts (
                       id int not null primary key auto_increment,
                       code varchar(50) not null unique,
                       name varchar(50),
                       element_type int not null
);