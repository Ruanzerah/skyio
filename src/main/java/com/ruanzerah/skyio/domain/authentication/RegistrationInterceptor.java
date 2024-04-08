//package com.ruanzerah.skyio.domain.authentication;
//
//import com.ruanzerah.skyio.domain.email.EmailContent;
//import com.ruanzerah.skyio.domain.email.EmailProducerService;
//import com.ruanzerah.skyio.domain.token.TokenService;
//import com.ruanzerah.skyio.domain.user.User;
//import com.ruanzerah.skyio.domain.user.UserRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class RegistrationInterceptor implements HandlerInterceptor {
//    private final EmailProducerService emailProducerService;
//    private final UserRepository userRepository;
//    private final TokenService tokenService;
//
//    public RegistrationInterceptor(EmailProducerService emailProducerService, UserRepository userRepository, TokenService tokenService) {
//        this.emailProducerService = emailProducerService;
//        this.userRepository = userRepository;
//        this.tokenService = tokenService;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        if (response.getStatus() == HttpServletResponse.SC_CREATED && request.getRequestURI().equals("/auth/register")) {
//
//            String token = request.getHeader("Authorization").replace("Bearer ", "");
//            tokenService.validate(token).ifPresent(email -> {
//                User user = userRepository.findByEmail(email).orElseThrow();
//                    sendRegistrationConfirmationEmail(user.getEmail());
//            });
//        }
//    }
//
//    private void sendRegistrationConfirmationEmail(String email) {
//        // Prepare email content
//        EmailContent emailMessage = new EmailContent();
//        emailMessage.setTo(email);
//        emailMessage.setFrom(emailMessage.getFrom());
//        emailMessage.setSubject("Registration Confirmation");
//        emailMessage.setText("Thank you for registering with us!");
//
//        emailProducerService.sendEmail(emailMessage);
//    }
//}
