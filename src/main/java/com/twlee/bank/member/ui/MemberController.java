package com.twlee.bank.member.ui;

import com.twlee.bank.member.application.MemberService;
import com.twlee.bank.member.application.dto.MemberRequest;
import com.twlee.bank.member.application.dto.MemberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody MemberRequest memberRequest) {
        MemberResponse response = memberService.save(memberRequest);
        return ResponseEntity
                .created(URI.create("/members/" + response.getId()))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
