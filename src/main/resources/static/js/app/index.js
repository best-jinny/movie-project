var main = {
    init : function () {
        var _this = this;
        $('#btn-search').on('click', function () {
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

                        element.innerHTML+=('<p>'+movies.items[0].title+'</p>');
                        element.innerHTML+=('<img src = " '+movies.items[0].image + '" ></img>');
                        element.innerHTML+=('<a href = " '+movies.items[0].link + '" >네이버검색결과</a>');


        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
            //window.location.href = '/searc
};

main.init();