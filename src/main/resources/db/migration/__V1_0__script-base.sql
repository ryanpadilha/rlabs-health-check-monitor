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

alter table vl_developer add constraint fk_developer_organization foreign key (organization_id) references vl_organization(id);

-- table: product
create table vl_product (
	id bigserial not null primary key,
	internal uuid not null unique default uuid_generate_v4(),
	created timestamp not null default now(),
	active boolean not null default true,
	name text not null,
	description text not null,
	artifact_id text not null unique,
	environment text not null,
	version text not null,
	hostname text not null,
	project_repository text not null,
	project_page text,
	organization_id bigint not null
);

alter table vl_product add constraint fk_product_organization foreign key (organization_id) references vl_organization(id);

-- table: dependency
create table vl_dependency (
	id bigserial not null primary key,
	internal uuid not null unique default uuid_generate_v4(),
	created timestamp not null default now(),
	name text not null,
	type text not null,
	version text not null,
	hostname text not null,
	complement text
);

-- table: product_dependency
create table vl_product_dependency (
	product_id bigint not null,
	dependency_id bigint not null
);

alter table vl_product_dependency add constraint pk_product_dependency primary key (product_id, dependency_id);
alter table vl_product_dependency add constraint fk_product foreign key (product_id) references vl_product(id);
alter table vl_product_dependency add constraint fk_dependency foreign key (dependency_id) references vl_dependency(id);

-- table: vl_status_details
create table vl_status_details (
	id bigserial not null primary key,
	internal uuid not null unique default uuid_generate_v4(),
	created timestamp not null default now(),
	description text not null,
	result text not null,
	details text,
	dependency_id bigint not null
);

alter table vl_status_details add constraint fk_dependency_status foreign key (dependency_id) references vl_dependency(id);



