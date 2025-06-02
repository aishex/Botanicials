import { Calendar, Clock, MessageCircle, User } from "lucide-react";
import { formatDate, formatTime } from "../../../common/utils/utils";
import { ExtendedForumPost } from "../../../common/hooks/use-fetch-forum-post";

function MobilePostInfo({
  post,
  commentsLength,
}: {
  post: ExtendedForumPost;
  commentsLength: number;
}) {
  return (
    <div className="mb-6 rounded-2xl bg-[#d4c4a8] p-6">
      <h2 className="mb-4 text-xl font-bold text-gray-800">Informacje</h2>
      <div className="space-y-3">
        <div className="flex items-center gap-3">
          <User size={18} className="text-gray-600" />
          <span className="text-gray-800">Autor: {post.userName}</span>
        </div>
        <div className="flex items-center gap-3">
          <Calendar size={18} className="text-gray-600" />
          <span className="text-gray-800">
            Data: {formatDate(post.createdAt)}
          </span>
        </div>
        <div className="flex items-center gap-3">
          <Clock size={18} className="text-gray-600" />
          <span className="text-gray-800">
            Godzina: {formatTime(post.createdAt)}
          </span>
        </div>
        <div className="flex items-center gap-3">
          <MessageCircle size={18} className="text-gray-600" />
          <span className="text-gray-800">Komentarze: {commentsLength}</span>
        </div>
      </div>
    </div>
  );
}

export default MobilePostInfo;
