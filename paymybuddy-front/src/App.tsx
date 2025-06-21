import './App.scss'
import Connexion from "./pages/Connexion/Connexion.tsx";
import Inscription from "./pages/Inscription/Inscription.tsx";
import {BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import Profil from "./pages/Profil/Profil.tsx";
import Transfer from "./pages/Transfer/Transfer.tsx";
import Relation from "./pages/Relation/Relation.tsx";

function App() {

    return (

            <Router>
                <Routes>
                    <Route path="/" element={<Navigate to="/inscription" replace />} />
                    <Route path="/connexion" element={<Connexion/>} />
                    <Route path="/inscription" element={<Inscription />} />
                    <Route path="/profil" element={<Profil />} />
                    <Route path="/transfer" element={<Transfer />} />
                    <Route path="/relation" element={<Relation />} />
                </Routes>
            {/*<Header/>*/}
            </Router>
    )
}

export default App
