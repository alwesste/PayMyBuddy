import React, {useEffect, useState} from "react";
import Layout from "../../components/Layout/layout.tsx";
import "./Relation.scss"

interface User {
    email: string,
    username: string,
}

const Relation: React.FC = () => {

    const existingRelations = JSON.parse(localStorage.getItem("relations") || "[]");
    const currentUserEmail: string | null = localStorage.getItem("currentUserEmail");
    const [formData, setFormData] = useState({
        targetUserEmail: "",
        currentUserEmail: currentUserEmail,
    });
    const [connexions, setConnexions] = useState([])
    const [error, setError] = useState("")


    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const  loadConnexion = async() => {
         try {
             const res = await fetch(`http://localhost:8080/api/seeConnexion?currentUserEmail=${currentUserEmail}`)

             if(!res.ok) {
                 throw new Error("Erreur lors du chargement des connexions.");
             }
             const data = await res.json();
             setConnexions(data)

         } catch (error) {
             console.error("Erreur :", error);
             setError("Vous n'avez pas de relation pour le moment");
         }
    }

    useEffect(() => {
        loadConnexion()
    }, [currentUserEmail]);


    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        fetch(`http://localhost:8080/api/addConnexion`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData)
       })
            .then((res) => {
                if (!res.ok) {
                    throw new Error("Erreur lors de l'envoie de la requete d'ajout de relation.");
                }
            })
            .then(() => {
                loadConnexion();
                if (!existingRelations.includes(formData.targetUserEmail)) {
                   existingRelations.push(formData.targetUserEmail)
                }
                localStorage.setItem("relations", JSON.stringify(existingRelations));
                setFormData({...formData, targetUserEmail: ""});
                setError("");
            })

            .catch((err) => {
                console.error("Erreur:", err);
                setError("Impossible de charger les connexions.");
            })
    };

    return (
        <Layout>
            <div className="relation">

                <form className="relationForm">
                    <label
                        className="relationForm-title"
                        htmlFor="relation">
                        Chercher une relation
                    </label>
                    <input
                        className="relationForm-input"
                        id="relation"
                        type="email"
                        name="targetUserEmail"
                        value={formData.targetUserEmail}
                        onChange={handleChange}
                        placeholder="Saisir une adresse mail"
                    />
                </form>

                <button
                    onClick={handleSubmit}
                    className="relationButton"
                    type="submit">Ajouter
                </button>



            </div>

            <div className="relation-name">
                <p className="relation-title">Mes connexions</p>

                {error && <p>{error}</p>}

                {connexions.length > 0 ? (
                    <ul>
                        {connexions.map((user: User, index) => (
                            <li key={index}>{user.username}</li>
                        ))}
                    </ul>
                ) : (
                    <p></p>

                )}
            </div>

        </Layout>
    );
}

export default Relation;