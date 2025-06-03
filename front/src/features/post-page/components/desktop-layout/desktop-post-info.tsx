import { Calendar, Clock, Info, MessageCircle, User } from "lucide-react";
import { formatDate, formatTime } from "../../../common/utils/utils";
import { ExtendedForumPost } from "../../../common/hooks/use-fetch-forum-post";

function DesktopPostInfo({
  post,
  commentsLength,
}: {
  post: ExtendedForumPost;
  commentsLength: number;
}) {
  return (
    <div className="rounded-2xl bg-[#d4c4a8] p-8">
      <h3 className="mb-6 text-xl font-bold text-gray-800">
        Informacje o poście
      </h3>

      <div className="grid grid-cols-2 gap-6">
        <div className="space-y-4">
          <div className="flex items-center gap-3">
            <User size={20} className="text-gray-600" />
            <span className="text-gray-800">Autor: {post.userName}</span>
          </div>
          <div className="flex items-center gap-3">
            <Calendar size={20} className="text-gray-600" />
            <span className="text-gray-800">
              Data: {formatDate(post.createdAt)}
            </span>
          </div>
        </div>

        <div className="space-y-4">
          <div className="flex items-center gap-3">
            <Clock size={20} className="text-gray-600" />
            <span className="text-gray-800">
              Godzina: {formatTime(post.createdAt)}
            </span>
          </div>
          <div className="flex items-center gap-3">
            <MessageCircle size={20} className="text-gray-600" />
            <span className="text-gray-800">Komentarze: {commentsLength}</span>
          </div>
        </div>
      </div>

      <div className="mt-6 border-t border-gray-600/20 pt-6">
        <div className="flex items-start gap-3">
          <Info size={20} className="mt-1 flex-shrink-0 text-gray-600" />
          <div>
            <h4 className="mb-2 font-semibold text-gray-800">
              O forum roślinnym
            </h4>
            <p className="text-gray-700">
              Nasze forum to miejsce dla wszystkich miłośników roślin. Dziel się
              swoimi doświadczeniami, zadawaj pytania i poznawaj innych
              pasjonatów zieleni. Pamiętaj o przestrzeganiu zasad społeczności i
              szacunku dla innych użytkowników.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default DesktopPostInfo;
