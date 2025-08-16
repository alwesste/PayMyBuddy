import React, {useEffect, useState} from "react";
import './Profil.scss'
import Layout from "../../components/Layout/layout.tsx";

const Profil: React.FC = () => {

    const currentUserEmail: string | null = localStorage.getItem("currentUserEmail");
    const currentUserPassword: string | null = localStorage.getItem("currentUserPassword");
    const [successChange, setSuccessChange] = useState<boolean>();
    const [formData, setFormData] = useState({
        username: "",
        email: currentUserEmail ?? "",
        password: currentUserPassword ?? "",
    })
    const [version, setVersion] = useState("");
    useEffect(() => {
        fetch("/api/version")
            .then((res) => res.json())
            .then((data) => setVersion(data.version))
            .catch((err) => console.error("Erreur récupération version:", err));
    }, []);

    const handleChange = (e : React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData, [e.target.name] : e.target.value
        })
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

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
                <p>
                    {successChange ? "La modification a ete prise en compte" : 'Votre requete a echoue'}
                </p>
                <h1>Mon App React</h1>
                <p>Version : {version}</p>


                <button type="submit">Modifier</button>

            </form>


        </Layout>
    )
}

export default Profil;