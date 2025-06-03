import { Trash2 } from "lucide-react";
import { type ForumPost } from "../../common/hooks/use-fetch-forum-posts";
import { formatDate, truncateContent } from "../../common/utils/utils";
import { API_URL } from "../../../const/constants";
import { useQueryClient } from "@tanstack/react-query";

function DashboardPostCard({ post }: { post: ForumPost }) {
  const queryClient = useQueryClient();

  const onDeletePost = async (postId: number) => {
    if (confirm("Czy na pewno chcesz usunąć ten post?")) {
      const res = await fetch(`${API_URL}/posts/${postId}`, {
        method: "DELETE",
      });
      if (!res.ok) {
        throw new Error("Something went wrong");
      }
      await queryClient.invalidateQueries({
        predicate: (query) =>
          query.queryKey[0] === "forumPosts" ||
          query.queryKey[0] === "allComments",
      });
      return;
    }
  };

  return (
    <div className="flex items-start justify-between rounded-xl bg-[#c4b49d] p-6">
      <div className="flex-1">
        <div className="flex items-start gap-4">
          {post.imageUrl && (
            <img
              src={post.imageUrl || "/placeholder.svg"}
              alt={post.title}
              className="h-16 w-16 rounded-lg object-cover"
              crossOrigin="anonymous"
            />
          )}
          <div className="flex-1">
            <h3 className="mb-2 font-semibold text-gray-800">{post.title}</h3>
            <p className="mb-2 text-sm text-gray-600">
              {truncateContent(post.content)}
            </p>
            <div className="text-xs text-gray-500">
              {post.userName} • {formatDate(post.createdAt)} • ID: {post.id}
            </div>
          </div>
        </div>
      </div>
      <button
        onClick={() => onDeletePost(post.id)}
        className="ml-4 cursor-pointer rounded-lg bg-red-500 p-2 text-white transition-colors hover:bg-red-600"
        title="Usuń post"
      >
        <Trash2 size={16} />
      </button>
    </div>
  );
}

export default DashboardPostCard;
