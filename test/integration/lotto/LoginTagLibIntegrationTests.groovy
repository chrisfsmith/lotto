package lotto

import grails.test.GroovyPagesTestCase
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class LoginTagLibIntegrationTests extends GroovyPagesTestCase {

    void testLoginControl() {
        def role = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        def user = User.build().save(flush: true)
        UserRole.create user, role, true

        SpringSecurityUtils.doWithAuth(user.username, {
            def template = '<lotto:loginControl/>'
            def result = applyTemplate(template)

            assertEquals 'firstName lastName [<a href="/logout/index">Logout</a>]', result
        })

        def template = '<lotto:loginControl/>'
        def result = applyTemplate(template)

        assertEquals '[<a href="/login/index">Login</a>]', result
    }

    void testIsAdmin() {
        def user = User.build().save(flush: true)

        SpringSecurityUtils.doWithAuth(user.username, {
            def template = '<lotto:isAdmin>Secure Content</lotto:isAdmin>'
            def result = applyTemplate(template)

            assertEquals '', result
        })

        def role = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        UserRole.create user, role, true

        SpringSecurityUtils.doWithAuth(user.username, {
            def template = '<lotto:isAdmin>Secure Content</lotto:isAdmin>'
            def result = applyTemplate(template)

            assertEquals 'Secure Content', result
        })
    }

    void testIsNotAdmin() {
        def user = User.build().save(flush: true)

        SpringSecurityUtils.doWithAuth(user.username, {
            def template = '<lotto:isNotAdmin>Boring Content</lotto:isNotAdmin>'
            def result = applyTemplate(template)

            assertEquals 'Boring Content', result
        })

        def role = new Role(authority: 'ROLE_ADMIN').save(flush: true)
        UserRole.create user, role, true

        SpringSecurityUtils.doWithAuth(user.username, {
            def template = '<lotto:isNotAdmin>Boring Content</lotto:isNotAdmin>'
            def result = applyTemplate(template)

            assertEquals '', result
        })
    }
}
