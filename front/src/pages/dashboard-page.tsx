import { FileText, MessageSquare } from "lucide-react";
import { useState } from "react";
import { useFetchForumPosts } from "../features/common/hooks/use-fetch-forum-posts";
import DashboardCommentCard from "../features/dashboard-page/components/dashboard-comment-card";
import DashboardHeader from "../features/dashboard-page/components/dashboard-header";
import DashboardNavigationButtons from "../features/dashboard-page/components/dashboard-navigation-buttons";
import DashboardPostCard from "../features/dashboard-page/components/dashboard-post-card";
import { useFetchAllComments } from "../features/dashboard-page/hooks/use-fetch-all-comments";

function DashboardPage() {
  const { data: posts, isLoading: postsLoading } = useFetchForumPosts();
  const { data: comments, isLoading: commentsLoading } = useFetchAllComments();
  const [activeTab, setActiveTab] = useState<"posts" | "comments">("posts");

  return (
    <div className="min-h-screen bg-[#f5f1e8] py-8">
      <div className="px-6 pb-6">
        <div className="mx-auto max-w-7xl">
          <DashboardHeader
            postsLength={posts?.length || 0}
            commentsLength={comments?.length || 0}
          />

          {/* Tabs */}
          <div className="rounded-2xl bg-[#d4c4a8] p-6">
            <DashboardNavigationButtons
              activeTab={activeTab}
              setActiveTab={setActiveTab}
              commentsLength={comments?.length || 0}
              postsLength={posts?.length || 0}
            />

            {activeTab === "posts" && (
              <div className="space-y-4">
                {postsLoading ? (
                  <div className="animate-pulse space-y-4">
                    {[...Array(3)].map((_, i) => (
                      <div
                        key={i}
                        className="h-24 rounded-xl bg-gray-300"
                      ></div>
                    ))}
                  </div>
                ) : posts?.length === 0 ? (
                  <div className="py-12 text-center">
                    <FileText
                      size={48}
                      className="mx-auto mb-4 text-gray-400"
                    />
                    <p className="text-gray-600">Brak postów do wyświetlenia</p>
                  </div>
                ) : (
                  posts?.map((post) => (
                    <DashboardPostCard key={post.id} post={post} />
                  ))
                )}
              </div>
            )}

            {/* Comments Tab */}
            {activeTab === "comments" && (
              <div className="space-y-4">
                {commentsLoading ? (
                  <div className="animate-pulse space-y-4">
                    {[...Array(3)].map((_, i) => (
                      <div
                        key={i}
                        className="h-20 rounded-xl bg-gray-300"
                      ></div>
                    ))}
                  </div>
                ) : comments?.length === 0 ? (
                  <div className="py-12 text-center">
                    <MessageSquare
                      size={48}
                      className="mx-auto mb-4 text-gray-400"
                    />
                    <p className="text-gray-600">
                      Brak komentarzy do wyświetlenia
                    </p>
                  </div>
                ) : (
                  comments?.map((comment) => (
                    <DashboardCommentCard key={comment.id} comment={comment} />
                  ))
                )}
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default DashboardPage;
