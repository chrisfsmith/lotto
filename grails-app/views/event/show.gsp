<%@ page import="lotto.Event" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-event" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-event" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list event">

        <g:if test="${eventInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="event.name.label" default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${eventInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${eventInstance?.description}">
            <li class="fieldcontain">
                <span id="description-label" class="property-label"><g:message code="event.description.label"
                                                                               default="Description"/></span>

                <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${eventInstance}"
                                                                                               field="description"/></span>

            </li>
        </g:if>

        <g:if test="${eventInstance?.startDate}">
            <li class="fieldcontain">
                <span id="startDate-label" class="property-label"><g:message code="event.startDate.label"
                                                                             default="Start Date"/></span>

                <span class="property-value" aria-labelledby="startDate-label"><g:formatDate
                        date="${eventInstance?.startDate}"/></span>

            </li>
        </g:if>

        <g:if test="${eventInstance?.cost}">
            <li class="fieldcontain">
                <span id="cost-label" class="property-label"><g:message code="event.cost.label" default="Cost"/></span>

                <span class="property-value" aria-labelledby="cost-label"><g:fieldValue bean="${eventInstance}"
                                                                                        field="cost"/></span>

            </li>
        </g:if>

        <g:if test="${eventInstance?.maxAttendees}">
            <li class="fieldcontain">
                <span id="maxAttendees-label" class="property-label"><g:message code="event.maxAttendees.label"
                                                                                default="Max Attendees"/></span>

                <span class="property-value" aria-labelledby="maxAttendees-label"><g:fieldValue bean="${eventInstance}"
                                                                                                field="maxAttendees"/></span>

            </li>
        </g:if>

        <g:if test="${eventInstance?.lottery}">
            <li class="fieldcontain">
                <span id="lottery-label" class="property-label"><g:message code="event.lottery.label"
                                                                           default="Lottery"/></span>

                <span class="property-value" aria-labelledby="lottery-label"><g:link controller="lottery" action="show"
                                                                                     id="${eventInstance?.lottery?.id}">${eventInstance?.lottery?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${eventInstance?.registrations}">
            <li class="fieldcontain">
                <span id="registrations-label" class="property-label"><g:message code="event.registrations.label"
                                                                                 default="Registrations"/></span>

                <g:each in="${eventInstance.registrations}" var="r">
                    <span class="property-value" aria-labelledby="registrations-label"><g:link controller="registration"
                                                                                               action="show"
                                                                                               id="${r.id}">${r?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${eventInstance?.id}"/>
            <g:link class="edit" action="edit" id="${eventInstance?.id}"><g:message code="default.button.edit.label"
                                                                                    default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
