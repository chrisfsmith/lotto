<%@ page import="lotto.Event" %>

<div id="list-event" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'event.name.label', default: 'Name')}"/>

            <g:sortableColumn property="description"
                              title="${message(code: 'event.description.label', default: 'Description')}"/>

            <g:sortableColumn property="startDate"
                              title="${message(code: 'event.startDate.label', default: 'Start Date')}"/>

            <g:sortableColumn property="cost" title="${message(code: 'event.cost.label', default: 'Cost')}"/>

            <g:sortableColumn property="maxAttendees"
                              title="${message(code: 'event.maxAttendees.label', default: 'Max Attendees')}"/>

            <th><g:message code="event.lottery.label" default="Lottery"/></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${eventInstanceList}" status="i" var="eventInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${eventInstance.id}">${fieldValue(bean: eventInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: eventInstance, field: "description")}</td>

                <td><g:formatDate date="${eventInstance.startDate}"/></td>

                <td>${fieldValue(bean: eventInstance, field: "cost")}</td>

                <td>${fieldValue(bean: eventInstance, field: "maxAttendees")}</td>

                <td>${fieldValue(bean: eventInstance, field: "lottery")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${eventInstanceTotal}"/>
    </div>
</div>
