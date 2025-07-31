export default function Footer() {
    return (
        <>

            <footer className=" text-light pt-4">
                <div className="container">
                    <div className="row">
                        <div className="col-md-6 mb-3">
                            <h5>Navigation</h5>
                            <ul className="list-unstyled">
                                <li>
                                    <a href="/" className="text-light text-decoration-none">
                                        Homepage
                                    </a>
                                </li>
                                <li>
                                    <a href="/games" className="text-light text-decoration-none">
                                        Games
                                    </a>
                                </li>
                                <li>
                                    <a href="/genres" className="text-light text-decoration-none">
                                        Genres
                                    </a>
                                </li>
                                <li>
                                    <a href="/platforms" className="text-light text-decoration-none">
                                        Platforms
                                    </a>
                                </li>
                                <li>
                                    <a href="/aboutus" className="text-light text-decoration-none">
                                        About
                                    </a>
                                </li>
                            </ul>
                        </div>

                        {/* <div className="col-md-4 mb-3">
                            <h5>About Us</h5>
                            <ul className="list-unstyled">
                                <li>
                                    <a href="/about" className="text-light text-decoration-none">
                                        About
                                    </a>
                                </li>
                                <li>
                                    <a href="/contact" className="text-light text-decoration-none">
                                        Contact
                                    </a>
                                </li>
                                <li>
                                    <a href="/careers" className="text-light text-decoration-none">
                                        Careers
                                    </a>
                                </li>
                                <li>
                                    <a href="/faq" className="text-light text-decoration-none">
                                        FAQ
                                    </a>
                                </li>
                            </ul>
                        </div> */}

                        <div className="col-md-4 mb-3">
                            <h5>Follow Us</h5>
                            <ul className="list-unstyled d-flex gap-3 justify-content-around">
                                <li>
                                    <a href="#" className="text-light text-decoration-none">
                                        Facebook
                                    </a>
                                </li>
                                <li>
                                    <a href="#" className="text-light text-decoration-none">
                                        Twitter
                                    </a>
                                </li>
                                <li>
                                    <a href="#" className="text-light text-decoration-none">
                                        Instagram
                                    </a>
                                </li>
                            </ul>
                            <p className="mt-3 mb-0">© 2025 YourCompany</p>
                        </div>
                    </div>

                    <hr className="border-secondary" />

                    <div className="row">
                        <div className="col-12 text-center">
                            <img
                                src="/logo.png"
                                alt="Logo"
                                style={{ height: "50px" }}
                            />
                            <p className="mt-2 mb-0">
                                 Privacy Policy | Terms of Service
                            </p>
                        </div>
                    </div>
                </div>
            </footer>
        </>
    );
}
