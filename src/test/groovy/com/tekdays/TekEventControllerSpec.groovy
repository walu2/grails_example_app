package com.tekdays

import grails.test.mixin.*
import spock.lang.*

@TestFor(TekEventController)
@Mock([TekEvent, TekUser, Task])
class TekEventControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["name"] = 'Really Unmissable Event'
        params["city"] = 'Big Green City'
        params["organizer"] = new TekUser()
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.tekEventList
            model.tekEventCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.tekEvent!= null
    }

    void "Test the save action correctly persists an instance"() {

        given:
        TaskService taskService = Mock()
        controller.taskService = taskService
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'

        when:"The save action is executed with an invalid instance"
        def tekEvent = new TekEvent()
        tekEvent.validate()
        controller.save(tekEvent)

        then:"The create view is rendered again with the correct model"
        model.tekEvent!= null
        view == 'create'

        when:"The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        tekEvent = new TekEvent(params)

        controller.save(tekEvent)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/tekEvent/show/1'
        controller.flash.message != null
        TekEvent.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tekEvent = new TekEvent(params)
            controller.show(tekEvent)

        then:"A model is populated containing the domain instance"
            model.tekEvent == tekEvent
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tekEvent = new TekEvent(params)
            controller.edit(tekEvent)

        then:"A model is populated containing the domain instance"
            model.tekEvent == tekEvent
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/tekEvent/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def tekEvent = new TekEvent()
            tekEvent.validate()
            controller.update(tekEvent)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.tekEvent == tekEvent

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            tekEvent = new TekEvent(params).save(flush: true)
            controller.update(tekEvent)

        then:"A redirect is issued to the show action"
            tekEvent != null
            response.redirectedUrl == "/tekEvent/show/$tekEvent.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/tekEvent/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def tekEvent = new TekEvent(params).save(flush: true)

        then:"It exists"
            TekEvent.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(tekEvent)

        then:"The instance is deleted"
            TekEvent.count() == 0
            response.redirectedUrl == '/tekEvent/index'
            flash.message != null
    }
}
