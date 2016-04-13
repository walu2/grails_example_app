package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TekUser)
class TekUserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "user is valid"(){
        when: "user has ne properties"
        def u = new TekUser()

        then: "validation should fail"
        !u.validate()
        u.hasErrors()
        u.errors.errorCount == 4
        u.errors['fullName'].code == 'nullable'
        u.errors['userName'].code == 'nullable'
        u.errors['password'].code == 'nullable'
        u.errors['email'].code == 'nullable'

        when: "an invalid email is provided"
        u = new TekUser(email: 'bogus')

        then: "validation should fail"
        !u.validate()
        u.errors['email'].code == 'email.invalid'

        when: "an invalid website URL is provided"
        u = new TekUser(website: 'gloop')

        then: "validation should fail"
        !u.validate()
        u.errors['website'].code == 'url.invalid'

        when: "fullName, userName, password and email are provide correctly"
        u = new TekUser(fullName: 'fullName',
                        userName: 'userName',
                        password: 'password',
                        email: 'someone@somewhere.com')

        then: "validation should pass"
        u.validate()

    }

    void "test toString"() {
        when:
        def tekUser = new TekUser(fullName: 'John Doe',
                userName: 'jonnyD',
                password: 'password',
                email: 'jonny@mail.com',
                website: 'http://www.thebigjd.com')

        then: "the toString method will display the fullName"
        tekUser.toString() == 'John Doe'
    }
}
