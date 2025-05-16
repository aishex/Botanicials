import { Menu, X } from "lucide-react";

type Props = {
  isOpen: boolean;
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>;
};

function MobileNavTrigger({ isOpen, setIsOpen }: Props) {
  return (
    <button
      className="hover:bg-dark-beige-hover flex h-10 w-10 cursor-pointer items-center justify-center rounded-full transition-colors duration-200 ease-in-out md:hidden"
      onClick={() => setIsOpen((prev) => !prev)}
    >
      <span className="sr-only">Menu</span>
      {isOpen ? <X /> : <Menu />}
    </button>
  );
}

export default MobileNavTrigger;
