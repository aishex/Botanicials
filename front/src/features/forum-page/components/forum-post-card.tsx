import { Link } from "react-router";
import { type ForumPost } from "../../common/hooks/use-fetch-forum-posts";
import { formatDate, truncateContent } from "../../common/utils/utils";

function ForumPostcard({ post }: { post: ForumPost }) {
  return (
    <Link key={post.id} to={`/forum/${post.id}`} className="block">
      <div className="relative flex aspect-square flex-col justify-between overflow-hidden rounded-2xl bg-[#d4c4a8] p-6">
        {post.imageUrl ? (
          <div className="absolute inset-0">
            <img
              src={post.imageUrl || "/placeholder.svg"}
              alt={post.title}
              className="h-full w-full object-cover"
              crossOrigin="anonymous"
            />
            <div className="absolute inset-0 bg-black/50"></div>
          </div>
        ) : (
          <div className="absolute inset-0 bg-gradient-to-br from-[#d4c4a8] to-[#c4b49d]"></div>
        )}

        <div className="relative z-10 text-white">
          <div className="mb-1 text-sm font-medium opacity-90 drop-shadow-md">
            {formatDate(post.createdAt)}
          </div>
          <div className="mb-2 text-sm font-medium opacity-90 drop-shadow-md">
            <p>meow</p>
          </div>
        </div>

        <div className="relative z-10">
          <h3 className="mb-3 text-lg leading-tight font-bold text-white drop-shadow-md">
            {truncateContent(post.title, 50)}
          </h3>
          <p className="text-sm leading-relaxed font-medium text-white/95 drop-shadow-sm">
            {truncateContent(post.content, 80)}
          </p>
        </div>
      </div>
    </Link>
  );
}

export default ForumPostcard;
