$(function () {
    
    $('#signup').click(function () {
        window.location.href = "/registration";
    });

    $('#signin').click(function () {
        window.location.href = "/signin";
    });

   $('#form').submit(function () {
       var template = $('#result').html();
        $.ajax({
            url: 'search',
            type: 'POST',
            dataType: 'json',
            data: $('#searchName').serialize(),
            success: function (schools) {
                $('div.result').remove();
                if (!schools || !Object.keys(schools).length) {
                    $("#NoRecordFound").show();
                } else {
                    $('#NoRecordFound').hide();
                    $.each(schools,function (i, school) {
                        $('div.container').append(Mustache.render(template,school));
                    });
                }

            }
        });

        return false;
   });
});
