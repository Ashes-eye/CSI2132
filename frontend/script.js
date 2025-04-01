// Fetch greeting
document.addEventListener("DOMContentLoaded", () => {
    fetchGreeting();
    const form = document.getElementById("searchForm");
    if (form) {
        form.addEventListener("submit", handleSearch);
    }
    checkSessionProtection();
});

function fetchGreeting() {
    fetch('http://localhost:8080/api/hotels/greeting')
        .then(res => res.ok ? res.text() : Promise.reject("Network Error"))
        .then(data => document.getElementById('response').innerText = data)
        .catch(console.error);
}

function handleSearch(e) {
    e.preventDefault();
    const location = document.getElementById("location").value.toLowerCase();
    const capacity = document.getElementById("capacity").value;
    const stars = document.getElementById("stars").value;
    const view = document.getElementById("View").value.toLowerCase();

    fetch("http://localhost:8080/api/rooms")
        .then(res => res.ok ? res.json() : Promise.reject("Failed to fetch rooms"))
        .then(data => {
            const filtered = data.filter(room =>
                (!location || room.hotelAddress.toLowerCase().includes(location)) &&
                (!capacity || room.capacity >= parseInt(capacity)) &&
                (!stars || String(room.hotelRating) === stars) &&
                (!view || room.view?.toLowerCase().includes(view))
            );
            displayRooms(filtered);
        })
        .catch(console.error);
}

function fetchHotels() {
    fetch('http://localhost:8080/api/hotels/list')
        .then(res => res.json())
        .then(data => {
            const list = document.getElementById('hotelList');
            list.innerHTML = '';
            data.forEach(hotel => {
                const li = document.createElement('li');
                li.textContent = hotel;
                list.appendChild(li);
            });
        })
        .catch(console.error);
}

function displayRooms(rooms) {
    const roomList = document.getElementById("room-list");
    roomList.innerHTML = rooms.length ? '' : "<li>No rooms found.</li>";

    rooms.forEach(room => {
        const li = document.createElement("li");
        li.innerHTML = `
            Room ID: ${room.roomId}, Hotel ID: ${room.hotelId}, Price: $${room.price}, 
            Capacity: ${room.capacity}, View: ${room.view || "N/A"}, 
            Extendable: ${room.extendable ? "Yes" : "No"}, 
            Damages: ${room.damages || "None"}
            <br><button onclick="bookRoom(${room.roomId})">Book</button>
        `;
        roomList.appendChild(li);
    });
}

function bookRoom(roomId) {
    const customerId = sessionStorage.getItem("customerID");
    const checkIn = document.getElementById("Check-in").value;
    const checkOut = document.getElementById("check-out").value;

    if (!customerId) return alert("Please log in first.");

    fetch("http://localhost:8080/api/booking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            customerId: parseInt(customerId),
            roomId: roomId,
            startDate: checkIn,
            endDate: checkOut
        })
    })
        .then(res => res.ok ? res.json() : res.text().then(text => Promise.reject(text)))
        .then(data => alert("Room booked successfully! Booking ID: " + data.bookingId))
        .catch(err => alert("Booking failed: " + err));
}

function login() {
    const role = document.getElementById("role").value.toLowerCase();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (!email || !password) return alert("Please enter both email and password");

    const loginUrl = `http://localhost:8080/api/auth/${role}Login`;

    fetch(loginUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    })
        .then(res => res.ok ? res.json() : res.text().then(msg => Promise.reject(msg)))
        .then(data => {
            if (role === "employee") {
                sessionStorage.setItem("employeeID", data.employeeId);
                sessionStorage.setItem("employeeName", data.fullName || data.name || "Employee");
                sessionStorage.removeItem("customerID");
            } else {
                sessionStorage.setItem("customerID", data.customerId);
                sessionStorage.setItem("customerName", data.fullName || data.name || "Customer");
                sessionStorage.removeItem("employeeID");
            }
            alert(`Welcome, ${data.fullName || data.name || "User"}!`);
            window.location.href = role === "employee" ? "dashboard.html" : "booking.html";
        })
        .catch(err => alert("Login failed: " + err));
}

function checkSessionProtection() {
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
}