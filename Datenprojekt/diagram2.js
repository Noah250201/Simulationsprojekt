
var jsonfile = {
"jsonarray": [{
    "stockprice":"112",
    "date":"16.1.23"},
{
    "stockprice":"122",
    "date":"15.2.23",},
{
    "stockprice":"124",
    "date":"14.3.23"
}
]
}; //die Variable wird später durch die Response ersetzt

var data = jsonfile.jsonarray.map(function(e){
    return e.stockprice;
});
var labels = jsonfile.jsonarray.map(function(e){
    return e.date;
});

var ctx = document.getElementById('d2');
var config = {
    type: 'line',
    data: {
        labels: labels,
        datasets: [{
            label: 'birthdates',
            data: data
        }]
    }
}

var chart = new Chart(ctx, config);