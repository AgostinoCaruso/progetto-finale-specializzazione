import GamesCard from "./GamesCard";

export default function PlatformDetailCard({ platform, relatedGames }) {
    return (
        <div className="container my-5">
            <div className="text-center mb-4">
                <img src={platform.logoUrl} alt={platform.name} style={{ maxHeight: '150px' }} />
                <h2 className="mt-3">{platform.name}</h2>
                <h4>
                    <strong>Description: </strong>
                    {platform.description}
                </h4>
            </div>

            <h2 className="mt-5">Games on this platform:</h2>
            <div className="row mt-3">
                {relatedGames.length > 0 ? (
                    relatedGames.map(game => (
                        <div className="col-md-3 mb-4" key={game.id}>
                            <GamesCard game={game} />

                        </div>
                    ))
                ) : (
                    <p>No games found for this platform.</p>
                )}
            </div>
        </div>
    );
}
