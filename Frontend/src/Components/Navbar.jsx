import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";

export default function Navbar() {
    const [query, setQuery] = useState("");
    const navigate = useNavigate();

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        if (query.trim() === '') {
            navigate('/games');
        } else {
            navigate(`/games/search?q=${encodeURIComponent(query.trim())}`);
            setQuery("");
        }
    };

    const logo = "/logo.png";

    return (
        <div className="navbar-mine bg-white shadow-sm">
            <nav className="navbar navbar-expand-lg">
                <div className="container">
                    <NavLink to="/" className="navbar-brand d-flex align-items-center">
                        <img src={logo} alt="Logo" width="100%" height="50px" className="me-2" />
                        <span className="fw-bold text-dark"></span>
                    </NavLink>

                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarContent"
                        aria-controls="navbarContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <NavLink
                                    to="/games"
                                    className={({ isActive }) =>
                                        "nav-link fw-bold " + (isActive ? "text-danger" : "text-dark")
                                    }
                                >
                                    Games
                                </NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink
                                    to="/genres"
                                    className={({ isActive }) =>
                                        "nav-link fw-bold " + (isActive ? "text-danger" : "text-dark")
                                    }
                                >
                                    Genres
                                </NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink
                                    to="/platforms"
                                    className={({ isActive }) =>
                                        "nav-link fw-bold " + (isActive ? "text-danger" : "text-dark")
                                    }
                                >
                                    Platforms
                                </NavLink>
                            </li>
                            <li className="nav-item">
                                <NavLink
                                    to="/aboutus"
                                    className={({ isActive }) =>
                                        "nav-link fw-bold " + (isActive ? "text-danger" : "text-dark")
                                    }
                                >
                                    About us
                                </NavLink>
                            </li>
                        </ul>

                        <form className="d-flex" onSubmit={handleSearchSubmit}>
                            <input
                                type="search"
                                className="form-control me-2"
                                placeholder="Search games..."
                                aria-label="Search"
                                value={query}
                                onChange={(e) => setQuery(e.target.value)}
                            />
                            <button className="btn btn-outline-secondary" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </div>
    );
}
