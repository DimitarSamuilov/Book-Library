function excluirItemNotificacao(e) {
    $('#item_notification_' + e.id).remove()
}

function displayNotifications(data){

	if(data.length>0){
		$('.badgeAlert').text(data.length +1);
var container  = $('<ul></ul>').addClass('list-notificacao dropdown-menu dropdown-menu-right');
		for (let not of data){
			console.log(not);
			
			var liElement = $('<li></li>');
			liElement.attr('id',not.id);
				container.add(liElement);
			}
		
		console.log(container);
    }
}

$(document).ready(function () {
    $.get("/notifications/latestNotifications", {}, function (data) {
        console.log(data);
        console.log(data.length);
		displayNotifications(data);
    }, 'json')
});