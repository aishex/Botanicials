import { Outlet } from "react-router";
import Header from "../components/header";

function Layout() {
  return (
    <div className="grid h-dvh grid-rows-[auto_1fr] overflow-auto">
      <Header />
      <main className="grow">
        <Outlet />
      </main>
    </div>
  );
}
export default Layout;
