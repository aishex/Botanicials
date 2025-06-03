import { Share2 } from "lucide-react";

export function ShareButton() {
  return (
    <button
      className="flex cursor-pointer items-center gap-2 text-gray-700 transition-colors hover:text-green-600"
      onClick={async () => {
        navigator.clipboard
          .writeText(window.location.href)
          .then(() => alert("Link copied"))
          .catch(() => console.log("Failed to copy the URL"));
      }}
    >
      <Share2 size={20} />
      <span>Udostępnij</span>
    </button>
  );
}
