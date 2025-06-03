function ForumLoader() {
  return (
    <div className="min-h-screen bg-[#f5f1e8] p-6">
      <div className="mx-auto max-w-4xl">
        <div className="animate-pulse">
          <div className="mb-6 h-96 rounded-3xl bg-gray-300"></div>
          <div className="grid grid-cols-2 gap-6">
            {[...Array(4)].map((_, i) => (
              <div key={i} className="h-64 rounded-2xl bg-gray-300"></div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default ForumLoader;
