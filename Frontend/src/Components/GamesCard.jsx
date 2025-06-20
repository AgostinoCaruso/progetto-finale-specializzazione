import { Link } from "react-router-dom";

export default function GamesCard({ game }) {
  const imageUrl = `http://localhost:8080/images/${game.images?.[0]?.imagePath}`;

  return (
    <Link
      to={`/games/${game.id}`}
      className="card game-card text-decoration-none text-dark shadow-sm border-0 h-100"
    >
      <div className="game-img-container position-relative">
        <img
          src={imageUrl}
          alt={game.name}
          className="card-img-top object-fit-cover rounded-top"
          style={{ height: "220px", width: "100%" }}
        />
        <span className="badge bg-success position-absolute top-0 end-0 m-2">
          ${game.price}
        </span>
      </div>

      <div className="card-body d-flex flex-column justify-content-between">
        <h5 className="card-title fw-bold ">{game.name}</h5>
        <p className="card-text mb-1">
          <strong>Publisher:</strong> {game.publisher}
        </p>
        <p className="card-text mb-2">
          <strong>Developer:</strong> {game.developer}
        </p>
      </div>
    </Link>
  );
}
