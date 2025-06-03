import { AlertTriangle, FileText, MessageSquare } from "lucide-react";

function DashboardHeader({
  postsLength,
  commentsLength,
}: {
  postsLength: number;
  commentsLength: number;
}) {
  return (
    <div className="mb-8 rounded-2xl bg-[#d4c4a8] p-8">
      <div className="mb-4 flex items-center gap-4">
        <AlertTriangle size={32} className="text-orange-600" />
        <h1 className="text-3xl font-bold text-gray-800">
          Panel Administratora
        </h1>
      </div>
      <p className="text-gray-700">
        Zarządzaj postami i komentarzami na forum. Usuń nieodpowiednie treści.
      </p>

      {/* Stats */}
      <div className="mt-6 grid grid-cols-2 gap-4">
        <div className="rounded-xl bg-[#c4b49d] p-4 text-center">
          <FileText size={24} className="mx-auto mb-2 text-gray-600" />
          <div className="text-2xl font-bold text-gray-800">{postsLength}</div>
          <div className="text-sm text-gray-600">Posty</div>
        </div>
        <div className="rounded-xl bg-[#c4b49d] p-4 text-center">
          <MessageSquare size={24} className="mx-auto mb-2 text-gray-600" />
          <div className="text-2xl font-bold text-gray-800">
            {commentsLength}
          </div>
          <div className="text-sm text-gray-600">Komentarze</div>
        </div>
      </div>
    </div>
  );
}

export default DashboardHeader;
