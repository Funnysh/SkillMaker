import { useEffect, useState } from "react";
import { apiGet, apiPost } from "../api/api";

const HomePage = () => {
    const [habits, setHabits] = useState([]);
    const[err, setErr] = useState("");
    const[ok, setOk] = useState("");
    const[loading, setLoading] = useState();

    useEffect(() => {
        let mounted = true;
        setLoading(true);
        apiGet("/api/habit/get")
            .then((data) => {
                if(!mounted) return;
                setHabits(data);
            })
            .catch((e) => setErr("Nepodařilo se načíst návyky."))
            .finally(() => setLoading(false));
        return () => { mounted = false };
    }, []);

    return(
        <div className="container">
            <h1 className="text-center">Dashboard</h1>
            <h4>Aktivní návyky</h4>
            <div className="row">
                {habits.map((habit) => (
                    <div key={habit.id} className="col-12 col-sm-6 col-md-3 mb-4">
                        <div className="card h-100">
                            <div className="card-body">
                                <h5 className="card-title">{habit.name}</h5>
                                <p className="card-text muted">Lvl: {habit.level}</p>
                                <p className="card-text muted">Xp: {habit.xp}</p>
                                <p className="card-text muted">Streak: {habit.streak}</p>
                                <button
                                    className="btn btn-success"
                                    onClick={async () => {
                                        try {
                                            const updatedHabit = await apiPost(`/api/habit/complete/${habit.id}`);
                                            setHabits((prev) =>
                                                prev.map((h) => (h.id === updatedHabit.id ? updatedHabit : h))
                                            );
                                        } catch (err) {
                                            setErr(err)
                                        }
                                    }}
                                >
                                    Splnit
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default HomePage;