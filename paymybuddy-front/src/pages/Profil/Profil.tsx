import React, {useState} from "react";
import './Profil.scss'
import Layout from "../../components/Layout/layout.tsx";

const Profil: React.FC = () => {

    const currentUserEmail: string | null = localStorage.getItem("currentUserEmail");
    const currentUserPassword: string | null = localStorage.getItem("currentUserPassword");
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

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        try {
            const response = await fetch("http://localhost:8080/api/updatePassword", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || "Échec de la mise à jour.");
            }
        } catch (error ) {
            console.error("Erreur :", error);
        }
    };

    return (
        <Layout>
            <form action="" className="profil-container" onSubmit={handleSubmit}>
                <div  className="profil-container_form">
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


                <button type="submit">Modifier</button>
            </form>
        </Layout>
    )
}

export default Profil;