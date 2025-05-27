import { Link } from "react-router";
import { Plant } from "../../common/types/plant-type";

function PlantCard({ plant }: { plant: Plant }) {
  return (
    <Link
      to={`../plants/${plant.id}`}
      key={plant.id}
      className="overflow-hidden rounded-2xl bg-white shadow-lg transition-shadow hover:shadow-2xl"
    >
      <img
        src={
          plant.default_image?.medium_url ??
          "https://placehold.co/600x400?text=Image+Placeholder"
        }
        alt={plant.common_name}
        className="h-48 w-full object-cover"
        loading="lazy"
      />
      <div className="p-4">
        <h2 className="text-lg font-semibold text-gray-900">
          {plant.common_name}
        </h2>
        <p className="text-sm text-gray-600 italic">
          {plant.scientific_name[0]}
        </p>
      </div>
    </Link>
  );
}

export default PlantCard;
