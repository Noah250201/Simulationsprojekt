
var jsonfile = {
"jsonarray": [{
    "game": "Destiny 2",
    
    "currentPrice":"59.90",
    "initalPrice":"59.90"},
{   
    "game": "Cyberpunk 2077",
    
    "currentPrice":"49.90",
    "initalPrice":"29.90"},
{
    "game": "The Witcher 3",
    
    "currentPrice":"89.90",
    "initalPrice":"19.90"
}
]
}; //variable will be replaced by the response later

var supportedLanguages = jsonfile.jsonarray.map(function(e){
    return e.supportedLanguages;
});
var currentPrice = jsonfile.jsonarray.map(function(e){
    return e.currentPrice;
});
var date = jsonfile.jsonarray.map(function(e){
    return e.initalPrice;
});
var labels = jsonfile.jsonarray.map(function(e){
    return e.game;
});

const data = {
    labels: labels,
    datasets:
     [
        {
        label: 'jetziger Preis in €',
        data: currentPrice,
        stack: 'Stack 1'

       }, {
        label: 'initialer Preis in €',
        data: date,
        stack: 'Stack 2'
    }
  ]
}


var ctx = document.getElementById('d2');
var config = {
    type: 'bar',
    data: data,
    options: {
        responsive: true,
        ineteraction: {
            intersect: false
        },
        ticks: {
            beginAtZero: true,
            font: {
                size: 14,
            }
        }
    }
}

function resizeCanvas(canvas) {
    if (canvas) {
      canvas.width = canvas.parentElement.clientWidth;
      canvas.height = canvas.parentElement.clientHeight;
    }
  }
  
  // Chart für das erste Canvas erstellen
  var ctx1 = document.getElementById('d2');
  resizeCanvas(ctx1);
  var chart1 = new Chart(ctx1, config);
  
  // Chart für das zweite Canvas erstellen
  var ctx2 = document.getElementById('d2_2');
  resizeCanvas(ctx2);
  var chart2 = new Chart(ctx2, config);
  
  // Bei Fenster-Resize beide Canvas-Größen anpassen
  window.addEventListener('resize', function() {
    resizeCanvas(ctx1);
    resizeCanvas(ctx2);
  });