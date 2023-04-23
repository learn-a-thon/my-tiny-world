package com.twlee.bank.member.ui;

import com.twlee.bank.member.application.AuthenticationService;
import com.twlee.bank.member.application.dto.AuthenticationRequest;
import com.twlee.bank.member.application.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest.email(), authenticationRequest.password());
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/expire")
    public ResponseEntity<String> expire() {
        //TODO
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh() {
        //TODO
        return ResponseEntity.ok("ok");
    }
}
