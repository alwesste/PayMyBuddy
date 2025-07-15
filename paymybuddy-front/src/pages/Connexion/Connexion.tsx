import "./Connexion.scss"
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";

const Connexion: React.FC = () => {

    const navigate = useNavigate();
    const [errorMessage, setErrorMessage] = useState("");
    const [formData, setFormData] = useState({
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
        fetch("http://localhost:8080/api/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
            if (!response.ok) {
               throw new Error("Le mot de passe ou le nom d'utilisateur est errone");
            }
            return response.json();
        })
            .then(data => {
                localStorage.setItem("currentUserEmail", data.email);
                localStorage.setItem("currentUserPassword", data.password);
                navigate("/transfer");

            })
            .catch(error => {
                console.error("Erreur" ,error);
                setErrorMessage(error.message);
            })
    }

    return (
        <div className="container_connexion">
            <h1 className="container_connexion-title">Pay My Buddy</h1>

            <input
                className="container_connexion-input"
                type="email"
                name="email"
                placeholder="Mail"
                value={formData.email}
                onChange={handleChange}
            />

            <input
                className="container_connexion-input"
                type="password"
                name="password"
                placeholder="Mot de passe"
                value={formData.password}
                onChange={handleChange}
            />
            {errorMessage && (
                <p>
                    {errorMessage}
                </p>
            )}

            <button
                className="container_connexion-submit"
                onClick={handleSubmit}
            >
                Se connecter
            </button>

        </div>
    )
}

export default Connexion;