import { Container, Row, Col, Carousel, Badge } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import Score from '../Components/Score';

const imageUrl = `http://localhost:8080/images/`;

export default function GameDetailCard({ game }) {
    return (
        <>
            <Container className="my-5">
                <Row className="mb-4">
                    {/* Immagini */}
                    <Col md={8}>
                        <Carousel>
                            {game.images.map((img, index) => (
                                <Carousel.Item key={index}>
                                    <img
                                        className="d-block w-100 rounded"
                                        src={`http://localhost:8080/images/${img.imagePath}` }
                                        alt={`Image ${index + 1}`}
                                        style={{ maxHeight: '400px', objectFit: 'cover' }}
                                    />
                                </Carousel.Item>
                            ))}
                        </Carousel>
                    </Col>

                    {/* Dati del gioco */}
                    <Col md={4}>
                        <h2>{game.name}</h2>
                        <h4 className="text-success mb-3">${game.price}</h4>

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

                {/* Descrizione */}
                <Row>
                    <Col>
                        <h4>Description</h4>
                        <p className="lead">{game.description}</p>
                    </Col>
                </Row>
            </Container>
        </>
    )
}