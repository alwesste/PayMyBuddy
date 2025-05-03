import './App.scss'
import Connexion from "./pages/Connexion/Connexion.tsx";
import Inscription from "./pages/Inscription/Inscription.tsx";
import Header from "./components/Header/Header.tsx";

function App() {

    return (
        <>
            <Header/>
            <Connexion/>
            <Inscription/>
        </>
    )
}

export default App
