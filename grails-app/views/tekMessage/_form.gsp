<g:if test="${tekMessageInstance?.parent}">
<div class="fieldcontain ${hasErrors(bean: tekMessageInstance, field: 'parent', 'error')} ">
	<label for="parent">
		In Reply to:
	</label>
	
	${tekMessageInstance?.parent?.author}
	
</div>
</g:if>