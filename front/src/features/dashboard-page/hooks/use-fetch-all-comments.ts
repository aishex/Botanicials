import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";
import { PostComment } from "../../common/hooks/use-fetch-post-comments";

const fetchAllComments = async () => {
  const response = await fetch(`${API_URL}/comments`);
  if (!response.ok) {
    throw new Error("Network response was not ok");
  }
  return response.json();
};

export const useFetchAllComments = () => {
  return useQuery<PostComment[]>({
    queryKey: ["allComments"],
    queryFn: fetchAllComments,
    refetchOnWindowFocus: false,
    refetchOnReconnect: false,
    retry: false,
    staleTime: 1000 * 60 * 10, // 10 minutes
  });
};
