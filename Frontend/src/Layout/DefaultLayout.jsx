import { useContext } from "react";
import { Outlet } from "react-router-dom";

import Header from "../Components/Header";
import Footer from "../Components/Footer";

import Loader from "../Components/Loader";
import GlobalContext from "../Context/GlobalContext";

export default function DefaultLayout() {

    return (

        <>
            <Header />
            <main className="container-fluid">
                <Outlet />
            </main>
            <Footer />
        </>
    )
}