import React, { useState} from "react";
import './Profil.scss'
import Layout from "../../components/Layout/layout.tsx";

const Profil: React.FC = () => {

    const currentUserEmail: string | null = localStorage.getItem("currentUserEmail");
    const currentUserPassword: string | null = localStorage.getItem("currentUserPassword");

    const [successChange, setSuccessChange] = useState<boolean | null>(null);
    const [formData, setFormData] = useState({
        username: "",
        email: currentUserEmail ?? "",
        password: currentUserPassword ?? "",
    })

    const handleChange = (e : React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData, [e.target.name] : e.target.value
        })
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setSuccessChange(null);
        try {
            const response = await fetch("http://localhost:8080/api/updatePassword", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });
            if (!response.ok) {
                setSuccessChange(false);
                throw new Error("Échec de la mise à jour.");

            } else {
                localStorage.setItem("currentUserPassword", formData.password);
                setSuccessChange(true);
            }
        } catch (error ) {
            console.error("Erreur :", error);
        }
    };

    return (
        <Layout>
            <form action="" className="profil-container" onSubmit={handleSubmit}>
                <div className="profil-container_form">
                    <label htmlFor="username">Username</label>
                    <input type="text"
                           id="username"
                           name="username"
                           value={formData.username}
                           onChange={handleChange}
                           placeholder="Votre username"/>
                </div>

                <div className="profil-container_form">
                    <label htmlFor="mail">Mail</label>
                    <input type="email"
                           id="email"
                           name="email"
                           value={formData.email}
                           onChange={handleChange}
                           placeholder="Votre mail"/>
                </div>

                <div className="profil-container_form">
                    <label htmlFor="password">Mot de passe</label>
                    <input type="password"
                           id="password"
                           name="password"
                           value={formData.password}
                           onChange={handleChange}
                           placeholder="Votre mot de passe"/>
                </div>

                {successChange === true && <p>La modification a été prise en compte ✅</p>}
                {successChange === false && <p>Votre requête a échoué ❌</p>}



                <button type="submit">Modifier</button>

            </form>


        </Layout>
    )
}

export default Profil;