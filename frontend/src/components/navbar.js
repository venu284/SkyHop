import React, { useState } from "react";
import { FaUserCircle } from "react-icons/fa";
import { MdMenuBook }   from "react-icons/md";
import { FiLogOut }     from "react-icons/fi";
import logo             from "../images/logo.png";
import "./NavBar.css";

const NavBar = ({ onLogout, userID, setReservations }) => {
  const [dropdownVisible, setDropdownVisible] = useState(false);

  const toggleDropdown = () => setDropdownVisible((v) => !v);

  const resetHandler = () => {
    setReservations([]);
    if (dropdownVisible) setDropdownVisible(false);
  };

  const handleBookedClick = () => {
    fetch(`http://localhost:8080/reservation/${userID}`, {
      method: "GET",
      mode: "cors",
      headers: { "Content-Type":"application/json" }
    })
      .then((res) => res.json())
      .then((data) => {
        setReservations(data);
        if (data.length === 0) alert("Please book a reservation");
        setDropdownVisible(false);
      })
      .catch(() => alert("Failed to fetch reservations"));
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    onLogout();
    alert("Logged out successfully!");
    setDropdownVisible(false);
  };

  return (
    <header className="navbar">
      {/* Logo & Title */}
      <div className="navbar-left" onClick={resetHandler}>
        <img src={logo} alt="SkyHop Logo" className="navbar-logo" />
        <h1 className="navbar-title">SkyHop</h1>
      </div>

      {/* User & Dropdown */}
      <div className="navbar-user" onClick={toggleDropdown}>
        <FaUserCircle className="user-icon" />
        {dropdownVisible && (
          <div className="dropdown-menu">
            <div className="dropdown-item" onClick={handleBookedClick}>
              <MdMenuBook className="dropdown-icon" />
              <span>Booked</span>
            </div>
            <div className="dropdown-item" onClick={handleLogout}>
              <FiLogOut className="dropdown-icon" />
              <span>Log Out</span>
            </div>
          </div>
        )}
      </div>
    </header>
  );
};

export default NavBar;
