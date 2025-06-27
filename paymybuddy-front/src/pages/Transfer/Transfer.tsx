import React, {useEffect, useState} from "react";
import "./Transfer.scss"
import Layout from "../../components/Layout/layout.tsx";

const Transfer: React.FC = () => {

    interface User {
        id: number;
        email: string;
        username: string;
    }

    interface Transaction {
        senderUsername: string;
        receiverUsername: string;
        description: string;
        amount: number;
    }

    const currentUserEmail = localStorage.getItem("currentUserEmail");
    const [connexions, setConnexions] = useState<User[]>([]);
    const [transactions, setTransactions] = useState<Transaction[]>([]);
    const [formData, setFormData] = useState({
        senderUsername : currentUserEmail,
        receiverUsername: "",
        description: "",
        amount: "",
    });

    const  loadConnexion = async() => {
        try {
            const res = await fetch(`http://localhost:8080/api/seeConnexion?currentUserEmail=${currentUserEmail}`)

            if(!res.ok) {
                throw new Error("Erreur lors du chargement des connexions.");
            }
            const data: User[] = await res.json();
            // const usernamesOnly = data.map((user) => user.username);
            setConnexions(data)

        } catch (error) {
            console.error("Erreur :", error);
        }
    }

    const loadTransactions = async() => {
        try {
            const res = await fetch(`http://localhost:8080/api/seeTransaction?currentUserEmail=${currentUserEmail}`);
            if (!res.ok) {
                throw new Error("Erreur lors du chargement des transactions.");
            }
            const data: Transaction[] = await res.json();
            setTransactions(data);
        }
        catch (error) {
            console.error("Erreur :", error);
        }
    }

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        fetch("http://localhost:8080/api/transaction" , {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(formData)
        })
            .then((res) => {
                if(!res.ok) {
                    throw new Error("Erreur lors du chargement des transfers.");
                }
                console.log('res', res);
                loadTransactions();
            })
            .catch((err) => {
                console.log(formData)
                console.error("Erreur :", err);
            })
    }

    useEffect(() => {
        loadConnexion();
        loadTransactions()
    },[currentUserEmail])

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
                    onChange={(e) =>
                        setFormData({ ...formData, receiverUsername: e.target.value })}
                >
                    <option value="">Sélectionner une relation</option>
                    {connexions.map(({ id, username } :User) => (
                        <option key={id} value={username}>
                            {username}
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

                        <button className="form-transfer btn">Payer</button>
                        </form>

                        <div className="transfer">
                            <h1 className="transfer-title">Mes transactions</h1>
                            <div>
                                <div className="transfer-detail-title">
                                    <h2>Relation</h2>
                                    <h2>Description</h2>
                                    <h2>Montant</h2>
                                </div>
                                <div>{transactions?.map((transaction: Transaction, index: number) => (
                                    <div key={index} className="transfer-detail">
                                    <span>{transaction.receiverUsername}</span>
                                        <span>{transaction.description}</span>
                                        <span>{transaction.amount} €</span>
                                    </div>
                                )).reverse()}
                                </div>
                            </div>

                        </div>



        </Layout>
    )
}

export default Transfer