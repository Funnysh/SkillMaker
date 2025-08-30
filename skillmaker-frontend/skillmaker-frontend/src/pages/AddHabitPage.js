import { use, useState } from "react";
import { apiPost } from "../api/api";
import { useNavigate } from "react-router-dom";

const AddhabitPage = () => {
    const navigate = useNavigate();
    const[form, setForm] = useState({name: "", description: "", frequency: ""});
    const[err,setErr] = useState("");
    const[ok,setOk] = useState("");

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErr("");
        setOk("");
        try {
            const response = await apiPost("/api/habit/create", form);
            navigate("/");
        } catch(err) {
            setErr("Vytvoření selhalo.")
        }
    };

    return (
        <div className="container mt-5">
            <h2>Tvorba návyku</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group mb-3">
                    <label>Název</label>
                    <input
                        type="text"
                        className="form-control"
                        name="name"
                        value={form.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group mb-3">
                    <label>Popis</label>
                    <input
                        type="text"
                        className="form-control"
                        name="description"
                        value={form.description}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="container">
                    <div className="row">
                        <div className="col">
                           <div className="form-check mb-3">
                                <input
                                    className="form-check-input"
                                    type="radio"
                                    name="frequency"
                                    value="DAILY"
                                    checked={form.frequency === "DAILY"}
                                    onChange={handleChange}
                                />
                                <label className="form-check-label">Denně</label>
                            </div> 
                        </div>
                        <div className="col">
                           <div className="form-check mb-3">
                                <input
                                    className="form-check-input"
                                    type="radio"
                                    name="frequency"
                                    value="WEEKLY"
                                    checked={form.frequency === "WEEKLY"}
                                    onChange={handleChange}
                                />
                                <label className="form-check-label">Týdně</label>
                            </div> 
                        </div>
                        <div className="col">
                           <div className="form-check mb-3">
                                <input
                                    className="form-check-input"
                                    type="radio"
                                    name="frequency"
                                    value="MONTHLY"
                                    checked={form.frequency === "MONTHLY"}
                                    onChange={handleChange}
                                />
                                <label className="form-check-label">Měsíčně</label>
                            </div> 
                        </div>
                    </div>
                </div>
                {err && <div className="alert alert-danger">{err}</div>}
                <button type="submit" className="btn btn-primary w-100">
                    Vytvořit
                </button>
            </form>
        </div>
    )
};

export default AddhabitPage;