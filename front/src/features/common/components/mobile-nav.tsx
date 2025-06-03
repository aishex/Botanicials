import { CheckCircle, Heart } from "lucide-react";
import { useEffect, useRef } from "react";
import { Link } from "react-router";
import PlantQueryInput from "./plant-query-input";

type Props = {
  onClose: () => void;
};

function MobileNav({ onClose }: Props) {
  const navRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (navRef.current && !navRef.current.contains(event.target as Node)) {
        onClose();
      }
    };

    document.addEventListener("mousedown", handleClickOutside);

    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [onClose]);

  return (
    <nav
      className="bg-dark-beige absolute top-full left-0 z-[999] flex h-screen w-fit flex-col items-start p-4 lg:hidden"
      ref={navRef}
    >
      <PlantQueryInput className="mb-6" />
      <ul className="flex flex-col gap-6 text-2xl">
        <li className="hover:underline">
          <Link to="/" onClick={onClose}>
            Home
          </Link>
        </li>
        <li className="hover:underline">
          <Link to="/forum" onClick={onClose}>
            Forum
          </Link>
        </li>
        <li className="hover:underline">
          <Link
            className="flex items-center gap-2"
            to="/wishlist"
            onClick={onClose}
          >
            <div className="rounded-full bg-pink-600 p-2">
              <Heart color="white" strokeWidth={1.5} />
            </div>
            <span>Wishlist</span>
          </Link>
        </li>
        <li className="hover:underline">
          <Link
            className="flex items-center gap-2"
            to="/wishlist"
            onClick={onClose}
          >
            <div className="rounded-full bg-green-900 p-2">
              <CheckCircle color="white" strokeWidth={1.5} />
            </div>
            <span>Owned</span>
          </Link>
        </li>
      </ul>
    </nav>
  );
}

export default MobileNav;
