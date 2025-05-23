import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../const/constants";

type User = {
  id: string;
  email: string;
  googleId: string;
  imageUrl: string;
  name: string;
};

async function getUser(): Promise<User | null> {
  const res = await fetch(`${API_URL}/auth/me`, {
    credentials: "include",
  });

  if (!res.ok) {
    throw new Error(`Failed to fetch user. Status: ${res.status}`);
  }

  const data = await res.json();

  if (!data.user) {
    return null;
  }

  return data.user;
}

export const useAuth = () => {
  return useQuery<User | null>({
    queryKey: ["user"],
    queryFn: getUser,
    retry: false,
    staleTime: 60 * 60 * 1000,
  });
};
