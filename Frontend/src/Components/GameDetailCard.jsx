import { Container, Row, Col, Carousel, Badge } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import Score from '../Components/Score';
import axios from 'axios';

const imageUrl = `http://localhost:8080/images/`;

export default function GameDetailCard({ game, recommendedGames }) {
    return (
        <>
            <Container className="my-5">
                <Row className="mb-4">
                    <Col md={8}>
                        <Carousel>
                            {game.images.map((img, index) => (
                                <Carousel.Item key={index}>
                                    <img
                                        className="d-block w-100 rounded"
                                        src={`http://localhost:8080/images/${img.imagePath}`}
                                        alt={`Image ${index + 1}`}
                                        style={{ maxHeight: '400px', objectFit: 'cover' }}
                                    />
                                </Carousel.Item>
                            ))}
                        </Carousel>
                    </Col>

                    <Col md={4}>
                        <h2>{game.name}</h2>
                        <h4 className="text-success mb-3">${game.price.toFixed(2)}</h4>

                        <div>
                            <strong>Score:</strong> <Score num={game.score} />
                        </div>
                        <p><strong>Publisher:</strong> {game.publisher}</p>
                        <p><strong>Developer:</strong> {game.developer}</p>

                        <div className="mt-3">
                            <strong>Genres:</strong><br />
                            {game.genres.map(ele => (
                                <Link key={ele.id} to={`/genres/${ele.id}`}>
                                    <Badge bg="secondary" className="me-1">{ele.name}</Badge>
                                </Link>
                            ))}
                        </div>

                        <div className="mt-3">
                            <strong>Platforms:</strong><br />
                            {game.platforms.map(ele => (
                                <Link key={ele.id} to={`/platforms/${ele.id}`}>
                                    <Badge bg="info" className="me-1">{ele.name}</Badge>
                                </Link>
                            ))}
                        </div>
                    </Col>
                </Row>

                <Row>
                    <Col>
                        <h4>Description</h4>
                        <p className="lead">{game.description}</p>
                    </Col>
                </Row>

                {recommendedGames.length > 0 && (
                    <>
                        <h4>Recommended Games</h4>
                        <Row>
                            {recommendedGames.map(g => (
                                <Col key={g.id} md={3} className='my-3'>
                                    <Link to={`/games/${g.id}`} className="text-decoration-none">
                                        <div className="card mb-3 shadow-sm h-100 d-flex flex-column game-card">
                                            <img src={`http://localhost:8080/images/${g.images[0].imagePath}`} className="card-img-top" alt={g.name} />
                                            <div className="card-body d-flex flex-column">
                                                <h6 className="card-title">{g.name}</h6>
                                                <div>
                                                    {g.genres.slice(0, 3).map(genre => (
                                                        <Badge key={genre.id} bg="secondary" className="me-1">{genre.name}</Badge>
                                                    ))}
                                                </div>
                                                <div className="mt-auto">
                                                    <p className="card-text text-success">${g.price}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </Link>
                                </Col>
                            ))}
                        </Row>
                    </>
                )}

            </Container>
        </>
    )
}