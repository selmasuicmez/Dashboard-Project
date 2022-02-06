var xmlhttp = new XMLHttpRequest();
var url = "http://127.0.0.1/JS/JSON/jsonData.json";
xmlhttp.open("GET", url, true);
xmlhttp.send();
xmlhttp.onreadystatechange = function () {
  if (this.readyState == 4 && this.status == 200) {
    var data = JSON.parse(this.responseText);
    //console.log(data);
    var dagen = data.months_temperature.map(function (elem) {
      return elem.dag;
    });
    //console.log(months);
    var storypoints = data.months_temperature.map(function (elem) {
      return elem.storypoints;
    });
    var storypointsGoal = data.months_temperature.map(function (elem) {
      return elem.storypointsGoal;
    });

    var ctx = document.getElementById("burndownChart").getContext("2d");
    var myChart1 = new Chart(ctx, {
      type: "line",
      data: {
        labels: dagen,
        datasets: [
          {
            label: "Gerealiseerde Story Points",
            data: storypoints,
            backgroundColor: "transparent",
            borderColor: "red",
            borderWidth: 4,
          },

          {
            label: "Sprint doel",
            data: storypointsGoal,
            backgroundColor: "transparent",
            borderColor: "green",
            borderWidth: 4,
          },
        ],
      },
      options: {
        elements: {
          line: {
            tension: 0,
          },
        },
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    });
  }
};
