(function() {
    $('.input-container input').focusout(function() {
        if ($(this).val() == '') {
            $(this).removeClass('filled');
        } else {
            console.log($(this).val());
            $(this).addClass('filled');
        }
    });
})();