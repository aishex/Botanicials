import { useMutation, useQueryClient } from "@tanstack/react-query";
import { API_URL } from "../../../const/constants";
import { ListType } from "./use-add-item-to-list";

const removeItemFromList = async (plantId: number, listType: ListType) => {
  const endpointUrl = `${API_URL}/${listType}`;
  const res = await fetch(endpointUrl, {
    method: "DELETE",
    credentials: "include",
    body: JSON.stringify({
      plantId,
    }),
  });

  if (!res.ok) {
    throw new Error(`Failed to add plant ${plantId} to ${listType}.`);
  }

  const data = await res.json();
  return data;
};

export const useRemoveItemFromList = (userId: string, listType: ListType) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ plantId }: { plantId: number }) =>
      removeItemFromList(plantId, listType),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: [userId, listType] });
    },
  });
};
