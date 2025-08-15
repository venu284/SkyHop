import React, { useState, useEffect } from "react";
import './body.css'; 
import { FaUser, FaChair, FaCalendarAlt, FaClock } from "react-icons/fa";
// import mockFlights from "../data/mockflights"; // Assuming this contains flight data

const Body = (reservation) => {
    const [currentStep, setCurrentStep] = useState(1);
    const [flights,setFlights] = useState([]);
    // const [seats,setSeats] = useState([]);
    // const[myFlight,setMyFlight] = useState()
    const [people, setPeople] = useState([])
    // const [numPeople,setNumPeople] = useState(1)

    const [formData, setFormData] = useState({
        from: '',
        to: '',
        departureDate: '',
        departureTime: '',
        seatClass: 'economy',  // Default to Economy
        numPeople: 1,
        passengerNames: [{ firstName: '', lastName: '' }]
    });
    const [selectedFlight, setSelectedFlight] = useState(null);
    // const backtopage1 = () => {
    //     setSeats([])
    // }
    function addMinutesToTime(timeString, minutesToAdd) {
        // Convert the time string to a Date object
        let [hours, minutes, seconds] = timeString.split(':').map(Number);
        let date = new Date();
        date.setHours(hours);
        date.setMinutes(minutes + minutesToAdd);
        date.setSeconds(seconds);
        // Format the result back to a time string
        let newHours = String(date.getHours()).padStart(2, '0');
        let newMinutes = String(date.getMinutes()).padStart(2, '0');
        let newSeconds = String(date.getSeconds()).padStart(2, '0');
        return `${newHours}:${newMinutes}:${newSeconds}`
    }
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
    const backtopage1 =  () => {
        setCurrentStep(1)
        setFlights([])
        setPeople([])
        setSelectedFlight(null)
        setFormData({
            from: '',
            to: '',
            departureDate: '',
            departureTime: '',
            seatClass: 'economy',  // Default to Economy
            numPeople: 1,
            passengerNames: [{ firstName: '', lastName: '' }]
        });
    }

    const handleNextStep = () => {
        if (currentStep === 1) {
            // setNumPeople(document.getElementById("numPeople").value)
            // console.log(numPeople)
            setPeople(formData.passengerNames)
            console.log(people)
        }
        if (currentStep < 4) {
            setCurrentStep(currentStep + 1);
        }
        if (currentStep === 1) {
            var from = document.getElementById("from").value
            var to = document.getElementById("to").value
            var date = document.getElementById("departureDate").value
            var url = `http://localhost:8080/flight/search?origin=${from}&destination=${to}&date=${date}`
            fetch(url, {
                method: "GET",
                mode:"cors",
                headers: {
                    "Content-Type":"application/json",
                    "Accept":"application/json",
                    'Access-Control-Allow-Origin':'*'
                }
            })
                .then(res=>res.json())
                .then(data=>{
                    if (data.length > 0) {
                        console.log(data)
                        // const options = data
                        setFlights(data);
                    }
                    else {
                        alert("No Flights Found")
                        setCurrentStep(1);
                        setCurrentStep(1)
                        setFlights([])
                        setPeople([])
                        setSelectedFlight(null)
                        setFormData({
                            from: '',
                            to: '',
                            departureDate: '',
                            departureTime: '',
                            seatClass: 'economy',  // Default to Economy
                            numPeople: 1,
                            passengerNames: [{ firstName: '', lastName: '' }]
                        });
                    }
                })
                .catch(error => console.error('There was a problem with the fetch operation:', error));
            // console.log(url);
        }
        // if (currentStep === 2) {
        //     console.log(selectedFlight.flightID)
        //     setMyFlight(selectedFlight.flightID)
        //     console.log(myFlight)
        // }
        if (currentStep === 3) {
            console.log(people)
            console.log(decodeJWT(localStorage.getItem("token")))
            var passenger = decodeJWT(localStorage.getItem("token")).passengerID
            // var totalPrice = numPeople*50
            var totalPrice = people.length*50
            var cardType = document.getElementById("card-type").value
            var cardNumber = document.getElementById("card-number").value
            var cardCode = document.getElementById("cvv").value
            var zipcode = document.getElementById("zip").value
            var expiryDate = document.getElementById("exp-date").value
            var reservation = {
                passengerId:passenger,
                cardCode:cardCode,
                cardNumber:cardNumber,
                cardType:cardType,
                expiryDate:expiryDate,
                zipcode:zipcode,
                totalPrice:totalPrice
            }
            fetch("http://localhost:8080/reservation", {
                method: "POST",
                mode: "cors",
                headers: {
                    "Content-Type":"application/json",
                    "Accept":"application/json"
                },
                body: JSON.stringify(reservation)
            })
                .then(res=>res.json())
                .then(data=>{
                    console.log(data)
                    console.log(people)
                    people.map((person) => {
                        var reservationID = data;
                        var firstName = person.firstName;
                        var lastName = person.lastName;
                        var seatNum = person.seat;
                        var flightId = selectedFlight.flightID;
                        var ticket = {
                            reservationID:reservationID,
                            firstName:firstName,
                            lastName:lastName,
                            seatNum:seatNum,
                            flightId:flightId,
                            unitPrice:50
                        }
                        console.log(ticket)
                        fetch("http://localhost:8080/reservation/ticket/"+reservationID,{
                            method: "POST",
                            mode: "cors",
                            headers: {
                                "Content-Type":"application/json",
                                "Accept":"application/json"
                            },
                            body: JSON.stringify(ticket)
                        })
                            .then(res=>res.json())
                            .then(data=>console.log(data))
                })

                })
            console.log(passenger,cardType,cardNumber,cardCode,zipcode,expiryDate,totalPrice)
        }
    };

    const handlePreviousStep = () => {
        if (currentStep > 1) {
            if (currentStep === 2) {
                setCurrentStep(1)
                setFlights([])
                setPeople([])
                setSelectedFlight(null)
                setFormData({
                    from: '',
                    to: '',
                    departureDate: '',
                    departureTime: '',
                    seatClass: 'economy',  // Default to Economy
                    numPeople: 1,
                    passengerNames: [{ firstName: '', lastName: '' }]
                });
            }
            setCurrentStep(currentStep - 1);
        }
    };

    const handleFlightSelect = (flight) => {
        setSelectedFlight(flight);

        // Update form data only if the flight is selected and the respective fields are not already filled
        setFormData((prevData) => ({
            ...prevData,
            from: prevData.from || flight.departureLocation,
            to: prevData.to || flight.arrivalLocation,
            departureDate: prevData.departureDate || flight.departureDate,
        }));
    };

    const handleFormChange = (e) => {
        const { id, value } = e.target;
        setFormData({
            ...formData,
            [id]: value,
        });
    };

    const handlePassengerNameChange = (index, field, value) => {
        const newPassengerNames = [...formData.passengerNames];

        if (!newPassengerNames[index].seat) {
            newPassengerNames[index].seat = generateRandomSeat();
        }

        newPassengerNames[index][field] = value;
        setFormData({
            ...formData,
            passengerNames: newPassengerNames,
        });
    };

    useEffect(() => {
        console.log('Updated FormData:', formData); // Log the formData to ensure it's updated
    }, [formData]); // Trigger this effect every time formData changes

    // Create an array of passenger name input fields based on the number of people
    const renderPassengerInputs = () => {
        return Array.from({ length: formData.numPeople }, (_, index) => (
            <div className="input-people passenger-input-row" key={index}>
                <label>Passenger {index + 1}</label>
                <div className="passenger-names">
                    <input
                        type="text"
                        placeholder="First Name"
                        value={formData.passengerNames[index]?.firstName || ''}
                        onChange={(e) => handlePassengerNameChange(index, 'firstName', e.target.value)}
                    />
                    <input
                        type="text"
                        placeholder="Last Name"
                        value={formData.passengerNames[index]?.lastName || ''}
                        onChange={(e) => handlePassengerNameChange(index, 'lastName', e.target.value)}
                    />
                </div>
            </div>
        ));
    };

    // Update passenger names array when the number of people changes
    useEffect(() => {
        const updatedPassengerNames = Array.from({ length: formData.numPeople }, (_, index) => ({
            firstName: formData.passengerNames[index]?.firstName || '',
            lastName: formData.passengerNames[index]?.lastName || '',
            seat: formData.passengerNames[index]?.seat || generateRandomSeat() // Ensure seat numbers are assigned
        }));
        setFormData((prevData) => ({
            ...prevData,
            passengerNames: updatedPassengerNames,
        }));
    }, [formData.numPeople]);


    const generateRandomSeat = () => {
        const row = Math.floor(Math.random() * 30) + 1; // Rows 1-30
        const seat = String.fromCharCode(65 + Math.floor(Math.random() * 6)); // Seats A-F
        // setSeats([...seats,`${row}${seat}`]);
        // console.log(seats)
        return `${row}${seat}`;
    };

    // generate random ticket ID
    const [ticketCounter, setTicketCounter] = useState(1);
    const generateTicketID = () => {
        return `TICKET${ticketCounter.toString().padStart(4, '0')}`; // Example: TICKET0001
    };

    return (
        <div className="main-container">
            <div className="body-container">
                {/* Step Indicators */}
                <div className="step-indicators">
                    <div className="line"></div>
                    {[1, 2, 3, 4].map((step) => (
                        <div
                            key={step}
                            className={`step ${currentStep === step ? 'active' : ''}`}
                        >
                            {step}
                        </div>
                    ))}
                    <div className="line"></div>
                </div>

                {/* Body Content */}
                <div className="step-content">
                    {currentStep === 1 && (
                        <div>
                            <form className="form-step">
                                <div className="input-row">
                                    <div className="input-group">
                                        <input
                                            type="text"
                                            id="from"
                                            value={formData.from}
                                            onChange={handleFormChange}
                                            placeholder="Enter origin"
                                        />
                                    </div>
                                    <div className="input-group">
                                        <input
                                            type="text"
                                            id="to"
                                            value={formData.to}
                                            onChange={handleFormChange}
                                            placeholder="Enter destination"
                                        />
                                    </div>
                                </div>

                                <div className="input-row">
                                    <div className="input-group">
                                        <FaUser className="input-icon" />
                                        <input
                                            type="number"
                                            id="numPeople"
                                            value={formData.numPeople}
                                            onChange={handleFormChange}
                                            placeholder="Number of people"
                                        />
                                    </div>
                                    <div className="input-group">
                                        <FaChair className="input-icon" />
                                        <select
                                            id="seatClass"
                                            value={formData.seatClass}
                                            onChange={handleFormChange}
                                        >
                                            <option value="economy">Economy</option>
                                            <option value="business">Business</option>
                                            <option value="first">First</option>
                                        </select>
                                    </div>
                                </div>

                                {/* Render passenger input fields */}
                                <div className="passenger-section">
                                    {renderPassengerInputs()}
                                </div>

                                <div className="input-row">
                                    <div className="input-group">
                                        <FaCalendarAlt className="input-icon"/>
                                        <input
                                            type="date"
                                            id="departureDate"
                                            value={formData.departureDate}
                                            onChange={handleFormChange}
                                        />
                                    </div>
                                    <div className="input-group">
                                        <FaClock className="input-icon"/>
                                        <input
                                            type="time"
                                            id="departureTime"
                                            value={formData.departureTime}
                                            onChange={handleFormChange}
                                        />
                                    </div>
                                </div>
                            </form>
                        </div>
                    )}

                    {currentStep === 2 && (
                        <div style={{padding: '20px'}}>
                            <h2 style={{textAlign: 'center', marginBottom: '20px'}}>Available Flights</h2>
                            <div className="flight-list">
                                {flights.map((flight, index) => (
                                    <div
                                        key={index}
                                        className={`flight-card ${selectedFlight === flight ? 'selected' : ''}`}
                                        onClick={() => handleFlightSelect(flight)}
                                    >
                                        <div className="flight-logo">
                                            {flight.airplaneID.airline.includes("Delta") && (
                                                <img
                                                    src='https://logos-world.net/wp-content/uploads/2021/08/Delta-Logo.png'
                                                    alt={`${flight.name} logo`}/>
                                            )}
                                            {flight.airplaneID.airline.includes("American") && (
                                                <img
                                                    src='https://logos-world.net/wp-content/uploads/2020/11/American-Airlines-Emblem.png'
                                                    alt={`${flight.name} logo`}/>
                                            )}
                                            {flight.airplaneID.airline.includes("United") && (
                                                <img
                                                    src='https://www.freepnglogos.com/uploads/united-airlines-logo-png-17.png'
                                                    alt={`${flight.name} logo`}/>
                                            )}
                                            {flight.airplaneID.airline.includes("Southwest") && (
                                                <img
                                                    src='https://logos-world.net/wp-content/uploads/2020/10/Southwest-Airlines-Emblem.png'
                                                    alt={`${flight.name} logo`}/>
                                            )}
                                        </div>
                                        <div className="flight-info">
                                            {/*{flight.startAirportCode.airportCode} - {flight.endAirportCode.airportCode} for*/}
                                            <h3>{flight.departureTime.substring(0, 10)} at {flight.departureTime.substring(11, 19)}</h3>
                                            <p>{flight.name}</p>
                                            <p>Total Time: {flight.duration} minutes</p>
                                        </div>
                                        <div className="flight-price">
                                            <h3>${formData.numPeople * 50}</h3>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    )}
                    {currentStep === 3 && (
                        <div>
                            <h2>Booking Summary</h2>
                            <div class="flight-summary">
                                <div className="user-summary">
                                    <p><strong>From:</strong> {formData.from || 'N/A'}</p>
                                    <p><strong>To:</strong> {formData.to || 'N/A'}</p>
                                    <p><strong>Seat Class:</strong> {formData.seatClass}</p>
                                    <p><strong>Number of People:</strong> {formData.numPeople}</p>

                                    <div style={{paddingLeft: '20px', marginTop: '10px'}}>
                                        <ul style={{listStyleType: 'none', padding: 0}}>
                                            {formData.passengerNames?.map((passenger, index) => (
                                                <li key={index}>
                                                    {passenger.firstName} {passenger.lastName} -
                                                    {' '+passenger.seat}
                                                </li>
                                            ))}
                                        </ul>
                                    </div>

                                    <p><strong>Departure Date:</strong> {formData.departureDate || 'N/A'}</p>
                                    <p><strong>Departure Time:</strong> {selectedFlight?.departureTime.substring(11,19) || 'N/A'}</p>
                                </div>
                                {selectedFlight ? (
                                    <div className="flight-sum">
                                        <p><strong>Flight Name:</strong> {selectedFlight.flightID}</p>
                                        <p><strong>Departure
                                            Time:</strong> {selectedFlight.departureTime.substring(11, 19)}</p>
                                        <p><strong>Arrival
                                            Time:</strong> {addMinutesToTime(selectedFlight.departureTime.substring(11, 19), selectedFlight.duration)}
                                        </p>
                                        <p><strong>Duration:</strong> {selectedFlight.duration} Minutes</p>
                                        <p><strong>Price:</strong> ${formData.numPeople * 50}</p>
                                    </div>
                                ) : (
                                    <p>No flight selected</p>
                                )}
                            </div>
                            <form className="payment-form">
                                <div className="input-group">
                                    <label htmlFor="card-type">Card Type:</label>
                                    <select name="card" id="card-type">
                                        <option value="Visa">Visa</option>
                                        <option value="Mastercard">Mastercard</option>
                                        <option value="American Express">American Express</option>
                                        <option value="Discover">Discover</option>
                                    </select>
                                </div>
                                <div className="input-group">
                                    <label htmlFor="card-number">Card Number:</label>
                                    <input type="text" id="card-number" placeholder="Enter card number"/>
                                </div>
                                <div className="input-group">
                                    <label htmlFor="exp-date">Expiration Date:</label>
                                    <input type="month" id="exp-date"/>
                                </div>
                                <div className="input-group">
                                    <label htmlFor="cvv">CVV:</label>
                                    <input type="text" id="cvv" placeholder="Enter CVV"/>
                                </div>
                                <div className="input-group">
                                    <label htmlFor="zip">Zip Code:</label>
                                    <input type="text" id="zip" placeholder="Enter Zip Code"/>
                                </div>
                            </form>
                        </div>


                    )}

                    {currentStep === 4 &&
                        <div>
                            <div className="ticket-container">
                                <div className="ticket-header">
                                    <div className="from-to">
                                        <span><strong>From:</strong> {formData.from || 'N/A'}</span>
                                        <span><strong>To:</strong> {formData.to || 'N/A'}</span>
                                    </div>
                                </div>
                                <div className="ticket-details">
                                    <div className="ticket-id">
                                        <p><strong>Ticket ID:</strong> {generateTicketID()}</p>
                                    </div>
                                    <div className="departure-arrival">
                                        <p>
                                            <strong>Departure:</strong> {selectedFlight?.departureTime.substring(11,19) || 'N/A'} - <strong>Arrival:</strong> {addMinutesToTime(selectedFlight.departureTime.substring(11, 19), selectedFlight.duration)}
                                        </p>
                                    </div>
                                    <div className="ticket-price">
                                        <p><strong>Price:</strong> ${formData.numPeople * 50}</p>
                                    </div>
                                </div>
                                <div className="passenger-details">
                                    {formData.passengerNames?.map((passenger, index) => (
                                        <div className="passenger-box" key={index}>
                                            <p>{passenger.firstName} {passenger.lastName} -
                                                {/*Seat {generateRandomSeat()}*/}
                                                {' '+ passenger.seat}
                                            </p>
                                        </div>
                                    ))}
                                </div>
                            </div>
                            <div style={{textAlign: "center"}}>
                                <button className="backtopage1"
                                        onClick={backtopage1}
                                >
                                    Return to the Home Page
                                </button>
                            </div>

                        </div>

                    }                </div>


                {/* Navigation buttons */}
                <div className="navigation-buttons">
                    <button onClick={handlePreviousStep} disabled={currentStep === 1 || currentStep === 4}>
                        Previous
                    </button>
                    <button onClick={handleNextStep} disabled={currentStep === 4}>
                        {currentStep === 3 ? 'Submit' : 'Next'}
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Body;
