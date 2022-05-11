DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users (
	uuid uuid NOT NULL,
	email varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	"role" varchar(255) NOT NULL,
	CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (uuid)
);
