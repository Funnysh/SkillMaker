import { useEffect, useState } from "react";
import { apiDelete, apiGet, apiPost } from "../api/api";

//Hlavní stránka

const HomePage = () => {
    const[habits, setHabits] = useState([]);
    const[err, setErr] = useState("");
    const[ok, setOk] = useState("");
    const[loading, setLoading] = useState(false);
    const[isManage, setIsManage] = useState(false);
    const[deleteHabitId, setDeleteHabitId] = useState(null);

    //Při načtení stránky se vola tahle funkce na GET
    useEffect(() => {
        let mounted = true;

        const fetchHabits = async () => {
            setLoading(true);
            try {
                const data = await apiGet("/api/habit/get");
                if (!mounted) return;
                setHabits(data);
            } catch (e) {
                setErr("Nepodařilo se načíst návyky.");
            } finally {
                if (mounted) setLoading(false);
            }
        };

        fetchHabits();

        return () => {
            mounted = false;
        };
    }, []);

    //Funkce na práci s tlačítkem. Přijímá id návyku, se kterým se pracuje
    const handleDone = async (habitId) => {
        try {
            const updatedHabit = await apiPost(`/api/habit/complete/${habitId}`);
            setHabits((prev) =>
            prev.map((h) => (h.id === updatedHabit.id ? updatedHabit : h))
            ); //Projede všechny návyky a změní pouze ten, který má stejné id
        } catch (err) {
            setErr(err)
            }
    };

    //Funkce na práci s tlačítkem. Přijímá id návyku, který se má odstranit
    const handleDelete = async (habitId) => {
        try {
            await apiDelete(`/api/habit/delete/${habitId}`);
            setHabits((prev) => prev.filter((h) => h.id !== habitId)); //zase projede všechny a odtraní jen ten, který má to id
        } catch (err) {
            setErr("Mazání selhalo")
            }
    };

    //Funkce na přepínání stavu
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
            {/* Pokud existuje id návyku vytvoř tohle */}
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
                {/* vytvoří políčko pro každý návyk, co existuje */}
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