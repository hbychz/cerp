package cerp

import grails.converters.JSON

class ResourceUrlController {

    def index(String resourceName) {
        def model = []

        if (resourceName) {
            model << all()[resourceName]
        } else {
            model << all()
        }

        JSON.use('deep')
        render model as JSON
    }

    private def all() {
        return [engineering: engineering()]
    }

    private def engineering() {
        return [
                List: [ "href": "${createLink(controller: 'engineering', action: 'index')}" ],
                show: [ "href": "${createLink(controller: 'engineering', action: 'show')}" ],
                create: [ "href": "${createLink(controller: 'engineering', action: 'create')}" ],
                update: [ "href": "${createLink(controller: 'engineering', action: 'update')}" ],
                delete: [ "href": "${createLink(controller: 'engineering', action: 'delete')}" ]
        ]
    }

}
