
var jsonfile = {
"jsonarray": [{
    "stockprice":"112",
    "date":"16.1.23",
    "googleViewCount":"1000"},
{
    "stockprice":"122",
    "date":"15.2.23",
    "googleViewCount":"2000"},
{
    "stockprice":"124",
    "date":"14.3.23"
}
]
}; //die Variable wird später durch die Response ersetzt

var data1 = jsonfile.jsonarray.map(function(e){
    return e.stockprice;
});
var data2 = jsonfile.jsonarray.map(function(e){
    return e.googleViewCount;
});
var labels = jsonfile.jsonarray.map(function(e){
    return e.date;
});

var ctx = document.getElementById('d1');
var config = {
    type: 'line',
    data: {
        labels: labels,
        datasets: [{
            label: 'Stock Prices',
            data: data1,
            yAxisID: 'y',
            borderColor: 'blue',
            fill: false
        }, {
            label: 'Google View Count',
            data: data2,
            yAxisID: 'y1',
            borderColor: 'red',
            fill: false
        }]
    },
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
          },
          y1: {
            type: 'linear',
            display: true,
            position: 'right',
    
            // grid line settings
            grid: {
              drawOnChartArea: false, // only want the grid lines for one axis to show up
            },
        }
    }
  }
};


var chart = new Chart(ctx, config);