function DashboardNavigationButtons({
  setActiveTab,
  activeTab,
  postsLength,
  commentsLength,
}: {
  setActiveTab: React.Dispatch<React.SetStateAction<"posts" | "comments">>;
  activeTab: "posts" | "comments";
  postsLength: number;
  commentsLength: number;
}) {
  return (
    <div className="mb-6 flex gap-4">
      <button
        onClick={() => setActiveTab("posts")}
        className={`cursor-pointer rounded-xl px-6 py-3 font-semibold transition-colors ${
          activeTab === "posts"
            ? "bg-[#a8b5a8] text-white"
            : "bg-[#c4b49d] text-gray-700 hover:bg-[#b8a891]"
        }`}
      >
        Posty ({postsLength})
      </button>
      <button
        onClick={() => setActiveTab("comments")}
        className={`cursor-pointer rounded-xl px-6 py-3 font-semibold transition-colors ${
          activeTab === "comments"
            ? "bg-[#a8b5a8] text-white"
            : "bg-[#c4b49d] text-gray-700 hover:bg-[#b8a891]"
        }`}
      >
        Komentarze ({commentsLength})
      </button>
    </div>
  );
}

export default DashboardNavigationButtons;
