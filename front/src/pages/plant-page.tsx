import { useParams } from "react-router";

function PlantPage() {
  const { plantId } = useParams();
  console.log(plantId);
  return <div className="bg-lighest-beige h-full">plant page</div>;
}

export default PlantPage;
