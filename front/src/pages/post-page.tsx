import { useState } from "react";
import { useParams } from "react-router";
import { useAuth } from "../features/common/hooks/use-auth";
import { useFetchForumPost } from "../features/common/hooks/use-fetch-forum-post";
import { useFetchPostComments } from "../features/common/hooks/use-fetch-post-comments";
import CommentsContainer from "../features/post-page/components/comments-container";
import CommentsSkeleton from "../features/post-page/components/comments-skeleton";
import DesktopPostContent from "../features/post-page/components/desktop-layout/desktop-post-content";
import DesktopPostInfo from "../features/post-page/components/desktop-layout/desktop-post-info";
import GoBackBtn from "../features/post-page/components/go-back-btn";
import HeroImage from "../features/post-page/components/hero-image";
import LoggedOutLink from "../features/post-page/components/logged-out-link";
import MobilePostContent from "../features/post-page/components/mobile-layout/mobile-post-content";
import MobilePostInfo from "../features/post-page/components/mobile-layout/mobile-post-info";
import NewCommentInput from "../features/post-page/components/new-comment-input";
import PostLoader from "../features/post-page/components/post-loader";
import { useAddComment } from "../features/post-page/hooks/use-add-comment";

function PostPage() {
  const [newComment, setNewComment] = useState("");
  const { postId } = useParams();
  const {
    data: post,
    isLoading: postLoading,
    error,
  } = useFetchForumPost(postId!);
  const { data: comments, isLoading: commentsLoading } = useFetchPostComments(
    postId!,
  );
  const { data: user } = useAuth();

  const isLoggedIn = Boolean(user);

  const { mutate: addComment, isPending } = useAddComment(postId!);

  const handleSubmitComment = (e: React.FormEvent) => {
    e.preventDefault();
    if (!newComment.trim()) return;
    addComment({
      content: newComment,
      forumPostId: Number(postId),
      userId: user!.id,
    });
    setNewComment("");
  };

  if (postLoading) {
    return <PostLoader />;
  }

  if (error || !post) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-[#f5f1e8] p-6">
        <div className="text-center">
          <p className="text-gray-600">Błąd podczas ładowania posta</p>
        </div>
      </div>
    );
  }

  return (
    <div>
      <div className="min-h-screen bg-[#f5f1e8] py-8">
        <div className="px-6 pb-6">
          <div className="mx-auto max-w-7xl">
            <GoBackBtn />

            <HeroImage post={post} />

            {/* Mobile Layout */}
            <div className="lg:hidden">
              <MobilePostContent
                post={post}
                commentsLength={comments?.length || 0}
              />

              <MobilePostInfo
                post={post}
                commentsLength={comments?.length || 0}
              />

              <div className="rounded-2xl bg-[#d4c4a8] p-6">
                <h2 className="mb-6 text-2xl font-bold text-gray-800">
                  DYSKUSJA
                </h2>

                {isLoggedIn ? (
                  <NewCommentInput
                    newComment={newComment}
                    setNewComment={setNewComment}
                    onSubmit={handleSubmitComment}
                    isPending={isPending}
                  />
                ) : (
                  <LoggedOutLink />
                )}

                {commentsLoading ? (
                  <CommentsSkeleton />
                ) : (
                  <CommentsContainer comments={comments} />
                )}
              </div>
            </div>

            {/* Desktop Layout */}
            <div className="hidden gap-8 lg:grid lg:grid-cols-5">
              <div className="space-y-8 lg:col-span-3">
                <DesktopPostContent
                  post={post}
                  commentsLength={comments?.length || 0}
                />

                <DesktopPostInfo
                  post={post}
                  commentsLength={comments?.length || 0}
                />
              </div>

              <div className="lg:col-span-2">
                <div className="sticky top-8 rounded-2xl bg-[#d4c4a8] p-6">
                  <h2 className="mb-6 text-2xl font-bold text-gray-800">
                    DYSKUSJA
                  </h2>

                  {isLoggedIn ? (
                    <NewCommentInput
                      newComment={newComment}
                      setNewComment={setNewComment}
                      onSubmit={handleSubmitComment}
                      isPending={isPending}
                    />
                  ) : (
                    <LoggedOutLink />
                  )}

                  {commentsLoading ? (
                    <CommentsSkeleton />
                  ) : (
                    <CommentsContainer comments={comments} />
                  )}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PostPage;
