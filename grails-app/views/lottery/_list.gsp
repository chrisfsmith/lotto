<%@ page import="lotto.Lottery" %>

<div id="list-lottery" class="body" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:each in="${lotteryInstanceList}"
            status="i" var="lotteryInstance">
        <g:if test="${lotteryInstance.completed == false}">
            <div class="event">
                <h2>${lotteryInstance.name}</h2>

                <p class="event-details">
                    <span class="question">Completed?</span>
                    <span class="answer">
                        ${lotteryInstance.completed}</span>
                </p>
            </div>
        </g:if>
    </g:each>

    <div class="pagination">
        <g:paginate total="${lotteryInstanceTotal}"/>
    </div>
</div>
