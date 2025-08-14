import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import HomePage from "./pages/HomePage";
import "bootstrap/dist/css/bootstrap.min.css";
import LoginPage from "./pages/LoginPage";
import { useEffect, useState } from "react";
import RegisterPage from "./pages/RegisterPage";
import ProfilePage from "./pages/ProfilePage";
import AppLayout from "./pages/AppLayout";

const App = () => {
  const [token, setToken] = useState(localStorage.getItem("token"));

  useEffect(() => {
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
        {/* veřejné */}
        <Route path="/login" element={<LoginPage onLogin={() => setToken(localStorage.getItem("token"))} />} />
        <Route path="/register" element={<RegisterPage />} />

        {/* chráněné */}
        <Route element={isLoggedIn ? <AppLayout onLogout={() => { localStorage.removeItem('token'); setToken(null); }} /> : <Navigate to="/login" replace />}>
          <Route path="/" element={<HomePage />} />
          <Route path="/me" element={<ProfilePage />} />
        </Route>

        {/* fallback */}
        <Route path="*" element={<Navigate to={isLoggedIn ? "/" : "/login"} replace />} />
      </Routes>
    </Router>
  );
};

export default App;
