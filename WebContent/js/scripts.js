// Function to check for error messages in the URL
document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const errorMessageElement = document.getElementById('error-message');

    // Check if there's an error in the URL query string
    if (urlParams.has('error')) {
        const error = urlParams.get('error');
        let errorMessage = '';

        if (error === 'invalid_credentials') {
            errorMessage = 'Invalid email or password. Please try again.';
        } else if (error === 'unknown_role') {
            errorMessage = 'Unknown role. Please contact the administrator.';
        } else {
            errorMessage = 'An unknown error occurred. Please try again later.';
        }

        // Display the error message in the div
        errorMessageElement.innerHTML = `<p class="error">${errorMessage}</p>`;
    }

    // Add form validation on submit
    const loginForm = document.getElementById('login-form');
    loginForm.addEventListener('submit', function (e) {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        if (!email || !password) {
            e.preventDefault();  // Prevent form submission if any field is empty
            errorMessageElement.innerHTML = '<p class="error">Please fill in both fields.</p>';
        } else {
            errorMessageElement.innerHTML = ''; // Clear error message if valid
        }
    });
});
