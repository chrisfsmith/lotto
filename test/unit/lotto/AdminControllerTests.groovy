package lotto

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AdminController)
class AdminControllerTests {

    void testIndex() {
        def model = controller.index()

        assert '' == response.text
        assert null == response.redirectedUrl
        assert null == model
    }
}
