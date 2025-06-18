import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import GameDetailCard from '../Components/GameDetailCard';

export default function GameDetail() {
  const { id } = useParams();
  const [game, setGame] = useState(null);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/games/${id}`)
      .then(res => setGame(res.data))
      .catch(err => console.error(err));
  }, [id]);

  if (!game) return <div className="text-center mt-5">Loading...</div>;

  return (
    <GameDetailCard game={game} />
  );
}
