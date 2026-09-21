package com.likelion.springhw.guestbook.controller;

import com.likelion.springhw.guestbook.dto.GuestbookCreateRequest;
import com.likelion.springhw.guestbook.dto.GuestbookDetailResponse;
import com.likelion.springhw.guestbook.dto.GuestbookSummaryResponse;
import com.likelion.springhw.guestbook.dto.GuestbookUpdateRequest;
import com.likelion.springhw.guestbook.service.GuestbookService;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guestbooks")
public class GuestbookController {

    private final GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @GetMapping
    public List<GuestbookSummaryResponse> getGuestbooks() {
        return guestbookService.getGuestbookSummaries();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuestbookDetailResponse createGuestbook(@Valid @RequestBody GuestbookCreateRequest request) {
        return guestbookService.createGuestbook(request);
    }

    @GetMapping("/{guestbookId}")
    public GuestbookDetailResponse getGuestbook(@PathVariable Long guestbookId) {
        return guestbookService.getGuestbook(guestbookId);
    }

    @PutMapping("/{guestbookId}")
    public GuestbookDetailResponse updateGuestbook(
            @PathVariable Long guestbookId,
            @Valid @RequestBody GuestbookUpdateRequest request
    ) {
        return guestbookService.updateGuestbook(guestbookId, request);
    }

    @DeleteMapping("/{guestbookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuestbook(@PathVariable Long guestbookId) {
        guestbookService.deleteGuestbook(guestbookId);
    }
}
