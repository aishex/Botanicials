import { ArrowLeft } from "lucide-react";
import { Link } from "react-router";

function GoBackBtn() {
  return (
    <Link
      to="/forum"
      className="mb-6 flex items-center gap-2 text-gray-600 transition-colors hover:text-gray-800"
    >
      <ArrowLeft size={20} />
      Powrót do forum
    </Link>
  );
}

export default GoBackBtn;
