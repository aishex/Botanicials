import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";

const fetchPostInfo = async (postId: string) => {
  const res = await fetch(`${API_URL}/comments/${postId}`);
  if (!res.ok) {
    throw new Error("Failed to fetch post information");
  }
  return res.json();
};

export const useFetchForumPost = (postId: string) => {
  return useQuery({
    queryKey: ["forumPost", postId],
    queryFn: () => fetchPostInfo(postId),
    retry: false,
    refetchOnWindowFocus: false,
    staleTime: 1000 * 60 * 10, // 10 minutes
  });
};
