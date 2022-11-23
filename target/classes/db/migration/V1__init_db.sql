CREATE SCHEMA IF NOT EXISTS "main";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "main"."maisUmTeste"(
    "id" INT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS "main"."category" (
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(50) UNIQUE NOT NULL,
    "created_at" TIMESTAMP DEFAULT current_timestamp(),
    "updated_at" TIMESTAMP DEFAULT current_timestamp() ON UPDATE current_timestamp()
);

CREATE TABLE IF NOT EXISTS "main"."spent"(
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(50) NOT NULL,
    "date" TIMESTAMP DEFAULT current_timestamp(),
    "description" VARCHAR(100) NOT NULL DEFAULT "No description",
    "value" NUMERIC NOT NULL,
    "category_id" UUID NOT NULL,
    "user_id" UUID NOT NULL,
    "created_at" TIMESTAMP DEFAULT current_timestamp(),
    "updated_at" TIMESTAMP DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    FOREIGN KEY ("category_id") REFERENCES "category",
    FOREIGN KEY ("user_id") REFERENCES "user"
);

CREATE TABLE IF NOT EXISTS "main"."user"(
    "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(50) NOT NULL,
    "login" VARCHAR(50) NOT NULL,
    "password" VARCHAR(128) NOT NULL,
    "phone" VARCHAR(20) NOT NULL,
    "birthdate" DATE NOT NULL,
    "created_at" TIMESTAMP DEFAULT current_timestamp(),
    "updated_at" TIMESTAMP DEFAULT current_timestamp() ON UPDATE current_timestamp()
);