import React, { useState } from 'react'
import Home from './components/home';  // Import Home component
import Navbar from './components/navbar';  // Import Navbar component
import Body from './components/body';  // Import Body component
import Footer from './components/footer';  // Import Footer component
import Res from './components/res';

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);  // State to track login status
    const [user,setUser] = useState(0);
    const [reservations, setReservations] = useState ([])
    const handleLogin = () => {
        setIsLoggedIn(true);  // Set login state to true
    };
    function decodeJWT(token) {
        // Split the token into its parts
        const parts = token.split('.');
        // Decode the payload (second part)
        const payload = parts[1];
        const decodedPayload = atob(payload);
        // Parse the JSON string
        const payloadObject = JSON.parse(decodedPayload);
        return payloadObject;
    }
    const handleLogout = () => {
        setIsLoggedIn(false);
        setUser(0)// Set login state to false (log out)
    };

    return (
        // "Hello World"
        <div className="App">
            {/* Render different components based on login state */}
            {isLoggedIn ? (
                <>
                    <Navbar setReservations = {setReservations} userID = {user} onLogout={handleLogout} />  {/* Pass logout function to Navbar */}

                    {reservations.length < 1 && <Body  />}   {/* Show the Body component when logged in */}
                    {reservations.length >= 1 &&  <Res reservations = {reservations} />}   {/* Show the Body component when logged in */}

                </>
            ) : (
                <>
                    <Home setUser={setUser} onLogin={handleLogin} />  {/* Pass login function to Home */}
                    <Footer />  {/* Footer is always visible */}
                </>
            )}
        </div>
    );
}

export default App;
