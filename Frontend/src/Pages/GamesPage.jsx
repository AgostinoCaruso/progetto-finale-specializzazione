import axios from 'axios';
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import Games from '../Components/GamesCard';
import Pagination from '../Components/Pagination';

export default function GamesPage() {
    const [games, setGames] = useState([]);
    const [totalPages, setTotalPages] = useState(0);

    const location = useLocation();
    const navigate = useNavigate();

    const queryParams = new URLSearchParams(location.search);
    const searchTerm = queryParams.get("q") || "";
    const pageParam = parseInt(queryParams.get("page")) || 0;

    const PAGE_SIZE = 12;

    useEffect(() => {
        window.scrollTo(0, 0);

        if (searchTerm) {
            fetchGamesBySearch(searchTerm, pageParam);
        } else {
            fetchAllGames(pageParam);
        }
    }, [searchTerm, pageParam]);

    const fetchAllGames = (page) => {
        axios.get(`http://localhost:8080/api/games?page=${page}&size=${PAGE_SIZE}`)
            .then(res => {
                setGames(res.data.content);
                setTotalPages(res.data.totalPages);
            })
            .catch(err => console.error(err));
    };

    const fetchGamesBySearch = (query, page) => {
        axios.get(`http://localhost:8080/api/games/search?q=${query}&page=${page}&size=${PAGE_SIZE}`)
            .then(res => {
                setGames(res.data.content);
                setTotalPages(res.data.totalPages);
            })
            .catch(err => console.error(err));
    };

    const changePage = (newPage) => {
        const params = new URLSearchParams(location.search);
        params.set("page", newPage);
        navigate(`?${params.toString()}`);
    };

    return (
        <div className="container my-4">
            <h1 className="mb-4">
                {searchTerm ? `Search results for "${searchTerm}"` : "Games in Store"}
            </h1>

            <div className={`d-flex flex-wrap justify-content-center`} style={{ gap: '1rem' }}>
                {games.map(game => (
                    <div key={game.id} style={{ flex: '1 1 250px', maxWidth: '300px' }} className={`${game.id === games.length -1 ? 'justify-content-start' : 'justify-content-center'}`} >
                        <Games game={game} />
                    </div>
                ))}
            </div>

            {games.length > 0
                ? <Pagination pageParam={pageParam} changePage={changePage} totalPages={totalPages} location={location} />
                : <p></p>
            }
        </div>
    );
}
