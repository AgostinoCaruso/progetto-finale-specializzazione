import { Carousel, Row, Col, Container } from 'react-bootstrap';
import { useEffect, useState } from 'react';
import axios from 'axios';
import GamesCard from '../Components/GamesCard';
import JumboCarousel from '../Components/JumboCarousel';
import { Link } from 'react-router';

export default function HomePage() {
    const [genres, setGenres] = useState([]);
    const [platforms, setPlatforms] = useState([]);
    const [topScore, setTopScore] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/platforms")
            .then(res => {
                //console.log("Fetched platforms:", res.data);
                setPlatforms(res.data);
            })
            .catch(err => console.error(err));

        axios.get("http://localhost:8080/api/genres")
            .then(res => {
                // console.log("Fetched genres:", res.data);
                setGenres(res.data.content);
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
    const genresChunks = chunkArray(genres, 4);

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
                    <h2 className="mb-3">All Platforms</h2>
                    <Carousel>
                        {platformChunks.map((group, index) => (
                            <Carousel.Item key={index}>
                                <Row className="g-1 bord-carousel">
                                    {group.map(console => (
                                        <Col md={3} key={console.id} className="d-flex justify-content-center">
                                            <Link to={`/platforms/${console.id}`}>
                                                <img
                                                    className="img-fluid rounded shadow hover-raise"
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


                <Container className="my-5">
                    <h2 className="mb-4 text-center">Genres</h2>
                    <Carousel indicators={false}>
                        {genresChunks.map((group, index) => (
                            <Carousel.Item key={index}>
                                <Row className="g-5 justify-content-center mb-5" style={{margin: '0px 200px'}}>
                                    {group.map(genre => (
                                        <Col key={genre.id} md={3}>
                                            <Link to={`/genres/${genre.id}`} className="text-decoration-none text-dark">
                                                <div className="card shadow-sm h-100 text-center hover-card py-3 px-0 hover-raise">
                                                    <div className="card-body d-flex flex-column justify-content-center">
                                                        <h5 className="card-title">{genre.name}</h5>
                                                    </div>
                                                </div>
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
