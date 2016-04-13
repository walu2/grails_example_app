package com.tekdays

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Sponsor)
class SponsorSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "sponsor is valid"(){
        when: "sponsor has no properties"
        def s = new Sponsor()

        then: "validation should fail"
        !s.validate()
        s.hasErrors()
        s.errors.errorCount == 2
        s.errors['name'].code == 'nullable'
        s.errors['website'].code == 'nullable'

        when: "an invalid URL is provided"
        s = new Sponsor(  name: 'name',
                website: 'gloop')

        then: "validation should fail"
        !s.validate()
        s.errors.errorCount == 1
        s.errors['website'].code == 'url.invalid'


        when: "name and website are provided correctly"
        s = new Sponsor(  name: 'name',
                website: 'http://website.com')

        then: "validation should pass"
        s.validate()
    }

    void "test toString"() {
        when:
        def sponsor = new Sponsor(
                name: 'Big Bucks Billy',
                website: 'http://www.bigbucksbilly.com')

        then: "the toString method will display the name"
        sponsor.toString() == 'Big Bucks Billy'
    }
}
