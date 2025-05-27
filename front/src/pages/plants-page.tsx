import PlantCard from "../features/plants-page/components/plant-card";
import { useFetchPlants } from "../features/plants-page/hooks/useFetchPlants";

function PlantsPage() {
  const { data: plants, isLoading, error } = useFetchPlants();

  if (isLoading)
    return (
      <Wrapper>
        <h1 className="text-center">Loading...</h1>
      </Wrapper>
    );
  if (error)
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
        {plants &&
          plants.map((plant) => <PlantCard key={plant.id} plant={plant} />)}
      </section>
    </Wrapper>
  );
}

export default PlantsPage;

const Wrapper = ({ children }: { children: React.ReactNode }) => {
  return (
    <div className="bg-lighest-beige flex h-full flex-col justify-start gap-6 px-6 py-20">
      {children}
    </div>
  );
};
