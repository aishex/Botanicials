import { ExtendedForumPost } from "../../common/hooks/use-fetch-forum-post";
import { formatDate } from "../../common/utils/utils";

function HeroImage({ post }: { post: ExtendedForumPost }) {
  return (
    <div className="relative mb-8 overflow-hidden rounded-3xl">
      <div className="relative h-64 lg:h-80">
        {post.imageUrl ? (
          <img
            src={post.imageUrl || "/placeholder.svg"}
            alt={post.title}
            className="h-full w-full object-cover"
            crossOrigin="anonymous"
          />
        ) : (
          <div
            className="h-full w-full"
            style={{
              background:
                "linear-gradient(135deg, #ff6b35 0%, #f7931e 50%, #ffd700 100%)",
            }}
          />
        )}
        <div className="absolute inset-0 bg-gradient-to-t from-black/60 via-black/20 to-transparent" />

        {/* Title Overlay */}
        <div className="absolute right-0 bottom-0 left-0 p-6 lg:p-8">
          <h1 className="mb-4 text-2xl leading-tight font-bold text-white text-shadow-md lg:text-4xl">
            {post.title}
          </h1>
          <div className="flex items-center gap-4 text-white/90">
            <span className="text-shadow-sm">{post.userName}</span>
            <span className="text-shadow-sm">•</span>
            <span className="text-shadow-sm">{formatDate(post.createdAt)}</span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default HeroImage;
