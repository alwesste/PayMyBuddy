import "./Connexion.scss"
import React from "react";
import {useNavigate} from "react-router-dom";

const Connexion: React.FC = () => {

    const navigate = useNavigate();

    function handleLogin() {
        navigate('/transfer')
    }

    return (
        <div className="container_connexion">
            <h1 className="container_connexion-title">Pay My Buddy</h1>

            <input
                className="container_connexion-input"
                type="email"
                name="email"
                placeholder="Mail"
            />

            <input
                className="container_connexion-input"
                type="password"
                name="password"
                placeholder="Mot de passe"/>

            <button
                className="container_connexion-submit"
                onClick={handleLogin}
            >
                Se connecter</button>

        </div>
    )
}

export default Connexion;