import { useEffect, useState } from "react";
import { apiDelete, apiGet, apiPost } from "../api/api";

const HomePage = () => {
    const[habits, setHabits] = useState([]);
    const[err, setErr] = useState("");
    const[ok, setOk] = useState("");
    const[loading, setLoading] = useState(false);
    const[isManage, setIsManage] = useState(false);
    const[deleteHabitId, setDeleteHabitId] = useState(null);

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

    const handleDone = async (habitId) => {
        
        try {
            const updatedHabit = await apiPost(`/api/habit/complete/${habitId}`);
            setHabits((prev) =>
            prev.map((h) => (h.id === updatedHabit.id ? updatedHabit : h))
            );
        } catch (err) {
            setErr(err)
            }
    };

    const handleDelete = async (habitId) => {
        try {
            await apiDelete(`/api/habit/delete/${habitId}`);
            setHabits((prev) => prev.filter((h) => h.id !== habitId));
        } catch (err) {
            setErr("Mazání selhalo")
            }
    };

    const handleManage = () => {
        setIsManage((prev) => !prev);
    };


    return(
        <div className="container">
            <div className="d-flex justify-content-between align-items-center mb-3">
                <h4 className="mb-0">Aktivní návyky</h4>
                <button
                    className="btn btn-outline-danger"
                    onClick={handleManage}
                >
                    {isManage ? "Zavřít správu" : "Spravovat"}
                </button>
            </div>
            {deleteHabitId  && (
            <div className="border bg-light z-1 position-absolute rounded-2 top-20 start-50 translate-middle p-3 text-center">
                <h3 className="mb-3">Opravdu odstranit tento návyk?</h3>
                <p className="text-muted">
                    "{habits.find((h) => h.id === deleteHabitId)?.name}"
                </p>
                <div className="d-flex gap-2 mt-2">
                    <button
                        className="btn btn-outline-danger flex-fill"
                        onClick={() => {
                            handleDelete(deleteHabitId);
                            setDeleteHabitId(null);
                        }}
                    >
                        Ano
                    </button>
                    <button
                    className="btn btn-outline-success flex-fill"
                    onClick={() => {
                        setDeleteHabitId(null);
                    }}
                    >
                        Ne
                    </button>
                </div>
            </div>
            )}

            <div className="row">
                {habits.map((habit) => (
                    <div key={habit.id} className="col-12 col-sm-6 col-md-3 mb-4">
                        <div className="card h-100">
                            <div className="card-body">
                                <h5 className="card-title">{habit.name}</h5>
                                <p className="card-text muted">Lvl: {habit.level}</p>
                                <p className="card-text muted">Xp: {habit.xp}</p>
                                <p className="card-text muted">Streak: {habit.streak}</p>
                                <div className="mt-auto d-flex gap-2">
                                    <button
                                        className="btn btn-success flex-fill"
                                        onClick={() => handleDone(habit.id)}
                                    >
                                        Splnit
                                    </button>
                                    {isManage && (
                                        <button
                                            className="btn btn-outline-danger flex-fill"
                                            onClick={() => {
                                                setDeleteHabitId(habit.id);
                                            }}
                                        >
                                            x
                                        </button>
                                    )}
                                </div>
                                
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default HomePage;