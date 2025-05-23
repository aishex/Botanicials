import { Route, Routes } from "react-router";
import HomePage from "./pages/home-page";
import Layout from "./layout/layout";
import LoginPage from "./pages/login-page";
import PlantPage from "./pages/plant-page";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<HomePage />} />
        <Route path="login" element={<LoginPage />} />
        <Route path="plant/:plantId" element={<PlantPage />} />
        <Route path="*" element={<div>404 Not Found</div>} />
      </Route>
    </Routes>
  );
}

export default App;
