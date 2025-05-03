import "./Connexion.scss"

const Connexion = () => {

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

            <button className="container_connexion-submit">Se connecter</button>

        </div>
    )
}

export default Connexion;