<%@ page import="lotto.Lottery" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'lottery.label', default: 'Lottery')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-lottery" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-lottery" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list lottery">

        <g:if test="${lotteryInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="lottery.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${lotteryInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${lotteryInstance?.events}">
            <li class="fieldcontain">
                <span id="events-label" class="property-label"><g:message code="lottery.events.label"
                                                                          default="Events"/></span>

                <g:each in="${lotteryInstance.events}" var="e">
                    <span class="property-value" aria-labelledby="events-label"><g:link controller="event" action="show"
                                                                                        id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${lotteryInstance?.users}">
            <li class="fieldcontain">
                <span id="users-label" class="property-label"><g:message code="lottery.users.label"
                                                                         default="Users"/></span>

                <g:each in="${lotteryInstance.users}" var="u">
                    <span class="property-value" aria-labelledby="users-label"><g:link controller="user" action="show"
                                                                                       id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${lotteryInstance?.completed}">
            <li class="fieldcontain">
                <span id="completed-label" class="property-label"><g:message code="lottery.completed.label"
                                                                             default="Completed"/></span>

                <span class="property-value" aria-labelledby="completed-label"><g:formatBoolean
                        boolean="${lotteryInstance?.completed}"/></span>

            </li>
        </g:if>

        <g:if test="${lotteryInstance?.dateCreated}">
            <li class="fieldcontain">
                <span id="dateCreated-label" class="property-label"><g:message code="lottery.dateCreated.label"
                                                                               default="Date Created"/></span>

                <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
                        date="${lotteryInstance?.dateCreated}"/></span>

            </li>
        </g:if>

        <g:if test="${lotteryInstance?.lastUpdated}">
            <li class="fieldcontain">
                <span id="lastUpdated-label" class="property-label"><g:message code="lottery.lastUpdated.label"
                                                                               default="Last Updated"/></span>

                <span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate
                        date="${lotteryInstance?.lastUpdated}"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${lotteryInstance?.id}"/>
            <g:link class="edit" action="edit" id="${lotteryInstance?.id}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
