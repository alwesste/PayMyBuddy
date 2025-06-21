import "./Inscription.scss"
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";

const Inscription: React.FC = () => {

    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        username: "",
        email: "",
        password: ""
    })

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = () => {
        fetch("http://localhost:8080/api/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erreur lors de l'inscription");
                }
                console.log("Inscription réussie, statut :", response.status);
                navigate("/transfer");
            })
            .catch(error => {
                console.error("Erreur :", error);
            });
    };


    return (
        <div className="container_inscription">
            <h1 className="container_inscription-title">Pay My Buddy</h1>

            <input
                className="container_inscription-input"
                type="text"
                name="username"
                placeholder="Username"
                value={formData.username}
                onChange={handleChange}
            />

            <input
                className="container_inscription-input"
                type="email"
                name="email"
                placeholder="Mail"
                value={formData.email}
                onChange={handleChange}
            />

            <input
                className="container_inscription-input"
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                placeholder="Mot de passe"/>

            <button className="container_inscription-submit"
                    type="submit"
                    onClick={handleSubmit}>
                S'inscrire
            </button>

        </div>
    )
}

export default Inscription