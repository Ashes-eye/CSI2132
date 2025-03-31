// Fetch greeting
function fetchGreeting() {
    fetch('http://localhost:8080/api/hotels/greeting')
        .then(response => {
            if (!response.ok) throw new Error("Network response was not ok");
            return response.text();
        })
        .then(data => {
            document.getElementById('response').innerText = data;
        })
        .catch(error => console.error('Error:', error));
}

// Search rooms
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("searchForm");

    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            const location = document.getElementById("location").value.toLowerCase();
            const capacity = document.getElementById("capacity").value.toLowerCase();
            const stars = document.getElementById("stars").value;
            const view = document.getElementById("View").value.toLowerCase();

            fetch("http://localhost:8080/api/rooms")
                .then(response => {
                    if (!response.ok) throw new Error("Failed to fetch rooms");
                    return response.json();
                })
                .then(data => {
                    const filtered = data.filter(room =>
                        (!location || room.hotelAddress.toLowerCase().includes(location)) &&
                        (!capacity || room.capacity.toLowerCase().includes(capacity)) &&
                        (!stars || room.hotelRating.includes(stars)) &&
                        (!view || room.view.toLowerCase().includes(view))
                    );
                    displayRooms(filtered);
                })
                .catch(error => console.error("Error fetching rooms:", error));
        });
    }
});

// Fetch hotels
function fetchHotels() {
    fetch('http://localhost:8080/api/hotels/list')
        .then(response => response.json())
        .then(data => {
            let list = document.getElementById('hotelList');
            list.innerHTML = '';
            data.forEach(hotel => {
                let li = document.createElement('li');
                li.textContent = hotel;
                list.appendChild(li);
            });
        })
        .catch(error => console.error('Error:', error));
}

// Show rooms
function displayRooms(rooms) {
    const roomList = document.getElementById("room-list");
    roomList.innerHTML = "";

    if (!rooms.length) {
        roomList.innerHTML = "<li>No rooms found.</li>";
        return;
    }

    rooms.forEach(room => {
        const li = document.createElement("li");
        li.innerHTML = `
            Room ID: ${room.roomID}, Hotel ID: ${room.hotelID}, Price: $${room.price}, 
            Capacity: ${room.capacity}, View: ${room.view}, 
            Extendable: ${room.extendable ? "Yes" : "No"}, 
            Damages: ${room.damages || "None"}
            <br><button onclick="bookRoom(${room.roomID})">Book</button>
        `;
        roomList.appendChild(li);
    });
}

// Booking room
function bookRoom(roomID) {
    const customerID = sessionStorage.getItem("customerID");
    const checkIn = document.getElementById("Check-in").value;
    const checkOut = document.getElementById("check-out").value;

    if (!customerID) {
        alert("Please log in first.");
        return;
    }

    fetch("http://localhost:8080/api/bookings", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            customerID,
            roomID,
            checkInDate: checkIn,
            checkOutDate: checkOut
        })
    })
        .then(response => {
            if (response.ok) {
                alert("Room booked successfully!");
            } else {
                alert("Booking failed.");
            }
        })
        .catch(error => console.error("Error booking room:", error));
}

// login function
function login() {
    const role = document.getElementById("role").value.toLowerCase();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const loginUrl = `http://localhost:8080/api/auth/${role}Login`;

    fetch(loginUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    })
        .then(response => {
            if (!response.ok) return response.text().then(msg => { throw new Error(msg); });
            return response.json();
        })
        .then(data => {
            const idKey = role === "employee" ? "employeeID" : "customerID";
            sessionStorage.setItem(idKey, data[idKey]);
            alert(`Welcome, ${data.name || data.fullName || "User"}!`);
            window.location.href = role === "employee" ? "dashboard.html" : "booking.html";
        })
        .catch(error => {
            alert("Login failed: " + error.message);
        });
}


document.addEventListener("DOMContentLoaded", () => {
    const protectedPages = ["booking.html", "mybooking.html", "dashboard.html"];
    const currentPage = window.location.pathname.split("/").pop();

    if (protectedPages.includes(currentPage)) {
        const customerID = sessionStorage.getItem("customerID");
        const employeeID = sessionStorage.getItem("employeeID");
        if (!customerID && !employeeID) {
            alert("You must log in first!");
            window.location.href = "login.html";
        }
    }
});
