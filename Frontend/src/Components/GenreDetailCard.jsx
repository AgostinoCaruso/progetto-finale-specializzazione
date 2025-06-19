import { Container, Row, Col, Badge } from 'react-bootstrap';
import { Link } from 'react-router-dom';

export default function GenreDetailCard({ genre, relatedGames }) {
  return (
    <Container className="my-5">
      {/* <h2 className="mb-4 text-center">{genre.name}</h2> */}

      {relatedGames.length > 0 ? (
        <>
          <h1>Top {relatedGames.length} games in {genre.name} </h1>
          <Row>
            {relatedGames.map(game => (
              <Col key={game.id} md={3} className="my-3">
                <Link to={`/games/${game.id}`} className="text-decoration-none">
                  <div className="card mb-3 shadow-sm h-100 d-flex flex-column game-card">
                    <img
                      src={`http://localhost:8080/images/${game.images?.[0]?.imagePath || 'default.jpg'}`}
                      className="card-img-top"
                      alt={game.name}
                      style={{ objectFit: 'cover', height: '150px' }}
                    />
                    <div className="card-body d-flex flex-column">
                      <h6 className="card-title">{game.name}</h6>
                      <div>
                        {game.genres?.slice(0, 3).map(g => (
                          <Badge key={g.id} bg="secondary" className="me-1">{g.name}</Badge>
                        ))}
                      </div>
                      <div className="mt-auto">
                        <p className="card-text text-success">${game.price.toFixed(2)}</p>
                      </div>
                    </div>
                  </div>
                </Link>
              </Col>
            ))}
          </Row>
        </>
      ) : (
        <p>No games found in this genre.</p>
      )}
    </Container>
  );
}
