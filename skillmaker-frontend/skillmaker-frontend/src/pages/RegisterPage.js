import { use, useState } from "react";
import { useNavigate } from "react-router-dom"
import { apiPost } from "../api/api";

//Stránka na registraci

const RegisterPage = () => {
    const navigate = useNavigate();

    const [form, setForm] = useState({ username: "", email: "", password: ""});
    const [error, setError] = useState(null);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);

        try {
            await apiPost("/api/auth/register", form);

            navigate("/login");
        } catch (err) {
            setError("Registrace selhala. Zkontrolujte údaje.");
        }
    }

    return (
        <div className="container mt-5" style={{ maxWidth: "400px" }}>
            <h2>Registrace</h2>
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
                    <label>E-mail</label>
                    <input
                        type="text"
                        className="form-control"
                        name="email"
                        value={form.email}
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
                    Registrovat se
                </button>                                
            </form>
            <div class="text-center">
                <a href="/login" class="btn btn-link">Máš už účet? Přihlaš se</a>
            </div>
        </div>
    )
}

export default RegisterPage;