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


                        var element = $("#movie-list");
                        var html = "";
                        for(var i = 0; i < movies.display; i++ ) {

                          html+='<div class="col mb-5">';
                          html+='<div class="card h-100">';
                          html+='<img class="card-img-top" id = "movieImg' + i + '" src = " '+movies.items[i].image + '" ></img>';
                          html+='<div class="card-body p-4">';
                          html+='<div class="text-center">';
                          html+='<a id="movieLink' + i + '" href = " '+movies.items[i].link + '" ><h5 class="fw-bolder" id="movieTitle' + i + '"> ' + movies.items[i].title + '</h5></a>';
                          html+='<p>감독:</p><p  id="movieDirector'+i+'">' + movies.items[i].director + '</p>';
                          html+='<p>출연진 :</p><p  id="movieActors'+i+'">' + movies.items[i].actor + '</p>';
                          html+='</div></div>';
                          html+='<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">';
                          html+='<div class="text-center">';
                          html+='<button class="btn btn-outline-dark mt-auto" id="items" value="' + i + '" >Seen</button> ';
                          html+='</div></div></div></div>';
                        }

                        element.append(html);


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
                alert('저장 완료.');
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
};

main.init();

