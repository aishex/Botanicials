import { useEffect, useState } from "react";
import { API_URL } from "../const/constants";

type User = {
  name: string;
  email: string;
  picture: string;
};

export const useAuth = () => {
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await fetch(`${API_URL}/auth/me`, {
          credentials: "include",
        });

        if (!response.ok) {
          throw new Error("Failed to fetch user");
        }
        const data = await response.json();
        if (!data) {
          setUser(null);
        }
        setUser(data);
      } catch (error) {
        console.error("Error fetching user:", error);
        setUser(null);
      }
    };
    fetchUser();
  }, []);

  return {
    user,
  };
};
