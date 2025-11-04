package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.AuthenticationRequest;
import com.devteria.identity_service.dto.request.IntrospectRequest;
import com.devteria.identity_service.dto.response.AuthenticationResponse;
import com.devteria.identity_service.dto.response.IntrospectResponse;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    @Autowired
    private UserRepository userRepository;
    @Value("${jwt.singerKey}")
    protected String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authentication = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authentication) {
            throw new AppException(ErrorCode.UNUATHENTICATION);
        }
        var token = generateToken(request.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);
        authenticationResponse.setAuthenticated(true);
        return authenticationResponse;
   }


   private String generateToken(String username) {
       JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

       JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
               .subject(username)
               .issuer("shopquanao.com")
               .issueTime(new Date())
               .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
               .build();
       Payload payload =new Payload(jwtClaimsSet.toJSONObject());

       JWSObject jwsObject = new JWSObject(header,payload);


       try{
       jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
           System.out.println("token= "+ jwsObject.serialize());
       return  jwsObject.serialize();
       }
       catch(JOSEException e){
           log.error("không thể tạo token",e);
       throw new AppException(ErrorCode.UNUATHENTICATION);
       }
    }
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified= signedJWT.verify(verifier);
        System.out.println("xin chao");
        IntrospectResponse introspectResponse = new IntrospectResponse();
        introspectResponse.setValid(verified && expityTime.after(new Date()));
        return introspectResponse;
    }
}

