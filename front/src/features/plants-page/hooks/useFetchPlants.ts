import { useQuery } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";
import { Plant } from "../../common/types/plant-type";

const fetchPlants = async () => {
  const res = await fetch(`${API_URL}/plants`);
  if (!res.ok) throw new Error("Something went wrong");
  const data = await res.json();
  if (!data.data) throw new Error("No data found in the response");
  return data.data;
};

export function useFetchPlants() {
  return useQuery<Plant[]>({
    queryKey: ["plants"],
    queryFn: fetchPlants,
    staleTime: Infinity,
  });
}
