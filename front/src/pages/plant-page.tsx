import { CheckCircle, Heart, X } from "lucide-react";
import { useFetchPlantInfo } from "../features/plant-page/hooks/useFetchPlantInfo";
import { useParams } from "react-router";
import { useAuth } from "../features/common/hooks/use-auth";
import { useAddItemToList } from "../features/common/hooks/use-add-item-to-list";
import { useUsersWishlist } from "../features/common/hooks/use-users-wishlist";
import { useUsersCollection } from "../features/common/hooks/use-users-collection";
import { useRemoveItemFromList } from "../features/common/hooks/use-remove-item-from-list";

function PlantPage() {
  const { plantId } = useParams();
  const { data: user } = useAuth();

  const { data: wishlist, isLoading: isLoadingWishlist } = useUsersWishlist(
    user?.id || "",
  );
  const { data: collection, isLoading: isLoadingCollection } =
    useUsersCollection(user?.id || "");

  const { data, isLoading, error } = useFetchPlantInfo(plantId!);

  const { mutate: mutateWishlist, isPending: isAddingToWishlist } =
    useAddItemToList(user?.id || "", "wishlist");
  const { mutate: mutateCollection, isPending: isAddingToCollection } =
    useAddItemToList(user?.id || "", "collection");

  const { mutate: removeFromWishlist, isPending: isRemovingFromWishlist } =
    useRemoveItemFromList(user?.id || "", "wishlist");
  const { mutate: removeFromCollection, isPending: isRemovingFromCollection } =
    useRemoveItemFromList(user?.id || "", "collection");

  if (!plantId) {
    return <Wrapper>404 - Plant not found</Wrapper>;
  }

  if (isLoading) {
    return <Wrapper>Loading plant information...</Wrapper>;
  }

  if (error) {
    return (
      <Wrapper>
        <p>Error: {(error as Error)?.message || "Couldn't load plant data."}</p>
      </Wrapper>
    );
  }

  if (!data) {
    return <Wrapper>No plant data available</Wrapper>;
  }

  const isLoggedIn = !!user;

  const isPlantInWishlist =
    isLoggedIn &&
    data &&
    wishlist &&
    !isLoadingWishlist &&
    wishlist.some((item) => item.plantId === data.id);

  const isPlantInCollection =
    isLoggedIn &&
    data &&
    collection &&
    !isLoadingCollection &&
    collection.some((item) => item.plantId === data.id);

  return (
    <Wrapper>
      <div className="grid grid-cols-1 gap-4 md:grid-cols-[auto_1fr]">
        <div className="bg-light-green h-fit max-w-[800px] rounded-2xl p-4 text-white md:order-2">
          <div className="mb-4 grid grid-cols-[1fr_auto_auto] items-start gap-4">
            <h1 className="text-3xl font-bold">{data.common_name}</h1>
            <button
              className={`cursor-pointer rounded-full bg-pink-600 p-2 whitespace-nowrap text-white transition-colors hover:bg-pink-700 ${
                !isLoggedIn ||
                isLoading ||
                isLoadingWishlist ||
                !data ||
                isAddingToWishlist ||
                isRemovingFromWishlist
                  ? "cursor-not-allowed opacity-50"
                  : ""
              }`}
              disabled={
                !isLoggedIn ||
                isLoading ||
                isLoadingWishlist ||
                !data ||
                isAddingToWishlist ||
                isRemovingFromWishlist
              }
              title={
                !isLoggedIn
                  ? "Log in to manage wishlist"
                  : isPlantInWishlist
                    ? "Remove from wishlist"
                    : "Add to wishlist"
              }
              onClick={() => {
                if (!isPlantInWishlist && isLoggedIn && data) {
                  mutateWishlist(data);
                } else {
                  removeFromWishlist({ plantId: data.id });
                }
              }}
            >
              <Heart
                color="white"
                size={26}
                fill={isPlantInWishlist && isLoggedIn ? "white" : "transparent"}
                strokeWidth="1.5"
              />
            </button>
            <button
              className={`cursor-pointer rounded-full bg-green-900 p-2 whitespace-nowrap text-white transition-colors hover:bg-green-950 ${
                !isLoggedIn ||
                isLoading ||
                !data ||
                isAddingToCollection ||
                isRemovingFromCollection
                  ? "cursor-not-allowed opacity-50"
                  : ""
              }`}
              disabled={
                !isLoggedIn ||
                isLoading ||
                !data ||
                isAddingToCollection ||
                isRemovingFromCollection
              }
              title={
                !isLoggedIn
                  ? "Log in to manage collection"
                  : isPlantInCollection
                    ? "Remove from collection"
                    : "Add to collection"
              }
              onClick={() => {
                if (!isPlantInCollection && isLoggedIn && data) {
                  mutateCollection(data);
                } else {
                  removeFromCollection({ plantId: data.id });
                }
              }}
            >
              {isPlantInCollection ? (
                <X color="white" size={26} strokeWidth="1.5" />
              ) : (
                <CheckCircle color="white" size={26} strokeWidth="1.5" />
              )}
            </button>
          </div>
          <p className="mb-6 text-sm font-semibold">
            Care Level: {data.care_level}
          </p>
          <p className="max-h-[450px] overflow-y-scroll font-medium">
            {data.description}
          </p>
        </div>
        <img
          src={
            data.default_image
              ? data.default_image?.original_url
              : "https://placehold.co/600x400?text=Image+Placeholder"
          }
          alt={data.common_name}
          className="aspect-[3/2] h-64 w-full rounded-2xl object-cover md:order-1 md:h-80 md:max-w-[450px] md:object-fill"
        />
      </div>
    </Wrapper>
  );
}

export default PlantPage;

const Wrapper = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="bg-lighest-beige flex h-full justify-center px-6 py-20">
      {children}
    </div>
  );
};
