package cerp

import grails.converters.JSON
import org.springframework.http.HttpStatus

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EngineeringController {
    static allowedMethods = [create: "POST", update: "POST", delete: "DELETE"]

    def webUtils

    def index() {
        params.max = params.max ? params.max : 10

        def model = [
                engineeringList: Engineering.list(params),
                count: Engineering.count()
        ]

        renderJSON(model)
//        respond object: [[name: 'aaa'], [name: 'bb']], model:[engineeringCount: Engineering.count()]
//        respond Engineering.list(params), model:[engineeringCount: Engineering.count()]
    }

    private def renderJSON(Map model, HttpStatus status = null) {
        model._links = [
                self: [ "href": "${createLink(action: actionName)}" ]
        ]
        model._links.List = [ "href": "${createLink(action: 'index')}" ]
        model._links.show = [ "href": "${createLink(action: 'show')}" ]
        model._links.create = [ "href": "${createLink(action: 'create')}" ]
        model._links.update = [ "href": "${createLink(action: 'update')}" ]
        model._links.delete = [ "href": "${createLink(action: 'delete')}" ]

        if (status) {
            response.status = status.value()
        }

        JSON.use('deep')

        render model as JSON
    }

    @Transactional
    def create(Engineering engineering) {
        if (engineering == null) {
            renderJSON([], BAD_REQUEST)
            return
        }

        if (engineering.hasErrors()) {
            transactionStatus.setRollbackOnly()
            renderJSON([errors: this.webUtils.simplifyErrors(engineering)], BAD_REQUEST)
            return
        }

        engineering.save flush:true

        renderJSON([engineering: engineering], CREATED)
    }

    @Transactional
    def delete(Engineering engineering) {
        if (engineering == null) {
            renderJSON(model: [message: g.message(code: 'default.entity.not.exists.message')], status: BAD_REQUEST)
            return
        }
        if (engineering.version != params.int('version')) {
            renderJSON([message: g.message(code: 'default.entity.updated.by.other.user.message')], BAD_REQUEST)
            return
        }

        engineering.delete flush:true

        renderJSON(model: [], status: OK)
    }

    def show(Engineering engineering) {
        renderJSON([engineering: engineering], OK)
    }

    @Transactional
    def update(Engineering engineering) {
        if (engineering == null) {
            transactionStatus.setRollbackOnly()
            renderJSON([message: g.message(code: 'default.not.found.message')], BAD_REQUEST)
            return
        }

        if (engineering.hasErrors()) {
            transactionStatus.setRollbackOnly()
            renderJSON([errors: this.webUtils.simplifyErrors(engineering)], BAD_REQUEST)
            return
        }

        engineering.save flush:true

        renderJSON([engineering: engineering], OK)
    }

/*
    def create() {
        respond new Engineering(params)
    }

    def edit(Engineering engineering) {
        respond engineering
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'engineering.label', default: 'Engineering'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
*/
}
