import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiPost } from "../api/api";

const LoginPage = ({ onLogin }) => {
  const navigate = useNavigate();

  const [form, setForm] = useState({ username: "", password: "" });
  const [error, setError] = useState(null);

  const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);

    try {
      const response = await apiPost("/api/auth/login", form);

        localStorage.setItem("token", response.token);
        onLogin?.();
        navigate("/");
        console.log("Odpověď z backendu:", response);
    } catch (err) {
        setError("Přihlášení selhalo. Zkontroluj přihlašovací údaje.");
    }
  };

  return (
        <div className="container mt-5" style={{ maxWidth: "400px" }}>
            <h2>Přihlášení</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group mb-3">
                    <label>Uživatelské jméno</label>
                    <input
                    type="text"
                    className="form-control"
                    name="username"
                    value={form.username}
                    onChange={handleChange}
                    required
                    />
                </div>
                <div className="form-group mb-3">
                    <label>Heslo</label>
                    <input
                        type="password"
                        className="form-control"
                        name="password"
                        value={form.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                {error && <div className="alert alert-danger">{error}</div>}
                <button type="submit" className="btn btn-primary w-100">
                    Přihlásit se
                </button>
            </form>
            <div class="text-center">
                <a href="/register" class="btn btn-link">Nemáš účet? založi si ho</a>
            </div>
        </div>
    );
};

export default LoginPage;
