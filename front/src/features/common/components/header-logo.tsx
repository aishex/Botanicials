import { Link } from "react-router";

function HeaderLogo() {
  const handleClick = (event: React.MouseEvent<HTMLAnchorElement>) => {
    event.currentTarget.blur();
  };

  return (
    <div className="focus-within:outline-dark-green h-auto w-16 focus-within:outline-[3px]">
      <Link
        to="/"
        className="focus-visible:outline-none!"
        onClick={handleClick}
      >
        <img
          src="/logo.png"
          alt="Logo"
          className="h-full w-full object-contain"
          loading="lazy"
        />
      </Link>
    </div>
  );
}

export default HeaderLogo;
