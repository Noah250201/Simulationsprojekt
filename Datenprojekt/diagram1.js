
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
}; //variable will be replaced by the response later

var data1 = jsonfile.jsonarray.map(function(e){
    return e.stockprice;
});
var data2 = jsonfile.jsonarray.map(function(e){
    return e.googleViewCount;
});
var initialPrice = jsonfile.jsonarray.map(function(e){
    return e.date;
});

var ctx = document.getElementById('d1');
var config = {
    type: 'line',
    data: {
        labels: initialPrice,
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
            title: {
              display: true,
              text: 'Aktienkurs in €', //currency needs to be checked, dont know what the response will look like
              align: 'center',
              font: {
                size: 20,
              },
            },
            beginAtZero: true,
            ticks: {
              
              max: 200, //TODO hard coded ticks, need to be adjusted
              min: 0,
              stepSize: 20
            },
            // grid line settings
           
          },
          y1: {
            type: 'linear',
            display: true,
            position: 'right',
            title: {
                display: true,
                text: 'Google Aufrufe',
                align: 'center',
                font: {
                  size: 20,
                },
              },
              beginAtZero: true,
            ticks: {
              beginAtZero: true,
              max: 3000, //TODO hard coded ticks, need to be adjusted
              min: 0,
              stepSize: 500
            },
            // grid line settings
            
            grid: {
              drawOnChartArea: false, // only want the grid lines for one axis to show up
            },
        }
    }
  }
};


var chart = new Chart(ctx, config);