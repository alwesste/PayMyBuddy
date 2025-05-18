import React from "react";
import Layout from "../../components/Layout/layout.tsx";
import "./Relation.scss"

const Relation: React.FC = () => {
    return (
        <Layout>
            <form className="relation">
                <div className="relationForm">
                    <label className="" htmlFor="relation">Chercher une relation</label>
                    <input
                        className="relationForm-input"
                        id="relation"
                        type="email"
                        name="email"
                        placeholder="Saisir une adresse mail"/>
                </div>

                <button type="submit">Ajouter</button>
            </form>


        </Layout>
    )
}

export default Relation;