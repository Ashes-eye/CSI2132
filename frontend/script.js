// ✅ Function to fetch greeting from backend
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

// ✅ Function to fetch hotels (Example for another page)
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
