$(function () {
    var $password = $('#password');

    $('#next').click(function () {
        var username = $('#username').val();

        if($.trim(username).length === 0){
            $('#email').hide();
            $('#empty').show();
            $('#username').addClass('inputError');
        }else if(!isEmail(username)){
            $('#empty').hide();
            $('#email').show();
            $('#username').addClass('inputError');
        }
        else {
            $.ajax({
                url: '/check-email',
                type: 'POST',
                dataType: 'json',
                data:{email:username},
                success: function (email) {
                    if(!email.isAvailable){
                        $('#userholder').html(username);
                        $('#first').slideToggle();
                        $('#second').slideToggle();
                    }else{
                        $('#ntexist').show();
                        $('#username').addClass('inputError');
                    }
                }
            });

        }
    });

    $('#submit').click(function () {
        $.ajax({
            url: '/login',
            type: 'GET',
            dataType: 'json',
            data: {
                email: $('#username').val(),
                password: $('#password').val()
            },
            success: function (password) {
                if(!password.isCorrect){
                    $('#pass_error').show();
                    $password.addClass("inputError");
                    $password.val("");
                }else{
                    location.href = "/school";
                }
            }
        });
    });

    $password.focus(function () {
        $password.removeClass("inputError");
    });

    $('#username').on('focus',function () {
        $('#ntexist').hide();
        $('#empty').hide();
        $('#email').hide();
        $('#username').removeClass('inputError');
    });

    $('#back').click(function () {
        $('#username').removeClass('inputError');
        $('#email').hide();
        $('#empty').hide();
        $('#pass_error').hide();
        $('#second').slideToggle();
        $('#first').slideToggle();
        $('#password').val("");
    });

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    function isPhoneNumber(phone) {
        var regex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
        return regex.test(phone);
    }
});
