import { Link } from "react-router";

const plantId = "1"; // TODO: swap it

type Props = {
  title: string;
  imageUrl: string;
};

function LinkCard({ title, imageUrl }: Props) {
  return (
    <Link to={`/plants/${plantId}`}>
      <div className="bg-dark-green grid h-full grid-rows-[64px,1fr] rounded-xl p-4 text-white">
        <h2 className="row-span-2 mb-6 text-3xl md:text-2xl">{title}</h2>
        <img
          src={imageUrl}
          alt={title}
          className="h-48 w-full grow rounded-2xl object-cover"
        />
      </div>
    </Link>
  );
}

export default LinkCard;
