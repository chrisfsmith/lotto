<%--
  Created by IntelliJ IDEA.
  User: chris
  Date: 4/6/12
  Time: 11:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lotto.Lottery" %>

<div id="list-lottery" class="body content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${eventInstanceList.size() == 0}">
        <p class="info">Sorry, there are no events for this lottery.</p>
    </g:if>
    <g:else>
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
                    <span class="question">Max Attendees?</span>
                    <span class="answer">
                        <g:formatNumber
                                number="${eventInstance.maxAttendees}"/></span>
                </p>

                <% howManyLeft = eventInstance.maxAttendees - (eventInstance.registrations ? eventInstance.registrations.size() : 0) %>
                <p class="event-details">
                    <span class="question">How Many Left?</span>
                    <span class="answer">
                        <g:formatNumber
                                number="${howManyLeft}"/></span>
                </p>

                <g:if test="${howManyLeft > 0}">
                    <p class="event-details">
                        <g:link controller="lottery" action="pick"
                                params="[lottery: params.lottery, event: eventInstance.id]">
                            Pick
                        </g:link>
                    </p>
                </g:if>

            </div>
        </g:each>
    </g:else>

    <div class="pagination">
        <g:paginate total="${eventInstanceTotal}" params="[lottery: params.lottery]"/>
    </div>
</div>
