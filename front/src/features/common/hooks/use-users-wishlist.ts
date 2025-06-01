import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";
import { Plant } from "../types/plant-type";

async function getWishlist() {
  const res = await fetch(`${API_URL}/wishlist`, {
    credentials: "include",
  });

  if (!res.ok) {
    throw new Error(`Failed to fetch user's wishlist.`);
  }

  const data = await res.json();

  return data;
}

export const useUsersWishlist = (userId: string) => {
  return useQuery<Plant[]>({
    queryKey: [userId, "wishlist"],
    queryFn: getWishlist,
    retry: false,
    staleTime: 60 * 60 * 1000,
    refetchOnWindowFocus: false,
    enabled: !!userId,
  });
};
