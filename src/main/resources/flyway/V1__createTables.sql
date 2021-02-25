create table accounts (
    id bigint not null auto_increment,
    data varchar(255),
    user_id bigint,
    primary key (id)
) engine=InnoDB;

create table developer_skills (
    developer_id bigint not null,
    skill_id bigint not null,
    primary key (developer_id, skill_id)
) engine=InnoDB;

create table developers (
    id bigint not null auto_increment,
    first_name varchar(100),
    last_name varchar(100),
    specialty varchar(100),
    account_id bigint,
    primary key (id)
) engine=InnoDB;

create table roles (
    id bigint not null auto_increment,
    name varchar(50),
    created datetime(6),
    status varchar(50),
    updated datetime(6),
    primary key (id)
) engine=InnoDB;

create table skills (
    id bigint not null auto_increment,
    name varchar(100),
    primary key (id)
) engine=InnoDB;

create table user_roles (
    user_id bigint not null,
    role_id bigint not null
) engine=InnoDB;

create table users (
    id bigint not null auto_increment,
    user_name varchar(100),
    password varchar(100),
    phone_number varchar(20),
    created datetime(6),
    status varchar(50) default 'APPROVAL_REQUIRED',
    sms varchar(20),
    updated datetime(6),
    primary key (id)
) engine=InnoDB;

alter table accounts add constraint fk_user_account_users foreign key (user_id) references users (id);
alter table developer_skills add constraint fk_developer_skill_developers foreign key (developer_id) references developers (id);
alter table developer_skills add constraint fk_skill_developer_skills foreign key (skill_id) references skills (id);
alter table developers add constraint fk_developer_account_accounts foreign key (account_id) references accounts (id);
alter table user_roles add constraint fk_user_role_roles foreign key (role_id) references roles (id);
alter table user_roles add constraint fk_role_user_users foreign key (user_id) references users (id);