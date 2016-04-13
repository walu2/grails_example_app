<li class="fieldcontain">
    <span id="${label.toLowerCase()}-label" class="property-label">
    	<g:message code="${label}" default="${label}" />
    </span>
    <div class="property-value" aria-labelledby="${label.toLowerCase()}-label">
        <g:link controller="tekUser" action="show" id="${this.task.assignedTo?.id}">${this.task.assignedTo}</g:link>
    </div>
</li>