import { CheckCircle, Heart } from "lucide-react";
import { useFetchPlantInfo } from "../features/plant-page/hooks/useFetchPlantInfo";
// import { useAuth } from "../hooks/use-auth";
import { useParams } from "react-router";

function PlantPage() {
  const { plantId } = useParams();

  // TODO: add functionality to add plant to wishlist/collection && add validation
  // const { data: user } = useAuth();

  const { data, isLoading, error } = useFetchPlantInfo(plantId!);

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

  return (
    <Wrapper>
      <div className="grid grid-cols-1 gap-4 md:grid-cols-[auto_1fr]">
        <div className="bg-light-green h-fit max-w-[800px] rounded-2xl p-4 text-white md:order-2">
          <div className="mb-4 grid grid-cols-[1fr_auto_auto] items-start gap-4">
            <h1 className="text-3xl font-bold">{data.common_name}</h1>
            <button className="cursor-pointer rounded-full bg-pink-400 p-2 whitespace-nowrap text-white transition-colors hover:bg-pink-600">
              <Heart
                color="white"
                size={26}
                fill="transparent"
                strokeWidth="1.5"
              />
            </button>
            <button className="cursor-pointer rounded-full bg-green-900 p-2 whitespace-nowrap text-white transition-colors hover:bg-green-950">
              <CheckCircle
                color="white"
                size={26}
                fill="transparent"
                strokeWidth="1.5"
              />
            </button>
          </div>
          <p className="mb-6 text-sm font-semibold">
            Care Level: {data.care_level}
          </p>
          <p className="font-medium">{data.description}</p>
        </div>
        <img
          src={
            data.default_image
              ? data.default_image?.original_url
              : "https://placehold.co/600x400?text=Image+Placeholder"
          }
          alt={data.common_name}
          className="h-64 w-full rounded-2xl object-cover md:order-1 md:h-80 md:max-w-[450px] md:object-fill"
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
