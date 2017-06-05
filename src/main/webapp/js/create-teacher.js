$(function(){

    var resource = [
        "Math","Algebra","Physics","Математика"
    ];

    var $phonebox = $('#phone');
    $phonebox.keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
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

    $phonebox.attr('maxlength','11');

    $('input[type="checkbox"]').on('change', function () {
       $('input[type="checkbox"]').not(this).prop('checked', false);
    });

    $('#subject').autocomplete({
        source:resource
    });

    $('#addTeacher').click(function(){
        var $phone = $('#phone');
        var $teacher = $('#name');
        var $teacherDiv = $('#ateacher').html();

        if(!$phone.val().trim()){
            $('#pempty').show();
            $phone.focus();
            return false;
        }else if(!$teacher.val().trim()){
            $('#tempty').show();
            $teacher.focus();
            return false;
        }else{
            var name = {teacherName:$teacher.val()};
            $('#teachers').append(Mustache.render($teacherDiv,name));
            $phone.val("");
            $teacher.val("");
            $('#pempty').hide();
            $('#tempty').hide();
        }
    });

    $('#addSubject').click(function () {
        var $subject = $('#subject');
        if(!$subject.val().trim()){
            $subject.focus();
        }else {
            var sdiv = $('#asubject').html();
            var subjectName = {subjectName: $subject.val()};
            $subject.val("").focus();
            $('#subjects').append(Mustache.render(sdiv, subjectName));
        }

    });

    $('#subjects').delegate('.close','click',function () {
        $(this).closest('.subject').remove();
    });
});
