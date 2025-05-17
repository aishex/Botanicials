import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../const/constants";

type User = {
  id: string;
  email: string;
  google_id: string;
  image_url: string;
  name: string;
};

async function getUser(): Promise<User | null> {
  const res = await fetch(`${API_URL}/auth/me`);

  if (!res.ok) {
    throw new Error(`Failed to fetch user. Status: ${res.status}`);
  }

  const data = await res.json();

  if (data && Object.keys(data).length === 0) {
    return null;
  }

  if (data && data.id) {
    return data as User;
  }

  throw new Error("Unexpected data structure received for user.");
}

export const useAuth = () => {
  return useQuery<User | null>({
    queryKey: ["user"],
    queryFn: getUser,
    retry: false,
    staleTime: 60 * 60 * 1000,
  });
};
