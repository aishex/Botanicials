import { PostComment } from "../../common/hooks/use-fetch-post-comments";
import { formatDate } from "../../common/utils/utils";

function CommentsContainer({
  comments,
}: {
  comments: PostComment[] | undefined;
}) {
  return (
    <div className="space-y-4 lg:max-h-96 lg:overflow-y-auto">
      {comments?.map((comment) => (
        <div key={comment.id} className="rounded-xl bg-[#a8b5a8] p-4">
          <div className="mb-2 flex items-start justify-between">
            <span className="font-semibold text-white">{comment.userName}</span>
            <span className="text-sm text-white/70">
              {formatDate(comment.createdAt)}
            </span>
          </div>
          <p className="leading-relaxed text-white">{comment.content}</p>
        </div>
      ))}
    </div>
  );
}

export default CommentsContainer;
