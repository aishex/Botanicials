import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";

export type PostComment = {
  content: string;
  createdAt: Date;
  id: number;
  forumPostId: number;
  userId: number;
  userImageUrl: string;
  userName: string;
};

const fetchPostComments = async (postId: string) => {
  const res = await fetch(`${API_URL}/comments/post/${postId}`);
  if (!res.ok) {
    throw new Error("Failed to fetch comments");
  }
  return res.json();
};

export const useFetchPostComments = (postId: string) => {
  return useQuery<PostComment[]>({
    queryKey: ["postComments", postId],
    queryFn: () => fetchPostComments(postId),
    retry: false,
    refetchOnWindowFocus: false,
    staleTime: 1000 * 60 * 10, // 10 minutes
  });
};
