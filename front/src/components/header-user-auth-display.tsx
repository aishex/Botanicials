import { CheckCircle, Heart, User } from "lucide-react";
import { Link } from "react-router";
import { useAuth } from "../hooks/use-auth";

function UserAuthDisplay() {
  const { data: user } = useAuth();

  return (
    <div className="grid gap-6">
      <div className="bg-light-beige flex w-fit items-center justify-between gap-4 rounded-full pr-4">
        <div>
          {user ? (
            <div className="flex h-10 w-10 items-center justify-center overflow-hidden rounded-full bg-gray-200">
              {user.image_url ? (
                <img
                  src={user.image_url}
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
      <div className="hidden place-content-end gap-3 md:grid">
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
    </div>
  );
}

export default UserAuthDisplay;
