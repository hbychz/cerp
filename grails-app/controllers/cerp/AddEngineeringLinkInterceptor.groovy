package cerp

import grails.converters.JSON


class AddEngineeringLinkInterceptor {
    AddEngineeringLinkInterceptor() {
        match(controller:"engineering")
    }

    boolean before() { true }

    boolean after() {
        true
    }

    void afterView() {
        // no-op
    }
}
