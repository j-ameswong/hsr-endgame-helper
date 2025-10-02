function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg">
            <div className="container-fluid">
                <a className="navbar-brand d-flex align-items-center" href="/index.html">
                    {/*<img 
                        src="/Character_Evernight_Icon.webp"
                        width={50}
                        height={50}
                        className="d-inline-block align-top me-2" 
                    />*/}
                    HSR Endgame Helper
                </a>

                <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li className="nav-item"><a className="nav-link">AV Calculator</a></li>
                    <li className="nav-item"><a className="nav-link">Combat Simulator</a></li>
                    <li className="nav-item"><a className="nav-link">Leaderboard</a></li>
                </ul>
            </div>
        </nav>
    )
}

export default Navbar