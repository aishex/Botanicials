import { useFetchForumPosts } from "../features/common/hooks/use-fetch-forum-posts";
import FeaturedPost from "../features/forum-page/components/featured-post";
import ForumError from "../features/forum-page/components/forum-error";
import ForumLoader from "../features/forum-page/components/forum-loader";
import ForumPostcard from "../features/forum-page/components/forum-post-card";

function ForumPage() {
  const { data: posts, isLoading, error } = useFetchForumPosts();

  if (isLoading) {
    return <ForumLoader />;
  }

  if (error) {
    return <ForumError />;
  }

  const featuredPost = posts?.[0];
  const otherPosts = posts?.slice(1) || [];

  return (
    <div className="min-h-screen bg-[#f5f1e8] py-8">
      <div className="px-6 pb-6">
        <div className="mx-auto max-w-4xl">
          {/* Featured Post */}
          {featuredPost && <FeaturedPost featuredPost={featuredPost} />}

          {/* Other Posts Grid */}
          <div className="grid grid-cols-2 gap-6">
            {otherPosts.map((post) => (
              <ForumPostcard key={post.id} post={post} />
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default ForumPage;
