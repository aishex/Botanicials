import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";
import { ForumPost } from "./use-fetch-forum-posts";

export type ExtendedForumPost = ForumPost & { userImageUrl: string };

const fetchPostInfo = async (postId: string) => {
  const res = await fetch(`${API_URL}/posts/${postId}`);
  if (!res.ok) {
    throw new Error("Failed to fetch post information");
  }
  return res.json();
};

export const useFetchForumPost = (postId: string) => {
  return useQuery<ExtendedForumPost>({
    queryKey: ["forumPost", postId],
    queryFn: () => fetchPostInfo(postId),
    retry: false,
    refetchOnWindowFocus: false,
    staleTime: 1000 * 60 * 10, // 10 minutes
  });
};
