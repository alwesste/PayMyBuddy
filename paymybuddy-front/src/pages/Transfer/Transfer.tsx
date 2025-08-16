import React, {useEffect, useState} from "react";
import "./Transfer.scss"
import {User} from  "../../types/User.ts";
import {Transaction} from  "../../types/Transaction.ts"
import Layout from "../../components/Layout/layout.tsx";
const Transfer: React.FC = () => {

    const currentUserEmail = localStorage.getItem("currentUserEmail");
    const [connexions, setConnexions] = useState<User[]>([]);
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const [formData, setFormData] = useState({
        senderUsername: currentUserEmail,
        receiverUsername: "",
        description: "",
        amount: "",
    });

    const loadConnexion = async () => {
        try {
            const res = await fetch(`http://localhost:8080/api/seeConnexion?currentUserEmail=${currentUserEmail}`)

            if (!res.ok) {
                throw new Error("Erreur lors du chargement des connexions.");
            }
            const data: User[] = await res.json();
            setConnexions(data)

        } catch (error) {
            console.error("Erreur :", error);
        }
    }

    const loadTransactions = async () => {
        try {
            const res = await fetch(`http://localhost:8080/api/seeTransaction?currentUserEmail=${currentUserEmail}`);
            if (!res.ok) {
                throw new Error("Erreur lors du chargement des transactions.");
            }
            const data: Transaction[] = await res.json();
            setTransactions(data);
        } catch (error) {
            console.error("Erreur :", error);
        }
    }

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();

        if (formData.amount <=  "0") {
            setErrorMessage("Vous ne pouvez envoyer un  montant de 0 euros")
            return;
        }
        fetch("http://localhost:8080/api/transaction", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData)
        })
            .then((res) => {
                if (!res.ok) {
                    throw new Error("Erreur lors du chargement des transfers.");
                }
                loadTransactions();
            })
            .catch((err) => {
                console.error("Erreur :", err);
            })
    }

    useEffect(() => {
        loadConnexion();
        loadTransactions()
    }, [currentUserEmail])

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    return (
        <Layout>
            <form className="form-container" onSubmit={handleSubmit}>
                <select
                    className="form-transfer"
                    value={formData.receiverUsername}
                    name="receiverUsername"
                    id="relation"
                    required
                    onChange={async (e) => {
                        setFormData({
                            ...formData,
                            receiverUsername: e.target.value

                        });
                    }}
                >
                    <option value="">Sélectionner une relation</option>
                    {connexions.map((user: User) => (
                        <option key={user.username} value={user.email}>
                            {user.username}
                        </option>
                    ))}
                </select>

                <input className="form-transfer"
                       type="text"
                       placeholder="Description"
                       name="description"
                       value={formData.description}
                       maxLength={50}
                       onChange={handleChange}
                       required={true}
                />

                <input className="form-transfer price"
                       type="number"
                       name="amount"
                       placeholder="0€"
                       value={formData.amount}
                       onChange={handleChange}
                       required={true}
                />
                {errorMessage && <p className="error-message">{errorMessage}</p>}


                <button className="form-transfer btn">Payer</button>
            </form>

            <div className="transfer">
                <h1 className="transfer-title">Mes transactions</h1>

                {!transactions.length ? (
                    <p>Pas de transactions effectues pour le moments...</p>
                ) : (
                    <div>
                        <div className="transfer-detail-title">
                            <h2>Relation</h2>
                            <h2>Description</h2>
                            <h2>Montant</h2>
                        </div>
                        <div>
                            {transactions.map((transaction, index) => (
                                <div key={index} className="transfer-detail">
                                    <span>{transaction.receiverUsername}</span>
                                    <span>{transaction.description}</span>
                                    <span>{transaction.amount} €</span>
                                </div>
                            )).reverse()}
                        </div>
                    </div>
                )}
            </div>


        </Layout>
    )
}

export default Transfer
