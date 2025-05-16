import { User } from "lucide-react";
import { useState } from "react";
import { Link } from "react-router";
import MobileNav from "./mobile-nav";
import MobileNavTrigger from "./mobile-nav-trigger";
import { useAuth } from "../hooks/use-auth";

function Header() {
  const [isOpen, setIsOpen] = useState(false);

  const { user } = useAuth();
  console.log(user);

  return (
    <header className="bg-dark-beige relative z-10 px-4 py-2 text-white">
      <div className="mx-auto flex w-full max-w-[1920px] items-center justify-between">
        <MobileNavTrigger isOpen={isOpen} setIsOpen={setIsOpen} />
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
      </div>
    </header>
  );
}

export default Header;
