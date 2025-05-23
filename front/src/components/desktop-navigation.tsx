import { CheckCircle, Heart, Search } from "lucide-react";
import { Link } from "react-router";

function DesktopNavigation() {
  return (
    <nav className="hidden max-w-[1000px] grow gap-8 lg:flex lg:items-center">
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
      <UserActionLinks />
    </nav>
  );
}

export default DesktopNavigation;

function UserActionLinks() {
  return (
    <div className="ml-8 hidden place-content-end gap-6 lg:flex">
      <Link to="/wishlist" className="w-fit rounded-full bg-pink-400 p-2">
        <Heart color="white" size={26} fill="transparent" strokeWidth="1.5" />
      </Link>
      <Link to="/collection" className="w-fit rounded-full bg-green-900 p-2">
        <CheckCircle
          color="white"
          size={26}
          fill="transparent"
          strokeWidth="1.5"
        />
      </Link>
    </div>
  );
}
