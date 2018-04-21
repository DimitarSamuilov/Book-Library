function excluirItemNotificacao() {
    var notificationMainElement = $(this).closest('li.removableNotification');
    console.log();
    $.ajax({
        method: 'POST',
        url: "/notifications/markViewed/",
        data: {'id': notificationMainElement.attr('data-notification-id')}
    });
    notificationMainElement.remove();
	$('.badgeAlert').text((parseInt($('.badgeAlert').text()))-1)


}

function displayNotifications(data) {

    if (data.length > 0) {
        $('.badgeAlert').text(data.length);
        var ulItem = $('<ul></ul>').addClass('list-notificacao dropdown-menu dropdown-menu-right');
        for (var not of data) {
            console.log(not);

            var liElement = $('<li></li>').addClass('removableNotification').attr('data-notification-id', not.id);

            var mainDiv = $('<div>').addClass('media');
            var mediaBody = $('<div>').addClass('media-body');
            var notifFirst = $('<div>').addClass('exclusaoNotificacao');
            var button = $('<button>').addClass('btn btn-danger btn-xs button_exclusao').attr('id', not.id).text('x');

            button.click(excluirItemNotificacao);
            notifFirst.append(button);
            mediaBody.append(notifFirst);
            mediaBody.append($('<h4>'));
            mediaBody.append($('<p>').text(not.message));

            mainDiv.append(mediaBody);
            liElement.append(mainDiv);
            console.log(liElement);
            $('.list-notificacao').append(liElement);
        }
    }
}


$(document).ready(function () {
    if ($('.list-notificacao').length > 0) {
        $.get("/notifications/latestNotifications", {}, function (data) {
            console.log(data);
            console.log(data.length);
            displayNotifications(data);
        }, 'json')
    }else {
		$('.list-notificacao').remove();
	}
});
