<%@ page import="lotto.Lottery" %>



<div class="fieldcontain ${hasErrors(bean: lotteryInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="lottery.name.label" default="Name"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" maxlength="50" required="" value="${lotteryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lotteryInstance, field: 'events', 'error')} ">
    <label for="events">
        <g:message code="lottery.events.label" default="Events"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${lotteryInstance?.events ?}" var="e">
            <li><g:link controller="event" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="event" action="create"
                    params="['lottery.id': lotteryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'event.label', default: 'Event')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: lotteryInstance, field: 'users', 'error')} ">
    <label for="users">
        <g:message code="lottery.users.label" default="Users"/>

    </label>
    <g:select name="users" from="${lotto.User.list()}" multiple="multiple" optionKey="id" size="5"
              value="${lotteryInstance?.users*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: lotteryInstance, field: 'completed', 'error')} ">
    <label for="completed">
        <g:message code="lottery.completed.label" default="Completed"/>

    </label>
    <g:checkBox name="completed" value="${lotteryInstance?.completed}"/>
</div>

