import { useQuery } from "@tanstack/react-query";
import { Plant } from "../../common/types/plant-type";

const fetchPlantInfo = async (plantId: string) => {
  if (!plantId) {
    throw new Error("Plant ID is required");
  }

  const response = await fetch(`http://localhost:8080/plants/${plantId}`);
  if (!response.ok) {
    throw new Error("Could not fetch plant information");
  }
  const data = await response.json();
  if (!data) {
    throw new Error("No plant data found");
  }
  return data;
};

export function useFetchPlantInfo(plantId: string) {
  return useQuery<Plant>({
    queryKey: ["plant", plantId],
    queryFn: () => fetchPlantInfo(plantId),
    enabled: !!plantId,
    staleTime: Infinity,
  });
}
