<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Room Booking</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../layout/navbar.jsp"></jsp:include>
<jsp:doBody/>
<footer>
    Select lang:
    <button class="btn btn-default" onclick="setLocale('en')">
        EN
    </button>
    <button class="btn btn-primary" onclick="setLocale('ru')">
        RU
    </button>
</footer>
<script>
    <%@include file="../../index.js" %>
</script>

</body>
</html>