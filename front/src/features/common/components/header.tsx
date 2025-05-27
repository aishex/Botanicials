import { useState } from "react";
import HeaderLogo from "./header-logo";
import UserAuthDisplay from "./header-user-auth-display";
import MobileNav from "./mobile-nav";
import MobileNavTrigger from "./mobile-nav-trigger";
import DesktopNavigation from "./desktop-navigation";

function Header() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <header className="bg-dark-beige relative z-10 px-4 py-2 text-white">
      <div className="mx-auto flex w-full max-w-[1920px] items-start justify-between gap-10">
        <MobileNavTrigger isOpen={isOpen} setIsOpen={setIsOpen} />
        <HeaderLogo />

        <DesktopNavigation />

        <UserAuthDisplay />
        {isOpen && <MobileNav onClose={() => setIsOpen(false)} />}
      </div>
    </header>
  );
}

export default Header;
