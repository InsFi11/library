
function validateRegForm(buttonEl){
    var password = $('#password').val();
    var repassword = $('#repassword').val();
    var birthDate = $('#birthday').val();
    var validate = true;
    var error_message = '';

    if(password != repassword){
        $('#repassword').addClass('wrong_input');
        error_message += 'Repeat password is wrong! <br/>';
        validate = false;
    }

    var d = new Date();
    var today = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDate();

    if(birthDate != ""){
        if(birthDate > today){
            $('#birthday').addClass('wrong_input');
            error_message += "Wrong birthday.<br/> ";
            validate = false;
        }
    }

    if(validate)
        buttonEl.submit();
    else{
        alertify.alert(error_message);
        return false;
    }
};

$(document).ready(function(){
    $('#registration table input').focus(function(){
        $(this).removeClass('wrong_input');
    });
});

function validateChangePass(buttonEl){
    var newpassword = $('#newpassword').val();
    var repassword = $('#repassword').val();

    var validate = true;
    var error_message = '';

    if(newpassword != repassword){
        $('#repassword').addClass('wrong_input');
        error_message += 'Repeat password is wrong! <br/>';
        validate = false;
    }


    if(validate)
        buttonEl.submit();
    else{
        alertify.alert(error_message);
        return false;
    }
};
