import { useMutation, useQueryClient } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";

type NewComment = {
  content: string;
  forumPostId: number;
  userId: string;
};

const addComment = async (newComment: NewComment) => {
  const response = await fetch(`${API_URL}/comments`, {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      content: newComment.content,
      forumPostId: newComment.forumPostId,
      userId: newComment.userId,
    }),
  });
  if (!response.ok) {
    throw new Error("Failed to add comment");
  }
};

export const useAddComment = (postId: string) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (newComment: NewComment) => addComment(newComment),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["postComments", postId] });
    },
  });
};
