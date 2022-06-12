use simple_journal_entry_db;

create table accounts (
                       id bigint not null primary key auto_increment,
                       code varchar(50) not null unique,
                       name varchar(50) not null,
                       element_type enum('ASSETS', 'LIABILITIES', 'NET_ASSETS', 'EXPENSES', 'REVENUE', 'NET_INCOME') not null
);