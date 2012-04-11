<%@ page import="lotto.Lottery" %>

<div id="list-registration" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <th><g:message code="registration.attendee.label" default="Attendee"/></th>

            <th><g:message code="registration.event.label" default="Event"/></th>

            <g:sortableColumn property="dateCreated"
                              title="${message(code: 'registration.dateCreated.label', default: 'Date Created')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${registrationInstanceList}" status="i" var="registrationInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${registrationInstance.id}">${fieldValue(bean: registrationInstance, field: "attendee")}</g:link></td>

                <td>${fieldValue(bean: registrationInstance, field: "event")}</td>

                <td><g:formatDate date="${registrationInstance.dateCreated}"/></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${registrationInstanceTotal}"/>
    </div>
</div>
