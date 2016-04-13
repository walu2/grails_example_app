package com.tekdays

class TekUser {
    String fullName
    String userName
    String password
    String email
    String website
    String bio

    String toString() { fullName }

    static constraints = {
        fullName blank: false
        userName blank: false
        password blank:false
        email blank: false, email: true
        website nullable: true, url: true
        bio nullable: true, widget: 'textarea', maxSize:5000
    }
}
