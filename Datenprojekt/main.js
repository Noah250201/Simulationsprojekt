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

    console.log(`Saved settings for ${popupId}:`, values);

    // Ausgewählte Settings an Backend senden
}


