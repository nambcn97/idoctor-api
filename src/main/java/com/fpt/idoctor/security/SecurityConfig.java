package com.fpt.idoctor.security;
//package com.fpt.idoctor.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//
//import com.fpt.idoctor.common.constant.ModelConstants.RoleEnum;
//
//
//
//@Configuration
//@EnableAuthorizationServer
//public class SecurityConfig extends ResourceServerConfigurerAdapter {
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see org.springframework.security.config.annotation.web.configuration.
//     * WebSecurityConfigurerAdapter#configure(org.springframework.security.
//     * config.annotation.web.builders.HttpSecurity)
//     */
//    @Override
//	public void configure(HttpSecurity http) throws Exception {
//    	
//        http
//            .authorizeRequests()
//                .antMatchers("/web/*").hasAnyAuthority(RoleEnum.ADMIN.getValue(), RoleEnum.BANKER.getValue())
//                .antMatchers("/auth/*").permitAll()
//                .antMatchers("/mobile/*").hasAnyAuthority("User")
//                .antMatchers("/get/*").permitAll()
//                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
//                //.anyRequest().fullyAuthenticated()
//                
//            
//    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//}
