package com.example.notification.service;

import com.example.notification.define.NotificationType;
import com.example.notification.define.SeverityLevel;
import com.example.notification.dto.NotificationDto;
import com.example.notification.entity.Notification;
import com.example.notification.repository.NotificationRepository;
import com.example.notification.test.MockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;


class NotificationServiceTest extends MockTest {
    private static final String TEST_MESSAGE = "test message";
    private static final String TEST_UPDATE_MESSAGE = "test update message";

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    @DisplayName("notification 저장 테스트")
    public void saveNotificationTest() {
        System.out.println(notificationRepository);
        System.out.println(notificationService);

        // given
        Notification notification = mock(Notification.class);
        given(notification.getId()).willReturn(1L);
        given(notification.getNotificationType()).willReturn((NotificationType.GENERAL));
        given(notification.getSeverityLevel()).willReturn(SeverityLevel.ERROR);
        given(notification.getMessage()).willReturn(TEST_MESSAGE);
        given(notificationRepository.save(any(Notification.class))).willReturn(notification);

        // when
        Notification savedNotification = notificationService.saveNotification(notification);

        // then
        Assertions.assertEquals(notification.getId(), savedNotification.getId());
        Assertions.assertEquals(notification.getNotificationType(), savedNotification.getNotificationType());
        Assertions.assertEquals(notification.getSeverityLevel(), savedNotification.getSeverityLevel());
        Assertions.assertEquals(notification.getMessage(), savedNotification.getMessage());
        then(notificationRepository).should(times(1)).save(any(Notification.class));
    }

    @Test
    @DisplayName("notification 조회 테스트")
    public void findAllTest() {
        // given
        List<Notification> notificationList = new ArrayList<>() {{
            add(initNotification(1L, NotificationType.GENERAL, SeverityLevel.ERROR));
            add(initNotification(2L, NotificationType.GENERAL, SeverityLevel.TRACE));
        }};
        Pageable pageable = PageRequest.of(0, 10);
        Page<Notification> page = new PageImpl<>(notificationList, pageable, notificationList.size());
        given(notificationRepository.findAll(pageable)).willReturn(page);

        // when
        Page<NotificationDto> result = notificationService.findAll(pageable);

        // then
        Assertions.assertEquals(notificationList.size(), result.getTotalElements());
        for (int seq = 0; seq < notificationList.size(); seq++) {
            Assertions.assertEquals(notificationList.get(seq).getId(), result.getContent().get(seq).getId());
            Assertions.assertEquals(notificationList.get(seq).getNotificationType(), result.getContent().get(seq).getNotificationType());
            Assertions.assertEquals(notificationList.get(seq).getSeverityLevel(), result.getContent().get(seq).getSeverityLevel());
            Assertions.assertEquals(notificationList.get(seq).getMessage(), result.getContent().get(seq).getMessage());
        }
        then(notificationRepository).should(times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("notification 상세 조회 테스트")
    public void findByIdTest() {
        Notification notification = initNotification(1L, NotificationType.GENERAL, SeverityLevel.ERROR);
        given(notificationRepository.findById(anyLong())).willReturn(Optional.of(notification));

        NotificationDto result = notificationService.findById(1L);

        Assertions.assertEquals(notification.getId(), result.getId());
        Assertions.assertEquals(notification.getNotificationType(), result.getNotificationType());
        Assertions.assertEquals(notification.getSeverityLevel(), result.getSeverityLevel());
        Assertions.assertEquals(notification.getMessage(), result.getMessage());
    }

    @Test
    @DisplayName("notification 수정 테스트")
    public void updateTest() {
        // given
        Long notificationId = 1L;
        Notification beforeUpdateNotification = initNotification(notificationId, NotificationType.GENERAL, SeverityLevel.INFO);

        // 변경 요청 데이터 mocking
        NotificationDto notificationDto = updateRequest(notificationId);
        given(beforeUpdateNotification.getSeverityLevel()).willReturn(notificationDto.getSeverityLevel());
        given(beforeUpdateNotification.getMessage()).willReturn(notificationDto.getMessage());
        given(notificationRepository.findById(notificationId)).willReturn(Optional.of(beforeUpdateNotification));
        given(notificationRepository.save(beforeUpdateNotification)).willReturn(beforeUpdateNotification);

        // when
        NotificationDto updatedNotificationDto = notificationService.update(notificationId, notificationDto);

        // then
        then(notificationRepository).should(times(1)).findById(notificationId);
        then(notificationRepository).should(times(1)).save(beforeUpdateNotification);
        Assertions.assertEquals(notificationDto.getNotificationType(), updatedNotificationDto.getNotificationType());
        Assertions.assertEquals(notificationDto.getSeverityLevel(), updatedNotificationDto.getSeverityLevel());
        Assertions.assertEquals(notificationDto.getMessage(), updatedNotificationDto.getMessage());
    }

    private NotificationDto updateRequest(Long notificationId) {
        return NotificationDto.builder()
                .id(notificationId)
                .notificationType(NotificationType.GENERAL)
                .severityLevel(SeverityLevel.ERROR)
                .message(TEST_UPDATE_MESSAGE)
                .build();
    }

    private Notification initNotification(Long id, NotificationType notificationType, SeverityLevel severityLevel) {
        Notification notification = mock(Notification.class);
        given(notification.getId()).willReturn(id);
        given(notification.getNotificationType()).willReturn((notificationType));
        given(notification.getSeverityLevel()).willReturn(severityLevel);
        given(notification.getMessage()).willReturn(TEST_MESSAGE);

        return notification;
    }

}
