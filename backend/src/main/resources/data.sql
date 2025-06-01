-- test user
INSERT INTO users (id, email, name, google_id, image_url)
VALUES (50, 'test@example.com', 'Test User', '12345', 'https://ipicasso.pl/image/cache/data/goods/00000004867-800x800.jpg')
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

-- comment
INSERT INTO forum_comments (id, forum_post_id, user_id, content, created_at)
VALUES (1, 1, 50, 'Spróbuj podlewać raz na 10 dni i nie przestawiaj jej.', NOW())
ON CONFLICT (id) DO NOTHING;
