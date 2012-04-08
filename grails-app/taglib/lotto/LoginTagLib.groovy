package lotto

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class LoginTagLib {

    static namespace = 'lotto'

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    def loginControl = {
        if (springSecurityService.isLoggedIn()) {
            def user = springSecurityService.currentUser
            out << "${user?.getFirstName()} ${user?.getLastName()} "
            out << """[${
                link(controller: "logout") {"Logout"}
            }]"""
        } else {
            out << """[${
                link(controller: "login") {"Login"}
            }]"""
        }
    }

    def isAdmin = {attrs, body ->
        if (SpringSecurityUtils.ifAllGranted("ROLE_ADMIN")) {
            out << body()
        }
    }

    def isNotAdmin = {attrs, body ->
        if (SpringSecurityUtils.ifNotGranted("ROLE_ADMIN")) {
            out << body()
        }
    }
}