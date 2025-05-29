import { useSearchParams } from "react-router";
import PlantCard from "../features/plants-page/components/plant-card";
import { useFetchPlants } from "../features/plants-page/hooks/useFetchPlants";
import React from "react";

function PlantsPage() {
  const [searchParams] = useSearchParams();
  const query = searchParams.get("query") || "";

  const {
    data,
    error,
    fetchNextPage,
    hasNextPage,
    isFetching,
    isFetchingNextPage,
    status,
  } = useFetchPlants(query);

  if (status === "pending")
    return (
      <Wrapper>
        <h1 className="text-center">Loading...</h1>
      </Wrapper>
    );
  if (status === "error")
    return (
      <Wrapper>
        <h1>Error fetching plants</h1>
        <p>{error.message}</p>
      </Wrapper>
    );

  return (
    <Wrapper>
      <h1 className="text-center text-4xl font-bold">All Plants</h1>
      <section className="grid grid-cols-[repeat(auto-fill,minmax(255px,1fr))] justify-center gap-6">
        {data &&
          data.pages.map((group, i) => (
            <React.Fragment key={i}>
              {group.map((plant) => (
                <PlantCard key={plant.id} plant={plant} />
              ))}
            </React.Fragment>
          ))}
      </section>

      <div className="mt-8 flex justify-center">
        <button
          onClick={() => fetchNextPage()}
          disabled={!hasNextPage || isFetching}
          className="cursor-pointer rounded-full bg-green-800 px-6 py-2 font-semibold text-white transition-colors hover:bg-green-950 disabled:cursor-not-allowed disabled:opacity-50"
        >
          {isFetchingNextPage
            ? "Loading more..."
            : hasNextPage
              ? "Load More"
              : "Nothing more to load"}
        </button>
      </div>
      <div className="text-center text-sm text-gray-500">
        {isFetching && !isFetchingNextPage && "Fetching..."}
      </div>
    </Wrapper>
  );
}

export default PlantsPage;

const Wrapper = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="bg-lighest-beige flex h-full flex-col justify-start gap-6 px-6 pt-20 pb-6">
      {children}
    </div>
  );
};
