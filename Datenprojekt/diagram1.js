function updateChartOneWithData(data, chartId) {
  const jsonfile = data;

  const stockPrice = jsonfile.jsonarray.map(function(e) {
      return e.stockPrice;
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
              data: stockPrice,
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

  // Canvas für das erste Diagramm erstellen
  var ctx1 = document.getElementById('d1');
  var chart1 = new Chart(ctx1, config);  // Chart für das erste Canvas

  // Canvas für das zweite Diagramm erstellen
  var ctx2 = document.getElementById('d1_2');
  var chart2 = new Chart(ctx2, config);  // Chart für das zweite Canvas

  // Bei Fenster-Resize beide Canvas-Größen anpassen
  window.addEventListener('resize', function() {
      resizeCanvas(ctx1);
      resizeCanvas(ctx2);
  });
}

// Funktion, um die Canvas-Größe bei Resize anzupassen
function resizeCanvas(canvas) {
  if (canvas) {
      canvas.width = canvas.parentElement.clientWidth;
      canvas.height = canvas.parentElement.clientHeight;
  }
}