import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";
import { Plant } from "../types/plant-type";

async function getCollection() {
  const res = await fetch(`${API_URL}/collection`, {
    credentials: "include",
  });

  if (!res.ok) {
    throw new Error(`Failed to fetch user's collection.`);
  }

  const data = await res.json();

  return data;
}

export const useUsersCollection = (userId: string) => {
  return useQuery<Plant[]>({
    queryKey: [userId, "collection"],
    queryFn: getCollection,
    retry: false,
    staleTime: 60 * 60 * 1000,
    refetchOnWindowFocus: false,
    enabled: !!userId,
  });
};
