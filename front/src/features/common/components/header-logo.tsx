import { Link } from "react-router";

function HeaderLogo() {
  return (
    <div className="h-auto w-16">
      <Link to="/">
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
