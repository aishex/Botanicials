import { Search } from "lucide-react";
import { Link } from "react-router";

function DesktopNavigation() {
  return (
    <nav className="hidden gap-8 md:flex md:items-center">
      <Link className="text-2xl" to="/">
        Home
      </Link>
      <Link className="text-2xl" to="/forum">
        Forum
      </Link>
      <div className="bg-light-beige flex w-full items-center justify-between rounded-full px-4 py-2">
        <input
          type="text"
          placeholder="Search..."
          className="grow text-white"
        />
        <Search className="text-white" />
      </div>
    </nav>
  );
}

export default DesktopNavigation;
