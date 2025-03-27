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
    const dateInputs = document.querySelectorAll(`#${popupId} input[type="date"]`);
    const values = {};
    dropdowns.forEach((dropdown) => {
        values[dropdown.id] = dropdown.value;
    });
    dateInputs.forEach((input) => {
        values[input.id] = input.value;  // Datumswert korrekt erfassen
    });

    let endpoint = 'http://localhost:8080';
    switch (popupId) {
        case 'popup_one':
            endpoint = endpoint + '/pricingGames'; // Controller for Diagram 1
            break;
        case 'popup_two':
            endpoint = endpoint + '/averagePlayersStockPrice'; // Controller for Diagram 2
            break;
        case 'popup_three':
            endpoint = endpoint + '/publisherStockAndViews'; // Controller for Diagram 3
            break;
        default:
            console.error(`Unknown popupId: ${popupId}`);
            return;
    }

const payload = {
    popupId: popupId, 
    settings: values, 
};

fetch(endpoint, {
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

        if (popupId === 'popup_one') {
            updateChartTwoWithData(data, 'chart2');
        } else if (popupId === 'popup_two') {
            updateChartThreeWithData(data, 'chart3');
        } else if (popupId === 'popup_three') {
            updateChartOneWithData(data, 'chart1');
        }
    })
    .catch((error) => {
        console.error('Error saving settings:', error);
    });

console.log(`Sent settings for ${popupId} to ${endpoint}:`, payload); 

}

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('diagram_one').addEventListener('click', () => openPopup('popup_one'));
    document.getElementById('diagram_two').addEventListener('click', () => openPopup('popup_two'));
    document.getElementById('diagram_three').addEventListener('click', () => openPopup('popup_three'));

    setupPublisherDropdown('dropdown1_1', 'popup_one');
    setupPublisherDropdown('dropdown2_1', 'popup_two');

    setupDatePickers(['dropdown1_2', 'dropdown1_3', 'dropdown3_2', 'dropdown3_3']);
});

document.addEventListener('DOMContentLoaded', () => {
    // Event-Listener für die Publisher-Dropdowns hinzufügen
    const publisher1Dropdown = document.getElementById('dropdown1_1');
    const publisher2Dropdown = document.getElementById('dropdown2_1');
    
    publisher1Dropdown.addEventListener('change', (event) => {
        const selectedPublisher = event.target.value;
        updateGamesDropdowns(selectedPublisher, '1');
    });
    
    // Initiales Befüllen der Spiele-Dropdowns für Popup 1
    const initial1Publisher = publisher1Dropdown.value;
    if (initial1Publisher) {
        updateGamesDropdowns(initial1Publisher, '1');
    }

    publisher2Dropdown.addEventListener('change', (event) => {
        const selectedPublisher = event.target.value;
        updateGamesDropdowns(selectedPublisher, '2');
    });

    // Initiales Befüllen der Spiele-Dropdowns für Popup 2
    const initial2Publisher = publisher2Dropdown.value;
    if (initial2Publisher) {
        updateGamesDropdowns(initial2Publisher, '2');
    }
});

// Objekt mit den Publishern und ihren Spielen
const publishersAndGames = {
Ubisoft: ['Age-Old Cities VR', 'Anno 1404 - History Edition', 'Anno 1800', 'Anno 2070', 'Anno 2205', 'Anno Online', 'Are you ready for Valve Index?', 'Assassin\'s Creed 2', 'Assassin\'s Creed Freedom Cry', 'Assassin\'s Creed III Remastered', 'Assassin\'s Creed Mirage', 'Assassin\'s Creed Odyssey', 'Assassin\'s Creed Origins', 'Assassin\'s Creed Revelations', 'Assassin\'s Creed Syndicate', 'Assassin\'s Creed Unity', 'Assassin\'s Creed Valhalla', 'Assassin\'s Creed: Director\'s Cut Edition', 'Assassin\’s Creed Brotherhood', 'Assassin\’s Creed Chronicles: China', 'Assassin\’s Creed Chronicles: India', 'Assassin\’s Creed Chronicles: Russia', 'Assassin’s Creed III', 'Assassin’s Creed IV Black Flag', 'Assassin’s Creed Liberation HD', 'Assassin’s Creed Rogue', 'ATOMEGA', 'Avatar: Frontiers of Pandora', 'Avatar: Frontiers of Pandora', 'Beyond Good & Evil - 20th Anniversary Edition', 'Beyond Good and Evil', 'Bloody Good Time', 'Brawlhalla', 'Brothers in Arms: Earned in Blood', 'Brothers in Arms: Hell\'s Highway', 'Brothers in Arms: Road to Hill 30', 'Cave to Kingdom', 'Child of Light', 'Cloudberry Kingdom', 'Cold Fear', 'Dark Messiah of Might & Magic', 'Dawn of Discovery', 'Eagle Flight', 'Far Cry', 'Far Cry 2', 'Far Cry 3', 'Far Cry 3 - Blood Dragon', 'Far Cry 4', 'Far Cry 5', 'Far Cry 6', 'Far Cry New Dawn', 'Far Cry Primal', 'Flashback', 'FOR HONOR', 'From Dust', 'Grow Home', 'Grow Up', 'Heroes of Might & Magic III - HD Edition', 'Heroes of Might & Magic V', 'Heroes of Might & Magic V: Hammers of Fate', 'Heroes of Might & Magic V: Tribes of the East', 'I Am Alive', 'Immortals Fenyx Rising', 'Just Dance 2017', 'Might & Magic Heroes Online', 'Might & Magic Heroes VII', 'Might & Magic X - Legacy', 'Might & Magic: Clash of Heroes', 'Might & Magic: Heroes VI', 'Might & Magic: Heroes VI - Shades of Darkness', 'Might and Magic: Heroes VII – Trial by Fire', 'MONOPOLY PLUS', 'Notre-Dame de Paris: Journey Back in Time', 'Prince of Persia', 'Prince of Persia The Lost Crown', 'Prince of Persia: The Forgotten Sands', 'Prince of Persia: The Sands of Time', 'Prince of Persia: The Two Thrones', 'Prince of Persia: Warrior Within', 'Rayman Legends', 'Rayman Origins', 'Rayman Raving Rabbids', 'Riders Republic', 'RoboBlitz', 'Rocksmith', 'Rocksmith 2014 Edition REMASTERED LEARN & PLAY', 'Scott Pilgrim vs. The World: The Game – Complete Edition', 'ShootMania Storm', 'Silent Hunter 5: Battle of the Atlantic', 'Silent Hunter III', 'Silent Hunter: Wolves of the Pacific', 'Silent Hunter: Wolves of the Pacific U-Boat Missions', 'Skull and Bones', 'South Park: The Fractured But Whole', 'South Park: The Stick of Truth', 'Space Junkies', 'Space Junkies', 'Star Trek: Bridge Crew', 'Star Wars Outlaws', 'Starlink: Battle for Atlas', 'Steep', 'Tetris Ultimate', 'The Crew', 'The Crew 2', 'The Crew Motorfest', 'The Rogue Prince of Persia', 'The Settlers : Heritage of Kings - History Edition', 'The Settlers : Rise of an Empire - History Edition', 'The Settlers 7 : History Edition', 'The Settlers Online', 'The Settlers: New Allies', 'Tom Clancy\'s EndWar', 'Tom Clancy\'s Ghost Recon', 'Tom Clancy\'s Ghost Recon Breakpoint', 'Tom Clancy\'s Ghost Recon Desert Siege', 'Tom Clancy\'s Ghost Recon Island Thunder', 'Tom Clancy\'s Ghost Recon Wildlands', 'Tom Clancy\'s Ghost Recon: Future Soldier', 'Tom Clancy\'s Rainbow Six 3 Gold', 'Tom Clancy\'s Rainbow Six Lockdown', 'Tom Clancy\'s Rainbow Six Siege', 'Tom Clancy\'s Rainbow Six Vegas', 'Tom Clancy\'s Rainbow Six Vegas 2', 'Tom Clancy\'s Splinter Cell', 'Tom Clancy\'s Splinter Cell Chaos Theory', 'Tom Clancy\'s Splinter Cell Conviction', 'Tom Clancy\'s Splinter Cell Conviction', 'Tom Clancy\'s Splinter Cell Double Agent', 'Tom Clancy\’s Rainbow Six Extraction', 'Tom Clancy\’s Splinter Cell Blacklist', 'Tom Clancy’s The Division', 'Tom Clancy’s The Division 2', 'TrackMania Nations Forever', 'Trackmania Turbo', 'Trackmania United Forever', 'TrackMania² Canyon', 'Trackmania² Lagoon', 'TrackMania² Stadium', 'TrackMania² Valley', 'Transference', 'Trials Evolution: Gold Edition', 'Trials Fusion', 'Trials of the Blood Dragon', 'Trials Rising', 'UNO', 'Valiant Hearts: The Great War / Soldats Inconnus : Mémoires de la Grande Guerre', 'Watch Dogs: Legion', 'Watch_Dogs', 'Watch_Dogs 2', 'ZOMBI'],
EA: ['A Way Out', 'Alice: Madness Returns', 'Apex Legends', 'AudioSurf', 'Battlefield 1', 'Battlefield 2042', 'Battlefield 3', 'Battlefield 4', 'Battlefield Hardline', 'Battlefield V', 'Battlefield: Bad Company 2', 'Battlefield: Bad Company 2 Vietnam', 'Bejeweled 2 Deluxe', 'Bejeweled 3', 'Bejeweled Deluxe', 'Bejeweled Twist', 'Bullet Candy', 'Burnout Paradise Remastered', 'Burnout Paradise: The Ultimate Box', 'Chuzzle Deluxe', 'Command & Conquer 3: Kane\'s Wrath', 'Command & Conquer 3: Tiberium Wars', 'Command & Conquer 4: Tiberian Twilight', 'Command & Conquer Remastered Collection', 'Command & Conquer: Red Alert 3', 'Command & Conquer: Red Alert 3 - Uprising', 'Crysis', 'Crysis 2 - Maximum Edition', 'Crysis 3', 'Crysis Warhead', 'Damnation', 'Dead Space', 'Dead Space (2008)', 'Dead Space 2', 'Dead Space 3', 'DeathSpank', 'DeathSpank: Thongs of Virtue', 'DiRT 4', 'DIRT 5', 'DiRT Rally', 'DiRT Rally 2.0', 'Dragon Age II: Ultimate Edition', 'Dragon Age Inquisition', 'Dragon Age: Origins', 'Dragon Age: Origins - Ultimate Edition', 'Dragon Age: Origins Awakening', 'Dragon Age: The Veilguard', 'Dungeon Keeper 2', 'Dungeon Keeper Gold', 'EA SPORTS FC 24', 'EA SPORTS FC 25', 'EA SPORTS FIFA 21', 'EA SPORTS FIFA 23', 'EA SPORTS Madden NFL 25', 'EA SPORTS PGA TOUR', 'EA SPORTS WRC', 'F1 2012', 'F1 2014', 'F1 2016', 'F1 2017', 'F1 2018', 'F1 2019', 'F1 2020', 'F1 2021', 'F1 22', 'F1 23', 'F1 24', 'Fe', 'Feeding Frenzy 2 Deluxe', 'FIFA 22', 'GRID', 'GRID Legends', 'Hospital Tycoon', 'Immortals of Aveum', 'Insaniquarium Deluxe', 'It Takes Two', 'Jade Empire: Special Edition', 'Kingdoms of Amalur: Reckoning', 'Lost in Random', 'Madden NFL 21', 'Madden NFL 22', 'Madden NFL 23', 'Madden NFL 24', 'Maelstrom: The Battle for Earth Begins', 'Mass Effect (2007)', 'Mass Effect 2 (2010 Edition)', 'Mass Effect 3 N7 Digital Deluxe Edition (2012)', 'Mass Effect Legendary Edition', 'Mass Effect: Andromeda Deluxe Edition', 'Medal of Honor', 'Medal of Honor', 'Medal of Honor: Above and Beyond', 'Medal of Honor: Airborne', 'Micro Machines World Series', 'Mirror\'s Edge', 'Mirror\'s Edge Catalyst', 'Myst III: Exile', 'Need for Speed', 'Need for Speed Heat', 'Need for Speed Hot Pursuit Remastered', 'Need for Speed Most Wanted', 'Need for Speed Payback', 'Need for Speed Rivals', 'Need for Speed Unbound', 'Need for Speed Undercover', 'Need For Speed: Hot Pursuit', 'Need for Speed: Shift', 'Nimbus', 'Operation Flashpoint: Dragon Rising', 'Overlord', 'Overlord II', 'Overlord: Fellowship of Evil', 'Overlord: Raising Hell', 'Peggle Deluxe', 'Peggle Extreme', 'Peggle Nights', 'Plants vs. Zombies Garden Warfare 2: Deluxe Edition', 'Plants vs. Zombies GOTY Edition', 'Plants vs. Zombies: Battle for Neighborville', 'Populous', 'Populous II: Trials of the Olympian Gods', 'Populous: The Beginning', 'Project CARS', 'Rise of the Argonauts', 'Rocket Arena', 'Sea of Solitude', 'Shift 2 Unleashed', 'Sid Meier\'s Alpha Centauri Planetary Pack', 'SimCity 3000 Unlimited', 'SimCity 4 Deluxe Edition', 'SPORE', 'SPORE Creepy & Cute Parts Pack', 'SPORE Galactic Adventures', 'STAR WARS Battlefront', 'STAR WARS Battlefront II', 'STAR WARS Jedi: Fallen Order', 'STAR WARS Jedi: Survivor', 'STAR WARS: Squadrons', 'STAR WARS: The Old Republic', 'Super Mega Baseball 2', 'Super Mega Baseball 3', 'Super Mega Baseball 4', 'Super Mega Baseball: Extra Innings', 'Tales of Kenzera: ZAU', 'Tales of Kenzera: ZAU', 'The Saboteur', 'The Sims 3', 'The Sims 4', 'Titanfall­', 'Titanfall 2', 'Toybox Turbos', 'Unravel', 'Unravel Two', 'Warp', 'WILD HEARTS', 'Zuma Deluxe', 'Zuma\'s Revenge!'],
SquareEnix: ['Actraiser Renaissance', 'Anachronox', 'BABYLON\'S FALL', 'BALAN WONDERWORLD', 'Battlestations Pacific', 'Battlestations: Midway', 'Black The Fall', 'BRAVELY DEFAULT II', 'Children of Zodiarcs', 'CHRONO CROSS: THE RADICAL DREAMERS EDITION', 'CHRONO TRIGGER', 'COLLECTION of SaGa FINAL FANTASY LEGEND', 'Conflict Desert Storm', 'Conflict: Denied Ops', 'CRISIS CORE –FINAL FANTASY VII– REUNION', 'Daikatana', 'Deadbeat Heroes', 'Deathtrap Dungeon', 'DISSIDIA FINAL FANTASY NT Free Edition', 'DRAGON QUEST BUILDERS 2', 'DRAGON QUEST HEROES II', 'DRAGON QUEST HEROES Slime Edition', 'DRAGON QUEST III HD-2D Remake', 'DRAGON QUEST XI S: Echoes of an Elusive Age - Definitive Edition', 'DRAGON QUEST XI: Echoes of an Elusive Age - Digital Edition of Light', 'DUNGEON ENCOUNTERS', 'Dungeon Siege', 'Dungeon Siege II', 'Dungeon Siege III', 'FANTASIAN Neo Dimension', 'FINAL FANTASY', 'FINAL FANTASY II', 'FINAL FANTASY III', 'Final Fantasy III (3D Remake)', 'FINAL FANTASY IV', 'Final Fantasy IV (3D Remake)', 'FINAL FANTASY IV: THE AFTER YEARS', 'FINAL FANTASY IX', 'FINAL FANTASY TYPE-0 HD', 'FINAL FANTASY V', 'FINAL FANTASY V', 'FINAL FANTASY VI', 'FINAL FANTASY VI', 'FINAL FANTASY VII', 'FINAL FANTASY VII REMAKE INTERGRADE', 'FINAL FANTASY VIII', 'FINAL FANTASY VIII - REMASTERED', 'FINAL FANTASY X/X-2 HD Remaster', 'FINAL FANTASY XII THE ZODIAC AGE', 'FINAL FANTASY XIII', 'FINAL FANTASY XIII-2', 'FINAL FANTASY XIV Online', 'FINAL FANTASY XV WINDOWS EDITION', 'FINAL FANTASY XV WINDOWS EDITION MOD ORGANIZER', 'FINAL FANTASY XVI', 'Flora\'s Fruit Farm', 'Forspoken', 'Front Mission Evolved', 'Gyromancer', 'HARVESTELLA', 'I am Setsuna', 'Infinity Strash: DRAGON QUEST The Adventure of Dai', 'Just Cause', 'Just Cause 2', 'Just Cause 2: Multiplayer Mod', 'Just Cause 3', 'Just Cause 3: Multiplayer Mod', 'Just Cause 4 Reloaded', 'Kane & Lynch 2: Dog Days', 'Kane and Lynch: Dead Men', 'KINGDOM HEARTS -HD 1.5+2.5 ReMIX-', 'KINGDOM HEARTS III + Re Mind (DLC)', 'LEFT ALIVE', 'Legend of Mana', 'Life is Strange - Episode 1', 'Life is Strange 2', 'Life is Strange Remastered', 'Life is Strange: Before the Storm', 'Life is Strange: Before the Storm Remastered', 'Life is Strange: Double Exposure', 'Life is Strange: True Colors', 'LIGHTNING RETURNS: FINAL FANTASY XIII', 'Little Goody Two Shoes', 'LIVE A LIVE', 'LIVE A LIVE', 'Mini Ninjas', 'MOBIUS FINAL FANTASY', 'Murdered: Soul Suspect', 'NieR Replicant ver.1.22474487139...', 'NieR:Automata', 'OCTOPATH TRAVELER', 'OCTOPATH TRAVELER II', 'Oh My Godheads', 'Omikron: The Nomad Soul', 'ONINAKI', 'Order of War', 'OUTRIDERS', 'Pandemonium', 'PARANORMASIGHT: The Seven Mysteries of Honjo', 'PowerWash Simulator', 'Project: Snowblind', 'Quantum Conundrum', 'Romancing SaGa -Minstrel Song- Remastered', 'Romancing SaGa 2', 'Romancing SaGa 2: Revenge of the Seven', 'Romancing SaGa 3', 'SaGa Frontier Remastered', 'SaGa SCARLET GRACE: AMBITIONS', 'SEASON OF MYSTERY: The Cherry Blossom Murders', 'Secret of Mana', 'Sleeping Dogs', 'Sleeping Dogs: Definitive Edition', 'STAR OCEAN - THE LAST HOPE - 4K & Full HD Remaster', 'STAR OCEAN THE DIVINE FORCE', 'STAR OCEAN THE SECOND STORY R', 'STRANGER OF PARADISE FINAL FANTASY ORIGIN', 'Supreme Commander', 'Supreme Commander 2', 'Supreme Commander: Forged Alliance', 'Tactics Ogre: Reborn', 'The Awesome Adventures of Captain Spirit', 'The Centennial Case : A Shijima Story', 'The DioField Chronicle', 'The Last Remnant', 'THE QUIET MAN', 'Tokyo Dark', 'Trials of Mana', 'TRIANGLE STRATEGY', 'VALKYRIE ELYSIUM', 'VARIOUS DAYLIFE', 'Visions of Mana', 'Voice of Cards: The Forsaken Maiden', 'Voice of Cards: The Isle Dragon Roars', 'WORLD OF FINAL FANTASY', 'Yosumin!'],
BandaiNamco: ['11-11 Memories Retold', 'Accel World VS. Sword Art Online Deluxe Edition', 'ACE COMBAT 7: SKIES UNKNOWN', 'Ace Combat Assault Horizon - Enhanced Edition', 'ARCADE GAME SERIES: DIG DUG', 'ARCADE GAME SERIES: GALAGA', 'ARCADE GAME SERIES: Ms. PAC-MAN', 'ARCADE GAME SERIES: PAC-MAN', 'ARMORED CORE VI FIRES OF RUBICON', 'BLACK CLOVER: QUARTET KNIGHTS', 'Captain Tsubasa: Rise of New Champions', 'CODE VEIN', 'DARK SOULS II', 'DARK SOULS II: Scholar of the First Sin', 'DARK SOULS III', 'DARK SOULS: Prepare To Die Edition', 'DARK SOULS: REMASTERED', 'DEATH NOTE Killer Within', 'Digimon Story Cyber Sleuth: Complete Edition', 'Digimon Survive', 'Digimon World: Next Order', 'DORAEMON  STORY OF SEASONS', 'DRAGON BALL FighterZ', 'DRAGON BALL XENOVERSE', 'DRAGON BALL XENOVERSE 2', 'DRAGON BALL Z: KAKAROT', 'DRAGON BALL: Sparking! ZERO', 'DRAGON BALL: THE BREAKERS', 'Duelyst', 'ELDEN RING', 'ENSLAVED: Odyssey to the West Premium Edition', 'GET EVEN', 'GOD EATER 2 Rage Burst', 'GOD EATER 3', 'GUNDAM BREAKER 4', 'Impact Winter', 'Inversion', 'JoJo\'s Bizarre Adventure: All-Star Battle R', 'Jujutsu Kaisen Cursed Clash', 'JUMP FORCE', 'Katamari Damacy REROLL', 'Klonoa Phantasy Reverie Series', 'Little Nightmares', 'Little Nightmares II', 'Little Witch Academia: Chamber of Time', 'Mr. DRILLER DrillLand', 'MY HERO ONE\'S JUSTICE', 'MY HERO ONE\'S JUSTICE 2', 'NARUTO SHIPPUDEN: Ultimate Ninja STORM 2', 'NARUTO SHIPPUDEN: Ultimate Ninja STORM 3 Full Burst HD', 'NARUTO SHIPPUDEN: Ultimate Ninja STORM 4', 'NARUTO SHIPPUDEN: Ultimate Ninja STORM Revolution', 'NARUTO TO BORUTO: SHINOBI STRIKER', 'NARUTO X BORUTO Ultimate Ninja STORM CONNECTIONS', 'NARUTO: Ultimate Ninja STORM', 'Ni no Kuni II: Revenant Kingdom', 'Ni no Kuni Wrath of the White Witch Remastered', 'One Piece Burning Blood', 'ONE PIECE ODYSSEY', 'One Piece Pirate Warriors 3', 'ONE PIECE World Seeker', 'ONE PIECE: PIRATE WARRIORS 4', 'ONE PUNCH MAN: A HERO NOBODY KNOWS', 'PAC-MAN 256', 'PAC-MAN and the Ghostly Adventures', 'PAC-MAN CHAMPIONSHIP EDITION 2', 'PAC-MAN Championship Edition DX+', 'PAC-MAN MUSEUM', 'PAC-MAN MUSEUM+', 'PAC-MAN WORLD Re-PAC', 'Platformines', 'Professional Lumberjack 2015', 'Project CARS - Pagani Edition', 'Project CARS 2', 'Project CARS 3', 'RAD', 'Ridge Racer Unbounded', 'Saint Seiya: Soldiers\' Soul', 'SAND LAND', 'SCARLET NEXUS', 'SD GUNDAM BATTLE ALLIANCE', 'SD GUNDAM G GENERATION CROSS RAYS', 'SOULCALIBUR VI', 'Super Robot Wars 30', 'SWORD ART ONLINE Alicization Lycoris', 'SWORD ART ONLINE Fractured Daydream', 'SWORD ART ONLINE Last Recollection', 'Sword Art Online Re: Hollow Fragment', 'Sword Art Online: Fatal Bullet', 'Sword Art Online: Hollow Realization Deluxe Edition', 'Sword Art Online: Lost Song', 'Taiko no Tatsujin: Rhythm Festival', 'Tales of Arise', 'Tales of Berseria', 'Tales of Symphonia', 'Tales of Vesperia: Definitive Edition', 'Tales of Zestiria', 'TEKKEN 7', 'TEKKEN 8', 'The Dark Pictures Anthology: House of Ashes', 'The Dark Pictures Anthology: Little Hope', 'The Dark Pictures Anthology: Man of Medan', 'The Dark Pictures Anthology: The Devil in Me', 'TOKYO GHOUL:re [CALL to EXIST]', 'Unknown 9: Awakening', 'We Love Katamari REROLL+ Royal Reverie'],
NetEase: ['Creative Destruction', 'Cyber Hunter', 'NARAKA: BLADEPOINT', 'Nostos', 'Onmyoji', 'Rules Of Survival']

};

// Funktion, um die Spiele-Dropdowns basierend auf dem Publisher zu befüllen
function updateGamesDropdowns(publisher, popupNumber) {
    // Auswahl der Dropdowns basierend auf dem Popup-Nummer
    const gameDropdowns = [];
    
    if (popupNumber === '1') {
        // Dropdowns für Popup 1 (mit den IDs `dropdown1_X`)
        gameDropdowns.push(
            document.getElementById('dropdown1_4'),
            document.getElementById('dropdown1_5'),
            document.getElementById('dropdown1_6'),
            document.getElementById('dropdown1_7'),
            document.getElementById('dropdown1_8')
        );
    } else if (popupNumber === '2') {
        // Dropdowns für Popup 2 (mit den IDs `dropdown2_X`)
        gameDropdowns.push(
            document.getElementById('dropdown2_3'),
            document.getElementById('dropdown2_4'),
            document.getElementById('dropdown2_5'),
            document.getElementById('dropdown2_6'),
            document.getElementById('dropdown2_7')
        );
    }

    // Die Liste der Spiele basierend auf dem Publisher holen
    const games = publishersAndGames[publisher] || [];

    // Durch jedes Dropdown iterieren und es mit den entsprechenden Spielen füllen
    gameDropdowns.forEach((dropdown) => {
        dropdown.innerHTML = '';  // Zuerst alle aktuellen Optionen entfernen
        
        // Standard "Select Game" Option hinzufügen
        const defaultOption = document.createElement('option');
        defaultOption.value = '';
        defaultOption.textContent = 'Select Game';
        dropdown.appendChild(defaultOption);
        
        // Für jedes Spiel aus der Liste eine Option hinzufügen
        games.forEach(game => {
            const option = document.createElement('option');
            option.value = game;
            option.textContent = game;
            dropdown.appendChild(option);
        });
    });
}