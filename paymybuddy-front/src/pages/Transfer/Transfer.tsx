import React, {useState} from "react";
import "./Transfer.scss"
import Layout from "../../components/Layout/layout.tsx";

const Transfer: React.FC = () => {

    const [relation, setRelation] = useState<string>("");

    return (
        <Layout>
            <form className="form-container">
                <select
                    className="form-transfer"
                    value={relation}
                    name="relation"
                    id="relation"
                    onChange={(e) => setRelation(e.target.value)}
                >
                    <option value="">Sélectionner une relation</option>
                    <option value="Choix 1">Choix 1</option>
                    <option value="Choix 2">Choix 2</option>
                </select>
                <input className="form-transfer" type="text" placeholder="Description"/>
                <input className="form-transfer price" type="number" step="10" placeholder="0€"/>

                <button className="form-transfer btn">Payer</button>
            </form>

            <div className="transfer">
                <p>Mes transactions</p>

                <div className="transfer-payment">
                    <p className="transfer-payment-detail"><span>Relation</span><span>Description</span><span>Montant</span></p>
                </div>

            </div>
        </Layout>
    )
}

export default Transfer