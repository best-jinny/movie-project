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

        $(document).on( "click", "#items", function register() {
          var idx = this.value;
          var image = $('#movieImg' + idx).attr("src");
          var link = $('#movieLink' + idx).attr("href");
          var title = $('#movieTitle' + idx).text();
          var director = $('#movieDirector' + idx).text();
          var actors = $('#movieActors' + idx).text();

          console.log(image);
          console.log(link);
          console.log(title);
          console.log(director);
          console.log(actors);

          _this.save(image, link, title, director, actors);

        } );


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
                             element.innerHTML+=('<img class="card-img-top" id = "movieImg' + i + '" src = " '+movies.items[i].image + '" style="width:100%"></img>');
                             element.innerHTML+=('<div class="card-body">');
                             element.innerHTML+=('<a id="movieLink' + i + '" href = " '+movies.items[i].link + '" ><h4 class="card-title" id="movieTitle' + i + '"> ' + movies.items[i].title + '</h4></a>');
                             element.innerHTML+=('<p>감독:</p><p class="card-text" id="movieDirector'+i+'">' + movies.items[i].director + '</p>');
                             element.innerHTML+=('<p>출연진 :</p><p class="card-text" id="movieActors'+i+'">' + movies.items[i].actor + '</p>');
                             element.innerHTML+=('<button class="btn btn-primary" id="items" value="' + i + '" >봤어요</button> ');

                             element.innerHTML+=('</div></div>');

                        }

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    save : function (image, link, title, director, actors) {
            var data = {
                image : image,
                link : link,
                title : title,
                director : director,
                actors : actors
            };

            $.ajax({
                type: 'POST',
                url: '/api/v1/myList',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('글이 등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
};

main.init();

