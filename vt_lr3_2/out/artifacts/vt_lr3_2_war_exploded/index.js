function setLocale(locale) {
    $.ajax({
        method: "POST",
        url: "/Locale",
        data: {
            locale: locale
        },
        success: function (response) {
            location.reload();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

$(document).ready(function () {
    $('div#navbar ul li').click(function () {
        $('div#navbar ul li').removeClass('active');
        $(this).addClass('active');
    });

    $('.register-btn').click(function() {
        $.ajax({
            method: "POST",
            url: "/Register",
            data: {
                login: $('.login').val(),
                password: $('.password').val()
            },
            success: function (response) {
                console.log(response);
                alert(response);
            },
            error: function (response) {
                console.log(response);
            }
        });
    });

    $('.login-btn').click(function() {
        $.ajax({
            method: "POST",
            url: "/Login",
            data: {
                login: $('.login').val(),
                password: $('.password').val()
            },
            success: function (response) {
                alert(response);
                location.reload();
            },
            error: function (response) {
                console.log(response);
            }
        });
    });

    $('.logout').click(function() {
        $.ajax({
            method: "POST",
            url: "/Logout",
            data: {
                login: $('.login').val(),
                password: $('.password').val()
            },
            success: function (response) {
                location.reload();
            },
            error: function (response) {
                console.log(response);
            }
        });
    });
});