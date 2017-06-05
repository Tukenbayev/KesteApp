$(function () {
    var school_id = $('#school_id').html();
    var template = $('#cl_letter').html();
    var dialog = $('<div></div>');
    var no_class = $('#no_class').html();
    var $digitbox = $('#number');
    var $letter = $('#letter');
    var spans = $('span.digit');



    getAllClasses();

    $('#classes').click(function () {
        $('#main').hide();
        $('div#teachers').hide();
        $('#subjects').hide();

        $('div.create-class').slideDown();
        $('div.classes').slideDown();
    });

    $('#tchers').click(function () {
        $('div.create-class').hide();
        $('div.classes').hide();

        $('#main').slideDown();
        $('#teachers').slideDown();
        $('#subjects').slideDown();

    });

    $digitbox.focus(function () {
       $('#exist').hide();
    });

    $('#createClass').click(function () {
        var digit = $digitbox.val();
        var letter = $letter.val().toUpperCase();

        if(digit<0 || digit>11 || digit.trim() === ''){
            $('#empty_number').show();
            return false;
        }else
            $('#empty_number').hide();


        if(!$letter.val()){
            $('#empty_letter').show();
            return false;
        }else
            $('#empty_letter').hide();

        $.ajax({
            url:"/create-class",
            type:"POST",
            dataType: 'json',
            data: {
                school_id: school_id,
                class_Number: digit,
                class_Letter: letter
            },
            success: function (clss) {
                console.log(clss.isExist);

                if(clss.isExist){
                    $('#exist').show();
                    return false;
                }else{
                    getAllClasses();
                }
            }
        });


        $digitbox.val("");
        $letter.val("");

    });

    function getAllClasses() {
        $.ajax({

            url:'/get-all-classes',
            type:'POST',
            dataType:'json',
            data: {
                school_id: school_id
            },
            success: function (classes) {
                $('div.lttr').remove();
                $.each(classes,function (key,value) {
                    var id = "#"+value.class_Number;
                    $(id).parent().find('div.no-cl').remove();
                    $(id).append(Mustache.render(template,{class_Letter:value.class_Letter,class_id:key}))
                });
            }

        });
    }

    $('div.classes').delegate(".close",'click',function () {
        var clss = $(this).parent();
        var container = clss.parent();
        var class_id = clss.attr("datafld");
        console.log(class_id);

        dialog.html("Вы действительно хотите удалить этот класс? Все данные связанные с ним будут потеряны").dialog({
            autoOpen:false,
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            draggable:false,
            buttons: {
                Удалить: function() {
                    if(class_id !== null){
                        $.ajax({
                            url:'/delete-class',
                            dataType:'json',
                            data:{class_id:class_id}
                        });
                    }

                    clss.remove();
                    if(container.find("div.letter").length === 0)
                        container.append(no_class);
                    $( this ).dialog( "close" );
                },
                Отмена: function() {
                    $( this ).dialog( "close" );
                }
            }
        });

        dialog.dialog("open");
    });

    $digitbox.keydown(function (e) {
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

    $digitbox.attr('maxlength','2');
    $letter.attr('maxlength','1');

    /* -------------Manage teachers js file start---------------*/

    var cancelBtn = $('#cancel');
    var saveBtn = $('#save');
    var addTeacherBtn = $('#addTeacher');
    var $phonebox = $('#phone');
    var $namebox = $('#name');
    var $subjects = $('#subjects');
    cancelBtn.hide();

    getAllTeachers();

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


    addTeacherBtn.click(function(){
        var $phone = $('#phone');
        var $teacher = $('#name');
        var $teacherDiv = $('#ateacher').html();
        var $subjects = $('.tsubject');

        if(!$phone.val().trim()){
            $('#pempty').show();
            $phone.focus();
            return false;
        }else if(!$teacher.val().trim()){
            $('#tempty').show();
            $teacher.focus();
            return false;
        }else{
            if ($subjects.length === 0){
                $('#least').show();
                return false;
            }else {
                $.ajax({
                    url:"/check-teachers-phone",
                    type:"POST",
                    dataType:"json",
                    data:{phone:$phone.val().trim()},
                    success:function (phone) {
                        if (phone.isExist){
                            $("#phone-exist").show();
                            return false;
                        }else{
                            var teacher = {
                                phone: $phone.val(),
                                name: $teacher.val(),
                                schoolId: school_id,
                                subjects:[]
                            };

                            $subjects.each(function () {
                               var subjectId = $(this).attr('datafld');
                               var subjectName = $(this).find('.slabel').html();
                               teacher.subjects.push({
                                   subjectId: subjectId,
                                   subjectName: subjectName
                               });
                            });

                            console.log(teacher);
                            $.ajax({
                                url: "/add-teacher",
                                type: "POST",
                                data: {teacher: JSON.stringify(teacher)},
                                dataType: 'json'
                            });

                            $('#teachers').append(Mustache.render($teacherDiv, {
                                teacherId: $phone.val(),
                                teacherName: $teacher.val()
                            }));
                            $phone.val("");
                            $teacher.val("");
                            $('#pempty').hide();
                            $('#tempty').hide();
                            $('#least').hide();
                            $('#phone-exist').hide();
                            $('#subjects').find("div.tsubject").remove();
                        }
                    }
                });

            }
        }
    });

    $('#addSubject').click(function () {
        var $subject = $('#subject');
        if(!$subject.val().trim()){
            $subject.focus();
        }else {
            var sdiv = $('#asubject').html();
            var subjectName = $subject.val();
            $.ajax({
               url:"/check-subject",
                type: 'POST',
                dataType:'json',
                data:{subjectName:subjectName},
                success:function (data) {
                    $('#subjects').append(Mustache.render(sdiv, data));
                }
            });
            $subject.val("").focus();
        }

    });
    
    function getAllTeachers() {
        $.ajax({
            url:"/get-all-teachers",
            type:"GET",
            dataType:"json",
            data: {schoolId: school_id},
            success: function (teachers) {
                console.log(teachers);
                $.each(teachers,function (i,teacher) {
                   $('#teachers').append(Mustache.render($('#ateacher').html(),teacher));
                });
            }
        });
    }

    $subjects.delegate('.remove','click',function () {
        $(this).closest('.subject').remove();
    });

    $('#teachers').delegate('.edit','click',function () {
        saveBtn.show();
        cancelBtn.show();
        addTeacherBtn.hide();
        var teacherId = $(this).parent().attr("datafld");
        var teacherName = $(this).next().html();
        $phonebox.val(teacherId);
        $namebox.val(teacherName);
        $('p.subjectsLabel').append(teacherName);
    });



});
