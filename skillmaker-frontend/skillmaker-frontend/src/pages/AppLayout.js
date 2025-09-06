import { Outlet, NavLink, useNavigate } from "react-router-dom";

//Stálý základ stránky. Přijímá onLogout

const AppLayout = ({ onLogout }) => {
  const navigate = useNavigate(); //Přesměrování

  //Funkce na práci s odhlášením. Předaná funkce, která vymaže token
  const handleLogout = () => {
    if (onLogout) onLogout(); //Pokud se předá funkce, zavolej ji
    navigate("/login", { replace: true }); //Replace nahradí historii a uživatel se nemůže vrátit
  };

  return (
    <div className="d-flex flex-column min-vh-100">
      <nav className="navbar navbar-light bg-light border-bottom">
        <div className="container d-flex justify-content-between align-items-center">
          <NavLink to="/" className="navbar-brand">SkillMaker</NavLink>
          <div className="d-flex gap-2" style={{ minWidth: "300px" }}>
            <button className="btn btn-primary flex-fill" style={{ maxWidth: "100px" }} onClick={() => navigate("/add")}>
              Přidat
            </button>
            <button className="btn btn-outline-secondary flex-fill" style={{ maxWidth: "100px" }} onClick={() => navigate("/me")}>
              Profil
            </button>
            <button className="btn btn-outline-danger flex-fill" style={{ maxWidth: "100px" }} onClick={handleLogout}>
              Odhlásit
            </button>
          </div>
        </div>
      </nav>

      <main className="container my-4">
        <Outlet /> {/* Načtení obsahu aktuálního modulu */}
      </main>

        <footer className="mt-auto py-3 bg-light">
        <div className="container text-center text-muted">
          © {new Date().getFullYear()} SkillMaker
        </div>
      </footer>
    </div>
  );
};

export default AppLayout;
