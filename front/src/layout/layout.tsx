import { Outlet } from "react-router";
import Header from "../components/header";

function Layout() {
  return (
    <div className="grid min-h-dvh grid-rows-[auto_1fr] overflow-auto">
      <Header />
      <main>
        <Outlet />
      </main>
    </div>
  );
}
export default Layout;
