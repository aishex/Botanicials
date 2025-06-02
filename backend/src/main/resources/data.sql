-- test user
INSERT INTO users (id, email, name, google_id, image_url)
VALUES (50, 'test@example.com', 'Test User', '12345', 'https://ipicasso.pl/image/cache/data/goods/00000004867-800x800.jpg')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, email, name, google_id, image_url)
VALUES (51, 'jane.doe@example.com', 'Jane Doe', '67890', 'https://plus.unsplash.com/premium_photo-1671656349322-41de944d259b?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, email, name, google_id, image_url)
VALUES (52, 'bob.smith@example.com', 'Bob Smith', '11223', 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')
ON CONFLICT (id) DO NOTHING;

-- post
INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  1,
  50,
  'Jak podlewać monsterę?',
  'Mam problem z żółknięciem liści. Co robić?',
  'https://v.wpimg.pl/ZTMzMjc0dTUsGDtZTEt4IG9AbwMKEnZ2OFh3SEwGamR9AnlaUwB1MiQWKBgGQDV6Kgg4GgJHKno9FmILE1l1InxVKQMQQDY1NFUoBwFVPnt8GHVaBwlpZ2AfeAsFHW5nek9gU1EFY3l8SStSVlVpZ39NeQ9BTQ',
  NOW()
)
ON CONFLICT (id) DO NOTHING;

-- Additional Posts
INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  2,
  51,
  'Najlepsze doniczki do sukulentów',
  'Jakie doniczki polecacie do małych sukulentów? Chodzi mi o materiał i wielkość.',
  'https://images.unsplash.com/photo-1581879011839-713b3c49122c?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  NOW()
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  3,
  52,
  'Pomocy! Moja paprotka usycha.',
  'Liście mojej paprotki robią się brązowe i suche na końcach. Stoi w półcieniu, podlewam regularnie.',
  'https://plus.unsplash.com/premium_photo-1673292293042-cafd9c8a3ab3?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  NOW()
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  4,
  50,
  'Jakie rośliny do ciemnego mieszkania?',
  'Szukam roślin, które poradzą sobie przy małej ilości światła. Macie jakieś propozycje?',
  'https://images.unsplash.com/photo-1618706548227-e840a64ccc04?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  NOW()
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  5,
  51,
  'DIY nawóz do roślin doniczkowych',
  'Czy ktoś ma sprawdzone przepisy na domowe nawozy? Chciałabym spróbować czegoś naturalnego.',
  'https://images.unsplash.com/photo-1531875456634-3f5418280d20?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  NOW()
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  6,
  52,
  'Problem z mszycami na hibiskusie',
  'Na moim hibiskusie pojawiły się mszyce. Jak się ich skutecznie pozbyć?',
  'https://plus.unsplash.com/premium_photo-1669869250186-37a3f06180ce?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  NOW()
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  7,
  50,
  'Kiedy przesadzać storczyki?',
  'Mam kilka storczyków i zastanawiam się, kiedy jest najlepszy moment na ich przesadzenie.',
  'https://images.unsplash.com/photo-1463320898484-cdee8141c787?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  NOW()
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  8,
  51,
  'Rośliny oczyszczające powietrze - TOP 5',
  'Podzielcie się swoimi ulubionymi roślinami, które dobrze oczyszczają powietrze w domu!',
  'https://images.unsplash.com/photo-1601985705806-5b9a71f6004f?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  NOW()
)
ON CONFLICT (id) DO NOTHING;

INSERT INTO forum_post (id, user_id, title, content, image_url, created_at)
VALUES (
  9,
  52,
  'Jak ukorzenić gałązkę figowca?',
  'Dostałem gałązkę figowca i chciałbym ją ukorzenić. Jakieś wskazówki?',
  'https://images.unsplash.com/photo-1470058869958-2a77ade41c02?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
  NOW()
)
ON CONFLICT (id) DO NOTHING;


--comment
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (1, 1, 50, 'Spróbuj podlewać raz na 10 dni i nie przestawiaj jej.', NOW())
ON CONFLICT (id) DO NOTHING;

-- Additional Comments
-- Comments for post 2
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (2, 2, 50, 'Najlepsze są terakotowe, bo dobrze oddychają.', NOW())
ON CONFLICT (id) DO NOTHING;
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (3, 2, 52, 'Zgadzam się, terakota super. I pamiętaj o otworach drenażowych!', NOW())
ON CONFLICT (id) DO NOTHING;

-- Comments for post 3
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (4, 3, 50, 'Może ma za sucho? Paprotki lubią wilgoć. Spróbuj zraszać.', NOW())
ON CONFLICT (id) DO NOTHING;

-- Comments for post 4
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (5, 4, 51, 'Zamiokulkas jest nie do zdarcia!', NOW())
ON CONFLICT (id) DO NOTHING;
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (6, 4, 52, 'Sansewieria też da radę.', NOW())
ON CONFLICT (id) DO NOTHING;
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (7, 4, 50, 'A skrzydłokwiat? Podobno dobrze znosi cień.', NOW())
ON CONFLICT (id) DO NOTHING;

-- Comments for post 5 (no comments)

-- Comments for post 6
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (8, 6, 50, 'Spróbuj roztworu wody z szarym mydłem.', NOW())
ON CONFLICT (id) DO NOTHING;
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (9, 6, 51, 'Ja używam olejku neem, działa cuda.', NOW())
ON CONFLICT (id) DO NOTHING;

-- Comments for post 7
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (10, 7, 52, 'Najlepiej po kwitnieniu, jak korzenie zaczynają wyrastać z doniczki.', NOW())
ON CONFLICT (id) DO NOTHING;

-- Comments for post 8
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (11, 8, 50, 'Skrzydłokwiat, sansewieria, epipremnum, zielistka, dracena.', NOW())
ON CONFLICT (id) DO NOTHING;
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (12, 8, 52, 'Dokładnie! Moje top 5.', NOW())
ON CONFLICT (id) DO NOTHING;

-- Comments for post 9
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (13, 9, 51, 'Włóż do wody z węglem aktywowanym albo bezpośrednio do wilgotnej ziemi.', NOW())
ON CONFLICT (id) DO NOTHING;
