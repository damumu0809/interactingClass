function getPersonal(id){
    console.log(id);
    var blogId = {user:id};
    $.post("/interactingClass/PersonalPage", blogId, function(res){
        var message = $.parseJSON(res);
        var
    });
}