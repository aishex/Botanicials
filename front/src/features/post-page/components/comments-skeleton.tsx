export default function CommentsSkeleton() {
  return (
    <div className="animate-pulse space-y-4">
      {[...Array(2)].map((_, i) => (
        <div key={i} className="h-24 rounded-xl bg-gray-300"></div>
      ))}
    </div>
  );
}
