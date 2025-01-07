var jsonfile = {
"jsonarray": [{
    "stockprice":"112",
    "date":"14.8.23"},
{
    "stockprice":"114",
    "date":"17.8.23"
}]
}; //die Variable wird später durch die Response ersetzt

var labels = jsonfile.jsonarray.map(function(e){
    return e.stockprice;
});
var data = jsonfile.jsonarray.map(function(e){
    return e.date;
});

var ctx = canvas.getContext('2d');
var config = {
    type: 'line',
    data: {
        labels: labels,
        datasets: [{
            label: 'Graph Line',
            data: data
        }]
    }
}

var chart = new Chart(ctx, config);