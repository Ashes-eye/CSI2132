//  Function to fetch greeting from backend
function fetchGreeting() {
    fetch('http://localhost:8080/api/hotels/greeting')
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('response').innerText = data;
        })
        .catch(error => console.error('Error:', error));
}

//  Function to fetch hotels (Example for another page)
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

// Function to fetch and display rooms when the form is submitted
document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("searchForm");

    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault(); // Prevent form from submitting

            // Fetch all rooms
            fetch("http://localhost:8080/api/rooms")
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Failed to fetch rooms");
                    }
                    return response.json();
                })
                .then(data => displayRooms(data))
                .catch(error => console.error("Error fetching rooms:", error));
        });
    }
});

// Helper function to display room data in the DOM
function displayRooms(rooms) {
    const roomList = document.getElementById("room-list");
    roomList.innerHTML = ""; // Clear existing content

    if (!rooms.length) {
        roomList.innerHTML = "<li>No rooms found.</li>";
        return;
    }

    rooms.forEach(room => {
        const li = document.createElement("li");
        li.textContent = `Room ID: ${room.roomID}, Hotel ID: ${room.hotelID}, Price: $${room.price}, Capacity: ${room.capacity}, View: ${room.view}, Extendable: ${room.extendable ? "Yes" : "No"}, Damages: ${room.damages || "None"}`;
        roomList.appendChild(li);
    });
}

