import { Link } from "react-router";
import AboutUs from "../features/home-page/components/about-us";
import BgPlantImage from "../features/home-page/components/bg-plant-image";

const plantId = "1"; // TODO: swap it

function HomePage() {
  return (
    <div className="bg-lightest-beige relative h-full space-y-16 px-2 py-20">
      <BgPlantImage />
      <AboutUs />

      <div>
        <Link to={`/plant/${plantId}`}>
          <div className="bg-dark-green rounded-xl p-4 text-white">
            <p className="mb-2 text-xs">ZNAJDŹ COŚ DLA SIEBIE</p>
            <h2 className="mb-6 text-3xl">Roślina dnia</h2>
            <img
              src="https://plus.unsplash.com/premium_photo-1673203734665-0a534c043b7f?w=700&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjF8fHBsYW50fGVufDB8fDB8fHww"
              alt="Roślina dnia"
              className="max-h-48 w-full rounded-2xl object-cover"
            />
          </div>
        </Link>
      </div>
    </div>
  );
}

export default HomePage;
