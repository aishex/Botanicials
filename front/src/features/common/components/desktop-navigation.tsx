import { CheckCircle, Heart } from "lucide-react";
import { Link } from "react-router";
import PlantQueryInput from "./plant-query-input";

function DesktopNavigation() {
  return (
    <nav className="hidden max-w-[1000px] grow gap-8 lg:flex lg:items-center">
      <Link className="text-2xl" to="/">
        Home
      </Link>
      <Link className="text-2xl" to="/forum">
        Forum
      </Link>
      <PlantQueryInput />
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
