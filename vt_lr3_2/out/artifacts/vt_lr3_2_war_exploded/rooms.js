function book(bookId, ownerId) {
    $.ajax({
        method: "POST",
        url: "BookRoom",
        data: { id: bookId, ownerId: ownerId },
        success: function (response) {
            if(response != "success") {
                alert(response);
            }
            location.reload();
        },
        error: function(response) {
            console.log(response);
        }
    })
}