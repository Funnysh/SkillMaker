import React, { useEffect, useMemo, useState } from "react";
import { apiGet, apiPut } from "../api/api";

const Avatar = ({ name }) => {
  const initials = useMemo(() => {
    if (!name) return "?";
    const parts = name.trim().split(/\s+/);
    const a = parts[0]?.[0] ?? "";
    const b = parts[1]?.[0] ?? "";
    return (a + b).toUpperCase();
  }, [name]);

  return (
    <div
        className="rounded-circle d-flex align-items-center justify-content-center bg-primary text-white"
        style={{ width: 72, height: 72, fontWeight: 700 }}
        aria-label={`Avatar ${name}`}
    >
        {initials || "?"}
    </div>
  );
};

const ProfilePage = () => {
  const [me, setMe] = useState(null);
  const [form, setForm] = useState({ username: "", email: "", name: "", surname: "", about: "" });
  const [isEdit, setIsEdit] = useState(false);
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [err, setErr] = useState("");
  const [ok, setOk] = useState("");

  useEffect(() => {
    let mounted = true;
    setLoading(true);
    apiGet("/api/user/me")
        .then((data) => {
            if (!mounted) return;
            setMe(data);
            setForm({ username: data.username || "", email: data.email || "", name: data.name || "", surname: data.surname || "", about: data.about || "" });
        })
        .catch((e) => setErr("Nepodařilo se načíst profil."))
        .finally(() => setLoading(false));
    return () => { mounted = false; };
  }, []);

  const onChange = (e) => {
    setForm((f) => ({ ...f, [e.target.name]: e.target.value }));
  };

  const onSave = async (e) => {
    e.preventDefault();
    setErr("");
    setOk("");
    setSaving(true);
    try {
        const updated = await apiPut("/api/user/me", form);
        setMe(updated);
        setForm({ username: updated.username || "", email: updated.email || "", name: updated.name || "", surname: updated.surname || "", about: updated.about || "" });
        setIsEdit(false);
        setOk("Profil byl úspěšně uložen.");
    } catch (e2) {
        setErr("Uložení selhalo. Zkontroluj údaje nebo to zkus později.");
    } finally {
        setSaving(false);
    }
  };

  if (loading) return <div className="container my-4">Načítám profil…</div>;

  return (
    <div className="container my-4" style={{ maxWidth: 680 }}>
        <div className="d-flex align-items-center gap-3 mb-3">
            <Avatar name={me?.username} />
            <div>
                <h3 className="mb-0">{me?.username}</h3>
                <div className="text-muted">
                    {me?.email} · role: <span className="badge text-bg-secondary">{me?.role}</span>
                </div>
            </div>
            <div className="ms-auto">
                {!isEdit ? (
                    <button className="btn btn-outline-primary" onClick={() => setIsEdit(true)}>
                        Upravit
                    </button>
                ) : (
                    <button className="btn btn-outline-secondary" onClick={() => { setIsEdit(false); setForm({ username: me.username, email: me.email }); }}>
                        Zrušit
                    </button>
                )}
            </div>
        </div>

      {ok && <div className="alert alert-success py-2">{ok}</div>}
      {err && <div className="alert alert-danger py-2">{err}</div>}

      {!isEdit ? (
        <div className="card">
            <div className="card-body">
                <dl className="row mb-0">
                    <dt className="col-sm-3">Uživatelské jméno</dt>
                    <dd className="col-sm-9">{me?.username}</dd>

                    <dt className="col-sm-3">Email</dt>
                    <dd className="col-sm-9">{me?.email}</dd>

                    <dt className="col-sm-3"> Jméno</dt>
                    <dd className="col-sm-9">{me?.name}</dd>

                    <dt className="col-sm-3"> Příjmení</dt>
                    <dd className="col-sm-9">{me?.surname}</dd>

                    <dt className="col-sm-3"> O mně</dt>
                    <dd className="col-sm-9">{me?.about}</dd>

                    <dt className="col-sm-3">Vytvořen</dt>
                    <dd className="col-sm-9">
                    {me?.createdAt ? new Date(me.createdAt).toLocaleString() : "—"}
                    </dd>
                </dl>
            </div>
        </div>
        ) : (
        <form className="card" onSubmit={onSave} noValidate>
            <div className="card-body">
                <div className="mb-3">
                    <label className="form-label">Uživatelské jméno</label>
                    <input
                        name="username"
                        className="form-control"
                        value={form.username}
                        onChange={onChange}
                        required
                        minLength={3}
                        maxLength={32}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Email</label>
                    <input
                        name="email"
                        type="email"
                        className="form-control"
                        value={form.email}
                        onChange={onChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Jméno</label>
                    <input
                        name="name"
                        className="form-control"
                        value={form.name}
                        onChange={onChange}
                        required
                        minLength={3}
                        maxLength={32}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Příjmení</label>
                    <input
                        name="surname"
                        className="form-control"
                        value={form.surname}
                        onChange={onChange}
                        required
                        minLength={3}
                        maxLength={32}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">O mně</label>
                    <input
                        name="about"
                        className="form-control"
                        value={form.about}
                        onChange={onChange}
                        required
                        minLength={0}
                        maxLength={128}
                    />
                </div>
            </div>
            <div className="card-footer d-flex justify-content-end gap-2">
                <button type="button" className="btn btn-outline-secondary" onClick={() => { setIsEdit(false); setForm({ username: me.username, email: me.email }); }}>
                    Zrušit
                </button>
                <button type="submit" className="btn btn-primary" disabled={saving}>
                    {saving ? "Ukládám…" : "Uložit"}
                </button>
            </div>
        </form>
        )}
    </div>
  );
};

export default ProfilePage;
