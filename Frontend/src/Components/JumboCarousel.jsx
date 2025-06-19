import { Carousel } from 'react-bootstrap';
import { Link } from 'react-router-dom'; // se usi react-router

export default function JumboCarousel() {
    const jumboImages = [
        { src: '/jumbo/1.jpg', link: '/platforms/9' },
        { src: '/jumbo/2.jpg', link: '/games/84' },
        { src: '/jumbo/3.jpg', link: '/games/64' },
    ];

    return (
        <Carousel>
            {jumboImages.map((item, index) => (
                <Carousel.Item key={index}>
                    <Link to={item.link} style={{ display: 'block' }}>
                        <div 
                            className='fullscreen-jumbo'
                        >
                            <img
                                src={item.src}
                                alt={`Jumbotron image ${index + 1}`}
                            />
                        </div>
                    </Link>
                </Carousel.Item>
            ))}
        </Carousel>

    );
}
