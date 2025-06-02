import { MessageCircle } from "lucide-react";
import { ExtendedForumPost } from "../../../common/hooks/use-fetch-forum-post";
import { ShareButton } from "../share-btn";

function MobilePostContent({
  post,
  commentsLength,
}: {
  post: ExtendedForumPost;
  commentsLength: number;
}) {
  return (
    <div className="mb-6 rounded-2xl bg-[#d4c4a8] p-6">
      <p className="text-lg leading-relaxed text-gray-800">{post.content}</p>

      <div className="mt-6 flex items-center gap-6 border-t border-gray-600/20 pt-6">
        <p className="flex items-center gap-2 text-gray-700 transition-colors hover:text-blue-600">
          <MessageCircle size={20} />
          <span>{commentsLength}</span>
        </p>
        <ShareButton />
      </div>
    </div>
  );
}

export default MobilePostContent;
