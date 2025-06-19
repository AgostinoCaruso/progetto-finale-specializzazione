import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import GameDetailCard from '../Components/GameDetailCard';

export default function GameDetail() {
  const { id } = useParams();
  const [game, setGame] = useState(null);
  const [recommendedGames, setRecommendedGames] = useState([]);

  useEffect(() => {
    window.scrollTo(0, 0);
    axios.get(`http://localhost:8080/api/games/${id}`)
      .then(res => setGame(res.data))
      .catch(err => console.error(err));
  }, [id]);

  useEffect(() => {
    if (game?.id) {
      axios.get(`http://localhost:8080/api/games/${game.id}/recommendations`)
        .then(res => {
          res.data.forEach((game, index) => {
            console.log(`Game ${index}:`, game);
          });
          setRecommendedGames(res.data);
        })
        .catch(err => {
          console.error('Errore fetch games recomended:', err)
        });
    }
  }, [game]);
  if (!game) return <div className="text-center mt-5">Loading...</div>;

  return (
    <GameDetailCard game={game} recommendedGames={recommendedGames} />
  );
}
