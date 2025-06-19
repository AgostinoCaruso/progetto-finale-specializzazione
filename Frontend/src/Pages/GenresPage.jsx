import axios from 'axios';
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import Pagination from '../Components/Pagination';

export default function GenresPage() {
    const [genres, setGenres] = useState([]);
    const [totalPages, setTotalPages] = useState(0);

    const location = useLocation();
    const navigate = useNavigate();

    const queryParams = new URLSearchParams(location.search);
    const pageParam = parseInt(queryParams.get("page")) || 0;

    const PAGE_SIZE = 20;

    useEffect(() => {
        window.scrollTo(0, 0);
        fetchGenres(pageParam);
    }, [pageParam]);

    const fetchGenres = (page) => {
        axios.get(`http://localhost:8080/api/genres?page=${page}&size=${PAGE_SIZE}`)
            .then(res => {
                setGenres(res.data.content);
                setTotalPages(res.data.totalPages);
            })
            .catch(err => console.error(err));
    };

    const changePage = (newPage) => {
        const params = new URLSearchParams(location.search);
        params.set("page", newPage);
        navigate(`?${params.toString()}`);
    };

    const goToGenre = (id) => {
        navigate(`/genres/${id}`);  // Modifica questo percorso se vuoi
    };

    return (
        <div className="container my-4">
            <h1 className="mb-4">Genres</h1>

            <div className="d-flex flex-wrap justify-content-center gap-4" style={{ gap: '1rem' }}>
                {genres.map(genre => (
                    <div 
                        key={genre.id} 
                        style={{ flex: '1 1 250px', maxWidth: '300px', cursor: 'pointer' }} 
                        className="card p-3"
                        onClick={() => goToGenre(genre.id)}
                    >
                        <h5 className="card-title text-center">{genre.name}</h5>
                    </div>
                ))}
            </div>

            {genres.length > 0 
                ? <Pagination pageParam={pageParam} changePage={changePage} totalPages={totalPages} location={location} />
                : <p>No genres found.</p>
            }
        </div>
    );
}
