import { FaStar, FaStarHalfAlt, FaRegStar } from "react-icons/fa";

export default function Score({ num }) {
    const renderStars = () => {
        const stars = [];
        let remaining = num;

        for (let i = 0; i < 5; i++) {
            if (remaining >= 2) {
                stars.push(<FaStar key={i} color="gold" />);
                remaining -= 2;
            } else if (remaining >= 1) {
                stars.push(<FaStarHalfAlt key={i} color="gold" />);
                remaining -= 1;
            } else {
                stars.push(<FaRegStar key={i} color="grey" />);
            }
        }

        return stars;
    };

    return <div>{renderStars()}</div>;
}
