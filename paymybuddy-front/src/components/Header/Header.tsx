import "./Header.scss"
const Header = () => {

    return (
        <div className="container_header">
            <p className="container_header-title">Pay My Buddy</p>

            <ul className= "container_header-list">
                <li>Transferer</li>
                <li>profil</li>
                <li>Ajouter relation</li>
                <li>Se deconnecter</li>
            </ul>

        </div>
    )
}

export default Header