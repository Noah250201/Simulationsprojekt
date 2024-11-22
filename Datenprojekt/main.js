// Function to open a popup
function openPopup(popupId) {
    const popup = document.getElementById(popupId);
    if (popup) {
        popup.style.display = 'block'; // Show the popup
    } else {
        console.error(`Popup with ID '${popupId}' not found.`);
    }
}

// Function to close a popup
function closePopup(popupId) {
    const popup = document.getElementById(popupId);
    if (popup) {
        popup.style.display = 'none'; // Hide the popup
    } else {
        console.error(`Popup with ID '${popupId}' not found.`);
    }
}

// Attach event listeners after DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('diagram_one').addEventListener('click', () => openPopup('popup_one'));
    document.getElementById('diagram_two').addEventListener('click', () => openPopup('popup_two'));
    document.getElementById('diagram_three').addEventListener('click', () => openPopup('popup_three'));
});

function openSettings(popupId) {
    const popupContent = document.querySelector(`#${popupId} .main-content`);
    if (popupContent) {
        popupContent.innerHTML = `
            <h2>Einstellungen</h2>
            <p>Hier kannst du deine Einstellungen vornehmen.</p>
            <button onclick="closeSettings('${popupId}')">Zurück</button>
        `;
    } else {
        console.error(`Main content not found for popup '${popupId}'`);
    }
}

function closeSettings(popupId) {
    const popupContent = document.querySelector(`#${popupId} .main-content`);
    if (popupContent) {
        popupContent.innerHTML = `
            <span class="close" onclick="closePopup('${popupId}')">&times;</span>
            <p>Content for Diagram 1</p>
        `;
    } else {
        console.error(`Main content not found for popup '${popupId}'`);
    }
}

