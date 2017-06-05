$(function () {

    var $email = $('#email');
    var nameEN_error = true;
    var nameRU_error = true;
    var nameKZ_error = true;
    var address_error = true;
    var phone_error = true;
    var email_match_error = true;
    var email_null_error = true;
    var pass1_error = true;
    var pass2_error = true;

    $('#form').submit(function () {
        if(!nameEN_error && !nameKZ_error && !nameRU_error && !address_error && !phone_error && !email_match_error
            && !email_null_error && !pass1_error && !pass2_error) {
            return true;
        }

    });




    $('#nameEN').focusout(function () {
        nameEN_error = check_name_error($('#nameEN').val());
        if(nameEN_error)
            $('#en_name_error').show();
        else
            $('#en_name_error').hide();

    });

    $('#nameKZ').focusout(function () {
        nameKZ_error = check_name_error($('#nameKZ').val());
        if(nameKZ_error)
            $('#kz_name_error').show();
        else
            $('#kz_name_error').hide();
    });

    $('#nameRU').focusout(function () {
        nameRU_error = check_name_error($('#nameRU').val());
        if(nameRU_error)
            $('#ru_name_error').show();
        else
            $('#ru_name_error').hide();
    });

    $('#address').focusout(function () {
        address_error = check_name_error($('#address').val());
        if(address_error)
            $('#address_error').show();
        else
            $('#address_error').hide();
    });

    $('#phone').focusout(function () {
        phone_error = check_phone_error($('#phone').val());
        if(phone_error)
            $('#phone_error').show();
        else
            $('#phone_error').hide();
    });


    $email.focusout(function () {
        if (check_name_error($email.val())) {
            $('#email_null_error').show();
            email_null_error = true;
        }
        else{
            $('#email_null_error').hide();
            email_null_error = false;
        }

        if(isEmail($email.val())){
            $('#email_match_error').hide();
            $.ajax({
                url: "/check-email",
                type: 'POST',
                dataType: 'json',
                data: $email.serialize(),
                success: function (result) {
                    if(!result.isAvailable)
                    {
                        $('#email_not_available').show();
                        email_match_error = true;
                    }else{
                        $('#email_not_available').hide();
                    }
                }
            });
            email_match_error = false;
        }else{
            $('#email_match_error').show();
            email_match_error = true;
        }
    });

    $('#pass1').focusout(function () {
        if($('#pass1').val().trim().length >= 8){
            $('#password_min_error').hide();
            pass1_error = false;
        }else{
            $('#password_min_error').show();
            pass1_error = true;
        }
    });

    $('#pass2').focusout(function () {
        var pass1 = $('#pass1').val().trim();
        var pass2 = $('#pass2').val().trim();

        if(pass1 != pass2){
            $('#password_match_error').show();
            pass2_error = true;
        }
        else{
            $('#password_match_error').hide();
            pass2_error = false;
        }
    });

    function check_name_error(name) {
        if(name.trim() == '')
            return true;
        else
            return false;
    }

    function check_phone_error(phone) {
        if(phone.trim() < 5)
            return true;
        else
            return false;
    }

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    var $phonebox = $('#phone');
    $phonebox.keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190,109,189]) !== -1 ||
            // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });


});