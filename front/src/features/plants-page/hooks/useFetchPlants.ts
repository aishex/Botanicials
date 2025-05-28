import { useInfiniteQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";
import { Plant } from "../../common/types/plant-type";

// now takes name and page
const fetchPlants = async (name: string | undefined, page: unknown) => {
  let url = `${API_URL}/plants?page=${page}`;
  if (name) url += `&name=${encodeURIComponent(name)}`;
  const res = await fetch(url);
  if (!res.ok) throw new Error("Something went wrong");
  const data = await res.json();
  if (!data.data) throw new Error("No data found in the response");
  return data.data as Plant[];
};

export function useFetchPlants(name?: string) {
  return useInfiniteQuery<Plant[]>({
    queryKey: name ? ["plants", name] : ["plants"],
    queryFn: ({ pageParam }) => fetchPlants(name, pageParam),
    initialPageParam: 1,
    getNextPageParam: (_lastPage, pages) => pages.length + 1,
    staleTime: Infinity,
    refetchOnWindowFocus: false,
  });
}
