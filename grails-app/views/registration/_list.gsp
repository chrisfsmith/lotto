<%@ page import="lotto.Lottery" %>

<div id="list-registration" class="content scaffold-list" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${registrationInstanceList.size() == 0}">
        <p class="info">Sorry, you are not attending of any events.</p>
    </g:if>
    <g:else>
        <g:each in="${registrationInstanceList}"
                status="i" var="registrationInstance">
            <div class="event">
                <h2>${registrationInstance.event.name}</h2>

                <p class="event-details">
                    <span class="question">What?</span>
                    <span class="answer">
                        ${registrationInstance.event.description}</span>
                </p>

                <p class="event-details">
                    <span class="question">When?</span>
                    <span class="answer">
                        ${registrationInstance.event.startDate.
                                format("EEEE, MMMM d, yyyy HH:mm")}</span>
                </p>

                <p class="event-details">
                    <span class="question">How Much?</span>
                    <span class="answer">
                        <g:formatNumber
                                number="${registrationInstance.event.cost}"
                                format="\$###,##0"/></span>
                </p>

            </div>
        </g:each>
    </g:else>

    <div class="pagination">
        <g:paginate total="${registrationInstanceTotal}"/>
    </div>
</div>
