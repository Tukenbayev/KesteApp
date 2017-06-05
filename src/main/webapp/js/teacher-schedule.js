$(function () {

    $('.hws').delegate('.remove','click', function () {
        var container = $(this).parent().parent();
        $(this).closest('.hw').remove();

        if(container.find('.hw').length == 0){
            container.append("<p class='empty'>Пока нет никаких дз</p>");
        }
    });

    $(".subjectName").click(function () {
       var $subjectDIV = $(this).parent();
       var $dayDIV = $(this).parent().parent();
       $dayDIV.find('.hws').slideUp();
       $dayDIV.find('.newhw').hide();
       $dayDIV.find('.addhw').hide();

       if($subjectDIV.find('.hws').is(':visible')){
           $subjectDIV.find('.hws').slideUp();
           $subjectDIV.find('.newhw').hide();
           $subjectDIV.find('.addhw').hide();
       }
       else {
           $subjectDIV.find('.hws').slideDown();
           $subjectDIV.find('.newhw').show();
           $subjectDIV.find('.addhw').show();
       }


    });
    
    $('.addhw').click(function () {
        var container = $(this).closest('.subject');
        var newDiv = $('#homework').html();

        if(!container.find('.newhw').val().trim()){
            container.find('.newhw').focus();
            return false;
        }
        else {
            var newHW = {
                newhw: container.find('.newhw').val()
            };

            container.find('.empty').remove();
            container.find('.hws').append(Mustache.render(newDiv, newHW));
            container.find('.newhw').val("");
        }
    });

    $('.newhw').keypress(function (e) {
        if(e.which == 13){
            var container = $(this).closest('.subject');
            var newDiv = $('#homework').html();

            if(!container.find('.newhw').val().trim()){
                container.find('.newhw').focus();
                return false;
            }
            else {
                var newHW = {
                    newhw: container.find('.newhw').val()
                };

                container.find('.empty').remove();
                container.find('.hws').append(Mustache.render(newDiv, newHW));
                container.find('.newhw').val("");
            }
        }
    });


});
