package com.tekdays
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekEventController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond TekEvent.list(params),
		model:[tekEventInstanceCount: TekEvent.count()]
	}
	
	def show(TekEvent tekEventInstance) {
		respond tekEventInstance
	}
	
	def create() {
		respond new TekEvent(params)
	}
	
	@Transactional
	def save(TekEvent tekEventInstance) {
		
		if (tekEventInstance == null) {
			notFound()
			return
		}
		
		if (tekEventInstance.hasErrors()) {
			respond tekEventInstance.errors, view:'create'
			return
		}
		
		tekEventInstance.save flush:true
		
		request.withFormat {
			form {
				flash.message = message(code: 'default.created.message',
				args: [message(code: 'tekEventInstance.label',
				default: 'TekEvent'), tekEventInstance.id])
				redirect tekEventInstance
			}
			'*' { respond tekEventInstance, [status: CREATED] }
		}
	}
	
	def edit(TekEvent tekEventInstance) {
		respond tekEventInstance
	}
	
	@Transactional
	def update(TekEvent tekEventInstance) {
		
		if (tekEventInstance == null) {
			notFound()
			return
		}
		if (tekEventInstance.hasErrors()) {
			respond tekEventInstance.errors, view:'edit'
			return
		}
		
		tekEventInstance.save flush:true
		
		request.withFormat {
			form {
				flash.message = message(code: 'default.updated.message',
				args: [message(code: 'TekEvent.label', default: 'TekEvent'),
				tekEventInstance.id])
				redirect tekEventInstance
			}
			'*'{ respond tekEventInstance, [status: OK] }
		}
	}
}