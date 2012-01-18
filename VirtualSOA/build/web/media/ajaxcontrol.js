function before_ajax() {

}

function ajax_get_action()
{   
    var vmstart = $('input[name=startVm]');
    var vmstop = $('input[name=stopVm]');
    var processStart = $('input[name=startProcess]');
        
    var data = 'startVm=' + vmstart.val() + "&" + 'stopVm=' + vmstop.val() + "&" + "startProcess=" + processStart.val();
    $.ajax({
        url: "vmcontrol",
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

function ajax_reset()
{
        var data = 'resetVm=true';
    $.ajax({
        url: "vmcontrol",
        dataType: 'html',
        type: 'GET',
        data: data,
        success: load_trigger,
        beforeSend: before_ajax,
        error: function(jqXHR) {
            load_trigger(jqXHR.responseText, 'error');
        }
    });
}

function ajax_connect()
{
        var data = 'connectToVBox=true';
    $.ajax({
        url: "vmcontrol",
        dataType: 'html',
        type: 'GET',
        data: data,
        success: load_trigger,
        beforeSend: before_ajax,
        error: function(jqXHR) {
            load_trigger(jqXHR.responseText, 'error');
        }
    });
}

$(document).ready(function(){
    $('form#controls').submit(ajax_get_action);
    $('button#reset').click(ajax_reset);
    $('button#connect').click(ajax_connect);
  //  $(document).delegate('form:not(.non_ajax)', 'submit', ajax_get_action);
//$(document).delegate('a:not(.non_ajax):not(#uvTabLabel)', 'click', ajax_get_action);
//$(document).delegate('.any_click_submits', 'submit', ajax_post_action);
});


