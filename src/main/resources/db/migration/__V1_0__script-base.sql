---
--- PostgreSQL Database (UTF-8)
---

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- table: organization
create table vl_organization (
	id bigserial not null primary key,
	internal uuid not null unique default uuid_generate_v4(),
	created timestamp not null default now(),
	active boolean not null default true,
	name text not null,
	alias text not null unique
);

-- table: developer
create table vl_developer (
	id bigserial not null primary key,
	internal uuid not null unique default uuid_generate_v4(),
	created timestamp not null default now(),
	active boolean not null default true,
	name text not null,
	email text not null unique,
	organization_id bigint not null
);

alter table vl_developer add constraint fk_organization_developer foreign key (organization_id) references vl_organization(id);


-- table: product
create table vl_product (
	id bigserial not null primary key,
	internal uuid not null unique default uuid_generate_v4(),
	created timestamp not null default now(),
	active boolean not null default true,
	

);
