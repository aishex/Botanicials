import { Search } from "lucide-react";
import { useState } from "react";

function PlantQueryInput({ className }: { className?: string }) {
  const [query, setQuery] = useState("");

  //   const onSubmit = () => {};

  return (
    <form
      className={`bg-light-beige flex w-full items-center justify-between rounded-full px-4 py-2 ${className}`}
    >
      <input
        type="text"
        placeholder="Search..."
        className="grow text-white"
        value={query}
        onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
          setQuery(e.target.value)
        }
      />
      <Search className="text-white" />
    </form>
  );
}

export default PlantQueryInput;
