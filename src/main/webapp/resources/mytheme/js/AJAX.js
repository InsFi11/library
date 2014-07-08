function doAjaxOrder(id_book) {


    var id_user = $("#id_user").val();

    alertify.confirm("Do you really want to ORDER this Book?", function (e) {
        if (e) {
            $.ajax({
                url : 'order',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data : ({
                    id_book: id_book,
                    id_user: id_user
                }),
                success: function (data) {

                    alertify.alert(data.string);
                },
                error: function ()
                {
                    alertify.alert('Error in Ajax');
                }

            });
        }
    });

}
function doAjaxPreOrder(id_series) {


    var id_user = $("#id_user").val();
    alertify.confirm("Do you really want to PREORDER this Series?", function (e) {
        if (e) {
            $.ajax({
                url: 'preorder',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    id_series: id_series,
                    id_user: id_user
                }),
                success: function (data) {

                    alertify.alert(data.string);

                },
                error: function ()
                {
                    alertify.alert('Error in Ajax');
                }
            });
        }
        });
}
function doAjaxPassTicket() {

    var passTicketMonth = $("#passTicketMonth").val();
    var id_user = $("#id_user").val();
    alertify.confirm("Do you really want to BUY Passticket?", function (e) {
        if (e) {
            $.ajax({
                url: 'buyPassTicket',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    passTicketMonth: passTicketMonth,
                    id_user: id_user
                }),
                success: function (data) {
                    alertify.alert(data.string, function(e){

                        if(e)
                        {

                            window.location.reload('/user?userId='+id_user)
                        }
                    });

                },
                error: function ()
                {
                    alertify.alert('Error in Ajax');
                }
            });
        }
    });
}

function doAjaxDeleteBookFromCollection(id_book) {

    var id_series = $("#id_series").val();

    alertify.confirm("Do you really want to DELETE this Book from Collection?", function (e) {
        if (e) {
            $.ajax({
                url: 'deleteBookFromSeries',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    id_series: id_series,
                    id_book: id_book
                }),
                success: function (data) {

                    alertify.alert(data.string, function(e){

                        if(e)
                        {

                            window.location.replace('/updateSeriesPage?id_series='+id_series)
                        }
                    });

                },
                error: function ()
                {
                    alertify.alert('Error in Ajax');
                }
            });
        }
    });
}
function doAjaxDeleteUser() {

    var password = $("#password").val();
    var userId = $("#userId").val();
    var secretQuestion = $("#secretQuestion").val();

    alertify.confirm("Do you really want to DELETE this Account with unable to renew it?", function (e) {
        if (e) {


            window.location.replace('/deleteUserSuccess?userId='+userId+'&password='+password+'&secretQuestion='+secretQuestion)



        }
    });
}
function doAjaxReadingRoom(id) {

    alertify.confirm("Do you really want to DELETE this Record with unable to renew it?", function (e) {
        if (e) {


            window.location.replace('/deleteReadingRoom?readingRoomId='+id)



        }
    });
}
function DeleteOrder(id,userId) {

    alertify.confirm("Do you really want to DELETE this Record with unable to renew it?", function (e) {
        if (e) {


            window.location.replace('/deleteOrder?id='+id+'&userId='+userId)



        }
    });
}
function DeletePreOrder(id,userId) {

    alertify.confirm("Do you really want to DELETE this Record with unable to renew it?", function (e) {
        if (e) {


            window.location.replace('/deletePreoder?id='+id+'&userId='+userId)



        }
    });
}
function DeletePassTicket(id,userId) {

    alertify.confirm("Do you really want to DELETE this Record with unable to renew it?", function (e) {
        if (e) {


            window.location.replace('/deletePassTicket?id='+id+'&userId='+userId)



        }
    });
}
function validate() {

    var login = $("#login").val();


            $.ajax({
                url : 'checkLogin',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data : ({
                    login: login

                }),
                success: function (data) {

                    alertify.alert(data.string);
                },
                error: function ()
                {
                    alertify.alert('Error in Ajax');
                }

            });



}
