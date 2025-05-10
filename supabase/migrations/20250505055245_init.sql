CREATE TABLE IF NOT EXISTS public.profiles (
  id uuid PRIMARY KEY REFERENCES auth.users (id) ON DELETE CASCADE,
  username text,
  email text,
  created_at timestamp with time zone DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS public.device (
  id uuid DEFAULT gen_random_uuid() NOT NULL,
  created_at timestamp with time zone DEFAULT now() NOT NULL,
  last_login timestamp without time zone DEFAULT now(),
  user_id uuid,
  info text,
  CONSTRAINT device_pkey PRIMARY KEY (id),
  CONSTRAINT device_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.profiles (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.journal (
  id uuid DEFAULT gen_random_uuid() NOT NULL,
  created_at timestamp with time zone DEFAULT now() NOT NULL,
  user_id uuid,
  title text,
  CONSTRAINT journal_pkey PRIMARY KEY (id),
  CONSTRAINT journal_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.profiles (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.entry (
  id uuid DEFAULT gen_random_uuid() NOT NULL,
  created_at timestamp with time zone DEFAULT now() NOT NULL,
  content text,
  journal_id uuid,
  CONSTRAINT entry_pkey PRIMARY KEY (id),
  CONSTRAINT entry_journal_id_fkey FOREIGN KEY (journal_id)
    REFERENCES public.journal (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.feedback (
  id uuid DEFAULT gen_random_uuid() NOT NULL,
  created_at timestamp with time zone DEFAULT now() NOT NULL,
  entry_id uuid,
  content text,
  CONSTRAINT feedback_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.mood (
  id uuid DEFAULT gen_random_uuid() NOT NULL,
  created_at timestamp with time zone DEFAULT now() NOT NULL,
  journal_id uuid,
  score smallint,
  CONSTRAINT mood_pkey PRIMARY KEY (id),
  CONSTRAINT mood_journal_id_fkey FOREIGN KEY (journal_id)
    REFERENCES public.journal (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.streak (
  id uuid DEFAULT gen_random_uuid() NOT NULL,
  created_at timestamp with time zone DEFAULT now() NOT NULL,
  mood_id uuid,
  count smallint,
  CONSTRAINT streak_pkey PRIMARY KEY (id),
  CONSTRAINT streak_mood_id_fkey FOREIGN KEY (mood_id)
    REFERENCES public.mood (id) ON DELETE CASCADE
);
