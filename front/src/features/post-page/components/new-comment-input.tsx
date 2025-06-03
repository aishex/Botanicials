import { Send } from "lucide-react";

function NewCommentInput({
  newComment,
  setNewComment,
  onSubmit,
  isPending,
}: {
  newComment: string;
  setNewComment: React.Dispatch<React.SetStateAction<string>>;
  onSubmit: (e: React.FormEvent) => void;
  isPending: boolean;
}) {
  return (
    <form onSubmit={onSubmit} className="mb-6">
      <div className="mb-4 rounded-xl bg-[#a8b5a8] p-4">
        <textarea
          value={newComment}
          onChange={(e) => setNewComment(e.target.value)}
          placeholder="Napisz komentarz..."
          className="w-full resize-none bg-transparent text-white placeholder-white/70 outline-none"
          rows={3}
        />
      </div>
      <button
        disabled={isPending}
        type="submit"
        className="flex cursor-pointer items-center gap-2 rounded-full bg-green-600 px-6 py-2 text-white transition-colors hover:bg-green-700"
      >
        <Send size={16} />
        Wyślij
      </button>
    </form>
  );
}

export default NewCommentInput;
