import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import HomePage from "./pages/HomePage";
import "bootstrap/dist/css/bootstrap.min.css";
import LoginPage from "./pages/LoginPage";
import { useEffect, useState } from "react";
import RegisterPage from "./pages/RegisterPage";

const App = () => {
  const [token, setToken] = useState(localStorage.getItem("token"));

  useEffect(() => {
    // Listener na změnu tokenu v localStorage z jiných záložek
    const handleStorageChange = () => {
        setToken(localStorage.getItem("token"));
    };

    window.addEventListener("storage", handleStorageChange);

    return () => {
        window.removeEventListener("storage", handleStorageChange);
    };
  }, []);

  const isLoggedIn = !!token;

  return (
    <Router>
      <Routes>
        <Route path="/" element={isLoggedIn ? <HomePage /> : <Navigate to="/login" />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Routes>
    </Router>
  );
};

export default App;
