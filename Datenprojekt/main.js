function openPopup(popupId) {
    const popup = document.getElementById(popupId);
    if (popup) {
        popup.style.display = 'block'; 
    } else {
        console.error(`Popup with ID '${popupId}' not found.`);
    }
}

function closePopup(popupId) {
    const popup = document.getElementById(popupId);
    if (popup) {
        popup.style.display = 'none'; 
    } else {
        console.error(`Popup with ID '${popupId}' not found.`);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('diagram_one').addEventListener('click', () => openPopup('popup_one'));
    document.getElementById('diagram_two').addEventListener('click', () => openPopup('popup_two'));
    document.getElementById('diagram_three').addEventListener('click', () => openPopup('popup_three'));
});

function openSettings(popupId) {
    const settingsContent = document.querySelector(`#${popupId} .settings-content`);
    const mainContent = document.querySelector(`#${popupId} .main-content`);
    if (settingsContent) {
        
        mainContent.style.display = 'none';

       
        settingsContent.classList.remove('slide-out');
        settingsContent.classList.add('slide-in');
        settingsContent.style.display = 'block'; 
    } else {
        console.error(`Settings content not found for popup '${popupId}'`);
    }
}

function closeSettings(popupId) {
    const settingsContent = document.querySelector(`#${popupId} .settings-content`);
    const mainContent = document.querySelector(`#${popupId} .main-content`);
    if (settingsContent) {
        
        settingsContent.classList.remove('slide-in');
        settingsContent.classList.add('slide-out');

        
        setTimeout(() => {
            settingsContent.style.display = 'none';
            mainContent.style.display = 'block'; 
        }, 500); 
    } else {
        console.error(`Settings content not found for popup '${popupId}'`);
    }
}


function saveSettings(popupId) {
    
    const dropdowns = document.querySelectorAll(`#${popupId} select`);
    const values = {};
    dropdowns.forEach((dropdown) => {
        values[dropdown.id] = dropdown.value;
    });

    let endpoint;
    switch (popupId) {
        case 'popup_one':
            endpoint = '/save-settings-one'; // Controller for Diagram 1
            break;
        case 'popup_two':
            endpoint = '/save-settings-two'; // Controller for Diagram 2
            break;
        case 'popup_three':
            endpoint = '/save-settings-three'; // Controller for Diagram 3
            break;
        default:
            console.error(`Unknown popupId: ${popupId}`);
            return;
    }

const payload = {
    popupId: popupId, 
    settings: values, 
};

fetch(endpoint, { // endpoint controller in backend set in switchcase above
    method: 'POST',
    headers: {
        'Content-Type': 'application/json', 
    },
    body: JSON.stringify(payload),
})
    .then((response) => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json(); 
    })
    .then((data) => {
        console.log('Settings successfully saved:', data);
    })
    .catch((error) => {
        console.error('Error saving settings:', error); 
    });

console.log(`Sent settings for ${popupId} to ${endpoint}:`, payload); 

}
