
function updateChartTwoWithData(data, chartId) {
    const jsonfile = data;

    // Hole die Daten
    var currentPrice = jsonfile.jsonarray.map(function(e) {
        return e.currentPrice;
    });

    var initialPrice = jsonfile.jsonarray.map(function(e) {
        return e.initalPrice;
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

    // Canvas für das Diagramm holen und es anpassen
    var ctx1 = document.getElementById(chartId); // Hier wird die ID für das Canvas verwendet
    resizeCanvas(ctx1);
    var chart1 = new Chart(ctx1, config);

    // Wenn du noch ein zweites Canvas hast (z.B. d2_2), dann das hier:
    var ctx2 = document.getElementById(chartId + "_2");
    resizeCanvas(ctx2);
    var chart2 = new Chart(ctx2, config);

    // Bei Fenstergröße-Änderungen die Größe der Diagramme anpassen
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