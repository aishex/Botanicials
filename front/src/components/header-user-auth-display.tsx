import { User } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { Link } from "react-router";
import { useAuth } from "../hooks/use-auth";

function UserAvatarDisplay({
  user,
  isLoggedIn,
}: {
  user?: { imageUrl?: string | null };
  isLoggedIn: boolean;
}) {
  if (!isLoggedIn) {
    return <div className="bg-light-green h-10 w-10 rounded-full"></div>;
  }

  return (
    <div className="flex h-10 w-10 items-center justify-center overflow-hidden rounded-full bg-gray-200">
      {user?.imageUrl ? (
        <img
          src={user.imageUrl}
          alt="User Avatar"
          className="h-full w-full object-cover"
        />
      ) : (
        <User className="text-gray-600" />
      )}
    </div>
  );
}

function LoginPrompt() {
  return (
    <Link
      to="/login"
      className="bg-light-beige flex w-fit items-center gap-4 rounded-full pr-4 text-gray-700 transition-colors hover:bg-gray-100"
      aria-label="Log in"
    >
      <UserAvatarDisplay isLoggedIn={false} />
      <div>Log in</div>
    </Link>
  );
}

function LoggedInUserProfile({
  user,
}: {
  user: { name: string; imageUrl?: string | null };
}) {
  const [isOpen, setIsOpen] = useState(false);
  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (ref.current && !ref.current.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  const handleLogout = () => {
    console.log("Logging out...");
  };

  return (
    <div className="grid gap-6">
      <div className="relative">
        <button
          onClick={() => setIsOpen((prev) => !prev)}
          className="bg-light-beige flex w-fit items-center gap-4 rounded-full pr-4 text-left transition-colors hover:bg-gray-300 focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 focus:outline-none"
          aria-expanded={isOpen}
          aria-haspopup="true"
          aria-label={`Logged in as ${user.name}. Click to open user menu.`}
        >
          <UserAvatarDisplay user={user} isLoggedIn={true} />
          <p className="line-clamp-1 max-w-20 overflow-y-auto">{user.name}</p>
        </button>
        {isOpen && (
          <div
            className="ring-opacity-5 absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black focus:outline-none"
            role="menu"
            ref={ref}
          >
            <button
              onClick={() => {
                handleLogout();
                setIsOpen(false);
              }}
              className="block w-full px-4 py-2 text-left text-sm text-gray-700 hover:bg-gray-100"
              role="menuitem"
            >
              Log out
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

function UserAuthDisplay() {
  const { data: user } = useAuth();

  if (!user) {
    return <LoginPrompt />;
  }

  const profileUser = {
    name: user.name || "User",
    imageUrl: user.imageUrl,
  };

  return <LoggedInUserProfile user={profileUser} />;
}

export default UserAuthDisplay;
