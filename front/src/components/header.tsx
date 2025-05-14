import { Menu, User, X } from "lucide-react";
import { useState } from "react";
import { Link } from "react-router";
import MobileNav from "./mobile-nav";

// const user: { name: string; picture: string } = {
//   name: "John Doe",
//   picture:
//     "https://lh3.googleusercontent.com/a/ACg8ocL9muqW8FZe79WL71Ac-E2GgDCXiooPRBMVeaffpITewRBmN-7b=s96-c",
// };

const user: { name: string; picture: string } | null = null;

function Header() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <header className="bg-dark-beige relative z-10 flex items-center justify-between px-4 py-2 text-white">
      <button
        className="hover:bg-dark-beige-hover flex h-10 w-10 cursor-pointer items-center justify-center rounded-full transition-colors duration-200 ease-in-out md:hidden"
        onClick={() => setIsOpen((prev) => !prev)}
      >
        <span className="sr-only">Menu</span>
        {isOpen ? <X /> : <Menu />}
      </button>
      <div className="h-auto w-16">
        <Link to="/">
          <img
            src="/logo.png"
            alt="Logo"
            className="h-full w-full object-contain"
            loading="lazy"
          />
        </Link>
      </div>
      <div className="bg-light-beige flex w-fit items-center justify-between gap-4 rounded-full pr-4">
        <div>
          {user ? (
            <div className="flex h-10 w-10 items-center justify-center overflow-hidden rounded-full bg-gray-200">
              {user.picture ? (
                <img
                  src={user.picture}
                  alt="User Avatar"
                  className="h-full w-full object-cover"
                />
              ) : (
                <User className="text-gray-600" />
              )}
            </div>
          ) : (
            <div className="bg-light-green h-10 w-10 rounded-full"></div>
          )}
        </div>
        <div>
          {user ? (
            <p className="max-w-20 overflow-y-auto">{user.name}</p>
          ) : (
            <Link to="/login">Log in</Link>
          )}
        </div>
      </div>
      {isOpen && <MobileNav onClose={() => setIsOpen(false)} />}
    </header>
  );
}

export default Header;
