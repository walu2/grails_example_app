package com.tekdays

class TekMessage {
    String subject
    String content
    TekMessage parent
    TekEvent event
    TekUser author

    String toString() { subject }

    static constraints = {
        subject blank: false
        content blank: false, widget: 'textarea', maxSize: 2000
        parent nullable: true
        author nullable: false 
    }
    static belongsTo = TekEvent
}
