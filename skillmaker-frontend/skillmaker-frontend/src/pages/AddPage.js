import { Navigate, useNavigate } from "react-router-dom";

const AddPage = () => {
    const navigate = useNavigate();
    return (
        <div className="d-flex justify-content-center mt-5 gap-3">
            <div className="card text-center" style={{ width: "300px", height: "300px" }}>
                <div className="card-body">
                    <h5 className="card-title">Vytvořit nový návyk</h5>
                    <button
                        className="btn btn-primary flex-fill"
                        style={{ maxWidth: "200px" }}
                        onClick={() => navigate("/addHabit")}
                    >
                        Vytvořit návyk
                    </button>
                </div>
            </div>

            <div className="card text-center" style={{ width: "300px", height: "300px" }}>
                <div className="card-body">
                    <h5 className="card-title">Vytvořit nový skill</h5>
                    <button
                        className="btn btn-primary flex-fill"
                        style={{ maxWidth: "200px" }}
                        onClick={() => navigate("/addHabit")}
                    >
                        Vytvořit skill
                    </button>
                </div>
            </div>
        </div>
    )
}

export default AddPage;