$(document).ready(function () {
    $.get("/notifications/latestNotifications", {}, function (data) {
        console.log(data);
    })
});