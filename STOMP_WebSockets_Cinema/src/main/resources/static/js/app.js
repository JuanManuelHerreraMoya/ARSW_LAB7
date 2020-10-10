var app = (function () {

  var cinemaName;
  var functionDate;
  var functionCinema;
  var originalFunctions;
  var seats;
  var modulo = appiclient;
  var updateF;
  var movieName;
  var dateByMovie;

  function _setCinemaName(cinema){
       cinemaName = cinema;
  }

  function _setFunctionDate (date){
    functionDate = date;
  }

  function _setDateToHour(cinema){
    return {name: cinema.movie.name, genre: cinema.movie.genre, hour: cinema.date.substring(11, 16)};
  }

  function getFunctionsByCinemaAndDate(){
    _setCinemaName($("#nameC").val());
    _setFunctionDate($("#dateC").val());
    //apimock.getFunctionsByCinemaAndDate(cinemaName, functionDate, _prettyPrint);
    modulo.getFunctionsByCinemaAndDate(cinemaName, functionDate, _prettyPrint);
  }

  function _prettyPrint(list){
    var table = $("#table1");
    var body = $("tbody");
    originalFunctions = list;
    functionCinema = list.map(_setDateToHour);
    $("#CinemaS").text("Cinema Selected: "+ cinemaName);
    $("#Movie").text("Movies:");
    body.remove();
    table.append("<tbody>");
    var newBody = $("tbody");
    newBody.append(functionCinema.map(_print));
    table.append(newBody);
    table.append("</tbody>");
    $("#Ava").text("Availability of: ");
    _resetCanvas();
  }

  function _print(res){
    var dateTime =  "".concat(functionDate, " ", res.hour);
    var boton = "<button type='button' onclick='app.getSeatsByFunction(\"" + res.name + '" , "' + dateTime + "\")' > Open Seats</button>"
    var temp = '<tr><td>' + res.name + '</td><td>' + res.genre + '</td><td>' + res.hour + '</td><td>' + boton + '</td></tr>';
    return temp;
  }

  function getSeatsByFunction(name, dateTime){
    originalFunctions.forEach(function(cinema) {
        if(cinema.movie.name===name  && cinema.date===dateTime){
            seats = cinema.seats;
            updateF = cinema;
            dateByMovie = cinema.date;

        }
    });
    _drawSeats(name);
  }

  function _drawSeats(name){
    $("#Ava").text("Availability of: "+name);
    movieName = name;
    _resetCanvas();
    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    ctx.font = "30px Arial";
    ctx.fillText("Screen", 140, 30);
    ctx.translate(20,50);

    for (var i = 0; i < seats.length; i++) {
        for (var j = 0; j < seats[0].length; j++) {
            if( !seats[i][j] ){
                ctx.fillStyle = "#ba0b0b";
            }else{
                ctx.fillStyle = "#61659b";
            }
            ctx.fillRect(j*30, i*30, 20, 20);
        }
    }
  }

  function _resetCanvas(){
    var c = document.getElementById("myCanvas");
    c.width = c.width;
    var ctx = c.getContext("2d");
    ctx.clearRect(0, 0,  c.width, c.height);
  }

  function windowAdmin() {
    var ticket = document.getElementById("buy");
    ticket.style.display = "none";
    var admi = document.getElementById("admin");
    if (admi.style.display === "none") {
      admi.style.display = "block";
    } else {
      admi.style.display = "none";
    }
  }

  function windowTicket() {
    var admi = document.getElementById("admin");
    admi.style.display = "none";
    var ticket = document.getElementById("buy");
    if (ticket.style.display === "none") {
      ticket.style.display = "block";
    } else {
      ticket.style.display = "none";
    }
  }

  function updateHour(){
    var dateTime =  "".concat(functionDate, " ", $("#hour").val());
    updateF.date = dateTime;
    modulo.updateFunction(cinemaName, updateF, _reset);
    _reset();
  }

  function deleteFunction(){
    modulo.deleteFunction(cinemaName,updateF, _reset);
  }

  function newMovie(){
    var seats = [[true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true], [true, true, true, true, true, true, true, true, true, true, true, true]];
    var movie = $("#newMovie").val();
    var genre = $("#newGenre").val();
    var dateTime =  "".concat(functionDate, " ", $("#newHour").val());
    modulo.createFunction(cinemaName,  {"movie": {"name": movie, "genre": genre}, "seats": seats, "date": dateTime}, _reset);
    var post = document.getElementById("post");
    post.style.display = "none";
    getFunctionsByCinemaAndDate();
  }

  function createFunction(){
      var admi = document.getElementById("admin");
      admi.style.display = "none";
      var ticket = document.getElementById("post");
      if (ticket.style.display === "none") {
        ticket.style.display = "block";
      } else {
        ticket.style.display = "none";
      }
  }

  function _reset(){
    getFunctionsByCinemaAndDate();
    getSeatsByFunction();
  }

  function buyTicket(){
    _setCinemaName($("#nameC").val());
    _setFunctionDate($("#dateC").val());
    var row = $("#row").val();
    var col = $("#column").val();
    modulo.buyTicket(row, col, cinemaName, dateByMovie, movieName, _confirmation);
  }

  function _confirmation(){
    _resetCanvas();
    modulo.getFunctionsByCinemaAndDate(cinemaName, functionDate, _ok);
  }

  function _ok(data){
    var temp = null;
    data.forEach(function(cinema) {
        if(cinema.movie.name===movieName  && cinema.date===dateByMovie){
            temp = cinema.seats;
        }
    });
    seats = temp;
    _drawSeats(movieName);
  }

  return {

        getFunctionsByCinemaAndDate: getFunctionsByCinemaAndDate,
        getSeatsByFunction: getSeatsByFunction,
        windowTicket: windowTicket,
        windowAdmin: windowAdmin,
        buyTicket: buyTicket,
        updateHour: updateHour,
        createFunction: createFunction,
        deleteFunction: deleteFunction,
        newMovie: newMovie

  };

})();