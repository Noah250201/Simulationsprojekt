let chartInstances = {};

function updateChartThreeWithData(data, chartId) {
    const jsonfile = data;

    var stockprice = jsonfile.jsonarray.map(function(e) {
        return e.stockprice;
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
                data: stockprice,
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

    let ctx1 = document.getElementById('d2');
    let ctx2 = document.getElementById('d2_2');

    if (chartInstances['d2']) {
        chartInstances['d2'].destroy();
    }
    if (chartInstances['d2_2']) {
        chartInstances['d2_2'].destroy();
    }

    if (ctx1) {
        chartInstances['d2'] = new Chart(ctx1.getContext('2d'), config);
    }
    if (ctx2) {
        chartInstances['d2_2'] = new Chart(ctx2.getContext('2d'), config);
    }

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
