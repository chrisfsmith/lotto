<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 4/6/12
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type"
          content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Lotto</title>
</head>

<body>
<div class="body">
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:each in="${eventInstanceList}"
            status="i" var="eventInstance">
        <div class="event">
            <h2>${eventInstance.name}</h2>

            <p class="event-details">
                <span class="question">What?</span>
                <span class="answer">
                    ${eventInstance.description}</span>
            </p>

            <p class="event-details">
                <span class="question">When?</span>
                <span class="answer">
                    ${eventInstance.startDate.
                            format("EEEE, MMMM d, yyyy HH:mm")}</span>
            </p>

            <p class="event-details">
                <span class="question">How Much?</span>
                <span class="answer">
                    <g:formatNumber
                            number="${eventInstance.cost}"
                            format="\$###,##0"/></span>
            </p>

            <p class="event-details">
                <span class="question">How Many?</span>
                <span class="answer">
                    <g:formatNumber
                            number="${eventInstance.maxAttendees}"/></span>
            </p>
        </div>
    </g:each>
    <div class="paginateButtons">
        <g:paginate total="${eventInstanceTotal}"/>
    </div>
</div>
</body>
</html>