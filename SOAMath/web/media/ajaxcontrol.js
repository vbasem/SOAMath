function before_ajax() {

}

function ajax_get_action()
{   
    var mathExpression = $('input[name=math_expression]');
    mathExpression = encodeURIComponent(mathExpression.val());
    var data = 'math_expression=' + mathExpression;
   
    
    $.ajax({
        url: "math",
        dataType: 'html',
        type: 'GET',
        data: data,
        success: load_trigger,
        beforeSend: before_ajax,
        error: function(jqXHR) {
            load_trigger(jqXHR.responseText, 'error');
        }
    });
    
    return false;
}


function load_trigger(html, action) {
    if (html != null) {
        var loader = $('<div id="ajax_result">' + html + '</div><br />');


        var old_content = $('#content');
        //old_content.empty();
        old_content.prepend(loader);
        //loader.show();
    }
}


$(document).ready(function(){
    $('form#expression_form').submit(ajax_get_action);
  //  $(document).delegate('form:not(.non_ajax)', 'submit', ajax_get_action);
//$(document).delegate('a:not(.non_ajax):not(#uvTabLabel)', 'click', ajax_get_action);
//$(document).delegate('.any_click_submits', 'submit', ajax_post_action);
});


