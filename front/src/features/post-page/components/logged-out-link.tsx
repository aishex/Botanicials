import { Link } from "react-router";

export default function LoggedOutLink() {
  return (
    <div className="mb-6 rounded-xl bg-[#a8b5a8] p-4 text-center">
      <p className="mb-4 text-white/70">Zaloguj się, aby dodać komentarz</p>
      <Link
        to="/login"
        className="rounded-full bg-green-600 px-6 py-2 text-white transition-colors hover:bg-green-700"
      >
        Zaloguj się
      </Link>
    </div>
  );
}
