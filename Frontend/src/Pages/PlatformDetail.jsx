import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import PlatformDetailCard from '../Components/PlatformDetailCard';

export default function PlatformDetail() {
    const { id } = useParams();
    const [platform, setPlatform] = useState(null);
    const [relatedGames, setRelatedGames] = useState([]);

    useEffect(() => {
        window.scrollTo(0, 0);

        axios.get(`http://localhost:8080/api/platforms/${id}`)
            .then(res => setPlatform(res.data))
            .catch(err => console.error(err));

        axios.get(`http://localhost:8080/api/platforms/${id}/games`)
            .then(res => {
                res.data.forEach((ele)=>{
                    console.log(ele);
                })
                setRelatedGames(res.data)})
            .catch(err => console.error(err));
    }, [id]);

    if (!platform) return <div className="text-center mt-5">Loading...</div>;

    return (
        <div style={relatedGames.length < 5 ? { paddingBottom: '200px' } : undefined}>
            <PlatformDetailCard platform={platform} relatedGames={relatedGames} />
        </div>
    );
}
