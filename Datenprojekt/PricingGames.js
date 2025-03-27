let chartInstances = {};

function updateChartTwoWithData(data, chartId) {
    const jsonfile = data;

    // Hole die Daten
    var currentPrice = jsonfile.jsonarray.map(function(e) {
        return e.currentPrice;
    });

    var initialPrice = jsonfile.jsonarray.map(function(e) {
        return e.initialPrice;
    });

    // Hier gehe ich davon aus, dass es ein Feld "date" im JSON gibt, das du verwenden möchtest
    var date = jsonfile.jsonarray.map(function(e) {
        return e.date || "Kein Datum"; // Sicherstellen, dass ein Platzhalter verwendet wird, falls kein Datum vorhanden ist
    });

    var labels = jsonfile.jsonarray.map(function(e) {
        return e.game;
    });


    const chartData = {
        labels: labels,
        datasets: [
            {
                label: 'Aktueller Preis in €',
                data: currentPrice,
                stack: 'Stack 1'
            },
            {
                label: 'Initialer Preis in €',
                data: initialPrice,
                stack: 'Stack 2'
            }
        ]
    };

    const config = {
        type: 'bar',
        data: chartData,
        options: {
            responsive: true,
            interaction: {
                intersect: false
            },
            scales: {
                x: {
                    // Zeigt die Spiele an (labels)
                    display: true,
                    title: {
                        display: true,
                        text: 'Spiele'
                    }
                },
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Preis (€)'
                    }
                }
            },
            ticks: {
                beginAtZero: true,
                font: {
                    size: 14,
                }
            }
        }
    };

    let ctx1 = document.getElementById('d3');
    let ctx2 = document.getElementById('d3_2');

    // Falls die Charts schon existieren -> zerstören, um sie mit neuen Daten zu rendern
    if (chartInstances['d3']) {
        chartInstances['d3'].destroy();
    }
    if (chartInstances['d3_2']) {
        chartInstances['d3_2'].destroy();
    }

    // Neue Charts in beide Canvas rendern
    if (ctx1) {
        chartInstances['d3'] = new Chart(ctx1.getContext('2d'), config);
    }
    if (ctx2) {
        chartInstances['d3_2'] = new Chart(ctx2.getContext('2d'), config);
    }

    // Resize-Event für beide Canvas-Elemente
    window.addEventListener('resize', function() {
        resizeCanvas(ctx1);
        resizeCanvas(ctx2);
    });
}
// Hilfsfunktion, um die Größe des Canvas anzupassen
function resizeCanvas(canvas) {
    if (canvas) {
        canvas.width = canvas.parentElement.clientWidth;
        canvas.height = canvas.parentElement.clientHeight;
    }
}