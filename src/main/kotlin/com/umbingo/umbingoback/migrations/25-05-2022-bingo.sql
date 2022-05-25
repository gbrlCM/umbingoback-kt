CREATE TABLE IF NOT EXISTS public.bingo
(
    id uuid NOT NULL,
    name text COLLATE pg_catalog."default" NOT NULL,
    emoji text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
    words text[] COLLATE pg_catalog."default" NOT NULL,
    theme text NOT NULL,
    creator_id text COLLATE pg_catalog."default" NOT NULL,
    match_count bigint,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    CONSTRAINT bingo_pkey PRIMARY KEY (id)
    )