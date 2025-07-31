import { Carousel, Row, Col, Container } from 'react-bootstrap';
import { useEffect, useState, useMemo } from 'react';
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

    const stablePlatforms = useMemo(() => platforms, [platforms.length]);
    const stableGenres = useMemo(() => genres, [genres.length]);
    const platformChunks = useResponsiveChunks(stablePlatforms, { sm: 1, md: 3, lg: 4 });
    const genresChunks = useResponsiveChunks(stableGenres, { sm: 1, md: 2, lg: 4 });



    return (
        <>
            <div>
                <JumboCarousel />
            </div>
            <div className='container'>


                <div className=" my-5 ">
                    <h2 className="mb-3">Top Games by Score</h2>
                    <div className="row d-flex justify-content-center">
                        {topScore.map((game) => (
                            <div key={game.id} className="col-12 col-md-6 col-lg-4 mb-4">
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
                                <Row className="g-1 bord-carousel d-flex justify-content-center align-items-center">
                                    {group.map(console => (
                                        <Col xs={12} md={4} lg={3} key={console.id} className="d-flex justify-content-center align-items-center">
                                            <Link to={`/platforms/${console.id}`}>
                                                <img
                                                    className="img-fluid rounded shadow hover-raise"
                                                    src={console.logoUrl}
                                                    alt={console.name}
                                                    style={{
                                                        maxHeight: '120px',
                                                        width: '100%',
                                                        objectFit: 'contain',
                                                        backgroundColor: 'white',
                                                        padding: '1px',
                                                    }} />
                                            </Link>
                                        </Col>
                                    ))}
                                </Row>
                            </Carousel.Item>
                        ))}
                    </Carousel>
                </Container>


                <Container className="my-5 justify-content-center">
                    <h2 className="mb-4 text-center">Genres</h2>
                    <Carousel indicators={false}>
                        {genresChunks.map((group, index) => (
                            <Carousel.Item key={index}>
                                <Row className="g-0 d-flex justify-content-center px-lg-0 px-md-5 px-2 py-5">
                                    {group.map(genre => (
                                        <Col key={genre.id} xs={12} sm={12} md={4} lg={2} className='d-flex justify-content-center align-items-center'>
                                            <Link to={`/genres/${genre.id}`} className="text-decoration-none text-dark">
                                                <div className="genre-box h-100 d-flex hover-raise card-hover justify-content-center">
                                                    <h5 className="m-0">{genre.name}</h5>
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

function useResponsiveChunks(data, sizes) {
    const [chunks, setChunks] = useState([]);

    useEffect(() => {
        function getChunkSize(width) {
            if (width >= 992) return sizes.lg;
            if (width >= 768) return sizes.md;
            return sizes.sm;
        }

        function updateChunks() {
            const width = window.innerWidth;
            const size = getChunkSize(width);
            const chunked = [];
            for (let i = 0; i < data.length; i += size) {
                chunked.push(data.slice(i, i + size));
            }
            setChunks(chunked);
        }

        updateChunks(); // iniziale
        window.addEventListener('resize', updateChunks);
        return () => window.removeEventListener('resize', updateChunks);
    }, [data]);

    return chunks;
}
