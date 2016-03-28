package com.tekdays

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekMessageController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def list
		def count
		def event = TekEvent.get(params.id)
		if(event){
			list = TekMessage.findAllByEvent(event, params)
			count = TekMessage.countByEvent(event)
		}
		else {
			list = TekMessage.list(params)
			count = TekMessage.count()
		}
		[tekMessageInstanceList: list, tekMessageInstanceCount: count, event: event]
	}

	def show(TekMessage tekMessage) {
		respond tekMessage
	}

	def create() {
		respond new TekMessage(params)
	}

	@Transactional
	def save(TekMessage tekMessage) {
		if (tekMessage == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (tekMessage.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond tekMessage.errors, view:'create'
			return
		}

		tekMessage.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'tekMessage.label', default: 'TekMessage'), tekMessage.id])
				redirect tekMessage
			}
			'*' { respond tekMessage, [status: CREATED] }
		}
	}

	def edit(TekMessage tekMessage) {
		respond tekMessage
	}

	@Transactional
	def update(TekMessage tekMessage) {
		if (tekMessage == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (tekMessage.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond tekMessage.errors, view:'edit'
			return
		}

		tekMessage.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'tekMessage.label', default: 'TekMessage'), tekMessage.id])
				redirect tekMessage
			}
			'*'{ respond tekMessage, [status: OK] }
		}
	}

	@Transactional
	def delete(TekMessage tekMessage) {

		if (tekMessage == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		tekMessage.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'tekMessage.label', default: 'TekMessage'), tekMessage.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekMessage.label', default: 'TekMessage'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
