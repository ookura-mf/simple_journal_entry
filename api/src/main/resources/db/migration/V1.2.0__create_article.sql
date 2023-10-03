use simple_journal_entry_db;

create table articles (
    id bigint not null primary key auto_increment,
    account_id bigint not null,
    title varchar(50) not null,
    content text not null,
    created_at DATE not null,
    updated_at DATE not null,

    FOREIGN KEY (account_id) REFERENCES articles(id)
);