function PostLoader() {
  return (
    <div className="min-h-screen bg-[#f5f1e8] p-6">
      <div className="mx-auto max-w-7xl">
        <div className="animate-pulse">
          <div className="mb-6 h-64 rounded-3xl bg-gray-300"></div>
          <div className="grid gap-8 lg:grid-cols-5">
            <div className="lg:col-span-3">
              <div className="h-96 rounded-2xl bg-gray-300"></div>
            </div>
            <div className="lg:col-span-2">
              <div className="h-96 rounded-2xl bg-gray-300"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default PostLoader;
