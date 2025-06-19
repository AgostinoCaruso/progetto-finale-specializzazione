import { Carousel, Row, Col, Container } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import axios from 'axios';
import GamesCard from '../Components/GamesCard';
import JumboCarousel from '../Components/JumboCarousel';
import { Link } from 'react-router';

export default function HomePage() {
    const [games, setGames] = useState([]);
    const [platforms, setPlatforms] = useState([]);
    const [topScore, setTopScore] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/games")
            .then(res => {
                //console.log(res.data.content);
                setGames(res.data.content);
            })
            .catch(err => console.error(err));

        axios.get("http://localhost:8080/api/platforms")
            .then(res => {
                //console.log("Fetched platforms:", res.data);
                setPlatforms(res.data);
            })
            .catch(err => console.error(err));

        axios.get("http://localhost:8080/api/games/topscore")
            .then(res => {
                // res.data.forEach((game, index) => {
                //     console.log(`Game ${index}:`, game);
                // });
                setTopScore(res.data);
            })
            .catch(err => console.err(err));
    }, []);


    const platformChunks = chunkArray(platforms, 4);

    // Console immagini per il carosello (esempio statico)


    return (
        <>
            {/* Jumbotron */}
            <JumboCarousel />

            <div className='container'>


                <div className=" my-5 ">
                    <h2 className="mb-3">Top Games by Score</h2>
                    <div className="row d-flex justify-content-center">
                        {topScore.map((game) => (
                            <div key={game.id} className="col-6 col-md-3 mb-4">
                                <GamesCard game={game} />
                            </div>
                        ))}
                    </div>
                </div>

                <Container className="my-5">
                    <h2 className="mb-3">Top Platforms to play</h2>
                    <Carousel>
                        {platformChunks.map((group, index) => (
                            <Carousel.Item key={index}>
                                <Row className="g-1 bord-carousel">
                                    {group.map(console => (
                                        <Col md={3} key={console.id} className="d-flex justify-content-center">
                                            <Link to={`/platforms/${console.id}`}>
                                                <img
                                                    className="img-fluid rounded shadow"
                                                    src={console.logoUrl}
                                                    alt={console.name}
                                                    style={{ maxHeight: '150px', objectFit: 'contain', backgroundColor: 'white', padding: '10px' }}
                                                />
                                            </Link>
                                        </Col>
                                    ))}
                                </Row>
                            </Carousel.Item>
                        ))}
                    </Carousel>
                </Container>
            </div>
        </>
    );


}

function chunkArray(arr, size) {
    const chunks = [];
    for (let i = 0; i < arr.length; i += size) {
        chunks.push(arr.slice(i, i + size));
    }
    return chunks;
}
