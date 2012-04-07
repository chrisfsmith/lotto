<div id="search">
    <g:form url='[controller: "event", action: "search"]'
            id="eventSearchForm"
            name="eventSearchForm"
            method="get">
        <g:textField name="q" value="${params.q}"/>
        <input type="submit" value="Find an event"/>
    </g:form>
</div>