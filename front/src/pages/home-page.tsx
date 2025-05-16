import AboutUs from "../features/home-page/components/about-us";
import BgPlantImage from "../features/home-page/components/bg-plant-image";
import LinkCard from "../features/home-page/components/link-card";

function HomePage() {
  return (
    <div className="bg-lighest-beige relative h-full space-y-16 px-2 py-20">
      <BgPlantImage />
      <AboutUs />

      <div className="mx-auto grid max-w-[1200px] grid-cols-1 gap-4 md:grid-cols-3">
        <LinkCard
          title="Roślina dnia"
          imageUrl="https://plus.unsplash.com/premium_photo-1673203734665-0a534c043b7f?w=700&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjF8fHBsYW50fGVufDB8fDB8fHww"
        />

        <LinkCard
          title="Losowa roślina"
          imageUrl="https://images.unsplash.com/photo-1601985705806-5b9a71f6004f?q=80&w=987&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        />

        <LinkCard
          title="Polecenie od Autorów"
          imageUrl="https://images.unsplash.com/photo-1446292532430-3e76f6ab6444?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fHBsYW50c3xlbnwwfHwwfHx8MA%3D%3D"
        />
      </div>
    </div>
  );
}

export default HomePage;
