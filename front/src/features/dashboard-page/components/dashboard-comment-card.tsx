import { Trash2 } from "lucide-react";
import { PostComment } from "../../common/hooks/use-fetch-post-comments";
import { formatDate } from "../../common/utils/utils";
import { API_URL } from "../../../const/constants";
import { useQueryClient } from "@tanstack/react-query";

function DashboardCommentCard({ comment }: { comment: PostComment }) {
  const queryClient = useQueryClient();

  const onDeleteComment = async (commentId: number) => {
    if (confirm("Czy na pewno chcesz usunąć ten komentarz?")) {
      const res = await fetch(`${API_URL}/comments/${commentId}`, {
        method: "DELETE",
      });
      if (!res.ok) {
        throw new Error("Something went wrong");
      }
      await queryClient.invalidateQueries({ queryKey: ["allComments"] });
      return;
    }
    return;
  };

  return (
    <div
      key={comment.id}
      className="flex items-start justify-between rounded-xl bg-[#c4b49d] p-6"
    >
      <div className="flex-1">
        <p className="mb-2 text-gray-800">{comment.content}</p>
        <div className="text-xs text-gray-500">
          {comment.userName} • {formatDate(comment.createdAt)} • Post ID:{" "}
          {comment.forumPostId} • Comment ID: {comment.id}
        </div>
      </div>
      <button
        onClick={() => onDeleteComment(comment.id)}
        className="ml-4 cursor-pointer rounded-lg bg-red-500 p-2 text-white transition-colors hover:bg-red-600"
        title="Usuń komentarz"
      >
        <Trash2 size={16} />
      </button>
    </div>
  );
}

export default DashboardCommentCard;
