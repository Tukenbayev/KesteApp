$(function(){

    var $subject = $('.subject');
    $('.add').click(function () {
        var edit = $('#editing').html();
        $(this).hide();
        var container = $(this).parent();
        container.addClass('addedSubject');
        container.append(edit);
    });

    $subject.delegate('.saveSubject','click',function(){
        var updates = {
          subjectName:$(this).parent().find('.sname').val(),
          teacherName : $(this).parent().find('.tname').val()
        };

        var saved = $('#saved').html();
        var container = $(this).parent();

        container.find('.selector').hide();
        container.find('.saveSubject').hide();
        container.append(Mustache.render(saved,updates));
    });

    $subject.delegate('.remove','click',function () {
        var container = $(this).parent().parent();
        container.find('.tools').remove();
        container.find('.selector').remove();
        container.find('.saveSubject').remove();
        container.find('.scontain').remove();

        container.removeClass('addedSubject');
        container.find('.add').show();
    });

    $subject.delegate('.edit','click',function () {
        var container = $(this).parent().parent();
        var scontain = container.find('.scontain');
        container.find('.tools').remove();
        scontain.remove();

        container.find('.selector').show();
        container.find('.saveSubject').show();
    });


});




