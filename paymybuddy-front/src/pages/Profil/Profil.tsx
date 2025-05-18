import React from "react";
import './Profil.scss'
import Layout from "../../components/Layout/layout.tsx";

const Profil: React.FC = () => {
    return(
        <Layout>
            <div className="profil-container">
                <p>Username</p>
                <p>Mail</p>
                <p>Mot de passe</p>
            </div>

        </Layout>
    )
}

export default Profil;