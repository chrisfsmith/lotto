<%@ page import="lotto.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="event.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" maxlength="50" required="" value="${eventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'description', 'error')} required">
    <label for="description">
        <g:message code="event.description.label" default="Description"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="description" maxlength="250" required="" value="${eventInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'startDate', 'error')} required">
    <label for="startDate">
        <g:message code="event.startDate.label" default="Start Date"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="startDate" precision="day" value="${eventInstance?.startDate}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'cost', 'error')} required">
    <label for="cost">
        <g:message code="event.cost.label" default="Cost"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="cost" step="0.01" min="0.0" max="1000.0" required=""
             value="${fieldValue(bean: eventInstance, field: 'cost')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'maxAttendees', 'error')} required">
    <label for="maxAttendees">
        <g:message code="event.maxAttendees.label" default="Max Attendees"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="maxAttendees" min="1" max="1000" required=""
             value="${fieldValue(bean: eventInstance, field: 'maxAttendees')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'lottery', 'error')} required">
    <label for="lottery">
        <g:message code="event.lottery.label" default="Lottery"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="lottery" name="lottery.id" from="${lotto.Lottery.list()}" optionKey="id" required=""
              value="${eventInstance?.lottery?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'registrations', 'error')} ">
    <label for="registrations">
        <g:message code="event.registrations.label" default="Registrations"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${eventInstance?.registrations ?}" var="r">
            <li><g:link controller="registration" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="registration" action="create"
                    params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'registration.label', default: 'Registration')])}</g:link>
        </li>
    </ul>

</div>

