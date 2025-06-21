import React, {useEffect, useState} from "react";
import Layout from "../../components/Layout/layout.tsx";
import "./Relation.scss"

interface User {
    email: string;
}

const Relation: React.FC = () => {

    const [formData, setFormData] = useState({email: ""});
    const [connexions, setConnexions] = useState([])
    const [error, setError] = useState("")
    const currentUserEmail = "shiori@gmail.com";

    useEffect(() => {
        fetch(`http://localhost:8080/api/seeConnexion?currentUserEmail=${currentUserEmail}`)
            .then((res) => {
                if (!res.ok) throw new Error("Erreur lors du chargement des connexions.");
                return res.json();
            })
            .then((data) => {
                setConnexions(data);
            })
            .catch((err) => {
                throw err;
                setError("Impossible de charger les connexions.");
            });
    }, []);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
    };

    return (
        <Layout>
            <div>
                <h2>Mes connexions</h2>

                {error && <p className="error">{error}</p>}

                {connexions.length > 0 ? (
                    <ul>
                        {connexions.map((user: User, index) => (
                            <li key={index}>{user.email}</li>
                        ))}
                    </ul>
                ) : (
                    <p>Aucune connexion trouvée.</p>
                )}

                <form className="relationForm" onSubmit={handleSubmit}>
                    <label htmlFor="relation">Chercher une relation</label>
                    <input
                        className="relationForm-input"
                        id="relation"
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        placeholder="Saisir une adresse mail"
                    />
                    <button type="submit">Ajouter</button>
                </form>
            </div>
        </Layout>
    );
}

export default Relation;