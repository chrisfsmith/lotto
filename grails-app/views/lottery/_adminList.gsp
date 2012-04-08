<%@ page import="lotto.Lottery" %>

<div id="list-lottery" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'lottery.name.label', default: 'Name')}"/>

            <g:sortableColumn property="completed"
                              title="${message(code: 'lottery.completed.label', default: 'Completed')}"/>

            <g:sortableColumn property="dateCreated"
                              title="${message(code: 'lottery.dateCreated.label', default: 'Date Created')}"/>

            <g:sortableColumn property="lastUpdated"
                              title="${message(code: 'lottery.lastUpdated.label', default: 'Last Updated')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${lotteryInstanceList}" status="i" var="lotteryInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${lotteryInstance.id}">${fieldValue(bean: lotteryInstance, field: "name")}</g:link></td>

                <td><g:formatBoolean boolean="${lotteryInstance.completed}"/></td>

                <td><g:formatDate date="${lotteryInstance.dateCreated}"/></td>

                <td><g:formatDate date="${lotteryInstance.lastUpdated}"/></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${lotteryInstanceTotal}"/>
    </div>
</div>
