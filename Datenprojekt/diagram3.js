
var jsonfile = {
"jsonarray": [{
    "stockprice":"112",
    "date":"16.1.23",
    "game":"Assassins Creed: Valhalla",
    "averagePlayers":"1000"},
{
    "stockprice":"1000",
    "date":"17.1.23",
    "game":"Assassins Creed: Odyssey",
    "averagePlayers":"2500"},
{
    "stockprice":"122",
    "date":"18.1.23",
    "game":"Assassins Creed: Origins",
    "averagePlayers":"1750"
}
]
}; //die Variable wird später durch die Response ersetzt

var stockPrice = jsonfile.jsonarray.map(function(e){
    return e.stockprice;
});
var date = jsonfile.jsonarray.map(function(e){
    return e.date;
});
var game = jsonfile.jsonarray.map(function(e){
    return e.game;
});
var averagePlayers = jsonfile.jsonarray.map(function(e){
    return e.averagePlayers;
});

var ctx = document.getElementById('d3');
var config = {
    type: 'bar',
    data: {
        labels: game,
        datasets: [
            {
                label: 'Stock Preis',
                data: stockPrice,
                type: 'line',
                yAxisID: 'y1',
                xAxisID: 'x1',
            },
            {
            label: 'durchschnittliche Spieler der letzten zwei Wochen',
            data: averagePlayers,
            yAxisID: 'y',
        }
        
    ]

    },
    options:{
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
                    text: 'durchschnittliche Spieler',
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
                labels: date,
                ticks: {
                    
                    font: {
                        size: 14,
                    }
                }
            }

        }
    }
    
}

function resizeCanvas() {
    let canvas = document.getElementById('d3');
    if (canvas) {
        canvas.width = canvas.parentElement.clientWidth;
        canvas.height = canvas.parentElement.clientHeight;
    }
  }
  
  resizeCanvas();
  
  window.addEventListener('resize', resizeCanvas);

var chart = new Chart(ctx, config);