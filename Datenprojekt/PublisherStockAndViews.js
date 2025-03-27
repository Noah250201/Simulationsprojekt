let chartInstances = {};

function updateChartOneWithData(data, chartId) {
  const jsonfile = data;

  const stockprice = jsonfile.jsonarray.map(function(e) {
      return e.stockprice;
  });
  const googleViewCount = jsonfile.jsonarray.map(function(e) {
      return e.googleViewCount;
  });
  const date = jsonfile.jsonarray.map(function(e) {
      return e.date;
  });

  var config = {
      type: 'line',
      data: {
          labels: date,
          datasets: [{
              label: 'Aktienpreise',
              data: stockprice,
              yAxisID: 'y',
              borderColor: 'blue',
              fill: false
          }, {
              label: 'Google Aufrufe',
              data: googleViewCount,
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
          ticks: {
              beginAtZero: true,
              font: {
                  size: 14,
              }
          },
          scales: {
              y: {
                  type: 'linear',
                  display: true,
                  position: 'left',
                  title: {
                      display: true,
                      text: 'Aktienkurs in €',
                      align: 'center',
                      font: { size: 20 },
                  },
                  beginAtZero: true,
                  ticks: {
                      max: 200,  // Maximalwert anpassen
                      min: 0,
                      stepSize: 20
                  }
              },
              y1: {
                  type: 'linear',
                  display: true,
                  position: 'right',
                  title: {
                      display: true,
                      text: 'Google Aufrufe',
                      align: 'center',
                      font: { size: 20 },
                  },
                  beginAtZero: true,
                  ticks: {
                      beginAtZero: true,
                      max: 3000,  // Maximalwert anpassen
                      min: 0,
                      stepSize: 500
                  },
                  grid: {
                      drawOnChartArea: false,
                  }
              }
          }
      }
  };


  let ctx1 = document.getElementById('d1');
    let ctx2 = document.getElementById('d1_2');

    // Falls die Charts schon existieren -> zerstören, um sie mit neuen Daten zu rendern
    if (chartInstances['d1']) {
        chartInstances['d1'].destroy();
    }
    if (chartInstances['d1_2']) {
        chartInstances['d1_2'].destroy();
    }

    // Neue Charts in beide Canvas rendern
    if (ctx1) {
        chartInstances['d1'] = new Chart(ctx1.getContext('2d'), config);
    }
    if (ctx2) {
        chartInstances['d1_2'] = new Chart(ctx2.getContext('2d'), config);
    }

    // Resize-Event für beide Canvas-Elemente
    window.addEventListener('resize', function() {
        resizeCanvas(ctx1);
        resizeCanvas(ctx2);
    });
}


function resizeCanvas(canvas) {
  if (canvas) {
      canvas.width = canvas.parentElement.clientWidth;
      canvas.height = canvas.parentElement.clientHeight;
  }
}