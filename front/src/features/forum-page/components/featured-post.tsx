import { Link } from "react-router";
import { type ForumPost } from "../../common/hooks/use-fetch-forum-posts";
import { formatDate, truncateContent } from "../../common/utils/utils";

function FeaturedPost({ featuredPost }: { featuredPost: ForumPost }) {
  return (
    <Link to={`/forum/${featuredPost.id}`} className="mb-8 block">
      <div
        className="relative overflow-hidden rounded-3xl p-8 text-white"
        style={{
          background:
            "linear-gradient(135deg, #ff6b35 0%, #f7931e 50%, #ffd700 100%)",
        }}
      >
        {/* Plant decoration */}
        <div className="absolute top-6 right-6 opacity-80">
          <div className="h-40 w-40 rounded-full bg-green-600 opacity-20"></div>
          <div className="absolute top-2 left-2 h-36 w-36">
            {featuredPost.imageUrl ? (
              <img
                src={featuredPost.imageUrl || "/placeholder.svg"}
                alt="Plant decoration"
                className="h-full w-full rounded-full object-cover opacity-80"
                crossOrigin="anonymous"
              />
            ) : (
              <div className="flex h-full w-full items-center justify-center rounded-full bg-green-700">
                <span className="text-5xl">🌿</span>
              </div>
            )}
          </div>
        </div>

        {/* Content */}
        <div className="relative z-10 max-w-2xl">
          <div className="mb-2 text-base font-medium opacity-95 text-shadow-sm">
            {formatDate(featuredPost.createdAt)}
          </div>
          <div className="mb-4 text-base font-medium opacity-95 text-shadow-sm">
            <p>meow</p>
          </div>
          <h1 className="mb-6 text-3xl leading-tight font-bold text-shadow-md">
            {featuredPost.title}
          </h1>
          <p className="text-base leading-relaxed font-medium opacity-95 text-shadow-sm">
            {truncateContent(featuredPost.content, 250)}
          </p>
        </div>
      </div>
    </Link>
  );
}

export default FeaturedPost;
