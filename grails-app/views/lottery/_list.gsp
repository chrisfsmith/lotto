<%@ page import="lotto.Lottery" %>

<div id="list-lottery" class="body content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${lotteryInstanceList.size() == 0}">
        <p class="info">Sorry, you are not part of an active lottery.</p>
    </g:if>
    <g:else>
        <g:each in="${lotteryInstanceList}"
                status="i" var="lotteryInstance">
            <div class="info">
                <g:link controller="event" action="list" params="[lottery: lotteryInstance.id]">
                    ${lotteryInstance.name}
                </g:link>
                <p>
                    Next picker: ${lotteryInstance.getPicker()}
                </p>
            </div>
        </g:each>
    </g:else>

    <div class="pagination">
        <g:paginate total="${lotteryInstanceTotal}"/>
    </div>
</div>
