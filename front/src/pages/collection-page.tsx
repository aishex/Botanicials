import { Heart, Leaf, X } from "lucide-react";
import { Link } from "react-router";
import { useUsersCollection } from "../features/common/hooks/use-users-collection";
import { useAuth } from "../features/common/hooks/use-auth";
import { useRemoveItemFromList } from "../features/common/hooks/use-remove-item-from-list";

function CollectionPage() {
  const { data: user } = useAuth();
  const {
    data: collection,
    isLoading,
    error,
  } = useUsersCollection(user?.id || "");

  const { mutate: removeItem, isPending } = useRemoveItemFromList(
    user?.id || "",
    "collection",
  );

  if (!user) {
    return (
      <div className="flex min-h-screen items-center justify-center bg-[#f5f1e8] p-6">
        <div className="text-center">
          <p className="text-gray-600">
            Musisz być zalogowany, aby zobaczyć kolekcję
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
          <p className="text-gray-600">Błąd podczas ładowania kolekcji</p>
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
              <Leaf size={32} className="text-green-600" />
              <h1 className="text-3xl font-bold text-gray-800">
                Moja Kolekcja
              </h1>
            </div>
            <p className="text-gray-700">
              Tutaj znajdziesz wszystkie rośliny, które posiadasz w swojej
              kolekcji. Możesz zarządzać swoimi roślinami i śledzić swój ogród.
            </p>
            <div className="mt-4 flex items-center gap-6">
              <span className="text-gray-600">
                Łącznie roślin: {collection?.length || 0}
              </span>
              <Link
                to="/wishlist"
                className="flex items-center gap-2 text-green-600 transition-colors hover:text-green-700"
              >
                <Heart size={16} />
                Zobacz listę życzeń
              </Link>
            </div>
          </div>

          {collection?.length === 0 ? (
            <div className="rounded-2xl bg-[#d4c4a8] p-12 text-center">
              <Leaf size={48} className="mx-auto mb-4 text-gray-400" />
              <h2 className="mb-2 text-xl font-semibold text-gray-600">
                Twoja kolekcja jest pusta
              </h2>
              <p className="text-gray-500">
                Dodaj swoje pierwsze rośliny, aby rozpocząć budowanie kolekcji!
              </p>
            </div>
          ) : (
            <div className="grid grid-cols-2 gap-6 md:grid-cols-3 lg:grid-cols-4">
              {collection?.map((plant) => (
                <div
                  key={plant.id}
                  className="group relative overflow-hidden rounded-2xl bg-[#d4c4a8] transition-transform duration-200 hover:scale-[1.02]"
                >
                  <button
                    onClick={() => removeItem({ plantId: plant.id })}
                    className="absolute top-3 right-3 z-10 cursor-pointer rounded-full bg-red-500 p-1.5 text-white opacity-0 transition-opacity group-hover:opacity-100 hover:bg-red-600"
                    title="Usuń z kolekcji"
                    disabled={isPending}
                  >
                    <X size={16} />
                  </button>
                  <div className="relative aspect-square overflow-hidden">
                    <img
                      src={plant.imageUrl || ""}
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

export default CollectionPage;
