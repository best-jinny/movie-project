var main = {
    init : function () {
       var _this = this;

       $( ".star_rating a" ).click(function() {
            $(this).parent().children("a").removeClass("on");
            $(this).addClass("on").prevAll("a").addClass("on");
            return false;
       });



       $(document).on( "click", "#btn-list-delete", function del() {
         var id = this.value;

        _this.delete(id);

       } );




       $('#myModal').on('show.bs.modal', function(event) {
           var id = $(event.relatedTarget).data('id');
           console.log(id);
            $('#movieId').val(id);

       });




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
     },
     getComment : function () {
        console.log("요기?");

     }
};

main.init();

