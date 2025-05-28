import { Search } from "lucide-react";
import { useState } from "react";
import { useNavigate } from "react-router";

function PlantQueryInput({ className }: { className?: string }) {
  const [query, setQuery] = useState("");
  const navigate = useNavigate();

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!query) {
      return;
    }
    navigate(`/plants?query=${query}`);
    setQuery("");
  };

  return (
    <form
      className={`bg-light-beige focus-within:outline-dark-green flex w-full items-center justify-between rounded-full px-4 py-2 focus-within:outline-3 focus-within:outline-offset-2 ${className}`}
      onSubmit={onSubmit}
    >
      <input
        type="text"
        placeholder="Search..."
        className="grow text-white focus-visible:outline-none!"
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
