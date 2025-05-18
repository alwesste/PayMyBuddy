import "./Header.scss"
import React from "react";
import { NavLink } from "react-router-dom";

const Header: React.FC = () => {

    return (
        <div className="container_header">
            <p className="container_header-title">Pay My Buddy</p>

            <ul className="container_header-list">
                <li>
                    <NavLink
                        to="/transfer"
                        className={({isActive}) => isActive ? "nav-link active" : "nav-link"}
                    >
                        Transférer
                    </NavLink>
                </li>
                <li>
                    <NavLink
                        to="/profil"
                        className={({isActive}) => isActive ? "nav-link active" : "nav-link"}
                    >
                        Profil
                    </NavLink>
                </li>
                <li>
                    <NavLink
                        to="/relation"
                        className={({isActive}) => isActive ? "nav-link active" : "nav-link"}
                    >
                        Ajouter relation
                    </NavLink>
                </li>
                <li>
                    <NavLink
                        to="/connexion"
                        className={({isActive}) => isActive ? "nav-link active" : "nav-link"}
                    >
                        Se deconnecter
                    </NavLink>
                </li>
            </ul>

        </div>
    )
}

export default Header