$(function () {
    $(".subjectName").click(function () {
        var container = $(this).closest('.subject');
        var currentHWS = container.children(".hws");
        $(this).parent().parent().find('.hws').slideUp();
        if(currentHWS.is(':visible'))
            currentHWS.slideUp();
        else
            currentHWS.slideDown();

        container.find('input.newhw').slideToggle();
        container.find('button.addhw').slideToggle();
    });

    
    $('#show').click(function () {
        $('.container').slideToggle();
    });
    

});
