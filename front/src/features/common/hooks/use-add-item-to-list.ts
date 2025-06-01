import { useMutation, useQueryClient } from "@tanstack/react-query";
import { Plant } from "../types/plant-type";
import { API_URL } from "../../../const/constants";

export type ListType = "wishlist" | "collection";

const addItemToListApi = async (plant: Plant, listType: ListType) => {
  const endpointUrl = `${API_URL}/${listType}`;
  const res = await fetch(endpointUrl, {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      plantId: plant.id,
      plantName: plant.common_name,
      imageUrl: plant.default_image?.medium_url || "",
    }),
  });

  if (!res.ok) {
    throw new Error(`Failed to add plant ${plant.id} to ${listType}.`);
  }

  const data = await res.json();
  return data;
};

export const useAddItemToList = (userId: string, listType: ListType) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (plant: Plant) => {
      if (!plant) {
        return Promise.reject(
          new Error(`Plant data is not available to add to ${listType}.`),
        );
      }
      return addItemToListApi(plant, listType);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [userId, listType] });
    },
    onError: (error: Error) => {
      console.error(`Error adding item to ${listType}:`, error.message);
    },
  });
};
