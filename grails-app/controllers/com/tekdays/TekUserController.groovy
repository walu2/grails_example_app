package com.tekdays

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekUserController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond TekUser.list(params), model:[tekUserCount: TekUser.count()]
	}

	def show(TekUser tekUser) {
		respond tekUser
	}

	def create() {
		respond new TekUser(params)
	}

	@Transactional
	def save(TekUser tekUser) {
		if (tekUser == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (tekUser.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond tekUser.errors, view:'create'
			return
		}

		tekUser.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUser.id])
				redirect tekUser
			}
			'*' { respond tekUser, [status: CREATED] }
		}
	}

	def edit(TekUser tekUser) {
		respond tekUser
	}

	@Transactional
	def update(TekUser tekUser) {
		if (tekUser == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (tekUser.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond tekUser.errors, view:'edit'
			return
		}

		tekUser.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUser.id])
				redirect tekUser
			}
			'*'{ respond tekUser, [status: OK] }
		}
	}

	@Transactional
	def delete(TekUser tekUser) {

		if (tekUser == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		tekUser.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUser.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
