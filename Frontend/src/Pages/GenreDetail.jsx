import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import GenreDetailCard from '../Components/GenreDetailCard';

export default function GenreDetail() {
    const { id } = useParams();
    const [genre, setGenre] = useState(null);
    const [relatedGames, setRelatedGames] = useState([]);

    useEffect(() => {
        window.scrollTo(0, 0);

        axios.get(`http://localhost:8080/api/genres/${id}`)
            .then(res => setGenre(res.data))
            .catch(err => console.error(err));

        axios.get(`http://localhost:8080/api/genres/${id}/games`)
            .then(res => setRelatedGames(res.data))
            .catch(err => console.error(err));
    }, [id]);

    if (!genre) return <div className="text-center mt-5">Loading...</div>;

    return (
        <div style={relatedGames.length < 5 ? {paddingBottom: '200px'} : undefined}>
            <GenreDetailCard genre={genre} relatedGames={relatedGames} />
        </div>
    );
}
