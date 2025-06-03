import { Heart, Leaf, X } from "lucide-react";
import { useAuth } from "../features/common/hooks/use-auth";
import { useUsersWishlist } from "../features/common/hooks/use-users-wishlist";
import { Link } from "react-router";
import { useRemoveItemFromList } from "../features/common/hooks/use-remove-item-from-list";

function WishlistPage() {
  const { data: user } = useAuth();

  const { data: wishlist, isLoading, error } = useUsersWishlist(user?.id || "");

  const { mutate: removeItem, isPending } = useRemoveItemFromList(
    user?.id || "",
    "wishlist",
  );

  if (!user) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-[#f5f1e8] p-6">
        <div className="text-center">
          <p className="text-gray-600">
            Musisz być zalogowany, aby zobaczyć wishliste
          </p>
          <Link
            to="/login"
            className="mt-4 inline-block rounded-lg bg-green-600 px-4 py-2 text-white transition-colors hover:bg-green-700"
          >
            Zaloguj się
          </Link>
        </div>
      </div>
    );
  }

  if (isLoading) {
    return (
      <div className="min-h-screen bg-[#f5f1e8] p-6">
        <div className="mx-auto max-w-6xl">
          <div className="animate-pulse">
            <div className="mb-8 h-16 rounded-2xl bg-gray-300"></div>
            <div className="grid grid-cols-2 gap-6 md:grid-cols-3 lg:grid-cols-4">
              {[...Array(8)].map((_, i) => (
                <div key={i} className="h-64 rounded-2xl bg-gray-300"></div>
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-[#f5f1e8] p-6">
        <div className="text-center">
          <p className="text-gray-600">Błąd podczas ładowania listy życzeń</p>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-[#f5f1e8] py-8">
      <div className="px-6 pb-6">
        <div className="mx-auto max-w-6xl">
          <div className="mb-8 rounded-2xl bg-[#d4c4a8] p-8">
            <div className="mb-4 flex items-center gap-4">
              <Heart size={32} className="text-red-500" />
              <h1 className="text-3xl font-bold text-gray-800">Lista Życzeń</h1>
            </div>
            <p className="text-gray-700">
              Tutaj znajdziesz wszystkie rośliny, które chciałbyś dodać do
              swojej kolekcji. Śledź swoje marzenia ogrodnicze!
            </p>
            <div className="mt-4 flex items-center gap-6">
              <span className="text-gray-600">
                Roślin na liście: {wishlist?.length || 0}
              </span>
              <Link
                to="/collection"
                className="flex items-center gap-2 text-green-600 transition-colors hover:text-green-700"
              >
                <Leaf size={16} />
                Zobacz kolekcję
              </Link>
            </div>
          </div>

          {wishlist?.length === 0 ? (
            <div className="rounded-2xl bg-[#d4c4a8] p-12 text-center">
              <Heart size={48} className="mx-auto mb-4 text-gray-400" />
              <h2 className="mb-2 text-xl font-semibold text-gray-600">
                Twoja lista życzeń jest pusta
              </h2>
              <p className="text-gray-500">
                Dodaj rośliny, które chciałbyś mieć w swojej kolekcji!
              </p>
            </div>
          ) : (
            <div className="grid grid-cols-2 gap-6 md:grid-cols-3 lg:grid-cols-4">
              {wishlist?.map((plant) => (
                <div
                  key={plant.id}
                  className="group relative overflow-hidden rounded-2xl bg-[#d4c4a8] transition-transform duration-200 hover:scale-[1.02]"
                >
                  <button
                    onClick={() => removeItem({ plantId: plant.id })}
                    className="absolute top-3 right-3 z-10 rounded-full bg-red-500 p-1.5 text-white opacity-0 transition-opacity group-hover:opacity-100 hover:bg-red-600"
                    title="Usuń z listy życzeń"
                    disabled={isPending}
                  >
                    <X size={16} />
                  </button>

                  <div className="relative aspect-square overflow-hidden">
                    <img
                      src={plant.imageUrl || "/placeholder.svg"}
                      alt={plant.plantName}
                      className="h-full w-full object-cover"
                    />
                    <div className="absolute inset-0 bg-gradient-to-t from-black/20 to-transparent" />
                  </div>

                  <div className="p-4">
                    <h3 className="text-center font-semibold text-gray-800">
                      {plant.plantName}
                    </h3>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default WishlistPage;
