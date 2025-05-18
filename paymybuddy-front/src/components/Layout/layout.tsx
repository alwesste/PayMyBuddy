import Header from "../Header/Header.tsx";
import React, {ReactNode} from "react";

type LayoutProps = {
    children: ReactNode; //  type qui accepte tout ce que React peut rendre (JSX, texte, tableau de JSX, etc.)
};

const Layout: React.FC<LayoutProps> = ({ children }) => {
    return (
        <>
            <Header />
            <main>{children}</main>
        </>
    );
};

export default Layout
