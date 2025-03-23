function updateChartWithData(data, chartId) {
    const jsonfile = data;

    var stockPrice = jsonfile.jsonarray.map(function(e) {
        return e.stockPrice;
    });

    var date = jsonfile.jsonarray.map(function(e) {
        return e.date;
    });

    var game = jsonfile.jsonarray.map(function(e) {
        return e.game;
    });

    var averagePlayers = jsonfile.jsonarray.map(function(e) {
        return e.averagePlayers;
    });

    // Daten für das Diagramm
    const chartData = {
        labels: game, // Die Spiele als Labels
        datasets: [
            {
                label: 'Stock Preis',
                data: stockPrice,
                type: 'line', // Linie für den Stock Preis
                yAxisID: 'y1',
                xAxisID: 'x1',
            },
            {
                label: 'Durchschnittliche Spieler',
                data: averagePlayers,
                yAxisID: 'y',
            }
        ]
    };

    // Diagramm-Konfiguration
    const config = {
        type: 'bar', // Balkendiagramm
        data: chartData,
        options: {
            responsive: true,
            interaction: {
                mode: 'index',
                intersect: false,
            },
            stacked: false,
            scales: {
                y: {
                    type: 'linear',
                    display: true,
                    position: 'left',
                    title: {
                        display: true,
                        text: 'Durchschnittliche Spieler',
                        font: {
                            size: 20,
                        }
                    },
                    ticks: {
                        beginAtZero: true,
                        font: {
                            size: 14,
                        }
                    }
                },
                y1: {
                    type: 'linear',
                    display: true,
                    position: 'right',
                    title: {
                        display: true,
                        text: 'Stock Preis',
                        font: {
                            size: 20,
                        }
                    },
                    ticks: {
                        beginAtZero: true,
                        font: {
                            size: 14,
                        }
                    }
                },
                x: {
                    display: true,
                    ticks: {
                        font: {
                            size: 14,
                        }
                    }
                },
                x1: {
                    display: true,
                    labels: date, // Das Datum als Label für die x-Achse
                    ticks: {
                        font: {
                            size: 14,
                        }
                    }
                }
            }
        }
    };

    // Canvas für das Diagramm holen und es anpassen
    var ctx1 = document.getElementById(chartId); // Die ID für das Canvas wird verwendet
    resizeCanvas(ctx1);
    var chart1 = new Chart(ctx1, config);

    // Wenn du noch ein zweites Canvas hast, das auch aktualisiert werden soll:
    var ctx2 = document.getElementById(chartId + "_2");
    resizeCanvas(ctx2);
    var chart2 = new Chart(ctx2, config);

    // Bei Fenstergrößenänderung wird die Größe der Diagramme angepasst
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
