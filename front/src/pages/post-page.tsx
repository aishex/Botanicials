import { useParams } from "react-router";
import { useFetchPostComments } from "../features/common/hooks/use-fetch-post-comments";
import { useFetchForumPost } from "../features/common/hooks/use-fetch-forum-post";

function PostPage() {
  const { postId } = useParams();
  // const {data: post} = useFetchForumPost(postId!);
  // console.log(post);
  // const { data: comments } = useFetchPostComments(postId!);
  // console.log(comments);
  return <div>Post Page</div>;
}

export default PostPage;
