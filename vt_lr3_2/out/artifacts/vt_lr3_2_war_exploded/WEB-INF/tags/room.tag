<%@tag description="Room item" pageEncoding="UTF-8" %>
<%@attribute name="room" required="true" type="persistance.models.Room" %>
<div class="room col-xs-3 isFree${room.booked}"
     onclick="book(${room.id}, ${room.userId})">
    <span>${room.number}</span>
</div>