import "./Inscription.scss"

const Inscription = () => {

    return (
        <div className="container_inscription">
            <h1 className="container_inscription-title">Pay My Buddy</h1>

            <input
                className="container_inscription-input"
                type="text"
                name="Username"
                placeholder="Username"
            />

            <input
                className="container_inscription-input"
                type="email"
                name="email"
                placeholder="Mail"
            />

            <input
                className="container_inscription-input"
                type="password"
                name="password"
                placeholder="Mot de passe"/>

            <button className="container_inscription-submit">S'inscrire</button>

        </div>
    )
}

export default Inscription