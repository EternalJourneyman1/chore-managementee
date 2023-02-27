package dev.robinsond.choremanagement.choremanagement.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val loginSuccessHandler: LoginSuccessHandler,
    private val logoutSuccessHandler: LogoutSuccessHandler
) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers( "/api/user", "/public/**", "/static/**", "/favicon.ico","/manifest.json", "/*.png", "/*.jpg").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .successHandler(loginSuccessHandler).permitAll()
            .and()
            .logout().logoutSuccessHandler(logoutSuccessHandler).permitAll()
    }


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("lydell").password(encoder().encode("stephens")).roles("USER")
            .and()
            .withUser("miracle").password(encoder().encode("reed")).roles("USER")
            .and()
            .withUser("michael").password(encoder().encode("jacobs")).roles("USER")
            .and()
            .withUser("regina").password(encoder().encode("robinson")).roles("USER")
            .and()
            .withUser("giaunni").password(encoder().encode("robinson")).roles("USER")
            .and()
            .withUser("Iva").password(encoder().encode("Ladiidgaf1!")).roles("USER","ADMIN")
            .and()
            .withUser("Marresha").password(encoder().encode("ImRichBitch!")).roles("USER","ADMIN")
            .and()
            .withUser("Eternal").password(encoder().encode("Robinson1!")).roles("USER","ADMIN")
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return Pbkdf2PasswordEncoder()
    }
}
