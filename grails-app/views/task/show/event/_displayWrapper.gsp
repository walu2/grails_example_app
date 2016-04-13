<li class="fieldcontain">
    <span id="${label.toLowerCase()}-label" class="property-label">
    	<g:message code="${label}" default="${label}" />
    </span>
    <div class="property-value" aria-labelledby="${label.toLowerCase()}-label">
        <g:link controller="tekEvent" action="show" id="${this.task.event?.id}">${this.task.event}</g:link>
    </div>
</li>