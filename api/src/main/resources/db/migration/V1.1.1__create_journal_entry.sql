use simple_journal_entry_db;

create table journal_entries
(
    id         bigint  not null primary key auto_increment,
    journal_id bigint  not null,
    side       tinyint not null,
    account_id bigint  not null,
    value      int     not null,
    foreign key fk_journal (journal_id) references journals (id),
    foreign key fk_account (account_id) references accounts (id)
);