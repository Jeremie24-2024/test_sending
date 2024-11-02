document.addEventListener('DOMContentLoaded', function() {
    // Button to animate on click
    const heroButton = document.getElementById('hero-button');
    
    heroButton.addEventListener('click', function() {
        alert('More information will be available soon!');
    });

    // Logout button functionality (for demonstration purposes)
    const logoutButton = document.getElementById('logout-button');

    logoutButton.addEventListener('click', function() {
        alert('Logging out...');
        // Here you would typically handle the logout process.
    });
});


//Add event listener to each faculty card
const facultyCards = document.querySelectorAll('.faculty-card');

faculties.forEach(card => {
    card.addEventListener('mouseover', () => {
        // Animate the card on hover
        card.classList.add('animate');
    });

    card.addEventListener('mouseout', () => {
        // Remove animation on mouse out
        card.classList.remove('animate');
    });
});



//... (previous JS code) 

document.addEventListener('DOMContentLoaded', function() {
    // Existing logout and hero button code...

    // Carousel for student life section
    const carouselItems = document.querySelectorAll('.carousel-item');
    let currentIndex = 0;

    function showNextItem() {
        carouselItems[currentIndex].classList.remove('active');
        currentIndex = (currentIndex + 1) % carouselItems.length; // Cycle through items
        carouselItems[currentIndex].classList.add('active');
    }

    // Initialize first item as active
    carouselItems[0].classList.add('active');

    // Change item every 3 seconds
    setInterval(showNextItem, 3000);
});






