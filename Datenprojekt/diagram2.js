
var jsonfile = {
"jsonarray": [{
    "game": "Destiny 2",
    "supportedLanguages":"2",
    "currentPrice":"59.90",
    "initalPrice":"59.90"},
{   
    "game": "Cyberpunk 2077",
    "supportedLanguages":"9",
    "currentPrice":"49.90",
    "initalPrice":"29.90"},
{
    "game": "The Witcher 3",
    "supportedLanguages":"12",
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
        label: 'unterstützte Sprachen',
        data: supportedLanguages,
        stack: 'Stack 0'
       }, {
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

function resizeCanvas() {
    let canvas = document.getElementById('d2');
    if (canvas) {
        canvas.width = canvas.parentElement.clientWidth;
        canvas.height = canvas.parentElement.clientHeight;
    }
  }
  
  resizeCanvas();
  
  window.addEventListener('resize', resizeCanvas);

var chart = new Chart(ctx, config);