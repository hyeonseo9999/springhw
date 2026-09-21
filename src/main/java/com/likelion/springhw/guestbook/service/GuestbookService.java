package com.likelion.springhw.guestbook.service;

import com.likelion.springhw.guestbook.dto.GuestbookSummaryResponse;
import com.likelion.springhw.guestbook.dto.GuestbookCreateRequest;
import com.likelion.springhw.guestbook.dto.GuestbookDetailResponse;
import com.likelion.springhw.guestbook.dto.GuestbookUpdateRequest;
import com.likelion.springhw.guestbook.entity.Guestbook;
import com.likelion.springhw.guestbook.repository.GuestbookRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;

    public List<GuestbookSummaryResponse> getGuestbookSummaries() {
        List<Guestbook> guestbooks = guestbookRepository.findAll();
        List<GuestbookSummaryResponse> responses = new ArrayList<>();

        for (Guestbook guestbook : guestbooks) {
            GuestbookSummaryResponse response = new GuestbookSummaryResponse(
                    guestbook.getTitle(),
                    guestbook.getWriter(),
                    guestbook.getPs()
            );
            responses.add(response);
        }

        return responses;
    }

    public GuestbookDetailResponse createGuestbook(GuestbookCreateRequest request) {
        Guestbook guestbook = new Guestbook(
                request.getTitle(),
                request.getContent(),
                request.getWriter(),
                request.getPs()
        );

        Guestbook savedGuestbook = guestbookRepository.save(guestbook);
        return toDetailResponse(savedGuestbook);
    }

    public GuestbookDetailResponse getGuestbook(Long guestbookId) {
        Guestbook guestbook = findGuestbook(guestbookId);
        return toDetailResponse(guestbook);
    }

    @Transactional
    public GuestbookDetailResponse updateGuestbook(Long guestbookId, GuestbookUpdateRequest request) {
        Guestbook guestbook = findGuestbook(guestbookId);
        guestbook.update(request.getTitle(), request.getContent());

        return toDetailResponse(guestbook);
    }

    @Transactional
    public void deleteGuestbook(Long guestbookId) {
        Guestbook guestbook = findGuestbook(guestbookId);
        guestbookRepository.delete(guestbook);
    }

    private Guestbook findGuestbook(Long guestbookId) {
        return guestbookRepository.findById(guestbookId).orElseThrow();
    }

    private GuestbookDetailResponse toDetailResponse(Guestbook guestbook) {
        return new GuestbookDetailResponse(
                guestbook.getId(),
                guestbook.getTitle(),
                guestbook.getContent(),
                guestbook.getWriter(),
                guestbook.getCreatedAt(),
                guestbook.getPs()
        );
    }
}
