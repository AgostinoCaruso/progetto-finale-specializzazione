import axios from 'axios';
import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import Pagination from '../Components/Pagination';

export default function PlatformsPage() {
    const [platforms, setPlatforms] = useState([]);
    const [totalPages, setTotalPages] = useState(0);

    const location = useLocation();
    const navigate = useNavigate();

    const queryParams = new URLSearchParams(location.search);
    const pageParam = parseInt(queryParams.get("page")) || 0;

    const PAGE_SIZE = 8;

    useEffect(() => {
        window.scrollTo(0, 0);
        fetchPlatforms(pageParam);
    }, [pageParam]);

    const fetchPlatforms = (page) => {
        axios.get(`http://localhost:8080/api/platforms/pages?page=${page}&size=${PAGE_SIZE}`)
            .then(res => {
                setPlatforms(res.data.content);
                setTotalPages(res.data.totalPages);
            })
            .catch(err => console.error(err));
    };

    const changePage = (newPage) => {
        const params = new URLSearchParams(location.search);
        params.set("page", newPage);
        navigate(`?${params.toString()}`);
    };

    const goToPlatform = (id) => {
        navigate(`/platforms/${id}`);
    };

    return (
        <div className="container my-4">
            <h1 className="mb-4">Platforms</h1>

            <div className={`d-flex flex-wrap justify-content-center gap-4`} style={{ gap: '1rem' }}>
                {platforms.map(platform => (
                    <div
                        key={platform.id}
                        style={{ flex: '1 1 250px', maxWidth: '300px', cursor: 'pointer' }}
                        className="card p-3 game-card"
                        onClick={() => goToPlatform(platform.id)}
                    >
                        <img
                            src={platform.logoUrl}
                            alt={platform.name}
                            className="card-img-top object-fit-contain rounded-top"
                            style={{ height: "220px", width: "100%" }}
                        />
                    </div>
                ))}
            </div>

            {platforms.length > 0
                ? <Pagination pageParam={pageParam} changePage={changePage} totalPages={totalPages} location={location} />
                : <p>No platforms found.</p>
            }
        </div>
    );
}
