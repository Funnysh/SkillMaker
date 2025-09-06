import { useState } from "react";
import { apiPost } from "../api/api";
import { useNavigate } from "react-router-dom";

//Stránka na přidání návku

const AddhabitPage = () => {
    const navigate = useNavigate(); //Přesměrování
    const[form, setForm] = useState({name: "", description: "", frequency: ""}); //Práce se stavem formuláře
    const[err,setErr] = useState(""); //Stav erroru
    const[ok,setOk] = useState(""); //Stav ok

    //Funkce na práci se změnou 
    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value }); //Nastavuje stav formuláře (podle atributu name, co je v kolonce)
    };

    //Fuknce na odeslání dat formuláře
    const handleSubmit = async (e) => {
        e.preventDefault(); //Zabrání realoadu stránky po stisknutí
        setErr(""); //Nasaví error
        setOk(""); //Naství ok
        try {
            await apiPost("/api/habit/create", form); //Zkusí poslat požadavek na POST s daty z formuláře
            navigate("/"); //Přesměrování
        } catch(err) { //Pokud chyba, hoď tohle
            setErr("Vytvoření selhalo.") //Nastaví error hlášku
        }
    };

    return (
        <div className="container mt-5">
            <h2>Tvorba návyku</h2>
            <form onSubmit={handleSubmit}> {/* když se stiskne submit, zavolá funkci */}
                <div className="form-group mb-3">
                    <label>Název</label>
                    <input
                        type="text"
                        className="form-control"
                        name="name"
                        value={form.name}
                        onChange={handleChange} /* při jakékoli změně volá funkci */
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
                {err && <div className="alert alert-danger">{err}</div>} {/* když error není null, vytvoří se div s textem erroru */}
                <button type="submit" className="btn btn-primary w-100">
                    Vytvořit
                </button>
            </form>
        </div>
    )
};

export default AddhabitPage;