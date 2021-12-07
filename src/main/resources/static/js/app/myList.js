var main = {
    init : function () {
       var _this = this;


       $(document).on( "click", "#btn-list-delete", function del() {
         var id = this.value;

        _this.delete(id);

     } );


    },
    delete : function (id) {

            var id = parseInt(id);

            $.ajax({
                type: 'DELETE',
                url: '/api/v1/myList/' + id,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function() {
                window.location.href = '/my-list';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
     }
};

main.init();

