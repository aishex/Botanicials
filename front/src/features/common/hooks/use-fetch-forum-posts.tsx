import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";

export type ForumPost = {
  id: number;
  title: string;
  content: string;
  userId: number;
  createdAt: Date;
  imageUrl?: string;
};

const fetchForumPosts = async () => {
  const response = await fetch(`${API_URL}/posts`);
  if (!response.ok) {
    throw new Error("Failed to fetch forum posts");
  }
  return response.json();
};

export const useFetchForumPosts = () => {
  return useQuery<ForumPost[]>({
    queryKey: ["forumPosts"],
    queryFn: fetchForumPosts,
    retry: false,
    staleTime: 1000 * 60 * 10, // 10 minutes
  });
};
