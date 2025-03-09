
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

var ctx = document.getElementById('d3');
var config = {
    type: 'bar',
    data: {
        labels: labels,
        datasets: [{
            label: 'birthdates',
            data: data
        }]
    }
}

function resizeCanvas() {
    let canvas = document.getElementById('d1');
    if (canvas) {
        canvas.width = canvas.parentElement.clientWidth;
        canvas.height = canvas.parentElement.clientHeight;
    }
  }
  
  resizeCanvas();
  
  window.addEventListener('resize', resizeCanvas);

var chart = new Chart(ctx, config);