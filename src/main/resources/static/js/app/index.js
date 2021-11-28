var main = {
    init : function () {
        var _this = this;
        $('#btn-search').on('click', function () {
           var cell = document.getElementById("movie-list");

           while ( cell.hasChildNodes() ) {
            cell.removeChild( cell.firstChild );
           }

            _this.search();
        });

    },
    search : function () {
        var keyword = $('#keyword').val();

        $.ajax({
            type: 'GET',
            url: '/api/v1/movies/'+ keyword,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function(res) {
            var movies = JSON.parse(JSON.stringify(res));


                        const element = document.getElementById("movie-list");

                        for(var i = 0; i < movies.display; i++ ) {

                             element.innerHTML+=('<div class="card">');
                             element.innerHTML+=('<img class="card-img-top" src = " '+movies.items[i].image + '" style="width:100%"></img>');
                             element.innerHTML+=('<div class="card-body">');
                             element.innerHTML+=('<a href = " '+movies.items[i].link + '" ><h4 class="card-title"> ' + movies.items[i].title + '</h4></a>');
                             element.innerHTML+=('<p class="card-text"> 감독 : ' + movies.items[i].director + ', 출연진 :' + movies.items[i].actor + '  </p>');
                             element.innerHTML+=('<input type="button" class="btn btn-primary" value="봤어요">');
                             element.innerHTML+=('</div></div>')

                        }

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },



};

main.init();

