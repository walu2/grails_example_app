<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'completed', 'error')} ">

	<label for="completed">
		<g:message code="task.completed.label" default="Completed" />
	</label>

	<g:checkBox name="completed" value="${taskInstance?.completed}" />
</div>