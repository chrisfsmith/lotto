package lotto

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class RegistrationController {

    static scaffold = Registration
}
