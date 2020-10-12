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
  var rol;
  var col;
  var tempSeats;

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

  function getFunctionsByCinemaAndDateMovie(){
    _setCinemaName($("#nameC").val());
    _setFunctionDate($("#dateC").val());
    //_setMovieName($("#movieName").val());
    console.log(movieName);
    modulo.getFunctionsByCinemaDateMovie(cinemaName, functionDate, movieName, _prettyPrint);
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
    movieName = name;
    init();
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
    //var ticket = document.getElementById("buy");
    //ticket.style.display = "none";
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
    verifyAvailability(row,col);
    //modulo.buyTicket(row, col, cinemaName, dateByMovie, movieName, _confirmation);
  }

  function _confirmation(){
    _resetCanvas();
    modulo.getFunctionsByCinemaAndDate(cinemaName, functionDate, _ok);
    //modulo.getFunctionsByCinemaAndDateMovie(cinemaName, functionDate, movieName, _ok);
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

  class Seat {
      constructor(row, col) {
          this.row = row;
          this.col = col;
      }
  }

  function _showLimits(x,y){

    var minx = 20;
    var maxx = 40;
    var newX = 0;
    var miny = 90;
    var maxy = 110;
    var newY = 0;
    var confirm = true;
    for (var i = 0; i < seats.length; i++) {
        if(minx<=x && x<=maxx){
            break;
        }else if(x>maxx){
            newX = newX+1;
            minx = maxx+10;
            maxx = maxx+30;
        }else{
            confirm = false;
            break;
        }
    }
    for (var i = 0; i < seats.length; i++) {
        if(miny<=y && y<=maxy){
            break;
        }else if(y>maxy){
            newY = newY+1;
            miny = maxy+10;
            maxy = maxy+30;
        }else{
            confirm = false;
            break;
        }
    }
    if(confirm){
        row = newY;
        col = newX;
        buyTicket();
    }

  }
  var stompClient = null;

  //get the x, y positions of the mouse click relative to the canvas
  var getMousePosition = function (evt) {
      _setCinemaName($("#nameC").val());
      _setFunctionDate($("#dateC").val());
      $('#myCanvas').click(function (e) {
          var rect = canvas.getBoundingClientRect();
          var x = e.clientX - rect.left;
          var y = e.clientY - rect.top;
          _showLimits(x,y);
      });

  };

  var connectAndSubscribe = function () {
      console.info('Connecting to WS...');
      var socket = new SockJS('/stompendpoint');
      stompClient = Stomp.over(socket);
      console.log(stompClient);
      //subscribe to /topic/TOPICXX when connections succeed
      stompClient.connect({}, function (frame) {
          console.log('Connected: ' + frame);
          stompClient.subscribe("/topic/buyticket."+cinemaName+"."+functionDate+"."+movieName, function (eventbody) {
             console.log("hola"+eventbody)
             var theObject=JSON.parse(eventbody.body);
             //alert("Seats bought for Cinema: "+ cinemaName +", row: "+theObject.row+", col: "+theObject.col);
             modulo.buyTicket(theObject.row, theObject.col, cinemaName, dateByMovie, movieName, _confirmation);

          });
      });

  };

  var verifyAvailability = function (row,col) {
      var st = new Seat(row, col);
      if (seats[row][col]===true){
          seats[row][col]=false;
          console.info("purchased ticket");
          stompClient.send("/app/buyticket."+cinemaName+"."+functionDate+"."+movieName, {}, JSON.stringify(st));
      }
      else{
          console.info("Ticket not available");
      }

  };

  init = function(){
    connectAndSubscribe(_confirmation);
  }


  return {

        getFunctionsByCinemaAndDate: getFunctionsByCinemaAndDate,
        getFunctionsByCinemaAndDateMovie: getFunctionsByCinemaAndDateMovie,
        getSeatsByFunction: getSeatsByFunction,
        windowTicket: windowTicket,
        windowAdmin: windowAdmin,
        buyTicket: buyTicket,
        updateHour: updateHour,
        createFunction: createFunction,
        deleteFunction: deleteFunction,
        newMovie: newMovie,
        init: init,
        getMousePosition: getMousePosition
  };

})();