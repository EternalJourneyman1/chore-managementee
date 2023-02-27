package dev.robinsond.choremanagement.choremanagement.security

import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        // Log user login information
        val user = authentication.principal as UserDetails
        logger.info("${user.username} logged in at ${LocalDateTime.now()}")

        response.status = HttpServletResponse.SC_OK
        val session = request.session
        session.setAttribute("username", authentication.name)
        val cookie = Cookie("SESSION", session.id)
        response.addCookie(cookie)

        super.onAuthenticationSuccess(request, response, authentication)
    }
}

@Component
class LogoutSuccessHandler : LogoutSuccessHandler {
    private val logger = LoggerFactory.getLogger(LogoutSuccessHandler::class.java)

    override fun onLogoutSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        // Log user logout information
        val user = authentication.principal as UserDetails
        logger.info("${user.username} logged out at ${LocalDateTime.now()}")

        // Redirect user to the login page
        response.sendRedirect("/login")
    }
}
