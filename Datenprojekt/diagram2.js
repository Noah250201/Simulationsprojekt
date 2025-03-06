
var jsonfile = {
"jsonarray": [{
    "game": "Destiny 2",
    "supported_languages":"2",
    "current_Price":"59.90",
    "inital_Price":"59.90"},
{   
    "game": "Cyberpunk 2077",
    "supported_languages":"9",
    "current_Price":"49.90",
    "inital_Price":"29.90"},
{
    "game": "The Witcher 3",
    "supported_languages":"12",
    "current_Price":"89.90",
    "inital_Price":"19.90"
}
]
}; //die Variable wird später durch die Response ersetzt

var supportedLanguages = jsonfile.jsonarray.map(function(e){
    return e.supported_languages;
});
var currentPrice = jsonfile.jsonarray.map(function(e){
    return e.current_Price;
});
var initialPrice = jsonfile.jsonarray.map(function(e){
    return e.inital_Price;
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
        label: 'jetziger Preis',
        data: currentPrice,
        stack: 'Stack 1'

       }, {
        label: 'initialer Preis',
        data: initialPrice,
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
        scales: {
            
           
        }
    }
}

var chart = new Chart(ctx, config);