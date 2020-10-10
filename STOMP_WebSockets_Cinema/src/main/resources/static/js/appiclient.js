appiclient = (function (){

    function getFunctionsByCinemaAndDate(cinema, date, callback){

        $.get("http://localhost:8080/cinemas/"+cinema+"/"+date, function(data){
                callback(data);
            }, 'json');
    }

    function getFunctionsByCinema(cinema, callback){

        $.get("http://localhost:8080/cinemas/"+cinema, function(data){
                callback(data);
            }, 'json');
    }

    function updateFunction(cinema, cf, callback){
        var cinemaFunction = JSON.stringify(cf);
        var postPromise = $.ajax({
            url: "http://localhost:8080/cinemas/" + cinema,
            type: 'PUT',
            data: cinemaFunction,
            contentType: "application/json"
        });
        postPromise.then(
            function () {
                console.info("OK");
                callback();
            }
        );
    }

    function createFunction(cinema, cf, callback){
        var cinemaFunction = JSON.stringify(cf);

        var postPromise = $.ajax({
            url: "http://localhost:8080/cinemas/" + cinema,
            type: 'POST',
            data: cinemaFunction,
            contentType: "application/json"
        });
        postPromise.then(
            function () {
                console.info("OK");
                callback();
            }
        );
    }

    function deleteFunction(cinema, cf, callback){

       var cinemaFunction = JSON.stringify(cf);
       var delPromise = $.ajax({
           url: "http://localhost:8080/cinemas/" + cinema,
           type: 'PUT',
           data: cinemaFunction,
           contentType: "application/json"
       });
       delPromise.then(
           function () {
               console.info("OK");
               callback();
           }
       );
    }

    function buyTicket(row, col, cinema, hour, name, callback){

       var buyPromise = $.ajax({
           url: "http://localhost:8080/cinemas/" + cinema + "/" + name + "/" + hour + "/" + row + "/" + col,
           type: 'PUT',
           contentType: "application/json"
       });
       buyPromise.then(
           function () {
               console.info("OK");
               callback();
           }
       );
    }

    return{
        getFunctionsByCinema: getFunctionsByCinema,
        getFunctionsByCinemaAndDate: getFunctionsByCinemaAndDate,
        updateFunction: updateFunction,
        createFunction: createFunction,
        deleteFunction: deleteFunction,
        buyTicket: buyTicket
    };

})();



