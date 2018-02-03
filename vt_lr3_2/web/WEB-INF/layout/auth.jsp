<%@ page import="business.configuration.AuthProvider" %>
<%@ page import="persistance.models.User" %><%
User user = AuthProvider.authManager.getAuthUser();
if(user != null) {
%>
<form class="navbar-form navbar-right">
    <div class="form-group">
        <label style="padding-top: 6px; padding-right: 8px; color:white";>Hello, <span style="text-transform: capitalize"> <%= user.login %></span></label>
    </div>
    <button class="btn btn-primary logout">Logout</button>
</form>
<% } else { %>
<form class="navbar-form navbar-right">
    <div class="form-group">
        <input type="text" class="form-control login" style="width: 150px" placeholder="login" id="login" name="login" />
    </div>
    <div class="form-group">
        <input type="password" class="form-control password" id="password" name="password" placeholder="password" style="width: 150px">
    </div>
    <button class="btn btn-success login-btn" type="button">Login</button>
    <button class="btn btn-primary register-btn" type="button">Register</button>
</form>
<% } %>